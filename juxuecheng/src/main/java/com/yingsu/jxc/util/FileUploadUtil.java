package com.yingsu.jxc.util;

import com.aliyun.oss.OSSClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public class FileUploadUtil {

    public static void upload(MultipartFile file, String newFileName, String url){
        try {
            InputStream input = file.getInputStream();
            // 创建OSSClient实例
            OSSClient ossClient = new OSSClient(Constant.ENDPOINT, Constant.ACCESSKEYID, Constant.ACCESSKEYSECRET);
            // 上传文件流
            ossClient.putObject(Constant.BUCKETNAME, url + newFileName, input);
            ossClient.shutdown();
        }catch (Exception e){

        }
    }
}
