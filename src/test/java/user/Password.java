package user;

import com.yeepay.payplus.bo.UserVerifyPWDReq;
import com.yeepay.payplus.core.PayplusConnector;
import com.yeepay.payplus.core.entity.Trophy;
import com.yeepay.payplus.util.PayplusURI;
import com.yeepay.payplus.util.PayplusUtil;
import org.junit.Test;

/**
 * Created by Marco on 21/12/2016.
 */
public class Password {

    @Test
    public void verify() {

        PayplusConnector payplusConnector = new PayplusConnector();

        UserVerifyPWDReq userVerifyPWDReq = new UserVerifyPWDReq();

        userVerifyPWDReq.setMerchantUserId("yangyang1");
        userVerifyPWDReq.setRequestNo(PayplusUtil.genRequestNo());
        userVerifyPWDReq.setTokenBizType(UserVerifyPWDReq.TOKENBIZTYPE_TRANSFER);
        userVerifyPWDReq.setWebCallBackUrl("payplus.yeepay.com");
        userVerifyPWDReq.setReturnUrl("payplus.yeepay.com");

        Trophy trophy = payplusConnector.call(PayplusURI.USER_VERIFYPWD, userVerifyPWDReq);

        trophy.print();

    }
}
