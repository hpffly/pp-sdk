package others;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marco on 05/01/2017.
 */
public class HTTP {

    private String url = "https://plus.yeepay.com/pp-user-app/app/verifyTradePasswordPage";

    @Test
    public void http() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        nvps.add(new BasicNameValuePair("password", "840113"));
        nvps.add(new BasicNameValuePair("externalNo", "USTKN20170105154241748Ztnx0IA"));
        nvps.add(new BasicNameValuePair("bizType", "UN_BIND_CARD"));
        nvps.add(new BasicNameValuePair("requestNo", "1d23370092f148c28f7d93840a8feeda"));
        nvps.add(new BasicNameValuePair("webCallBackUrl", "http://payplus.yeepay.com"));
        nvps.add(new BasicNameValuePair("serverCallBackUrl", "http://payplus.yeepay.com"));
        nvps.add(new BasicNameValuePair("returnUrl", "http://payplus.yeepay.com"));
        nvps.add(new BasicNameValuePair("ppMerchantNo", "BM12345678901221"));
        nvps.add(new BasicNameValuePair("merchantUserId", "yangyang1"));

        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        CloseableHttpResponse response2 = httpclient.execute(httpPost);

        try {
            System.out.println(response2.getStatusLine());
            HttpEntity entity2 = response2.getEntity();
            // do something useful with the response body
            // and ensure it is fully consumed
            EntityUtils.consume(entity2);
        } finally {
            response2.close();
        }
    }

    @Test
    public void https() {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            //设置参数
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();

            nvps.add(new BasicNameValuePair("password", "840113"));
            nvps.add(new BasicNameValuePair("externalNo", "USTKN20170105154241748Ztnx0IA"));
            nvps.add(new BasicNameValuePair("bizType", "UN_BIND_CARD"));
            nvps.add(new BasicNameValuePair("requestNo", "1d23370092f148c28f7d93840a8feeda"));
            nvps.add(new BasicNameValuePair("webCallBackUrl", "http://payplus.yeepay.com"));
            nvps.add(new BasicNameValuePair("serverCallBackUrl", "http://payplus.yeepay.com"));
            nvps.add(new BasicNameValuePair("returnUrl", "http://payplus.yeepay.com"));
            nvps.add(new BasicNameValuePair("ppMerchantNo", "BM12345678901221"));
            nvps.add(new BasicNameValuePair("merchantUserId", "yangyang1"));
            if (nvps.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nvps, "UTF-8");
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "UTF-8");
                }

                System.out.println(response.getStatusLine());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
