package exercise.xzxzq.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import exercise.xzxzq.constant.ErrorLogConstant;
import exercise.xzxzq.constant.MessageConstant;
import exercise.xzxzq.dao.CheckItemDao;
import exercise.xzxzq.entity.PageResult;
import exercise.xzxzq.entity.QueryPageBean;
import exercise.xzxzq.exception.BusinessException;
import exercise.xzxzq.pojo.CheckItem;
import exercise.xzxzq.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author : 徐志清
 * @Description :
 * @Time : 2021/1/6 9:05
 * @File : CheckItemServiceImpl
 */
@Service(interfaceClass = CheckItemService.class)
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemDao dao;

    @Override
    public List<CheckItem> findAll() {
        List<CheckItem> result = dao.findAll();
        return result;
    }

    @Override
    public void add(CheckItem item) {
        dao.add(item);
    }

    @Override
    public PageResult<CheckItem> getCheckItemPage(QueryPageBean query) {
        PageHelper.startPage(query.getCurrentPage(), query.getPageSize());
        if (StringUtils.isNotEmpty(query.getQueryString())) {
            query.setQueryString("%" + query.getQueryString() + "%");
        }
        Page<CheckItem> page = dao.findByCondition(query.getQueryString());
        return new PageResult<>(page.getTotal(), page.getResult());
    }

    @Override
    public CheckItem findById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public void update(CheckItem checkItem) {
        dao.update(checkItem);
    }

    @Override
    public void deleteById(Integer id) {
        // 统计使用了这个id的个数
        int count = dao.findCountByCheckItemId(id);
        if(count > 0){
            throw new BusinessException(MessageConstant.DELETE_CHECKITEM_FAIL+","+ErrorLogConstant.CHECKITEM_OCCUPIED);
        }
        dao.deleteById(id);
    }
}
