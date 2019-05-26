package com.yingsu.jxc.controller;

import com.sun.org.apache.regexp.internal.RE;
import com.yingsu.jxc.entity.ResultBody;
import com.yingsu.jxc.entity.TTeacher;
import com.yingsu.jxc.entity.TUser;
import com.yingsu.jxc.service.ITeacherService;
import com.yingsu.jxc.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private ITeacherService teacherService;

    /**
     * 获取教师列表
     * @param session
     * @return
     */
    @RequestMapping("/getList")
    @ResponseBody
    public ResultBody getTeacherList(HttpSession session){
        ResultBody resultBody = new ResultBody();
        try {
            TUser user = (TUser) session.getAttribute(Constant.USER_INFO);
            if (user != null) {
                Integer bussId = user.getBussesserId();
                resultBody = teacherService.getTeacherList(bussId);
            } else {
                resultBody.setResultCode(-100);
            }
        }catch (Exception e){
            resultBody.setResultCode(-1);
            resultBody.setResultMsg(Constant.ERROR_SYS_MSG);
        }
        return resultBody;
    }

    /**
     * 获取教师详情
     * @return
     */
    @RequestMapping("/getTeacher")
    @ResponseBody
    public ResultBody getTeacher(Integer teacherId){
        ResultBody resultBody = new ResultBody();
        try {
            resultBody = teacherService.getTeacher(teacherId);
        }catch (Exception e){
            resultBody.setResultCode(-1);
            resultBody.setResultMsg(Constant.ERROR_SYS_MSG);
        }
        return resultBody;
    }

    /**
     * 添加教师
     * @param request
     * @param session
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public ResultBody fileUpload(HttpServletRequest request, HttpSession session){
        ResultBody resultBody = new ResultBody();
        Integer bussId = null;
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
                TUser user = (TUser) session.getAttribute(Constant.USER_INFO);
                if (user != null) {
                    bussId = user.getBussesserId();
                }
                // 原始名称
                String oldFileName = teacherImg.getOriginalFilename(); // 获取上传文件的原名
                // 存储图片的虚拟本地路径（这里需要配置tomcat的web模块路径，双击猫进行配置）
                String saveFilePath = Constant.TEACHER_URL;
                // 上传图片
                if (teacherImg != null && oldFileName != null && oldFileName.length() > 0) {
                    newFileName = "teacher-img-" + new Date().getTime() / 1000 + oldFileName.substring(oldFileName.lastIndexOf("."));
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
            teacher.setBussesserId(bussId);
            teacher.setTeacherName(teacherName);
            teacher.setTeacherIntroduce(teacherInfo);
            teacher.setTeacherLogo(newFileName);
            teacherService.addTeacher(teacher);
        }catch (Exception e){
            resultBody.setResultCode(-1);
            resultBody.setResultMsg("系统异常！");
        }
        return resultBody;
    }

    /**
     * 删除教师
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResultBody deleteTeacher(String id){
        ResultBody resultBody = new ResultBody();
        try {
            resultBody = teacherService.deleteTeacher(Integer.parseInt(id));
        }catch (Exception e){
            resultBody.setResultCode(-1);
            resultBody.setResultMsg("系统异常！");
        }
        return resultBody;
    }

}
