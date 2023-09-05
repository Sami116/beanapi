package com.bean.beanapicommon.service;



import com.bean.beanapicommon.model.entity.InterfaceInfo;

/**
 * @author Administrator
 * @description 针对表【interface_info(接口信息)】的数据库操作Service
 * @createDate 2023-03-14 20:45:04
 */
public interface InnerInterfaceInfoService{


    /**
     * 从数据库中查询模拟接口是否存在
     * @param path
     * @param method
     * @return
     */
    InterfaceInfo getInterfaceInfo(String path, String method);
}
