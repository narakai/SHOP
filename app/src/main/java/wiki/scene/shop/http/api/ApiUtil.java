package wiki.scene.shop.http.api;

import java.util.HashMap;

import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.utils.MD5Util;

/**
 * Case By:API
 * package:wiki.scene.shop.http.api
 * Author：scene on 2017/6/27 12:51
 */

public class ApiUtil {
    private static final String SIGN_KEY = "045448f765b0c0592563123a2652fb63";
    public static final String API_PRE = "http://119.23.110.78:8082";

    //获取APP的配置文件
    public static final String APP_CONFIG = "/user/setting";
    public static final String APP_CONFIG_TAG = "APP_CONFIG_TAG";
    //登录
    public static final String LOGIN = "/user/login";
    public static final String LOGIN_TAG = "LOGIN_TAG";
    //注册
    public static final String REGISTER = "/user/reg";
    public static final String REGISTER_TAG = "REGISTER_TAG";
    //获取注册验证码
    public static final String GET_VERIFICATION_CODE = "/user/code";
    public static final String GET_VERIFICATION_CODE_TAG = "GET_VERIFICATION_CODE_TAG";
    //校验注册验证码
    public static final String CHECK_CODE = "/user/check_code";
    public static final String CHECK_CODE_TAG = "CHECK_CODE_TAG";
    //编辑个人资料
    public static final String UPDATE_USERINFO = "/personal/profile/edit";
    public static final String UPDATE_USERINFO_TAG = "UPDATE_USERINFO_TAG";
    //修改头像
    public static final String UPDATE_USER_AVATER = "/personal/profile/upload_avatar/";
    public static final String UPDATE_USER_AVATER_TAG = "UPDATE_USER_AVATER_TAG";
    //获取收货地址
    public static final String GET_ALL_ADDRESS = "/personal/address";
    public static final String GET_ALL_ADDRESS_TAG = "GET_ALL_ADDRESS_TAG";
    //新增或者编辑收货地址
    public static final String ADD_OR_EDIT_ADDRESS = "/personal/address/edit";
    public static final String ADD_OR_EDIT_ADDRESS_TAG = "ADD_OR_EDIT_ADDRESS_TAG";
    //删除收货地址
    public static final String DELETE_RECEIVER_ADDRESS = "/personal/address/delete";
    public static final String DELETE_RECEIVER_ADDRESS_ATG = "DELETE_RECEIVER_ADDRESS_ATG";
    //设置默认收货地址
    public static final String SET_DEFAULT_ADDRESS = "/personal/address/default";
    public static final String SET_DEFAULT_ADDRESS_TAG = "SET_DEFAULT_ADDRESS_TAG";
    //夺宝首页
    public static final String INDEX = "/index";
    public static final String INDEX_TAG = "INDEX_TAG";
    //购物车
    public static final String CAR = "/cart";
    public static final String CAR_TAG = "CAR_TAG";
    //加入或者修改购物车
    public static final String JOIN_CAR = "/cart/edit";
    public static final String JOIN_CAR_TAG = "JOIN_CAR_TAG";
    //删除购物车商品
    public static final String CART_DELETE="/cart/delete";
    public static final String CART_DELETE_TAG="CART_DELETE_TAG";
    //创建订单
    public static final String CREATE_ORDER="/cycle/order";
    public static final String CREATE_ORDER_TAG="CREATE_ORDER_TAG";
    //获取支付信息
    public static final String PAY="/cycle/order/pay";
    public static final String PAY_TAG="PAY_TAG";
    //首页的中奖提示
    public static final String WINNER_NOTICE="/winning_notice";
    public static final String WINNER_NOTICE_TAG="WINNER_NOTICE_TAG";



    /**
     * Case By:创建参数基础信息
     * Author: scene on 2017/5/19 13:19
     */
    public static HashMap<String, String> createParams() {
        HashMap<String, String> params = new HashMap<>();
        long timestamp = System.currentTimeMillis();
        params.put("agent_id", ShopApplication.CHANNEL_ID + "");
        params.put("timestamp", timestamp + "");
        params.put("signature", getSign(ShopApplication.CHANNEL_ID + "", timestamp + ""));
        params.put("app_type", "1");
        params.put("uuid", ShopApplication.UUID);
        params.put("version", ShopApplication.versionCode + "");
        return params;
    }

    /**
     * Case By:获取sign
     * Author: scene on 2017/5/19 13:19
     */
    private static String getSign(String agent_id, String timestamp) {
        return MD5Util.string2Md5(MD5Util.string2Md5(agent_id + timestamp + 1 + ShopApplication.UUID + ShopApplication.versionCode, "UTF-8") + SIGN_KEY, "UTF-8");
    }
}
