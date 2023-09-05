package com.bean.beanapicommon.service;


import com.bean.beanapicommon.model.entity.User;

/**
 * 用户服务
 *
 */
public interface InnerUserService{

    /**
     * 数据库中查是否已给用户分配密钥（accessKey）
     * @param accessKey
     * @return
     */
    User getInvokeUser(String accessKey);

}
