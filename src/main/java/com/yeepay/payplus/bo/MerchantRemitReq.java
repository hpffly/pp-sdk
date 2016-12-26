package com.yeepay.payplus.bo;

/**
 * Created by Marco on 23/12/2016.
 */
public class MerchantRemitReq extends BaseBO{

    private String remiteInfos;

    public MerchantRemitReq() {
    }

    public MerchantRemitReq(String requestNo, String serverCallbackUrl, String remitInfos, String trxRequestNo){
        super.setRequestNo(requestNo);
        super.setServerCallbackUrl(serverCallbackUrl);
        super.setTrxRequestNo(trxRequestNo);
        this.remiteInfos = remitInfos;
    }

    public String getRemiteInfos() {
        return remiteInfos;
    }

    public void setRemiteInfos(String remitInfos) {
        this.remiteInfos = remitInfos;
    }
}
