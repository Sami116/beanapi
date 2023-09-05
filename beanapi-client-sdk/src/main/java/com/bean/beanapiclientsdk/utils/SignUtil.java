package com.bean.beanapiclientsdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * @author Sami
 */
public class SignUtil {

   /**
    * 生成签名
    *
    * @param body
    * @param secretKey
    * @return
    */
   public static String genSign(String body, String secretKey) {
      Digester sha256 = new Digester(DigestAlgorithm.SHA256);
      String content = body + "." + secretKey;
      return sha256.digestHex(content);
   }
}
