package com.yingsu.jxc.controller;

import com.yingsu.jxc.entity.ResultBody;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

@Controller
public class FileController {
    @RequestMapping("file/upload")
    @ResponseBody
    public ResultBody fileUpload(HttpServletRequest request){
        ResultBody resultBody = new ResultBody();
        MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file");
        try {
            // 原始名称
            String oldFileName = file.getOriginalFilename(); // 获取上传文件的原名
            // 存储图片的虚拟本地路径（这里需要配置tomcat的web模块路径，双击猫进行配置）
            String saveFilePath = "E://picture";
            // 上传图片
            if (file != null && oldFileName != null && oldFileName.length() > 0) {
                String newFileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
                File newFile = new File(saveFilePath + "\\" + newFileName);
                //插入数据库
                // String result = fileUploadService.uploadPictrue(session,newFileName);
                // 将内存中的数据写入磁盘
                file.transferTo(newFile);
                resultBody.setResultCode(1);
            } else {
                return resultBody;
            }
        }catch (Exception e){
            resultBody.setResultCode(-1);
            resultBody.setResultMsg("系统异常！"+e);
        }
        return resultBody;
    }
}
