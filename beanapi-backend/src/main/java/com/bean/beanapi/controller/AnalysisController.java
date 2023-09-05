package com.bean.beanapi.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.bean.beanapi.annotation.AuthCheck;
import com.bean.beanapi.common.BaseResponse;
import com.bean.beanapi.common.ErrorCode;
import com.bean.beanapi.common.ResultUtils;
import com.bean.beanapi.exception.BusinessException;
import com.bean.beanapi.mapper.UserInterfaceInfoMapper;
import com.bean.beanapi.model.vo.InterfaceInfoVO;
import com.bean.beanapi.service.InterfaceInfoService;
import com.bean.beanapicommon.model.entity.InterfaceInfo;
import com.bean.beanapicommon.model.entity.UserInterfaceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 分析控制器
 *
 * @author Sami
 */

@RestController
@RequestMapping("/analysis")
@Slf4j
public class AnalysisController {


    @Resource
    private UserInterfaceInfoMapper userInterfaceInfoMapper;

    @Resource
    private InterfaceInfoService interfaceInfoService;

    @GetMapping("/top/interface/invoke")
    @AuthCheck(mustRole = "admin")
    public BaseResponse<List<InterfaceInfoVO>> getTopInvokedInterfaceInfoList() {
        List<UserInterfaceInfo> topInvokedInterfaceInfoList = userInterfaceInfoMapper.getTopInvokedInterfaceInfoList(3);
        Map<Long, List<UserInterfaceInfo>> interfaceInfoIdObjMap = topInvokedInterfaceInfoList.stream()
                .collect(Collectors.groupingBy(UserInterfaceInfo::getInterfaceInfoId));
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", interfaceInfoIdObjMap.keySet());
        List<InterfaceInfo> list = interfaceInfoService.list(queryWrapper);
        if (CollectionUtils.isEmpty(list)) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        List<InterfaceInfoVO> interfaceInfoVOList = list.stream().map(interfaceInfo -> {
            InterfaceInfoVO interfaceInfoVO = new InterfaceInfoVO();
            BeanUtils.copyProperties(interfaceInfo, interfaceInfoVO);
            Integer totalNum = interfaceInfoIdObjMap.get(interfaceInfo.getId()).get(0).getTotalNum();
            interfaceInfoVO.setTotalNum(totalNum);
            return interfaceInfoVO;
        }).collect(Collectors.toList());
        return ResultUtils.success(interfaceInfoVOList);
    }

}
