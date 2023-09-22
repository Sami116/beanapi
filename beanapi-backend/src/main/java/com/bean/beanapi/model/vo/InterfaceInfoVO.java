package com.bean.beanapi.model.vo;

import com.bean.beanapicommon.model.entity.InterfaceInfo;
import lombok.Data;

import java.io.Serializable;


/**
 * 接口信息封装视图
 *
 */
@Data
public class InterfaceInfoVO extends InterfaceInfo implements Serializable {

    /**
     * 被调用次数
     */
    private Integer totalNum;

    /**
     * 计费规则（元/条）
     */
    private Double charging;

    /**
     * 计费Id
     */
    private Long chargingId;

    /**
     * 接口剩余可调用次数
     */
    private String availablePieces;

    private static final long serialVersionUID = 1L;
}

