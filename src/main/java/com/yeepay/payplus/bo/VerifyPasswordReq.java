package com.yeepay.payplus.bo;

/**
 * Created by Marco on 21/12/2016.
 */
public class VerifyPasswordReq extends BaseBO {

    /**
     * TODO 定义常量类型,需完善!!
     */
    public static final String TRANSFER = "ORDER_TRANSFER";
    public static final String WITHDRAW = "WITHDRAW";
    public static final String UN_BIND_CARD = "UN_BIND_CARD";

    private String tokenBizType;
    private String webCallBackUrl;
    private String returnUrl;
    private String clientSource;

    public String getTokenBizType() {
        return tokenBizType;
    }

    public void setTokenBizType(String tokenBizType) {
        this.tokenBizType = tokenBizType;
    }

    public String getWebCallBackUrl() {
        return webCallBackUrl;
    }

    public void setWebCallBackUrl(String webCallBackUrl) {
        this.webCallBackUrl = webCallBackUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getClientSource() {
        return clientSource;
    }

    public void setClientSource(String clientSource) {
        this.clientSource = clientSource;
    }
}
