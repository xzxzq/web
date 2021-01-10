package exercise.xzxzq.service;

import exercise.xzxzq.exception.BusinessException;
import exercise.xzxzq.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

/**
 * @Author : 徐志清
 * @Description :
 * @Time : 2021/1/10 20:07
 * @File : OrderSettingService
 */
public interface OrderSettingService {
    /**
     * 批量导入预约设置
     *
     * @param orderSettingList
     */
    void addBatch(List<OrderSetting> orderSettingList) throws BusinessException;

    /**
     * 按月查询预约设置信息
     *
     * @param month
     * @return
     */
    List<Map<String, Integer>> getOrderSettingByMonth(String month);

    /**
     * 通过日期设置可预约的最大数
     *
     * @param orderSetting
     */
    void editNumberByDate(OrderSetting orderSetting) throws BusinessException;
}
