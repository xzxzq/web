package exercise.xzxzq.service;

import exercise.xzxzq.entity.PageResult;
import exercise.xzxzq.entity.QueryPageBean;
import exercise.xzxzq.pojo.CheckItem;

import java.util.List;

/**
 * @Author : 徐志清
 * @Description :
 * @Time : 2021/1/6 9:03
 * @File : CheckItemService
 */
public interface CheckItemService {
    List<CheckItem> findAll();

    void add(CheckItem item);

    PageResult<CheckItem> getCheckItemPage(QueryPageBean query);

    CheckItem findById(Integer id);

    void update(CheckItem checkItem);

    void deleteById(Integer id);
}
