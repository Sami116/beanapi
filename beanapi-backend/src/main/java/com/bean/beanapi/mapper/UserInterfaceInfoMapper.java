package com.bean.beanapi.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bean.beanapicommon.model.entity.UserInterfaceInfo;

import java.util.List;

/**
* @author Administrator
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Mapper
* @createDate 2023-03-24 16:03:55
* @Entity generator.domain.UserInterfaceInfo
*/
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {

    List<UserInterfaceInfo> getTopInvokedInterfaceInfoList(int limit);
}




