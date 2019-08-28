package com.yingsu.jxc.weixin;

import com.yingsu.jxc.entity.TWxShare;
import com.yingsu.jxc.service.IWxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
@RequestMapping("jxcIndex")
public class WxShare {

    @Autowired
    private IWxService wxService;

    @RequestMapping("/getWxShareInfo")
    @ResponseBody
    public HashMap<String, Object> getWxShareInfo(String param1) {
        HashMap<String, Object> hashMap = new HashMap<>();
        TWxShare wxShare = wxService.getWxShare(Integer.parseInt(param1));
        hashMap.put("title",wxShare.getTitle());
        hashMap.put("desc",wxShare.getDesc());
        return hashMap;
    }

    @RequestMapping("/share")
    @ResponseBody
    public HashMap<String, Object> share(HttpServletRequest request) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        String appId = WeixinConfig.APPID;

        String urlTemp = "http://" + request.getServerName() + request.getContextPath();
        String urlpath = "http://" + request.getServerName();
        String appUrl = request.getParameter("url");

        WecharShare wecharShare = new WecharShare();
        String token = wecharShare.getToken();
        String accessTicket = wecharShare.getTicket(token);
        String noncestr = wecharShare.create_nonce_str();
        String timestamp = wecharShare.getCurrentTimeMillis();
        String signature = wecharShare.signature(accessTicket, timestamp, noncestr, appUrl);

        hashMap.put("appId", appId);
        hashMap.put("timestamp", timestamp);
        hashMap.put("noncestr", noncestr);
        hashMap.put("signature", signature);
        return hashMap;
    }
}
