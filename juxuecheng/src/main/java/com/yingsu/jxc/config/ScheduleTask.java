package com.yingsu.jxc.config;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.yingsu.jxc.util.DateUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Configuration
@EnableScheduling
public class ScheduleTask {
    private final String PHONE = "15216846976";
    private final String TEMPLATECODE = "SMS_172601808";
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Scheduled(cron = "0 17 9 * * ?")
    private void configureTasks() {
        String first = "2018-9-17";
        String second = DateUtils.getReqDate(new Date());
        int dayCount = 0;
        try {
            Date firstDate = format.parse(first);
            Date secondDate = format.parse(second);
            dayCount = longOfTwoDate(firstDate, secondDate);
        } catch (Exception e){

        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","媳妇儿");
        jsonObject.put("one","糟老头子好得很");
        jsonObject.put("count",dayCount);

        sendMsm(this.PHONE, this.TEMPLATECODE, jsonObject.toJSONString());
    }

    public void sendMsm(String phone, String templateCode, String param){
        DefaultProfile profile = DefaultProfile.getProfile("default", "LTAIjZAE2ZVS3jGu", "m0hQIaDyZ8Pd2N7aah5i49eA75mjcx");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "英速科技");
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", param);
        try {
            CommonResponse response = client.getCommonResponse(request);
        } catch (Exception e) {

        }
    }

    public static int  longOfTwoDate(Date first,Date second) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(first);
        int cnt = 0;
        while(calendar.getTime().compareTo(second)!=0){
            calendar.add(Calendar.DATE, 1);
            cnt++;
        }
        return cnt;
    }
}
