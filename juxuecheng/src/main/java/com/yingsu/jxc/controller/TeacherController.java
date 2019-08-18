package com.yingsu.jxc.controller;

import com.yingsu.jxc.entity.ResultBody;
import com.yingsu.jxc.entity.TTeacher;
import com.yingsu.jxc.entity.TUser;
import com.yingsu.jxc.service.ITeacherService;
import com.yingsu.jxc.util.Constant;
import com.yingsu.jxc.util.FileUploadUtil;
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
     *
     * @param bussId
     * @return
     */
    @RequestMapping("/getList")
    @ResponseBody
    public ResultBody getTeacherList(Integer bussId) {
        ResultBody resultBody = new ResultBody();
        try {
            resultBody = teacherService.getTeacherList(bussId);
        } catch (Exception e) {
            resultBody.setResultCode(-1);
            resultBody.setResultMsg(Constant.ERROR_SYS_MSG);
        }
        return resultBody;
    }

    /**
     * 获取教师详情
     *
     * @return
     */
    @RequestMapping("/getTeacher")
    @ResponseBody
    public ResultBody getTeacher(Integer teacherId) {
        ResultBody resultBody = new ResultBody();
        try {
            resultBody = teacherService.getTeacher(teacherId);
        } catch (Exception e) {
            resultBody.setResultCode(-1);
            resultBody.setResultMsg(Constant.ERROR_SYS_MSG);
        }
        return resultBody;
    }

    /**
     * 添加教师
     *
     * @param request
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public ResultBody fileUpload(HttpServletRequest request, Integer bussId) {
        ResultBody resultBody = new ResultBody();
        try {
            String teacherName = request.getParameter("teacher_name").trim();
            String teacherInfo = request.getParameter("teacher_introduce").trim();
            String teacherDate = request.getParameter("teach_date").trim();
            teacherDate = teacherDate.substring(0,teacherDate.length()-1);
            String teacherSubject = request.getParameter("teacher_subject").trim();

            String newFileName = null;
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile teacherImg = multipartRequest.getFile("file");
            if (teacherImg == null || "".equals(teacherImg)) {
                // 默认图片
                newFileName = "teacher-img-1566103582jpg";
            } else {
                String oldFileName = teacherImg.getOriginalFilename();
                String[] imgarr = oldFileName.split("\\.");
                newFileName = "teacher-img-" + new Date().getTime() / 1000 + imgarr[imgarr.length-1];
                FileUploadUtil.upload(teacherImg,newFileName,"teacher_img/");
            }
            // 存入数据库
            TTeacher teacher = new TTeacher();
            teacher.setBussesserId(bussId);
            teacher.setTeacherName(teacherName);
            teacher.setTeacherIntroduce(teacherInfo);
            teacher.setTeacherLogo(newFileName);
            teacher.setTeachDate(Integer.parseInt(teacherDate));
            teacher.setTeacherSubject(teacherSubject);
            teacherService.addTeacher(teacher);
        } catch (Exception e) {
            resultBody.setResultCode(-1);
            resultBody.setResultMsg("网络异常，请稍后再试！");
        }
        return resultBody;
    }

    /**
     * 删除教师
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResultBody deleteTeacher(String id) {
        ResultBody resultBody = new ResultBody();
        try {
            resultBody = teacherService.deleteTeacher(Integer.parseInt(id));
        } catch (Exception e) {
            resultBody.setResultCode(-1);
            resultBody.setResultMsg("系统异常！");
        }
        return resultBody;
    }

}
