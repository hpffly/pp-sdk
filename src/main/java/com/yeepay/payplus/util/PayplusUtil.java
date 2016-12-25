package com.yeepay.payplus.util;

import com.yeepay.g3.utils.common.json.JSONException;
import com.yeepay.g3.utils.common.json.JSONObject;
import com.yeepay.payplus.core.entity.Trophy;
import com.yeepay.payplus.exception.QRCodeException;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Marco on 16/12/2016.
 */
public class PayplusUtil {

    private static Logger logger = Logger.getLogger(PayplusUtil.class);

    /**
     * TODO 显示相关文档、函数说明
     *
     * @return
     * @description 如果用户不填写, 那么自动生成一个RequestNo, 与业务关联。
     */
    public static String genRequestNo() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        return uuid;
    }

    public static void main(String[] args) {
        System.out.println(genRequestNo());
    }

    /**
     * <p>convert map to json string</p>
     *
     * @param map
     * @return a json string which is converted from a map.
     */
    public static String convert2JsonString(Map<String, String> map) {

        JSONObject jo = new JSONObject();

        Set keys = map.keySet();

        Iterator it = keys.iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            String value = map.get(key);

            try {
                jo.put(key, value);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jo.toString();
    }

    /**
     * 获取支付+规定的时间格式
     *
     * @param date
     * @return
     */
    public static String getFormatDateString(Date date) {
        DateFormat format = new SimpleDateFormat(PayplusConfig.DATE_FORMAT);
        return format.format(date);
    }

    //TODO 其他类型判断完善
    public static Boolean isNull(Object obj) {

        if (obj == null)
            return true;

        if (obj instanceof String) {
            String str = String.valueOf(obj);
            if (("null").equals(str) || "".equals(str))
                return true;
            else
                return false;
        } else {
            logger.error("other class type: " + obj.getClass().getName());
            return false;
        }
    }

    public static void generateQRCodeImage(Trophy trophy, String path) {

        if (trophy == null || trophy.getKeyInfo() == null || "".equals(trophy.getKeyInfo()) || trophy.getKeyInfo().contains("https://")) {
            throw new QRCodeException("\n\nHi buddy, please kindly check: \n 1, The request got correct return. \n 2, The service you called will send you QR code back. \n");
        }

        String hexImgString = trophy.getKeyInfo();

        //generate QRCode image
        byte[] b = hex2byte(hexImgString);
        ByteArrayInputStream in = new ByteArrayInputStream(b);
        BufferedImage image = null;
        try {
            image = ImageIO.read(in);
            ImageIO.write(image, "jpg", new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static byte[] hex2byte(String s) {
        byte[] src = s.toLowerCase().getBytes();
        byte[] ret = new byte[src.length / 2];
        for (int i = 0; i < src.length; i += 2) {
            byte hi = src[i];
            byte low = src[i + 1];
            hi = (byte) ((hi >= 'a' && hi <= 'f') ? 0x0a + (hi - 'a')
                    : hi - '0');
            low = (byte) ((low >= 'a' && low <= 'f') ? 0x0a + (low - 'a')
                    : low - '0');
            ret[i / 2] = (byte) (hi << 4 | low);
        }
        return ret;
    }
}
