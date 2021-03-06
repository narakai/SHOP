package wiki.scene.shop.http.api;

import com.blankj.utilcode.util.AppUtils;

import java.util.HashMap;

import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.utils.MD5Util;
import wiki.scene.shop.utils.NetTimeUtils;

/**
 * Case By:API
 * package:wiki.scene.shop.http.api
 * Author：scene on 2017/6/27 12:51
 */

public class ApiUtil {
    private static final String SIGN_KEY = "045448f765b0c0592563123a2652fb63";
    public static final String API_PRE = "http://api.banjiasc.com";

    //获取APP的配置文件
    public static final String APP_CONFIG = "/common";
    public static final String APP_CONFIG_TAG = "APP_CONFIG_TAG";
    //登录
    public static final String LOGIN = "/user/login";
    public static final String LOGIN_TAG = "LOGIN_TAG";
    //QQ登录
    public static final String LOGIN_QQ = "/user/qq_login";
    public static final String LOGIN_QQ_TAG = "LOGIN_QQ_TAG";
    //微信登录
    public static final String LOGIN_WX = "/user/wx_login";
    public static final String LOGIN_WX_TAG = "LOGIN_WX_TAG";

    //注册
    public static final String REGISTER = "/user/reg";
    public static final String REGISTER_TAG = "REGISTER_TAG";
    //QQ注册
    public static final String REGISTER_QQ = "/user/qq_reg";
    public static final String REGISTER_QQ_TAG = "REGISTER_QQ_TAG";
    //微信注册
    public static final String REGISTER_WX = "/user/wx_reg";
    public static final String REGISTER_WX_TAG = "REGISTER_WX_TAG";
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
    //获取最新一期数据
    public static final String CURRENT_CYCLE = "/business/current_cycle";
    public static final String CURRENT_CYCLE_TAG = "CURRENT_CYCLE_TAG";
    //夺宝首页
    public static final String INDEX = "/business";
    public static final String INDEX_TAG = "INDEX_TAG";
    //购物车
    public static final String CAR = "/cart";
    public static final String CAR_TAG = "CAR_TAG";
    //加入或者修改购物车
    public static final String JOIN_CAR = "/cart/edit";
    public static final String JOIN_CAR_TAG = "JOIN_CAR_TAG";
    //删除购物车商品
    public static final String CART_DELETE = "/cart/delete";
    public static final String CART_DELETE_TAG = "CART_DELETE_TAG";
    //创建订单
    public static final String CREATE_ORDER = "/cycle/order";
    public static final String CREATE_ORDER_TAG = "CREATE_ORDER_TAG";
    //获取支付信息
    public static final String PAY = "/cycle/order/pay";
    public static final String PAY_TAG = "PAY_TAG";
    //首页的中奖提示
    public static final String WINNER_NOTICE = "/winning_notice";
    public static final String WINNER_NOTICE_TAG = "WINNER_NOTICE_TAG";
    //产品详情
    public static final String GOODS_DETAIL = "/business/detail";
    public static final String GOODS_DETAIL_TAG = "GOODS_DETAIL_TAG";
    //弹幕
    public static final String DANMU = "/cycle/danmu";
    public static final String DANMU_TAG = "DANMU_TAG";
    //夺宝记录
    public static final String MINE_ORDER = "/personal/order";
    public static final String MINE_ORDER_TAG = "MINE_ORDER_TAG";
    //中奖记录
    public static final String WIN_RECORD = "/personal/order/win";
    public static final String WIN_RECORD_TAG = "WIN_RECORD_TAG";
    //订单中去支付
    public static final String ORDER_DETAIL_TO_PAY = "/cycle/order/detail";
    public static final String ORDER_DETAIL_TO_PAY_TAG = "ORDER_DETAIL_TO_PAY_TAG";
    //收藏
    public static final String ADD_COLLECTION = "/personal/collection/add";
    public static final String ADD_COLLECTION_TAG = "ADD_COLLECTION_TAG";
    //取消收藏
    public static final String CANCEL_COLLECTION = "/personal/collection/delete";
    public static final String CANCEL_COLLECTION_TAG = "CANCEL_COLLECTION_TAG";
    //收藏列表
    public static final String COLLECTION_LIST = "/personal/collection";
    public static final String COLLECTION_LIST_TAG = "COLLECTION_LIST_TAG";
    //最新揭晓
    public static final String NEWEST_OPEN = "/cycle/new_opened";
    public static final String NEWEST_OPEN_TAG = "NEWEST_OPEN_TAG";
    //我的晒单列表
    public static final String MINE_SHARE_LIST = "/personal/show";
    public static final String MINE_SHARE_LIST_TAG = "MINE_SHARE_LIST_TAG";
    //晒单列表
    public static final String SHARE_LIST = "/cycle/show";
    public static final String SHARE_LIST_TAG = "SHARE_LIST_TAG";
    //添加心愿产品
    public static final String ADD_WISH_GOODS = "/personal/feedback/wish";
    public static final String ADD_WISH_GOODS_TAG = "ADD_WISH_GOODS_TAG";
    //问题反馈
    public static final String BUG_FEED_BACK = "/personal/feedback";
    public static final String BUG_FEED_BACK_TAG = "BUG_FEED_BACK_TAG";
    //我的中奖记录
    public static final String MINE_WIN_LIST = "/personal/order/win";
    public static final String MINE_WIN_LIST_TAG = "MINE_WIN_LIST_TAG";
    //我的收藏
    public static final String MINE_COLLECTION = "/personal/collection";
    public static final String MINE_COLLECTION_TAG = "MINE_COLLECTION_TAG";
    //10元夺宝
    public static final String PRICE_10_INDIANA = "/cycle/ten";
    public static final String PRICE_10_INDIANA_TAG = "PRICE_10_INDIANA_TAG";
    //秒开夺宝
    public static final String SECOND_INDIANA = "/cycle/quick";
    public static final String SECOND_INDIANA_TAG = "SECOND_INDIANA_TAG";
    //晒单
    public static final String SHARE_ORDER = "/personal/show/add";
    public static final String SHARE_ORDER_TAG = "SHARE_ORDER_TAG";
    //晒单点赞
    public static final String ZAN_SHARE_ORDER = "/personal/show/like";
    public static final String ZAN_SHARE_ORDER_TAG = "ZAN_SHARE_ORDER_TAG";
    //红包
    public static final String MINE_RED = "/personal/coupon";
    public static final String MINE_RED_TAG = "MINE_RED_TAG";
    //充值
    public static final String RECHARGE = "/recharge";
    public static final String RECHARGE_TAG = "RECHARGE_TAG";
    //排行榜
    public static final String RANK = "/ranking";
    public static final String RANK_TAG = "RANK_TAG";
    //找回密码获取验证码
    public static final String FIND_PASSWORD_CODE = "/user/find_password_code";
    public static final String FIND_PASSWORD_CODE_TAG = "FIND_PASSWORD_CODE_TAG";
    //找回密码 重置密码
    public static final String FIND_PASSWORD_RESET = "/user/reset_password";
    public static final String FIND_PASSWORD_RESET_TAG = "FIND_PASSWORD_RESET_TAG";
    //开奖走势列表
    public static final String TREND_LIST = "/new_opened";
    public static final String TREND_LIST_TAG = "TREND_LIST";
    //获取绑定手机短信验证码
    public static final String RESET_PHONE_SMS = "/personal/profile/reset_mobile_sms";
    public static final String RESET_PHONE_SMS_TAG = "RESET_PHONE_SMS";
    //重置手机号
    public static final String RESET_PHONE = "/personal/profile/reset_mobile";
    public static final String RESET_PHONE_TAG = "RESET_PHONE";
    //最新获奖
    public static final String NEWEST_WIN = "/business/newest_win";
    public static final String NEWEST_WIN_TAG = "NEWEST_WIN";
    //最新参与
    public static final String NEWEST_BUY = "/business/newest_buy";
    public static final String NEWEST_BUY_TAG = "NEWEST_BUY";
    //最新参与指定产品
    public static final String NEWEST_BUY_GOODS = "/business/newest_buy_detail";
    public static final String NEWEST_BUY_GOODS_TAG = "NEWEST_BUY_GOODS";
    //银行卡列表
    public static final String BANK_LIST = "/personal/bank";
    public static final String BANK_LIST_TAG = "BANK_LIST_TAG";
    //添加收款方式
    public static final String ADD_BANK_CARD = "/personal/bank/edit";
    public static final String ADD_BANK_CARD_TAG = "ADD_BANK_CARD_TAG";
    //个人资料
    public static final String MINE_INFO = "/personal/profile";
    public static final String MINE_INFO_TAG = "MINE_INFO_TAG";
    //使用夺宝币购买商品
    public static final String ORDER_BUY = "/business/order";
    public static final String ORDER_BUY_TAG = "ORDER_BUY_TAG";
    //奖品列表
    public static final String PRIZE_LIST = "/exchange";
    public static final String PRIZE_LIST_TAG = "PRIZE_LIST_TAG";
    //兑换
    public static final String EXCHANGE = "/exchange/submit";
    public static final String EXCHANGE_TAG = "EXCHANGE_TAG";
    //兑换记录
    public static final String EXCHANGE_RECORD = "/personal/exchange";
    public static final String EXCHANGE_RECORD_TAG = "EXCHANGE_RECORD";
    //PK
    public static final String PK = "/personal/order/pk";
    public static final String PK_TAG = "PK";
    //申请提现
    public static final String APPPY_CASH = "/personal/withdraw/request";
    public static final String APPLY_CASH_TAG = "APPLY_CASH";
    //提现记录
    public static final String CASH_RECORD = "/personal/withdraw";
    public static final String CASH_RECORD_TAG = "CASH_RECORD";
    //检查支付结果
    public static final String CHECK_PAY_RESULT = "/recharge/is_success";
    public static final String CHECK_PAY_RESULT_TAG = "CHECK_PAY_RESULT";
    //检查更新
    public static final String UPDATE_VERSION = "/version/android";
    public static final String UPDATE_VERSION_TAG = "UPDATE_VERSION_TAG";
    //启动
    public static final String APP_START = "/data/start";
    public static final String APP_START_TAG = "APP_START";
    //上报停留时间
    public static final String UPDATE_STAY = "/data/stay";
    public static final String UPDATE_STAY_TAG = "UPDATE_STAY_TAG";
    //上报界面
    public static final String UPDATE_PAGE = "/data/position";
    public static final String UPDATE_PAGE_TAG = "/data/position";
    //别人的夺宝记录
    public static final String OTHERS_INDIANA_RECORD = "/ranking/detail";
    public static final String OTHERS_INDIANA_RECORD_TAG = "OTHERS_INDIANA_RECORD";
    //别人的中奖纪录
    public static final String OTHERS_WIN_RECORD = "/ranking/win";
    public static final String OTHERS_WIN_RECORD_TAG = "OTHERS_WIN_RECORD";

    /**
     * Case By:创建参数基础信息
     * Author: scene on 2017/5/19 13:19
     */
    public static HashMap<String, String> createParams() {
        HashMap<String, String> params = new HashMap<>();
        long timestamp = NetTimeUtils.getWebsiteDatetime();
        params.put("resource_id", ShopApplication.RESOURCE_ID + "");
        params.put("timestamp", timestamp + "");
        params.put("signature", getSign(timestamp + ""));
        params.put("app_type", "1");
        params.put("uuid", ShopApplication.UUID);
        params.put("version", AppUtils.getAppVersionCode() + "");
        return params;
    }

    /**
     * Case By:获取sign
     * Author: scene on 2017/5/19 13:19
     */
    private static String getSign(String timestamp) {
        return MD5Util.string2Md5(MD5Util.string2Md5(1 + ShopApplication.RESOURCE_ID + timestamp + ShopApplication.UUID + AppUtils.getAppVersionCode(), "UTF-8") + SIGN_KEY, "UTF-8");
    }
}
