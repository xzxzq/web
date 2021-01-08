package exercise.xzxzq.dao;

import com.github.pagehelper.Page;
import exercise.xzxzq.pojo.CheckItem;

import java.util.List;

/**
 * @Author : 徐志清
 * @Description :
 * @Time : 2021/1/6 9:06
 * @File : CheckItemDao
 */
public interface CheckItemDao {
    List<CheckItem> findAll();

    void add(CheckItem item);

    Page<CheckItem> findByCondition(String queryString);

    CheckItem findById(Integer id);

    void update(CheckItem checkItem);

    void deleteById(Integer id);

    int findCountByCheckItemId(Integer id);
}
