package exercise.xzxzq.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import exercise.xzxzq.dao.SetmealDao;
import exercise.xzxzq.entity.PageResult;
import exercise.xzxzq.entity.QueryPageBean;
import exercise.xzxzq.exception.BusinessException;
import exercise.xzxzq.pojo.Setmeal;
import exercise.xzxzq.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author : 徐志清
 * @Description :
 * @Time : 2021/1/10 19:16
 * @File : SetmealServiceImpl
 */
@Service(interfaceClass = SetmealService.class)
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealDao dao;

    @Override
    @Transactional
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        //- 先 添加套餐
        dao.add(setmeal);
        //- 获取套餐的id
        Integer setmealId = setmeal.getId();
        //- 遍历checkgroupIds数组
        if (null != checkgroupIds) {
            //- 添加套餐与检查组的关系
            for (Integer checkgroupId : checkgroupIds) {
                dao.addSetmealCheckGroup(setmealId, checkgroupId);
            }
        }
    }

    @Override
    public PageResult<Setmeal> findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        // 条件查询
        if (StringUtils.isNotEmpty(queryPageBean.getQueryString())) {
            // 模糊查询
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }
        Page<Setmeal> setmealPage = dao.findByCondition(queryPageBean.getQueryString());
        return new PageResult<>(setmealPage.getTotal(), setmealPage.getResult());
    }

    @Override
    public Setmeal findById(int id) {
        return dao.findById(id);
    }

    @Override
    public List<Integer> findCheckGroupIdsBySetmealId(int id) {
        return dao.findCheckGroupIdsBySetmealId(id);
    }

    @Override
    public void update(Setmeal setmeal, Integer[] checkgroupIds) {
        // 更新套餐
        dao.update(setmeal);
        // 删除旧关系
        dao.deleteSetmealCheckGroup(setmeal.getId());
        // 遍历添加 新关系
        if (null != checkgroupIds) {
            for (Integer checkgroupId : checkgroupIds) {
                dao.addSetmealCheckGroup(setmeal.getId(), checkgroupId);
            }
        }
    }

    @Override
    public void deleteById(int id) throws BusinessException {
        // 先判断 是否被订单使用了
        int count = dao.findCountBySetmealId(id);
        // 使用了，要报错，接口方法 异常声明
        if (count > 0) {
            throw new BusinessException("该套餐被订单使用了，不能删除");
        }
        // 没使用，则要先删除套餐与检查组的关系
        dao.deleteSetmealCheckGroup(id);
        // 再删除套餐
        dao.deleteById(id);
    }

    @Override
    public List<String> findImgs() {
        return dao.findImgs();
    }
}
