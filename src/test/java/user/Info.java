package user;

import com.yeepay.payplus.bo.AuthReq;
import com.yeepay.payplus.bo.RegisterReq;
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

        Trophy trophy = new PayplusConnector().call(PayplusURI.USER_REGISTER, new RegisterReq(null, null, PayplusUtil.genRequestNo()));

        trophy.print();

        Assert.assertEquals(trophy.getState(), 1);

        //new PayplusConnector().call(PayplusURI.USER_REGISTER, new RegisterReq(null,null,"Joey")).print();

        trophy.getRequestNo();

    }

    @Test
    public void auth() {
        Trophy trophy = new PayplusConnector().call(PayplusURI.USER_AUTH, new AuthReq(PayplusUtil.genRequestNo(), null, PayplusConfig.JOEY_TRIBBIANI, "payplus.yeepay.com", "payplus.yeepay.com", null));

        trophy.print();
    }
}
