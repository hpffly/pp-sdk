package marketing;

import com.yeepay.payplus.bo.ReceiveRedPacketReq;
import com.yeepay.payplus.bo.SendRedPacketReq;
import com.yeepay.payplus.bo.UnReceiveRedPacketReq;
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

    String activeNo = "AMACT20161222175211567hhfeSRC";

    String marketNo = "RSX20161222175218627U88G4RNZ";

    //@Test
    public void send() {
        PayplusConnector connector = new PayplusConnector();

        SendRedPacketReq sendRedPacketReq = new SendRedPacketReq(null, PayplusConfig.JOEY_TRIBBIANI, activeNo);

        Trophy trophy = connector.call(PayplusURI.MARKETING_REDPACKET_SEND, sendRedPacketReq);

        trophy.print();

        Assert.assertEquals(trophy.getState(),1);
    }

    @Test
    public void receive(){
        PayplusConnector connector = new PayplusConnector();

        ReceiveRedPacketReq receiveRedPacketReq = new ReceiveRedPacketReq(marketNo, null, "3991941028905236025");

        Trophy trophy = connector.call(PayplusURI.MARKETING_REDPACKET_RECEIVE, receiveRedPacketReq);

        trophy.print();

        Assert.assertEquals(trophy.getState(),1);
    }

    //@Test
    public void unreceive(){
        PayplusConnector connector = new PayplusConnector();

        UnReceiveRedPacketReq unReceiveRedPacketReq = new UnReceiveRedPacketReq(activeNo, null, PayplusConfig.JOEY_TRIBBIANI);

        Trophy trophy = connector.call(PayplusURI.MARKETING_REDPACKET_UNRECEIVED, unReceiveRedPacketReq);

        trophy.print();

        Assert.assertEquals(trophy.getState(),1);
    }
}
