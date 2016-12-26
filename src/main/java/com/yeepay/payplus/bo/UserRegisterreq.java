package com.yeepay.payplus.bo;

/**
 * Created by Marco on 15/12/2016.
 */
public class UserRegisterReq extends BaseBO{

    public UserRegisterReq() {
    }

    public UserRegisterReq(String requestNo, String merchantNo, String merchantUserId) {
        super.setRequestNo(requestNo);
        super.setMerchantNo(merchantNo);
        super.setMerchantUserId(merchantUserId);
    }

}
