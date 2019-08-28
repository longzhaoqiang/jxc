package com.yingsu.jxc.weixin;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.UUID;

public class WecharShare {

    private static Logger log = LoggerFactory.getLogger(WecharShare.class);

    private String appId = WeixinConfig.APPID;
    private String secret = WeixinConfig.SECRET;
    private String requestUrl_token = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+secret;
    public String requestUrl_ticket = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=";
    private String url = "www.juxuecheng.com";

    /**
     * 获得Token
     *
     * @return
     */
    public String getToken() {
        JSONObject jsonObject = httpRequest(requestUrl_token);
        log.info("微信分享token: " + jsonObject.toString());
        String token = jsonObject.getString("access_token");
        return token;
    }

    /**
     * 获得Token
     *
     * @return
     */
    public String getTicket(String token) {
        requestUrl_ticket = requestUrl_ticket + token + "&type=jsapi";
        JSONObject jsonObject = httpRequest(requestUrl_ticket);
        String ticket = jsonObject.getString("ticket");
        return ticket;

    }

    // 发送http请求
    private JSONObject httpRequest(String requestUrl) {
        String line = null;
        StringBuffer buffer = new StringBuffer();
        JSONObject jsonObject = null;
        StringBuffer positionName = new StringBuffer();
        try {
            URL url = new URL(requestUrl);
            //得到connection对象。
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //设置请求方式
            connection.setRequestMethod("GET");
            //连接
            connection.connect();
            //得到响应码
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                //得到响应流
                InputStream inputStream = connection.getInputStream();
                //获取响应
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                reader.close();
                //该干的都干完了,记得把连接断了
                connection.disconnect();
                String callback = buffer.toString();
                jsonObject = JSONObject.fromObject(callback);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 签名
     *
     * @param timestamp
     * @return
     */
    public String signature(String jsapi_ticket, String timestamp, String noncestr, String url) {
        jsapi_ticket = "jsapi_ticket=" + jsapi_ticket;
        timestamp = "timestamp=" + timestamp;
        noncestr = "noncestr=" + noncestr;
        url = "url=" + url;
        String[] arr = new String[]{jsapi_ticket, noncestr, timestamp, url};
        // 将token、timestamp、nonce,url参数进行字典序排序
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
            if (i != arr.length - 1) {
                content.append("&");
            }
        }
        MessageDigest md = null;
        String tmpStr = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        content = null;
        return tmpStr;
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {

        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];

        String s = new String(tempArr);
        return s;
    }

    /**
     * 获取当前时间戳
     *
     * @paramx
     */
    public String getCurrentTimeMillis() {
        long time = System.currentTimeMillis();
        time = time / 1000;
        return "" + time;
    }

    /**
     * 后去随机数
     *
     * @param
     */
    public String create_nonce_str() {
        return UUID.randomUUID().toString();
    }
}
