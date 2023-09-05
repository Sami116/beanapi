package com.bean.beanapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bean.beanapicommon.model.entity.InterfaceInfo;

/**
 * @author Administrator
 * @description 针对表【interface_info(接口信息)】的数据库操作Service
 * @createDate 2023-03-14 20:45:04
 */
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);
}
