package com.yingsu.jxc.controller;

import com.yingsu.jxc.entity.ResultBody;
import com.yingsu.jxc.entity.TTeacher;
import com.yingsu.jxc.service.ITeacherService;
import com.yingsu.jxc.util.Constant;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private ITeacherService teacherService;

    @RequestMapping("/add")
    @ResponseBody
    public ResultBody fileUpload(HttpServletRequest request, HttpServletResponse response){
        ResultBody resultBody = new ResultBody();
        try {
            String teacherName = request.getParameter("teacher_name").trim();
            String teacherInfo = request.getParameter("teacher_info").trim();
            String newFileName = null;
            if (teacherName.equals("") || teacherName == null){
                resultBody.setResultCode(-1);
                resultBody.setResultMsg("请输入教师姓名");
                return resultBody;
            }
            //转型为MultipartHttpRequest(重点的所在)
            MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;
            //获得第1张图片（根据前台的name名称得到上传的文件）
            MultipartFile teacherImg  =  multipartRequest.getFile("file");
            if (teacherImg == null || "".equals(teacherImg)){
                newFileName = "teacherOragineImg";
            } else {
                // 原始名称
                String oldFileName = teacherImg.getOriginalFilename(); // 获取上传文件的原名
                // 存储图片的虚拟本地路径（这里需要配置tomcat的web模块路径，双击猫进行配置）
                String saveFilePath = Constant.PIC_URL;
                // 上传图片
                if (teacherImg != null && oldFileName != null && oldFileName.length() > 0) {
                    newFileName = "teacher-img" + new Date().getTime() / 1000 + oldFileName.substring(oldFileName.lastIndexOf("."));
                    // File newFile = new File(saveFilePath + "\\" + newFileName);
                    File newFile = new File(saveFilePath, newFileName);
                    // 将内存中的数据写入磁盘
                    teacherImg.transferTo(newFile);
                    resultBody.setResultCode(1);
                } else {
                    return resultBody;
                }
            }
            TTeacher teacher = new TTeacher();
            teacher.setTeacherName(teacherName);
            teacher.setTeacherIntroduce(teacherInfo);
            teacher.setTeacherLogo(newFileName);
            teacherService.addTeacher(teacher);
        }catch (Exception e){
            resultBody.setResultCode(-1);
            resultBody.setResultMsg("系统异常！"+e);
        }
        return resultBody;
    }
}
