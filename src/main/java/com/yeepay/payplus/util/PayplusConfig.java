package com.yeepay.payplus.util;

import java.util.ResourceBundle;

/**
 * Created by Marco on 15/12/2016.
 */
public class PayplusConfig {

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 测试用户-1
     */
    public static final String JOEY_TRIBBIANI = "Joey";
    /**
     * 测试用户-2
     */
    public static final String MONICA_GELLER = "Monica";
    /**
     * 测试用户-3
     */
    public static final String PHOEBE_BUFFAY = "Phoebe";
    /**
     * 测试用户-4
     */
    public static final String ROSS_GELLER = "Ross";
    /**
     * 测试用户-5
     */
    public static final String CHANDLER_BING = "Chandler";
    /**
     * 测试用户-6
     */
    public static final String RACHEL_GREEN = "Rachel";
    /**
     * appKey
     */
    public static String appKey;
    /**
     * appSecret
     */
    public static String appSecret;
    /**
     * 加密算法
     */
    public static String signAlgorithm;
    /**
     * 调用地址
     */
    public static String url;

    /**
     * 测试merchantNo
     */
    public static String merchantNo;

    public static void init() {

        ResourceBundle rb = ResourceBundle.getBundle("cfg");

        appKey = rb.getString("APP_KEY");
        appSecret = rb.getString("APP_SECRET");
        url = rb.getString("URL");
        signAlgorithm = rb.getString("SIGN_ALGORITHM");
        merchantNo = rb.getString("MERCHANT_NO");
    }

}
