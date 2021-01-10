package exercise.xzxzq.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import exercise.xzxzq.dao.CheckGroupDao;
import exercise.xzxzq.entity.PageResult;
import exercise.xzxzq.entity.QueryPageBean;
import exercise.xzxzq.exception.BusinessException;
import exercise.xzxzq.exception.UnsupportedArgumentException;
import exercise.xzxzq.pojo.CheckGroup;
import exercise.xzxzq.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author : 徐志清
 * @Description :
 * @Time : 2021/1/9 8:42
 * @File : CheckGroupServiceImpl
 */
@Service(interfaceClass = CheckGroupService.class)
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupDao dao;

    @Override
    @Transactional
    public void add(CheckGroup checkgroup, Integer[] checkitemIds) {
        String code = checkgroup.getCode();
        if (StringUtils.isEmpty(code)) {
            throw new UnsupportedArgumentException("编码不能为空");
        }
        if (code.length() > 32) {
            throw new UnsupportedArgumentException("编码的长度不能超过32位");
        }
        //- 先添加检查组
        dao.add(checkgroup);
        //- 获取检查组的id
        Integer checkgroupId = checkgroup.getId();
        //- 遍历选中的检查项id的数组
        if (null != checkitemIds) {
            for (Integer checkitemId : checkitemIds) {
                //- 添加检查组与检查项的关系
                dao.addCheckGroupCheckItem(checkgroupId, checkitemId);
            }
        }
    }

    @Override
    public PageResult<CheckGroup> findPage(QueryPageBean query) {
        PageHelper.startPage(query.getCurrentPage(), query.getPageSize());
        if (StringUtils.isNotEmpty(query.getQueryString())) {
            // 有查询条件， 模糊查询
            query.setQueryString("%" + query.getQueryString() + "%");
        }
        Page<CheckGroup> page = dao.findByCondition(query.getQueryString());
        PageResult<CheckGroup> pageResult = new PageResult<>(page.getTotal(), page.getResult());
        return pageResult;
    }

    @Override
    public CheckGroup findById(int id) {
        return dao.findById(id);
    }

    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(int id) {
        return dao.findCheckItemIdsByCheckGroupId(id);
    }

    @Override
    @Transactional
    public void update(CheckGroup checkgroup, Integer[] checkitemIds) {
        //- 先更新检查组
        dao.update(checkgroup);
        //- 先删除旧关系
        dao.deleteCheckGroupCheckItem(checkgroup.getId());
        //- 遍历选中的检查项id的数组
        if(null != checkitemIds){
            for (Integer checkitemId : checkitemIds) {
                //- 添加检查组与检查项的关系
                dao.addCheckGroupCheckItem(checkgroup.getId(), checkitemId);
            }
        }
    }

    @Override
    @Transactional
    public void deleteById(int id) throws BusinessException {
        // 通过检查组id查询是否被套餐使用了
        int count = dao.findCountByCheckGroupId(id);
        // 使用了，抛出异常
        if(count > 0){
            throw new BusinessException("该检查组被套餐使用了，不能删除");
        }
        // 没使用，删除检查组与检查项的关系
        dao.deleteCheckGroupCheckItem(id);
        // 删除检查组
        dao.deleteById(id);
        // 事务控制
    }

    @Override
    public List<CheckGroup> findAll() {
        return dao.findAll();
    }
}
