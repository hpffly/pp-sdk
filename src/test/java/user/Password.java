package user;

import com.yeepay.payplus.bo.UserVerifyPWDReq;
import com.yeepay.payplus.core.PayplusConnector;
import com.yeepay.payplus.core.entity.PayplusResp;
import com.yeepay.payplus.util.PayplusConfig;
import com.yeepay.payplus.util.PayplusURI;
import com.yeepay.payplus.util.PayplusUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Marco on 21/12/2016.
 */
public class Password {

    PayplusConnector payplusConnector = new PayplusConnector();

    @Test
    public void verify() {

        UserVerifyPWDReq userVerifyPWDReq = new UserVerifyPWDReq();

        userVerifyPWDReq.setMerchantNo("");
        userVerifyPWDReq.setMerchantUserId(PayplusConfig.YANGYANG1);
        userVerifyPWDReq.setRequestNo(PayplusUtil.genRequestNo());
        userVerifyPWDReq.setTokenBizType(UserVerifyPWDReq.TOKENBIZTYPE_UN_BIND_CARD);

        PayplusResp payplusResp = payplusConnector.call(PayplusURI.USER_VERIFYPWD, userVerifyPWDReq);

        payplusResp.print();

    }

    @Test
    public void resetAndModify() {

        Map map = new HashMap();

        map.put("requestNo",PayplusUtil.genRequestNo());
        map.put("merchantNo",PayplusConfig.MERCHANT_NO);
        map.put("merchantUserId",PayplusConfig.YANGYANG1);
        map.put("webCallBackUrl","http://payplus.yeepay.com");
        map.put("returnUrl","http://payplus.yeepay.com");

        PayplusResp payplusResp = payplusConnector.call(PayplusURI.USER_RESETPWD, map);

        payplusResp.print();

    }
}

