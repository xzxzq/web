package exercise.xzxzq.dao;

import com.github.pagehelper.Page;
import exercise.xzxzq.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author : 徐志清
 * @Description :
 * @Time : 2021/1/9 8:31
 * @File : CheckGroupDao
 */
public interface CheckGroupDao {
    void add(CheckGroup checkgroup);

    void addCheckGroupCheckItem(@Param("checkgroupId") Integer checkgroupId, @Param("checkitemId") Integer checkitemId);

    Page<CheckGroup> findByCondition(String queryString);

    CheckGroup findById(int id);

    List<Integer> findCheckItemIdsByCheckGroupId(int id);

    void update(CheckGroup checkgroup);

    void deleteCheckGroupCheckItem(Integer id);

    int findCountByCheckGroupId(int id);

    void deleteById(int id);

    List<CheckGroup> findAll();
}
