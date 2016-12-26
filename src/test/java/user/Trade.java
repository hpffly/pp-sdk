package user;

import com.yeepay.payplus.bo.OrderTransferReq;
import com.yeepay.payplus.core.PayplusConnector;
import com.yeepay.payplus.core.entity.Trophy;
import com.yeepay.payplus.util.PayplusURI;
import org.junit.Test;

/**
 * Created by Marco on 21/12/2016.
 */
public class Trade {

    @Test
    public void transfer() {

        PayplusConnector payplusConnector = new PayplusConnector();

        OrderTransferReq orderTransferReq = new OrderTransferReq();
        orderTransferReq.setRequestNo("d010ca9ee2874bbda439efa73a7256ba");
        orderTransferReq.setAmount("0.01");
        orderTransferReq.setFromUserNo("yangyang1");
        orderTransferReq.setToUserNo("xiaolong");
        orderTransferReq.setToken("91e473364d9b427c85c87e42aeef2baa");
        orderTransferReq.setTransferType(OrderTransferReq.TRANSFERTYPE_USER_TO_USER);

        Trophy trophy = payplusConnector.call(PayplusURI.ORDER_TRANSFER, orderTransferReq);

        trophy.print();
    }
}
