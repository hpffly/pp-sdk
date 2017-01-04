package marketing;

import com.yeepay.payplus.bo.MarketingRedPacketReceiveReq;
import com.yeepay.payplus.bo.MarketingRedPacketSendReq;
import com.yeepay.payplus.bo.MarketingRedPacketUnreceivedReq;
import com.yeepay.payplus.core.PayplusConnector;
import com.yeepay.payplus.core.entity.Trophy;
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

    @Test
    public void send() {
        PayplusConnector connector = new PayplusConnector();

        MarketingRedPacketSendReq marketingRedPacketSendReq = new MarketingRedPacketSendReq(null, PayplusConfig.JOEY_TRIBBIANI, activeNo);

        Trophy trophy = connector.call(PayplusURI.MARKETING_REDPACKET_SEND, marketingRedPacketSendReq);

        trophy.print();

        Assert.assertEquals(trophy.getState(),1);
    }

    @Test
    public void receive(){
        PayplusConnector connector = new PayplusConnector();

        MarketingRedPacketReceiveReq marketingRedPacketReceiveReq = new MarketingRedPacketReceiveReq(marketNo, null, PayplusConfig.JOEY_TRIBBIANI);

        Trophy trophy = connector.call(PayplusURI.MARKETING_REDPACKET_RECEIVE, marketingRedPacketReceiveReq);

        trophy.print();

        Assert.assertEquals(trophy.getState(),1);
    }

    @Test
    public void unreceived(){
        PayplusConnector connector = new PayplusConnector();

        MarketingRedPacketUnreceivedReq marketingRedPacketUnreceivedReq = new MarketingRedPacketUnreceivedReq(activeNo, null, PayplusConfig.RACHEL_GREEN);

        Trophy trophy = connector.call(PayplusURI.MARKETING_REDPACKET_UNRECEIVED, marketingRedPacketUnreceivedReq);

        trophy.print();

        Assert.assertEquals(trophy.getState(),1);
    }
}
