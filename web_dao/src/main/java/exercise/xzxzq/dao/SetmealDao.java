package exercise.xzxzq.dao;

import com.github.pagehelper.Page;
import exercise.xzxzq.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author : 徐志清
 * @Description :
 * @Time : 2021/1/10 19:14
 * @File : SetmealDoa
 */
public interface SetmealDao {
    /**
     * 添加套餐
     *
     * @param setmeal
     */
    void add(Setmeal setmeal);

    /**
     * 添加套餐与检查组的关系
     *
     * @param setmealId
     * @param checkgroupId
     */
    void addSetmealCheckGroup(@Param("setmealId") Integer setmealId, @Param("checkgroupId") Integer checkgroupId);

    /**
     * 套餐条件查询
     *
     * @param queryString
     * @return
     */
    Page<Setmeal> findByCondition(String queryString);

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    Setmeal findById(int id);

    /**
     * 查询选中的检查组id集合
     *
     * @param id
     * @return
     */
    List<Integer> findCheckGroupIdsBySetmealId(int id);

    /**
     * 更新套餐
     *
     * @param setmeal
     */
    void update(Setmeal setmeal);

    /**
     * 删除套餐与检查组关系
     *
     * @param id
     */
    void deleteSetmealCheckGroup(Integer id);

    /**
     * 通过套餐id统计被订单使用的个数
     *
     * @param id
     * @return
     */
    int findCountBySetmealId(int id);

    /**
     * 通过套餐id删除套餐
     *
     * @param id
     */
    void deleteById(int id);

    /**
     * 查询套餐的所有图片
     *
     * @return
     */
    List<String> findImgs();
}
