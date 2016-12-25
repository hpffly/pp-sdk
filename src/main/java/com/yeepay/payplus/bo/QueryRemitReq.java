package com.yeepay.payplus.bo;

/**
 * Created by Marco on 23/12/2016.
 */
public class QueryRemitReq extends BaseBO {

    /**
     * return all remits in such trxRequestNo order, if this variable is null
     */
    private String remiteRequestNo;

    public QueryRemitReq(String merchantNo, String trxRequestNo, String remiteRequestNo) {
        super.setMerchantNo(merchantNo);
        super.setTrxRequestNo(trxRequestNo);
        this.remiteRequestNo = remiteRequestNo;
    }

    public String getRemiteRequestNo() {
        return remiteRequestNo;
    }

    public void setRemiteRequestNo(String remiteRequestNo) {
        this.remiteRequestNo = remiteRequestNo;
    }
}
