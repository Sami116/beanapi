package com.bean.beanapiclientsdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * @author Sami
 */
public class SignUtil {

    private static final Digester SHA1 = new Digester(DigestAlgorithm.SHA1);

    /**
     * 生成签名
     *
     * @param body
     * @param secretKey
     * @return
     */
    public static String
    genSign(String body, String secretKey) {
        String content = body + "." + secretKey;
        return SHA1.digestHex(content);
    }
}
