package com.bean.beanapi.service.impl.inner;

import com.bean.beanapi.service.UserInterfaceInfoService;
import com.bean.beanapicommon.service.InnerUserInterfaceInfoService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * @author Sami
 */
@DubboService
public class InnerUserInterfaceInfoServiceImpl implements InnerUserInterfaceInfoService {

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;

    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {

      return userInterfaceInfoService.invokeCount(interfaceInfoId,userId);
    }
}
