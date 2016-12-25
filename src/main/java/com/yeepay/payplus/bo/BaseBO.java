package com.yeepay.payplus.bo;

/**
 * Created by Marco on 15/12/2016.
 */
public class BaseBO {

    private String requestNo;

    private String trxRequestNo;

    private String merchantNo;

    private String merchantUserId;

    private String serverCallbackUrl;

    private String webCallbackUrl;

    private String returnUrl;

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getTrxRequestNo() {
        return trxRequestNo;
    }

    public void setTrxRequestNo(String trxRequestNo) {
        this.trxRequestNo = trxRequestNo;
    }

    public String getServerCallbackUrl() {
        return serverCallbackUrl;
    }

    public void setServerCallbackUrl(String serverCallbackUrl) {
        this.serverCallbackUrl = serverCallbackUrl;
    }

    public String getWebCallbackUrl() {
        return webCallbackUrl;
    }

    public void setWebCallbackUrl(String webCallbackUrl) {
        this.webCallbackUrl = webCallbackUrl;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getMerchantUserId() {
        return merchantUserId;
    }

    public void setMerchantUserId(String merchantUserId) {
        this.merchantUserId = merchantUserId;
    }
}
