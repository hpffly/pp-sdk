package com.yeepay.payplus.bo;

/**
 * Created by Marco on 23/12/2016.
 */
public class AuthReq extends BaseBO{

    private String clientSource;

    public AuthReq() {
    }

    public AuthReq(String requestNo, String merchantNo, String merchantUserId, String webCallbackUrl, String returnUrl, String clientSource) {

        super.setMerchantNo(merchantNo);
        super.setMerchantUserId(merchantUserId);
        super.setRequestNo(requestNo);
        super.setReturnUrl(returnUrl);
        super.setWebCallbackUrl(webCallbackUrl);
        this.clientSource = clientSource;
    }

    public String getClientSource() {
        return clientSource;
    }

    public void setClientSource(String clientSource) {
        this.clientSource = clientSource;
    }
}

