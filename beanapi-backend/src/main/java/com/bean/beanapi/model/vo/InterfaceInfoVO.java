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
     * 调用次数
     */
    private Integer totalNum;

    private static final long serialVersionUID = 1L;
}

