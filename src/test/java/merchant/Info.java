package merchant;

import com.yeepay.payplus.bo.LedgerRegisterReq;
import com.yeepay.payplus.bo.LedgerUploadReq;
import com.yeepay.payplus.core.PayplusConnector;
import com.yeepay.payplus.core.entity.Trophy;
import com.yeepay.payplus.util.PayplusConfig;
import com.yeepay.payplus.util.PayplusURI;
import com.yeepay.payplus.util.PayplusUtil;
import org.junit.Test;

/**
 * Created by Marco on 26/12/2016.
 */
public class Info {

    @Test
    public void ledgerRegister() {

        LedgerRegisterReq ledgerRegisterReq = new LedgerRegisterReq();

        ledgerRegisterReq.setRequestNo(PayplusUtil.genRequestNo());
        ledgerRegisterReq.setMerchantNo(PayplusConfig.merchantNo);
        ledgerRegisterReq.setProvince("北京");
        ledgerRegisterReq.setCity("北京");
        ledgerRegisterReq.setCustomerStyle(LedgerRegisterReq.CUSTOMERSTYLE_PERSON);
        //ledgerRegisterReq.setBusinessLicence("1024"); // customerStyle 为企业时填写
        ledgerRegisterReq.setSignedName("刘庆博");
        ledgerRegisterReq.setIdCard("513922199006050019");
        ledgerRegisterReq.setLegalPerson("刘庆博");
        ledgerRegisterReq.setContactor("刘庆博");
        ledgerRegisterReq.setBindMobile("13550311502");
        ledgerRegisterReq.setEmail("yang.yang-1@yeepay.com");
        ledgerRegisterReq.setBankAccountNumber("6227003813440208029");
        ledgerRegisterReq.setAccountName("刘庆博");
        ledgerRegisterReq.setBankAccountType(LedgerRegisterReq.BANKACCOUNTTYPE_PRIVATE_CASH);
        ledgerRegisterReq.setBankName("中国建设银行");
        ledgerRegisterReq.setBankCardProvince("北京");
        ledgerRegisterReq.setBankCardCity("北京");
        ledgerRegisterReq.setRiskReserveDay("1");
        ledgerRegisterReq.setMinSettleAmount("100");

        PayplusConnector payplusConnector = new PayplusConnector();
        Trophy trophy = payplusConnector.call(PayplusURI.LEDGER_REGISTER, ledgerRegisterReq);

        trophy.print();
    }

    @Test
    public void ledgerUpload() {

        String img = "/Users/edison/Downloads/test.jpeg";

        LedgerUploadReq ledgerUploadReq = new LedgerUploadReq(null, "BL12345678901716", LedgerUploadReq.QUALIFICATIONTYPE_ID_CARD_FRONT, PayplusUtil.getBase64Stream(img));

        PayplusConnector payplusConnector = new PayplusConnector();
        Trophy trophy = payplusConnector.call(PayplusURI.LEDGER_UPLOAD, ledgerUploadReq);
        trophy.print();
    }
}
