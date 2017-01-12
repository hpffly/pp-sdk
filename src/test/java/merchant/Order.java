package merchant;

import com.yeepay.payplus.bo.MerchantBalanceReq;
import com.yeepay.payplus.bo.MerchantRechargeReq;
import com.yeepay.payplus.bo.MerchantTransferQueryReq;
import com.yeepay.payplus.core.PayplusConnector;
import com.yeepay.payplus.core.entity.PayplusResp;
import com.yeepay.payplus.util.PayplusConfig;
import com.yeepay.payplus.util.PayplusURI;
import com.yeepay.payplus.util.PayplusUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * Created by Marco on 06/01/2017.
 */
public class Order {

    PayplusConnector payplusConnector = new PayplusConnector();

    @Test
    public void balance(){

        PayplusResp payplusResp = payplusConnector.call(PayplusURI.MERCHANT_BALANCE,new MerchantBalanceReq());

        payplusResp.print();

        Assert.assertEquals(payplusResp.getState(),1);
    }

    @Test
    public void MerchantRecharge(){

        MerchantRechargeReq merchantRechargeReq =new MerchantRechargeReq();

        merchantRechargeReq.setMerchantNo(PayplusConfig.MERCHANT_NO);
        merchantRechargeReq.setRequestNo(PayplusUtil.genRequestNo());
        merchantRechargeReq.setMerchantExpireTime("1440");
        merchantRechargeReq.setMerchantOrderDate(PayplusUtil.getFormatDateString(new Date()));
        merchantRechargeReq.setServerCallbackUrl(PayplusConfig.GENERAL_URL);
        merchantRechargeReq.setWebCallbackUrl(PayplusConfig.GENERAL_URL);
        merchantRechargeReq.setOrderAmount("0.01");
        //merchantRechargeReq.setPayTool("SALESB2B");
        //merchantRechargeReq.setBankCode("ABC");

        //PINGAN, CMBC, ICBC, 深发展和平安银行合并了。

        PayplusResp payplusResp = payplusConnector.call(PayplusURI.MERCHANT_RECHARGE,merchantRechargeReq);
        payplusResp.print();

        Assert.assertEquals(payplusResp.getState(),1);
    }

    @Test
    public void transferQuery(){

        MerchantTransferQueryReq merchantTransferQueryReq =new MerchantTransferQueryReq(PayplusConfig.MERCHANT_NO,"123");
        PayplusResp payplusResp = payplusConnector.call(PayplusURI.ORDER_TRANSFER_QUERY,merchantTransferQueryReq);

        payplusResp.print();

        Assert.assertEquals(payplusResp.getState(),1);
    }
}
