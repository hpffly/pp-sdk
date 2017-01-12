package user;

import com.yeepay.payplus.bo.UserVerifyPWDReq;
import com.yeepay.payplus.core.PayplusConnector;
import com.yeepay.payplus.core.entity.PayplusResp;
import com.yeepay.payplus.util.PayplusConfig;
import com.yeepay.payplus.util.PayplusURI;
import com.yeepay.payplus.util.PayplusUtil;
import org.junit.Test;

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
}
