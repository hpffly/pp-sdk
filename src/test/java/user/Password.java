package user;

import com.yeepay.payplus.bo.VerifyPasswordReq;
import com.yeepay.payplus.core.PayplusConnector;
import com.yeepay.payplus.core.entity.Trophy;
import com.yeepay.payplus.util.PayplusConfig;
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

        VerifyPasswordReq verifyPasswordReq = new VerifyPasswordReq();

        verifyPasswordReq.setMerchantNo(PayplusConfig.MERCHANT_NO);
        verifyPasswordReq.setMerchantUserId("yangyang1");
        verifyPasswordReq.setRequestNo(PayplusUtil.genRequestNo());
        verifyPasswordReq.setTokenBizType(verifyPasswordReq.TRANSFER);
        verifyPasswordReq.setWebCallBackUrl("payplus.yeepay.com");
        verifyPasswordReq.setReturnUrl("payplus.yeepay.com");

        Trophy trophy = payplusConnector.call(PayplusURI.USER_VERIFYPWD, verifyPasswordReq);

        trophy.print();

    }
}
