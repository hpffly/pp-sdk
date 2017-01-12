package marketing;

import com.yeepay.payplus.bo.MarketingRedPacketReceiveReq;
import com.yeepay.payplus.bo.MarketingRedPacketSendReq;
import com.yeepay.payplus.bo.MarketingRedPacketUnreceivedReq;
import com.yeepay.payplus.core.PayplusConnector;
import com.yeepay.payplus.core.entity.PayplusResp;
import com.yeepay.payplus.util.PayplusConfig;
import com.yeepay.payplus.util.PayplusURI;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Marco on 21/12/2016.
 */
public class RedPacket {

    // id of marketing
    String activeNo = "AMACT20161222175048464UePoYsN";

    // id of redpacket
    String marketNo = "RSX20170104101059209XSYN4M5L";

    PayplusConnector payplusConnector = new PayplusConnector();

    @Test
    public void send() {
        MarketingRedPacketSendReq marketingRedPacketSendReq = new MarketingRedPacketSendReq(null, PayplusConfig.JOEY_TRIBBIANI, activeNo);

        PayplusResp payplusResp = payplusConnector.call(PayplusURI.MARKETING_REDPACKET_SEND, marketingRedPacketSendReq);

        payplusResp.print();

        Assert.assertEquals(payplusResp.getState(),1);
    }

    @Test
    public void receive(){

        MarketingRedPacketReceiveReq marketingRedPacketReceiveReq = new MarketingRedPacketReceiveReq(marketNo, null, PayplusConfig.JOEY_TRIBBIANI);

        PayplusResp payplusResp = payplusConnector.call(PayplusURI.MARKETING_REDPACKET_RECEIVE, marketingRedPacketReceiveReq);

        payplusResp.print();

        Assert.assertEquals(payplusResp.getState(),1);
    }

    @Test
    public void unreceived(){

        MarketingRedPacketUnreceivedReq marketingRedPacketUnreceivedReq = new MarketingRedPacketUnreceivedReq(activeNo, null, PayplusConfig.RACHEL_GREEN);

        PayplusResp payplusResp = payplusConnector.call(PayplusURI.MARKETING_REDPACKET_UNRECEIVED, marketingRedPacketUnreceivedReq);

        payplusResp.print();

        Assert.assertEquals(payplusResp.getState(),1);
    }
}
