package merchant;

import com.yeepay.payplus.bo.LedgerRegisterReq;
import com.yeepay.payplus.bo.LedgerUploadReq;
import com.yeepay.payplus.core.PayplusConnector;
import com.yeepay.payplus.core.entity.PayplusResp;
import com.yeepay.payplus.util.PayplusConfig;
import com.yeepay.payplus.util.PayplusURI;
import com.yeepay.payplus.util.PayplusUtil;
import org.junit.Test;

/**
 * Created by Marco on 26/12/2016.
 */
public class Info {

    PayplusConnector payplusConnector = new PayplusConnector();

    @Test
    public void ledgerRegister() {

        LedgerRegisterReq ledgerRegisterReq = new LedgerRegisterReq();

        ledgerRegisterReq.setRequestNo(PayplusUtil.genRequestNo());
        ledgerRegisterReq.setMerchantNo(PayplusConfig.MERCHANT_NO);
        ledgerRegisterReq.setProvince("北京");
        ledgerRegisterReq.setCity("北京");
        ledgerRegisterReq.setCustomerStyle(LedgerRegisterReq.CUSTOMERSTYLE_PERSON);
        //ledgerRegisterReq.setBusinessLicence("1024"); // customerStyle 为企业时填写
        ledgerRegisterReq.setSignedName("杨洋");
        ledgerRegisterReq.setIdCard("");
        ledgerRegisterReq.setLegalPerson("杨洋");
        ledgerRegisterReq.setContactor("杨洋");
        ledgerRegisterReq.setBindMobile("");
        ledgerRegisterReq.setEmail("yang.yang-1@yeepay.com");
        ledgerRegisterReq.setBankAccountNumber("");
        ledgerRegisterReq.setAccountName("杨洋");
        ledgerRegisterReq.setBankAccountType(LedgerRegisterReq.BANKACCOUNTTYPE_PRIVATE_CASH);
        ledgerRegisterReq.setBankName("招商银行北京分行");
        ledgerRegisterReq.setBankCardProvince("北京");
        ledgerRegisterReq.setBankCardCity("北京");
        ledgerRegisterReq.setRiskReserveDay("2");
        ledgerRegisterReq.setMinSettleAmount("20");

        PayplusResp payplusResp = payplusConnector.call(PayplusURI.LEDGER_REGISTER, ledgerRegisterReq);

        payplusResp.print();
    }

    @Test
    public void ledgerUpload() {

        String img = "/Users/edison/Downloads/test.jpeg";

        LedgerUploadReq ledgerUploadReq = new LedgerUploadReq(null, "BL12345678901716", LedgerUploadReq.QUALIFICATIONTYPE_ID_CARD_FRONT, PayplusUtil.getBase64Stream(img));

        PayplusResp payplusResp = payplusConnector.call(PayplusURI.LEDGER_UPLOAD, ledgerUploadReq);
        payplusResp.print();
    }
}
