package user;

import com.yeepay.payplus.bo.TransferReq;
import com.yeepay.payplus.core.PayplusConnector;
import com.yeepay.payplus.core.entity.Trophy;
import com.yeepay.payplus.util.PayplusConfig;
import com.yeepay.payplus.util.PayplusURI;
import org.junit.Test;

/**
 * Created by Marco on 21/12/2016.
 */
public class Trade {

    @Test
    public void transfer() {

        PayplusConnector payplusConnector = new PayplusConnector();

        TransferReq transferReq = new TransferReq();
        transferReq.setRequestNo("d010ca9ee2874bbda439efa73a7256ba");
        transferReq.setAmount("0.01");
        transferReq.setFromUserNo("yangyang1");
        transferReq.setToUserNo("xiaolong");
        transferReq.setToken("91e473364d9b427c85c87e42aeef2baa");
        transferReq.setMerchantNo(PayplusConfig.MERCHANT_NO);
        transferReq.setTransferType(transferReq.USER_TO_USER);

        Trophy trophy = payplusConnector.call(PayplusURI.ORDER_TRANSFER, transferReq);

        trophy.print();
    }
}
