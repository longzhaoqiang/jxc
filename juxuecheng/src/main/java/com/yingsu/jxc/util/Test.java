package com.yingsu.jxc.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Test {
    public static void main(String[] args){
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<Object> objectResponseEntity = restTemplate.getForEntity("http://t.weather.sojson.com/api/weather/city/101030100", Object.class);
            // 获取结果状态,如果=200则成功
            int status = objectResponseEntity.getStatusCodeValue();
            if (status == 200) {
                // 获取返回结果
                Object object = objectResponseEntity.getBody();
                System.out.println(JSONObject.toJSONString(object));
            } else {
                // 请求失败
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
