package user;

import com.yeepay.payplus.bo.UserAuthReq;
import com.yeepay.payplus.bo.UserRegisterReq;
import com.yeepay.payplus.core.PayplusConnector;
import com.yeepay.payplus.core.entity.PayplusResp;
import com.yeepay.payplus.util.PayplusConfig;
import com.yeepay.payplus.util.PayplusURI;
import com.yeepay.payplus.util.PayplusUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Marco on 21/12/2016.
 */
public class Info {

    PayplusConnector payplusConnector = new PayplusConnector();

    @Test
    public void register() {

        PayplusResp payplusResp = payplusConnector.call(PayplusURI.USER_REGISTER, new UserRegisterReq(PayplusUtil.genRequestNo(), PayplusConfig.MERCHANT_NO,"pengfei"));

        payplusResp.print();

        Assert.assertEquals(payplusResp.getState(), 1);
    }

    @Test
    public void auth() {

        UserAuthReq userAuthReq = new UserAuthReq();

        userAuthReq.setRequestNo(PayplusUtil.genRequestNo());
        userAuthReq.setMerchantNo("");
        userAuthReq.setMerchantUserId("123");
        userAuthReq.setWebCallbackUrl("");
        userAuthReq.setReturnUrl("");

        PayplusResp payplusResp = payplusConnector.call(PayplusURI.USER_AUTH, userAuthReq);

        payplusResp.print();

        Assert.assertEquals(payplusResp.getState(), 1);
    }

    @Test
    public void info(){
        Map map =new HashMap();
        map.put("merchantNo",PayplusConfig.MERCHANT_NO);
        map.put("merchantUserId","lanpay_39-1484960716657");

        PayplusResp payplusResp = payplusConnector.call(PayplusURI.USER_INFO, map);

        payplusResp.print();

        Assert.assertEquals(payplusResp.getState(), 1);

    }
}
