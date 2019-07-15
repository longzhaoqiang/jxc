package com.yingsu.jxc.util;

import java.util.UUID;

public class Constant {

    // 英速科技bussId
    public static int BUSS_ID = 124;

    // 圖片保存路徑
    // public static String TEACHER_URL = "C:/workspace/image";
    public static String TEACHER_URL = "/data/images/TeacherImg";
    // 商家首页图片路径
    public static String BUSSINDEX_URL = "/data/images/BussImg";

    // 获取openId
    public static String OPENID_PATH = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa2fbe7ca7c970259&redirect_uri=http://www.juxuecheng.com/user/home/&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";

    // 获取微信用户详情
    public static String WXUSERINFO_PATH = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa2fbe7ca7c970259&redirect_uri=http://www.juxuecheng.com/user/login/&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";

    // 用户信息存进session
    public static String USER_INFO = "userInfo";

    // 用户openId
    public static String USER_OPENID = "openId";

    // 错误码
    public static Integer ERROR_CODE = -100;

    // 数据存在码
    public static Integer EXIST_CODE = 100;

    // 系统异常
    public static String ERROR_SYS_MSG = "网络异常";

    // 插入数据异常
    public static String ERROR_INSERT_MSG = "插入数据出错";

    // 插入数据异常
    public static String EXIST_MSG = "当前数据已存在";

    // 注册错误
    public static String ERROR_REGISTER = "你已经注册过，请登录！";

    // 登录错误
    public static String ERROR_LOGIN = "用户名密码不一致，请重新输入";

    // 没有数据
    public static String NO_DATA = "没有数据";

    public static final String JWT_ID = UUID.randomUUID().toString();
    public static final String JWT_AUTH = "jxc";
    /**
     * 加密密文
     */
    public static final String JWT_SECRET = "juxuecheng_2019_hh";
    public static final int JWT_TTL = 15*60*1000;  //millisecond
}
