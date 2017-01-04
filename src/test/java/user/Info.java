package user;

import com.yeepay.payplus.bo.UserAuthReq;
import com.yeepay.payplus.bo.UserRegisterReq;
import com.yeepay.payplus.core.PayplusConnector;
import com.yeepay.payplus.core.entity.Trophy;
import com.yeepay.payplus.util.PayplusConfig;
import com.yeepay.payplus.util.PayplusURI;
import com.yeepay.payplus.util.PayplusUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Marco on 21/12/2016.
 */
public class Info {

    @Test
    public void register() {

        Trophy trophy = new PayplusConnector().call(PayplusURI.USER_REGISTER, new UserRegisterReq(null, null, PayplusConfig.RACHEL_GREEN));

        trophy.print();

        Assert.assertEquals(trophy.getState(), 1);
    }

    @Test
    public void auth() {

        UserAuthReq userAuthReq =new UserAuthReq();

        userAuthReq.setRequestNo(PayplusUtil.genRequestNo());
        userAuthReq.setMerchantNo("");
        userAuthReq.setMerchantUserId(PayplusConfig.RACHEL_GREEN);
        userAuthReq.setWebCallbackUrl("");
        userAuthReq.setReturnUrl("");

        Trophy trophy = new PayplusConnector().call(PayplusURI.USER_AUTH, userAuthReq);

        trophy.print();

        Assert.assertEquals(trophy.getState(),1);
    }
}
