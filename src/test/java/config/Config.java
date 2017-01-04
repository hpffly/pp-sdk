package config;

import com.yeepay.payplus.core.PayplusConnector;
import com.yeepay.payplus.util.PayplusConfig;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Created by Marco on 04/01/2017.
 */
public class Config {

    Logger logger = Logger.getLogger(Config.class.getName());

    @Test
    public void assignPPConfigPath(){
        PayplusConnector payplusConnector = new PayplusConnector("/Users/edison/Downloads/payplus.properties");
        logger.debug(PayplusConfig.APP_SECRET);
    }

    @Test
    public void defaultConfig(){
        PayplusConnector payplusConnector =new PayplusConnector();
        logger.debug(PayplusConfig.APP_KEY);
    }
}
