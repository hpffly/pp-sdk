package allinone;

import com.yeepay.g3.utils.common.json.JSONObject;
import com.yeepay.payplus.bo.MerchantRemitQueryReq;
import com.yeepay.payplus.bo.MerchantRemitReq;
import com.yeepay.payplus.bo.OrderConsumeReq;
import com.yeepay.payplus.core.PayplusConnector;
import com.yeepay.payplus.core.entity.PayplusResp;
import com.yeepay.payplus.util.PayplusConfig;
import com.yeepay.payplus.util.PayplusURI;
import com.yeepay.payplus.util.PayplusUtil;
import org.junit.Assert;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Marco on 21/12/2016.
 */
public class Order {

    PayplusConnector payplusConnector = new PayplusConnector();

    @Test
    public void consume() throws Exception {
        OrderConsumeReq orderConsumeReq = new OrderConsumeReq();

        String payTool = "WECHATOFFICIAL"; //WECHATOFFICIAL
        String requestNo = String.valueOf(System.currentTimeMillis());
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Map<String, String> trxExtraInfo = new HashMap<String, String>();
        trxExtraInfo.put("terminaltype", "0");
        trxExtraInfo.put("deviceType", "PE-UL00");
        trxExtraInfo.put("smscheck", "N");
        trxExtraInfo.put("pwdcheck", "Y");
        trxExtraInfo.put("gameApp_id", "10535832");
        trxExtraInfo.put("longitude", "106.30788");
        trxExtraInfo.put("latitude", "29.554658");
        trxExtraInfo.put("terminalid", "865585020924282");
        trxExtraInfo.put("userip", "123.147.246.146");

        orderConsumeReq.setMerchantNo(PayplusConfig.MERCHANT_NO);
        orderConsumeReq.setRequestNo(requestNo);
        orderConsumeReq.setMerchantUserId(PayplusConfig.YANGYANG1);
        orderConsumeReq.setOrderAmount("0.01");
        orderConsumeReq.setFundAmount("0.01");
        orderConsumeReq.setPayTool(payTool);
        orderConsumeReq.setMerchantExpireTime("60");
        orderConsumeReq.setMerchantOrderDate(format.format(new Date()));
        orderConsumeReq.setWebCallbackUrl("http://payplus.yeepay.com");
        orderConsumeReq.setServerCallbackUrl("http://payplus.yeepay.com");
        orderConsumeReq.setProductCatalog("30");
        orderConsumeReq.setProductName("测试商品");
        orderConsumeReq.setProductDesc("测试商品描述");
        orderConsumeReq.setMcc("4511");
        orderConsumeReq.setIp("8.8.8.8");
        orderConsumeReq.setOpenId("oeo7Kt2nIuI2PU43UBfSgYQw8vqc");
        orderConsumeReq.setTrxExtraInfo(PayplusUtil.convert2JsonString(trxExtraInfo));

        PayplusResp payplusResp = payplusConnector.call(PayplusURI.ORDER_CONSUME, orderConsumeReq);

        payplusResp.print();

        if (payTool.equals("WECHATSCAN") || payTool.equals("ALIPAYSCAN")) {
            PayplusUtil.genQRCodeImage(payplusResp, "/Users/edison/Downloads/im.jpg");
        }
    }

    @Test
    public void remit() {

        // need prepend http:// OR https:// which matched the Regular Expression
        String serverCallbackUrl = "http://payplus.yeepay.com";

        List<JSONObject> list = new ArrayList<JSONObject>();
        Map remitInfosMap = new HashMap<String, String>();

        remitInfosMap.put("remiteType", "AMOUNT");
        remitInfosMap.put("bankCode", "CMB");
        remitInfosMap.put("bankName", "招商银行");
        remitInfosMap.put("branchBankName", "");
        remitInfosMap.put("userName", "杨洋");
        remitInfosMap.put("cardNo", "6214850107101245");
        remitInfosMap.put("bankAccountType", "pr");
        remitInfosMap.put("province", "110000");
        remitInfosMap.put("city", "110000");
        remitInfosMap.put("payeeMobile", "18514591959");
        remitInfosMap.put("leaveWord", "易宝测试");
        remitInfosMap.put("value", "0.01");

        list.add(PayplusUtil.convert2Json(remitInfosMap));

        MerchantRemitReq merchantRemitReq = new MerchantRemitReq(PayplusUtil.genRequestNo(), serverCallbackUrl, PayplusUtil.convert2JsonArray(list), "4780428817162709112");

        PayplusResp payplusResp = payplusConnector.call(PayplusURI.MERCHANT_REMIT, merchantRemitReq);

        payplusResp.print();

        Assert.assertEquals(payplusResp.getState(), 1);

    }

    //@Test
    public void queryRemits() {

        MerchantRemitQueryReq merchantRemitQueryReq = new MerchantRemitQueryReq(null, "1482465587650", null);

        PayplusResp payplusResp = payplusConnector.call(PayplusURI.MERCHANT_REMIT_QUERY, merchantRemitQueryReq);

        payplusResp.print();

        Assert.assertEquals(payplusResp.getState(), 1);
    }
}
