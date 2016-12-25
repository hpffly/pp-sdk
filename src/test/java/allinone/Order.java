package allinone;

import com.yeepay.payplus.bo.ConsumeReq;
import com.yeepay.payplus.bo.QueryRemitReq;
import com.yeepay.payplus.bo.RemitReq;
import com.yeepay.payplus.core.PayplusConnector;
import com.yeepay.payplus.core.entity.Trophy;
import com.yeepay.payplus.util.PayplusConfig;
import com.yeepay.payplus.util.PayplusURI;
import com.yeepay.payplus.util.PayplusUtil;
import org.junit.Assert;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Marco on 21/12/2016.
 */
public class Order {

    @Test
    public void consume() throws Exception {
        ConsumeReq consumeReq = new ConsumeReq();

        String payTool = "ALIPAYAPP";
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

        consumeReq.setRequestNo(requestNo);
        consumeReq.setMerchantNo(PayplusConfig.MERCHANT_NO);
        consumeReq.setMerchantUserId(PayplusConfig.JOEY_TRIBBIANI);
        consumeReq.setOrderAmount("0.01");
        consumeReq.setFundAmount("0.01");
        consumeReq.setPayTool(payTool);
        consumeReq.setMerchantExpireTime("60");
        consumeReq.setMerchantOrderDate(format.format(new Date()));
        consumeReq.setWebCallbackUrl("payplus.yeepay.com");
        consumeReq.setServerCallbackUrl("payplus.yeepay.com");
        consumeReq.setProductCatalog("30");
        consumeReq.setProductName("测试商品");
        consumeReq.setProductDesc("测试商品描述");
        consumeReq.setMcc("3101");
        consumeReq.setIp("8.8.8.8");
        consumeReq.setOpenId("ogiZrwBxHMFMJP5npHp0WOb84H94");
        consumeReq.setTrxExtraInfo(PayplusUtil.convert2JsonString(trxExtraInfo));

        Trophy trophy = new PayplusConnector().call(PayplusURI.ORDER_CONSUME, consumeReq);

        trophy.print();

        if (payTool.equals("WECHATSCAN") || payTool.equals("ALIPAYSCAN")) {
            PayplusUtil.generateQRCodeImage(trophy, "/Users/edison/Downloads/im.jpg");
        }
    }

    @Test
    public void remit() {

        /**

         RemiteBankDTO:
         private RemiteTypeEnum remiteType;//打款类型:RATE|AMOUNT--NOT NULL
         private String bankCode;//银行编码--NOT NULL|LENGTH=64、参考银行编码
         private String bankName;//银行名字--NOT NULL|LENGTH=128
         private String branchBankName;//支行名称--非必填
         private String userName;//卡用户名字--NOT NULL|LENGTH=128
         private String cardNo;//卡号--NOT NULL|LENGTH=64
         private String bankAccountType;//对公还是对私pr|pu--NOT NULL
         private String province;//省编码--NOT NULL|LENGTH=32--参考省市编码
         private String city;//市编码--NOT NULL|LENGTH=32--参考省市编码
         private String payeeMobile;//预留手机号--NOT NULL
         private String leaveWord;//留言--非必填
         private String value;//值--NOT NULL

         参考样例：
         [{'cardNo':'*******','bankAccountType':'pr','remiteType':'AMOUNT','leaveWord':'','province':'110000','value':'0.65','userName':'***','bankName':'中国银行','payeeMobile':'***','branchBankName':'','city':'110000','bankCode':'BOC'}]

         */

        String remitInfos = null;

        Map remitInfosMap =new HashMap<String, String>();

        remitInfosMap.put("remiteType","AMOUNT");
        remitInfosMap.put("bankCode","CMB");
        remitInfosMap.put("bankName","招商银行");
        remitInfosMap.put("branchBankName","");
        remitInfosMap.put("userName","杨洋");
        remitInfosMap.put("cardNo","6214850107101245");
        remitInfosMap.put("bankAccountType","pr");
        remitInfosMap.put("province","110000");
        remitInfosMap.put("city","110000");
        remitInfosMap.put("payeeMobile","18514591959");
        remitInfosMap.put("leaveWord","易宝测试");
        remitInfosMap.put("value","0.01");

        RemitReq remitReq = new RemitReq(PayplusUtil.genRequestNo(), "payplus.yeepay.com", PayplusUtil.convert2JsonString(remitInfosMap), "1482465587650");

        PayplusConnector payplusConnector = new PayplusConnector();

        Trophy trophy = payplusConnector.call(PayplusURI.REMIT_REMIT, remitReq);

        trophy.print();

        Assert.assertEquals(trophy.getState(), 1);

    }

    //@Test
    public void queryRemits(){

        QueryRemitReq queryRemitReq = new QueryRemitReq(PayplusConfig.MERCHANT_NO, "1482465587650", null);

        PayplusConnector payplusConnector = new PayplusConnector();

        Trophy trophy = payplusConnector.call(PayplusURI.REMIT_QUERY, queryRemitReq);

        trophy.print();

        Assert.assertEquals(trophy.getState(), 1);
    }
}
