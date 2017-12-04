package wiki.scene.shop.config;

/**
 * Case By:项目配置文件
 * package:wiki.scene.shop.config
 * Author：scene on 2017/6/29 16:38
 */

public class AppConfig {
    public static final int CHOOSE_AVATER_REQUEST_CODE = 1001;
    public static final int ADD_ADDRESS_REQUEST_CODE = 1002;
    public static final int ORDER_DETAIL_TO_PAY_REQUEST_CODE = 1003;
    public static final int SHARE_ORDER_REQUEST_CODE = 1004;
    public static final String WX_APPKEY = "wx9ca0dad4e08bf44c";
    public static final String WX_APPSECRET = "fcb60e5b094846ef4e87253454bf583e";
    public static final String QQ_APPKEY = "1106258470";
    public static final String QQ_APPSECRET = "YED2DoxyDzJGlmVX";
    public static final String WEIBO_APPKEY = "4230088431";
    public static final String WEIBO_APPSECRET = "18af9d20dddaffd4217940a8b407469b";
    public static final String WEIBO_REDIRECT_URL = "http://sns.whalecloud.com";

    public static final int PAY_TYPE_BALANCE = 1;
    public static final int PAY_TYPE_WECHAT = 1;
    public static final int PAY_TYPE_ALPAY = 2;
    public static final int DEFAULT_PAY_WAY = PAY_TYPE_WECHAT;

    public static final int GET_WINNER_NOTICE_DELAY = 60 * 1000;
    public static final int GET_DANMU_DELAY = 60 * 1000;
    public static final int SHOW_DANMU_DELAY = 3 * 1000;
    public static final int HIDE_DANMU_DELAY = 10 * 1000;

    //玩法
    public static final int PLAY_TYPE_TWO = 1;
    public static final int PLAY_TYPE_FOUR = 2;
    public static final int PLAY_TYPE_TEN = 3;
    //购买方式
    public static final int BUY_TYPE_BIG = 1;
    public static final int BUY_TYPE_SMALL = 2;
    public static final int BUY_TYPE_SINGLE = 3;
    public static final int BUY_TYPE_DOUBLE = 4;
    public static final int BUY_TYPE_BIG_SINGLE = 5;
    public static final int BUY_TYPE_BIG_DOUBLE = 6;
    public static final int BUY_TYPE_SMALL_SINGLE = 7;
    public static final int BUY_TYPE_SMALL_DOUBLE = 8;
    public static final int BUY_TYPE_NUM_1 = 9;
    public static final int BUY_TYPE_NUM_2 = 10;
    public static final int BUY_TYPE_NUM_3 = 11;
    public static final int BUY_TYPE_NUM_4 = 12;
    public static final int BUY_TYPE_NUM_5 = 13;
    public static final int BUY_TYPE_NUM_6 = 14;
    public static final int BUY_TYPE_NUM_7 = 15;
    public static final int BUY_TYPE_NUM_8 = 16;
    public static final int BUY_TYPE_NUM_9 = 17;
    public static final int BUY_TYPE_NUM_0 = 18;
    //单次的最大购买数量
    public static final int MAX_BUY_NUMBER = 100;
    //银行卡分类
    public static final int BANK_TYPE_BANK_CARD = 1;
    public static final int BANK_TYPE_ALIPAY = 2;
    //真实支付方式
    public static final int API_TYPE_WX_SCAN = 1;
    public static final int API_TYPE_WX_GZH_CHANGE = 2;
    public static final int API_TYPE_ALIPAY_SCAN = 3;
    //页面position
    //首页
    public static final int POSITION_INDEX = 1;
    //商品详情
    public static final int POSITION_GOODS_DETAIL = 2;
    //开奖走势
    public static final int POSITION_TRENT = 3;
    //排行榜
    public static final int POSITION_RANK = 4;
    //我的
    public static final int POSITION_MINE = 5;
    //设置
    public static final int POSITION_SETTING = 6;
    //客服中心
    public static final int POSITION_SERVICE_CENTER = 7;
    //设置头像昵称等
    public static final int POSITION_SETTING_AVATAR = 8;
    //充值
    public static final int POSITION_RECHARGE = 9;
    //兑换
    public static final int POSITION_EXCHANGE = 10;
    //提现
    public static final int POSITION_CASH = 11;
    //夺宝记录 全部
    public static final int POSITION_INDIANA_RECORD_ALL = 12;
    //夺宝记录 获胜
    public static final int POSITION_INDIANA_RECORD_WIN = 13;
    //PK详情
    public static final int POSITION_INDIANA_PK = 14;
    //兑换记录
    public static final int POSITION_EXCHANGE_RECORD = 15;
    //提现记录
    public static final int POSITION_CASH_RECORD = 16;
    //绑定手机号
    public static final int POSITION_MINE_PHONE = 17;
    //我的收款账号
    public static final int POSITION_MINE_BANK_CARD = 18;
    //绑定银行卡
    public static final int POSITION_BIND_BANK_CARD = 19;
    //绑定支付宝
    public static final int POSITION_BIND_ALIPAY = 20;
    //意见反馈
    public static final int POSITION_BUG_FEEDBACK = 21;
    //用户协议
    public static final int POSITION_USER_AGREEMENT = 22;
    //关于我们
    public static final int POSITION_ABOUT_US = 23;
    //登录
    public static final int POSITION_LOGIN = 24;
    //注册
    public static final int POSITION_REGISTER = 25;
    //找回密码
    public static final int POSITION_FIND_PASSWORD = 26;
    //登录和注册的那个新界面
    public static final int POSITION_LOGIN_AND_REGISTER = 27;
    //提现结果
    public static final int POSITION_CASH_RESULT = 28;
    //玩法说明
    public static final int POSITION_HOW_TO_PLAY = 29;
}
