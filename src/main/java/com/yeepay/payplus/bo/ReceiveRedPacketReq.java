package com.yeepay.payplus.bo;

/**
 * Created by Marco on 22/12/2016.
 */
public class ReceiveRedPacketReq extends BaseBO {

    private String marketNo;

    public ReceiveRedPacketReq() {
    }

    public ReceiveRedPacketReq(String marketNo, String merchantNo, String merchantUserId) {
        this.marketNo = marketNo;
        super.setMerchantNo(merchantNo);
        super.setMerchantUserId(merchantUserId);
    }

    public String getMarketNo() {
        return marketNo;
    }

    public void setMarketNo(String marketNo) {
        this.marketNo = marketNo;
    }
}
