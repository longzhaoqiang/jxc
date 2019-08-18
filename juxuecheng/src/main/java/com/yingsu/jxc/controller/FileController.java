package com.yingsu.jxc.controller;

import com.aliyun.oss.OSSClient;
import com.yingsu.jxc.entity.ResultBody;
import com.yingsu.jxc.entity.TBussesser;
import com.yingsu.jxc.entity.TUser;
import com.yingsu.jxc.service.IBussesserService;
import com.yingsu.jxc.util.Constant;
import com.yingsu.jxc.util.FileUploadUtil;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.InputStream;
import java.util.*;

@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private IBussesserService bussesserService;

    @RequestMapping("/upload")
    @ResponseBody
    public ResultBody fileUpload(HttpServletRequest request, HttpSession session) {
        ResultBody resultBody = new ResultBody();
        try {
            TBussesser bussesser = (TBussesser) session.getAttribute("bussId");
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Map<String,MultipartFile> fileMap = multipartRequest.getFileMap();
            MultipartFile file = fileMap.get("images");
            String fileName = file.getOriginalFilename();
            String[] fileNameArr = fileName.split("\\.");
            int random = (int)(Math.random()*900 + 100);
            String randoms = Integer.toString(random);
            String newFileName = "buss-img-" + new Date().getTime()/1000 + "_" + randoms +"." + fileNameArr[fileNameArr.length-1];
            FileUploadUtil.upload(file,newFileName,"buss_index/");
            bussesserService.uploadImg(bussesser.getId(),newFileName);
        } catch (Exception e) {
            resultBody.setResultCode(-1);
            resultBody.setResultMsg("系统异常！" + e);
        }
        return resultBody;
    }
}
