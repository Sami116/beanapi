package com.bean.beanapi.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bean.beanapi.common.ErrorCode;
import com.bean.beanapi.exception.BusinessException;
import com.bean.beanapi.exception.ThrowUtils;
import com.bean.beanapi.mapper.InterfaceInfoMapper;
import com.bean.beanapi.service.InterfaceInfoService;
import com.bean.beanapicommon.model.entity.InterfaceInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @description 针对表【interface_info(接口信息)】的数据库操作Service实现
 * @createDate 2023-03-14 20:45:04
 */
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
        implements InterfaceInfoService {

    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String name = interfaceInfo.getName();
        // 创建时，参数不能为空
        if (add) {
            ThrowUtils.throwIf(StringUtils.isAnyBlank(name), ErrorCode.PARAMS_ERROR);
        }
        // 有参数则校验
        if (StringUtils.isNotBlank(name) && name.length() > 80) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "名称过长");
        }

    }
}




