package com.bean.beanapi.model.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDevKeyVO implements Serializable {
    private static final long serialVersionUID = 6703326011663561616L;

   private String accessKey;
   private String secretKey;

}