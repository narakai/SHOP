package wiki.scene.shop;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.MemoryCategory;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.MemoryCookieStore;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.sunfusheng.glideimageview.progress.GlideApp;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;
import wiki.scene.loadmore.utils.PtrLocalDisplay;
import wiki.scene.shop.config.AppConfig;
import wiki.scene.shop.entity.ConfigInfo;
import wiki.scene.shop.entity.CurrentCycleInfo;
import wiki.scene.shop.entity.UserInfo;


/**
 * Case By:Application
 * package:wiki.scene.shop
 * Author：scene on 2017/6/26 11:51
 */

public class ShopApplication extends Application {
    public static final String USER_INFO_KEY = "user_info_key";
    public static final String UUID_KEY = "uuid";
    public static String UUID = "";
    public static String CHANNEL_ID;
    public static String RESOURCE_ID;
    public static int versionCode = 0;
    public static boolean hasLogin = false;
    public static UserInfo userInfo;
    public static ConfigInfo configInfo;

    public static CurrentCycleInfo currentCycleInfo;

    @Override
    public void onCreate() {
        super.onCreate();
        PtrLocalDisplay.init(this);
        CHANNEL_ID = getChannelName();
        RESOURCE_ID = getResouyceName();
        initOKhttp();
        initUmengShare();
        //初始化工具类
        Utils.init(this);
    }

    /**
     * Case By: 初始化Okhttp
     * package:
     * Author：scene on 2017/6/26 11:55
     */
    private void initOKhttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.WARNING);
        builder.addInterceptor(loggingInterceptor);
        //全局的读取超时时间
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的写入超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的连接超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //使用内存保持cookie，app退出后，cookie消失
        builder.cookieJar(new CookieJarImpl(new MemoryCookieStore()));
        //方法一：信任所有证书,不安全有风险
        HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();
        builder.sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager);
        OkGo.getInstance().init(this)                            //必须调用初始化
                .setOkHttpClient(builder.build())               //必须设置OkHttpClient
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(0);                         //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
        //初始化Glide
        //Glide.get(this).register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(builder.build()));
        GlideApp.get(this).setMemoryCategory(MemoryCategory.HIGH);
    }

    /**
     * Case By:获取渠道
     * Author: scene on 2017/5/19 10:46
     */
    private String getChannelName() {
        String resultData = "";
        try {
            PackageManager packageManager = getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        String str = applicationInfo.metaData.getString("CHANNEL");
                        if (TextUtils.isEmpty(str)) {
                            return "";
                        } else {
                            resultData = str.substring(0, str.indexOf(","));
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultData;
    }

    /**
     * Case By:获取渠道
     * Author: scene on 2017/5/19 10:46
     */
    private String getResouyceName() {
        String resultData = "";
        try {
            PackageManager packageManager = getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        String str = applicationInfo.metaData.getString("CHANNEL");
                        if (TextUtils.isEmpty(str)) {
                            return "";
                        } else {
                            resultData = str.substring(str.indexOf(",") + 1);
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultData;
    }

    private void initUmengShare() {
        Config.DEBUG = true;
        PlatformConfig.setWeixin(AppConfig.WX_APPKEY, AppConfig.WX_APPSECRET);
        PlatformConfig.setQQZone(AppConfig.QQ_APPKEY, AppConfig.QQ_APPSECRET);
        PlatformConfig.setSinaWeibo(AppConfig.WEIBO_APPKEY, AppConfig.WEIBO_APPSECRET, AppConfig.WEIBO_REDIRECT_URL);
        UMShareAPI.get(this);
    }

}
