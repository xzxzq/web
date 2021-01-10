package exercise.xzxzq.service;

import exercise.xzxzq.entity.PageResult;
import exercise.xzxzq.entity.QueryPageBean;
import exercise.xzxzq.exception.BusinessException;
import exercise.xzxzq.pojo.CheckGroup;

import java.util.List;

/**
 * @Author : 徐志清
 * @Description :
 * @Time : 2021/1/9 8:39
 * @File : CheckGroupService
 */
public interface CheckGroupService {
    void add(CheckGroup checkgroup, Integer[] checkitemIds);

    PageResult<CheckGroup> findPage(QueryPageBean queryPageBean);

    CheckGroup findById(int id);

    List<Integer> findCheckItemIdsByCheckGroupId(int id);

    void update(CheckGroup checkgroup, Integer[] checkitemIds);

    void deleteById(int id) throws BusinessException;

    List<CheckGroup> findAll();
}
