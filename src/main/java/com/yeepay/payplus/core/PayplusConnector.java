package com.yeepay.payplus.core;

import com.yeepay.g3.sdk.yop.client.YopClient;
import com.yeepay.g3.sdk.yop.client.YopRequest;
import com.yeepay.g3.sdk.yop.client.YopResponse;
import com.yeepay.g3.sdk.yop.error.YopError;
import com.yeepay.g3.sdk.yop.error.YopSubError;
import com.yeepay.g3.utils.common.json.JSONException;
import com.yeepay.g3.utils.common.json.JSONObject;
import com.yeepay.payplus.bo.BaseBO;
import com.yeepay.payplus.core.entity.Trophy;
import com.yeepay.payplus.util.PayplusConfig;
import com.yeepay.payplus.util.PayplusUtil;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.log4j.Logger;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by Marco on 15/12/2016.
 */
public class PayplusConnector {

    private Logger logger = Logger.getLogger(PayplusConnector.class);

    private YopRequest request;

    private String appKey;
    private String appSecret;

    public PayplusConnector() {
        this(null, null);
    }

    public PayplusConnector(String appKey, String appSecret) {

        this.appKey = appKey;
        this.appSecret = appSecret;

        establish();
    }

    private void establish() {

        //load pp-sdk configuration
        PayplusConfig.init();

        /**
         * if input parameters are null, we adopt default configuration to request payplus service.
         * Otherwise, using customized parameters.
         */
        if (appKey == null && appSecret == null)
            instanceYOPRequest(PayplusConfig.appKey, PayplusConfig.appSecret, PayplusConfig.url, PayplusConfig.signAlgorithm);
        else
            instanceYOPRequest(appKey, appSecret, PayplusConfig.url, PayplusConfig.signAlgorithm);
    }

    private void instanceYOPRequest(String appKey, String appSecret, String url, String signAlgorithm) {

        request = new YopRequest(appKey, appSecret, url);

        request.setEncrypt(true);
        request.setSignRet(true);
        request.setSignAlg(signAlgorithm);
    }

    private String formattedRequestParameters(Map<String, String> paras) {

        Set keys = paras.keySet();
        StringBuilder parameters = new StringBuilder("\n[Request of Payplus]\n");

        Iterator it = keys.iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            Object value = paras.get(key);

            if (PayplusUtil.isNull(value))
                continue;

            parameters.append(key + " - " + value + "\n");
        }

        return parameters.toString();
    }

    public Trophy call(String uri, Map<String, String> paras) {

        Trophy trophy = new Trophy();

        //set YOPRequest up
        setUpYOPRequest(request, paras);

        //equip requestNo, activeNo with Trophy object for users' convenience
        if (paras.containsKey("requestNo"))
            trophy.setRequestNo(PayplusUtil.isNull(paras.get("requestNo")) ? "requestNo is null" : paras.get("requestNo"));
        else if (paras.containsKey("activeNo"))
            trophy.setActiveNo(PayplusUtil.isNull(paras.get("activeNo")) ? "activeNo is null" : paras.get("activeNo"));

        logger.debug(formattedRequestParameters(paras));

        YopResponse resp = YopClient.post(uri, request);

        String state = resp.getState();

        JSONObject jo = null;

        if ("FAILURE".equals(state)) {

            YopError err = resp.getError();

            try {

                jo = new JSONObject();
                jo.put("code", err.getCode());
                jo.put("message", err.getMessage());

                JSONObject subErr = new JSONObject();

                List<YopSubError> errList = err.getSubErrors();

                for (YopSubError yopSubError : errList) {
                    subErr.put(yopSubError.getCode(), yopSubError.getMessage());
                }

                jo.put("subErrors", subErr);
                trophy.setResponse(jo.toString(2));
                trophy.setState(0);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else if ("SUCCESS".equals(state)) {

            try {
                jo = new JSONObject(resp.getStringResult());

                if (jo.has("redirectUrl")) {
                    trophy.setKeyInfo(String.valueOf(jo.get("redirectUrl")));
                }

                trophy.setResponse(jo.toString(2));
                trophy.setState(1);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return trophy;
    }

    public Trophy call(String uri, BaseBO bo) {
        return call(uri, convert2Map(bo));
    }

    /**
     * convert BO to Map
     *
     * @param bo
     * @return
     */
    private Map<String, String> convert2Map(BaseBO bo) {

        Map<String, String> params = new HashMap<String, String>(0);
        try {
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(bo);
            for (int i = 0; i < descriptors.length; i++) {
                String name = descriptors[i].getName();
                if (!"class".equals(name)) {
                    params.put(name, String.valueOf(propertyUtilsBean.getNestedProperty(bo, name)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.debug("[convert BO to MAP]\n" + params);

        return params;
    }

    /**
     * insert requestNo, merchantNo if non-exist
     *
     * @param paraMap
     */
    private void insertDefaultParameters(Map<String, String> paraMap) {

        if (paraMap.containsKey("requestNo")) {
            String requestNo = paraMap.get("requestNo");

            if (PayplusUtil.isNull(requestNo))
                paraMap.put("requestNo", PayplusUtil.genRequestNo());
        }

        if (paraMap.containsKey("merchantNo")) {
            String merchantNo = paraMap.get("merchantNo");

            if (PayplusUtil.isNull(merchantNo))
                paraMap.put("merchantNo", PayplusConfig.merchantNo);
        }

    }

    private void setUpYOPRequest(YopRequest request, Map<String, String> paraMap) {

        insertDefaultParameters(paraMap);

        Set keys = paraMap.keySet();
        Iterator it = keys.iterator();

        while (it.hasNext()) {
            String key = (String) it.next();
            String value = paraMap.get(key);
            request.addParam(key, value == null || value == "null" ? "" : value);
        }
    }

    private void convert2Map(Field[] fields, Map paraMap, Class subBO, BaseBO bo) {
        for (Field f : fields) {
            String methodName = f.getName();
            String getter = "get" + methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
            Object value = null;
            try {
                Method m = subBO.getMethod(getter, new Class[]{});
                value = m.invoke(bo, new Object[]{});
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            paraMap.put(methodName, String.valueOf(value));
        }
    }

}
