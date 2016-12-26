package com.yeepay.payplus.bo;

/**
 * Created by Marco on 22/12/2016.
 */
public class MarketingRedPacketUnreceivedReq extends BaseBO{

    private String activeNo;

    public MarketingRedPacketUnreceivedReq() {
    }

    public MarketingRedPacketUnreceivedReq(String activeNo, String merchantNo, String merchantUserId) {
        super.setMerchantNo(merchantNo);
        super.setMerchantUserId(merchantUserId);
        this.activeNo = activeNo;
        //
    }

    public String getActiveNo() {
        return activeNo;
    }

    public void setActiveNo(String activeNo) {
        this.activeNo = activeNo;
    }
}
