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
    public static final String WX_APPKEY = "wx4d1ef7a0167fde7e";
    public static final String WX_APPSECRET = "18af9d20dddaffd4217940a8b407469b";
    public static final String QQ_APPKEY = "1106258470";
    public static final String QQ_APPSECRET = "YED2DoxyDzJGlmVX";
    public static final String WEIBO_APPKEY = "4230088431";
    public static final String WEIBO_APPSECRET = "18af9d20dddaffd4217940a8b407469b";
    public static final String WEIBO_REDIRECT_URL = "http://sns.whalecloud.com";

    public static final int PAY_TYPE_BALANCE = 1;
    public static final int PAY_TYPE_WECHAT = 2;
    public static final int PAY_TYPE_ALPAY = 3;
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
    public static final int MAX_BUY_NUMBER = 1000;
}
