package user;

import com.yeepay.payplus.bo.UserAuthReq;
import com.yeepay.payplus.bo.UserCardListReq;
import com.yeepay.payplus.bo.UserUnbindCardReq;
import com.yeepay.payplus.core.PayplusConnector;
import com.yeepay.payplus.core.entity.PayplusResp;
import com.yeepay.payplus.util.PayplusConfig;
import com.yeepay.payplus.util.PayplusURI;
import com.yeepay.payplus.util.PayplusUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Marco on 05/01/2017.
 */
public class Card {

    PayplusConnector payplusConnector = new PayplusConnector();

    @Test
    public void queryCardList() {

        UserCardListReq userCardListReq = new UserCardListReq(PayplusConfig.MERCHANT_NO, PayplusConfig.YANGYANG1);
        PayplusResp payplusResp = payplusConnector.call(PayplusURI.USER_CARDLIST, userCardListReq);

        payplusResp.print();

        Assert.assertEquals(payplusResp.getState(), 1);
    }

    /**
     * before getting this service:
     * <p>
     * 1. acquire bindId through PayplusURI.USER_CARDLIST with the same merchantUserId
     * 2. acquire token through PayplusURI.USER_VERIFYPWD service
     * 3. ensure the requestNo is corresponding with the requestNo of PayplusURI.USER_VERIFYPWD
     */
    @Test
    public void unBindCard() {

        String bindId = "53965327";
        String token = "e4770b6982994922bff16fd76c85cf72";
        String requestNo = "0cbb8f087bea4df5bddb010d2b66b8db";

        UserUnbindCardReq userUnbindCardReq = new UserUnbindCardReq();

        userUnbindCardReq.setRequestNo(requestNo);
        userUnbindCardReq.setMerchantNo(PayplusConfig.MERCHANT_NO);
        userUnbindCardReq.setMerchantUserId(PayplusConfig.YANGYANG1);
        userUnbindCardReq.setBindId(bindId);
        userUnbindCardReq.setReason("personal affair");
        userUnbindCardReq.setToken(token);

        PayplusResp payplusResp = payplusConnector.call(PayplusURI.USER_UNBINDCARD, userUnbindCardReq);

        payplusResp.print();

        Assert.assertEquals(payplusResp.getState(), 1);

    }

    @Test
    public void bindCard(){
        // same with auth

        UserAuthReq userAuthReq =new UserAuthReq();

        userAuthReq.setRequestNo(PayplusUtil.genRequestNo());
        userAuthReq.setMerchantNo("");
        userAuthReq.setMerchantUserId(PayplusConfig.YANGYANG1);
        userAuthReq.setWebCallbackUrl("");
        userAuthReq.setReturnUrl("");

        PayplusResp payplusResp = payplusConnector.call(PayplusURI.USER_BINDCARD, userAuthReq);

        payplusResp.print();

        Assert.assertEquals(payplusResp.getState(),1);
    }
}
