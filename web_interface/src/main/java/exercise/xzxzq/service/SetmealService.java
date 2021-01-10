package exercise.xzxzq.service;

import exercise.xzxzq.entity.PageResult;
import exercise.xzxzq.entity.QueryPageBean;
import exercise.xzxzq.exception.BusinessException;
import exercise.xzxzq.pojo.Setmeal;

import java.util.List;

/**
 * @Author : 徐志清
 * @Description :
 * @Time : 2021/1/10 19:10
 * @File : SetmealService
 */
public interface SetmealService {
    /**
     * 添加套餐
     *
     * @param setmeal
     * @param checkgroupIds
     */
    void add(Setmeal setmeal, Integer[] checkgroupIds);

    /**
     * 分页条件查询
     *
     * @param queryPageBean
     * @return
     */
    PageResult<Setmeal> findPage(QueryPageBean queryPageBean);

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
     * 修改套餐
     *
     * @param setmeal
     * @param checkgroupIds
     */
    void update(Setmeal setmeal, Integer[] checkgroupIds);

    /**
     * 通过id删除套餐
     *
     * @param id
     */
    void deleteById(int id) throws BusinessException;

    List<String> findImgs();
}
