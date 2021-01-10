package exercise.xzxzq.dao;

import exercise.xzxzq.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author : 徐志清
 * @Description :
 * @Time : 2021/1/10 20:06
 * @File : OrderSettingDao
 */
public interface OrderSettingDao {
    /**
     * 通过日期查询预约设置信息,日期唯一
     *
     * @param orderDate
     * @return
     */
    OrderSetting findByOrderDate(Date orderDate);

    /**
     * 添加预约设置信息
     *
     * @param os
     */
    void add(OrderSetting os);

    /**
     * 通过日期更新最大预约数
     *
     * @param os
     */
    void updateNumber(OrderSetting os);

    /**
     * 按月查询预约设置信息
     *
     * @param month
     * @return
     */
    List<Map<String, Integer>> getOrderSettingByMonth(String month);
}
