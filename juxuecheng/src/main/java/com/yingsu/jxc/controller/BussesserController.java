package com.yingsu.jxc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yingsu.jxc.entity.ResultBody;
import com.yingsu.jxc.entity.TBussesser;
import com.yingsu.jxc.entity.TClasstype;
import com.yingsu.jxc.entity.TWeixinLogin;
import com.yingsu.jxc.service.IBussesserService;
import com.yingsu.jxc.service.IWxService;
import com.yingsu.jxc.util.Constant;
import com.yingsu.jxc.util.FileUploadUtil;

/**
 * Created by Administrator on 2018/8/23 0023.
 */
@Controller
@RequestMapping("/buss")
public class BussesserController {

    private static Logger log = LoggerFactory.getLogger(BussesserController.class);

    @Autowired
    private IBussesserService bussesserService;

    @Autowired
    private IWxService wxService;

    /**
     * 查看商家详情
     */
    @RequestMapping("/getBussInfo")
    @ResponseBody
    public ResultBody getBussInfo(Integer bussId) {
        ResultBody resultBody = new ResultBody();
        TBussesser bussesser = bussesserService.getBussById(bussId);
        if (bussesser != null) {
            resultBody.setResult(bussesser);
        } else {
            resultBody.setResultCode(0);
        }
        return resultBody;
    }

    /**
     * 我的信息--查看商家详情
     */
    @RequestMapping("/getBuss")
    @ResponseBody
    public ResultBody getBuss(Integer bussId) {
        ResultBody resultBody = new ResultBody();
        try {
            Map<String, String> map = bussesserService.getBuss(bussId);
            if (map != null) {
                resultBody.setResult(map);
            } else {
                resultBody.setResultCode(0);
            }
        }catch (Exception e){
            log.error("查询商家--我的信息也出错：" + e);
            resultBody.setResultCode(Constant.ERROR_CODE);
            resultBody.setResultMsg(Constant.ERROR_SYS_MSG);
        }

        return resultBody;
    }

    /**
     * 注册前查找教育类型
     *
     * @return
     */
    @RequestMapping("/getTypeList")
    @ResponseBody
    public ResultBody getTypeList() {
        ResultBody resultBody = new ResultBody();
        try {
            // 查询数据库获取列表
            List<TClasstype> list = bussesserService.getClassTypeList();
            if (list == null) {
                resultBody.setResultMsg(Constant.NO_DATA);
            } else {
                // 不为空，设置值
                resultBody.setResult(list);
            }
        } catch (Exception e) {
            resultBody.setResultCode(Constant.ERROR_CODE);
            resultBody.setResultMsg(Constant.ERROR_SYS_MSG);
        }
        return resultBody;
    }

    /**
     * 注册成商家
     *
     * @return
     */
    @RequestMapping("/addBuss")
    @ResponseBody
    public ResultBody addBuss(HttpServletRequest request, HttpSession session, TBussesser bussesser) {
        ResultBody resultBody = new ResultBody();
        try {
            String openId = (String) session.getAttribute("openId");
            log.info("注册用户时获取openId=" + openId);
            TWeixinLogin weixinLogin = wxService.getWeixinUser(openId);
            Integer uid = weixinLogin.getId();
            log.info("注册用户时获取id=" + uid);
            // 注册前先查询是否注册过
            TBussesser bussesser1 = bussesserService.getBussInfo(uid);
            if (bussesser1 != null) {
                resultBody.setResultCode(Constant.EXIST_CODE);
                resultBody.setResultMsg("您已经注册过，请不要重复注册");
                return resultBody;
            }
            String newFileName = "";
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile teacherImg = multipartRequest.getFile("file");
            if (teacherImg != null) {
                String oldFileName = teacherImg.getOriginalFilename();
                String[] imgarr = oldFileName.split("\\.");
                newFileName = "teacher-img-" + System.currentTimeMillis() + "." + imgarr[imgarr.length-1];
                FileUploadUtil.upload(teacherImg,newFileName,"buss_index/wechat_ewm/");
            }
            bussesser.setUserId(uid);
            bussesser.setWechat(newFileName);
            Integer result = bussesserService.addBuss(bussesser);
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

	/**
	 * 修改商家信息
	 * @param request
	 * @param session
	 * @param bussesser
	 * @return
	 */
	@RequestMapping("/updateInfo")
	@ResponseBody
	public ResultBody updateInfo(HttpServletRequest request, HttpSession session, TBussesser bussesser) {
		ResultBody resultBody = new ResultBody();
		try {
			String newFileName = "";
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile teacherImg = multipartRequest.getFile("file");
			if (teacherImg != null) {
				String oldFileName = teacherImg.getOriginalFilename();
				String[] imgarr = oldFileName.split("\\.");
				newFileName = "teacher-img-" + System.currentTimeMillis() + "." + imgarr[imgarr.length-1];
				FileUploadUtil.upload(teacherImg,newFileName,"buss_index/wechat_ewm/");
			}
			TBussesser bussesser1 = TBussesser.builder()
										.id(bussesser.getId())
										.bussName(bussesser.getBussName())
										.phone(bussesser.getPhone())
										.bussType(bussesser.getBussType())
										.address(bussesser.getAddress())
										.wechat(newFileName)
										.build();
			bussesserService.updateBuss(bussesser1);
		} catch (Exception e) {
			resultBody.setResultCode(Constant.ERROR_CODE);
			resultBody.setResultMsg(Constant.ERROR_SYS_MSG + e);
		}
		return null;
	}

    /**
     * 添加商家首页
     *
     * @return
     */
    @RequestMapping("/addBussIndex")
    @ResponseBody
    public ResultBody addBussIndex(TBussesser bussesser, HttpSession session) {
        ResultBody resultBody = new ResultBody();
        try {
            bussesserService.updateBuss(bussesser);
        } catch (Exception e) {
            resultBody.setResultCode(Constant.ERROR_CODE);
            resultBody.setResultMsg(Constant.ERROR_SYS_MSG);
        }
        return resultBody;
    }

    /**
     * 检查是否注册成商家
     *
     * @return
     */
    @RequestMapping("/checkRegister")
    @ResponseBody
    public ResultBody checkRegister(HttpSession session) {
        ResultBody resultBody = new ResultBody();
        try {
	        // String openId = (String) session.getAttribute("openId");
            // log.info("获取openId,{}",openId);
            String openId = "oO3ww1XNuGQW6uvtsU8SrU180340";
            session.setAttribute("openId", openId);
            TWeixinLogin weixinLogin = wxService.getWeixinUser(openId);
            if (weixinLogin == null) {
                resultBody.setResultCode(-1);
                log.info("进入首页时检查openId=" + openId + "还没有信息录入数据库");
                return resultBody;
            }
            TBussesser bussesser = bussesserService.getBussInfoOpenId(openId);
            if (bussesser != null) {
                // 用户进入Home页时验证是否有微信注册
                session.setAttribute("bussId", bussesser);
                resultBody.setResultCode(1);
                resultBody.setResultMsg(bussesser.getId().toString());
            } else {
                resultBody.setResultCode(0);
                resultBody.setResultMsg("您尚未注册过，立即注册");
            }
        } catch (Exception e) {
            resultBody.setResultCode(Constant.ERROR_CODE);
            resultBody.setResultMsg(Constant.ERROR_SYS_MSG + e);
        }

        return resultBody;
    }
}
