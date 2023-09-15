package com.bean.beanapicommon.service;

import com.bean.beanapicommon.model.entity.InterfaceInfo;
import com.bean.beanapicommon.model.entity.User;

/**
 * @author sami
 */
public interface ApiBackendService {

    /**
     * 数据库中查是否已给用户分配密钥（accessKey）
     *
     * @param accessKey
     * @return
     */
    User getInvokeUser(String accessKey);

    /**
     * 从数据库中查询模拟接口是否存在
     *
     * @param path
     * @param method
     * @return
     */
    InterfaceInfo getInterfaceInfo(String path, String method);

    /**
     * 统计接口调用次数
     *
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invokeCount(long interfaceInfoId, long userId);

    /**
     * 获取用户所拥有的接口剩余调用次数
     *
     * @param userId
     * @param interfaceInfoId
     * @return
     */
    int getLeftInvokeCount(long userId, long interfaceInfoId);

    /**
     * 根据接口id获取接口详情
     */
    InterfaceInfo getInterfaceById(long interfaceId);

    /**
     * 根据接口id获取接口库存
     *
     * @param interfaceId
     * @return
     */
    int getInterfaceStockById(long interfaceId);

    /**
     * 扣减库存
     *
     * @param interfaceId
     * @param num
     * @return
     */
    boolean updateInterfaceStock(long interfaceId, Integer num);


    /**
     * 订单支付超时，回滚库存
     *
     * @param interfaceId
     * @param num
     * @return
     */
    boolean recoverInterfaceStock(long interfaceId, Integer num);


    /**
     * 给指定用户分配接口调用次数
     *
     * @param userId
     * @param interfaceId
     * @param num
     * @return
     */
    boolean updateUserInterfaceInvokeCount(long userId, long interfaceId, int num);
}
