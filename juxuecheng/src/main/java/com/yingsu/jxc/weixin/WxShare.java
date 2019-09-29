package com.yingsu.jxc.weixin;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yingsu.jxc.entity.ResultBody;
import com.yingsu.jxc.entity.TWxShare;
import com.yingsu.jxc.service.IWxService;
import com.yingsu.jxc.util.Constant;
import com.yingsu.jxc.util.FileUploadUtil;

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
        if (wxShare != null) {
            hashMap.put("title", wxShare.getTitle());
            hashMap.put("content", wxShare.getContent());
            hashMap.put("imgUrl", wxShare.getImgUrl());
        }
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

    @RequestMapping("/setWxShare")
    @ResponseBody
    public ResultBody set(HttpServletRequest request, TWxShare wxShare) {
        ResultBody resultBody = new ResultBody();
        try {
            String newFileName = null;
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile shareImg = multipartRequest.getFile("file");
            if (shareImg != null) {
                String oldFileName = shareImg.getOriginalFilename();
                String[] imgarr = oldFileName.split("\\.");
                newFileName = "share-img-" + System.currentTimeMillis() + "." + imgarr[imgarr.length - 1];
                FileUploadUtil.upload(shareImg,newFileName,"buss_index/wx_share/");
            }
            wxShare.setImgUrl(newFileName);
            int result = wxService.setWxShare(wxShare);
            if (result != 1) {
                resultBody.setResultCode(Constant.ERROR_CODE);
                resultBody.setResultMsg(Constant.ERROR_INSERT_MSG);
            }
        } catch (Exception e) {
            resultBody.setResultCode(Constant.ERROR_CODE);
            resultBody.setResultMsg(Constant.ERROR_SYS_MSG + e);
        }
        return resultBody;
    }
}
