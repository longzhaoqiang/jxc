package com.yingsu.jxc.util;

import java.util.UUID;

public class Constant {

    // 英速科技bussId
    public static int BUSS_ID = 124;

    // 阿里云
    public static String ENDPOINT = "http://oss-cn-shanghai.aliyuncs.com";
    public static String ACCESSKEYID = "LTAIjZAE2ZVS3jGu";
    public static String ACCESSKEYSECRET = "m0hQIaDyZ8Pd2N7aah5i49eA75mjcx";
    public static String BUCKETNAME = "yingsu-jxc";
    public static String KEY = "jxc/";

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
