package wiki.scene.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import wiki.scene.shop.MainActivity;
import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.activity.mvpview.ILauncherView;
import wiki.scene.shop.activity.presenter.LauncherPresenter;
import wiki.scene.shop.entity.ConfigInfo;
import wiki.scene.shop.entity.UserInfo;
import wiki.scene.shop.mvp.BaseMvpActivity;
import wiki.scene.shop.utils.SharedPreferencesUtil;
import wiki.scene.shop.utils.ToastUtils;

/**
 * Case By:
 * package:wiki.scene.shop.activity
 * Author：scene on 2017/7/6 15:22
 */

public class LauncherActivity extends BaseMvpActivity<ILauncherView, LauncherPresenter> implements ILauncherView {
    private long beginTime = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        beginTime = System.currentTimeMillis();
        presenter.getAppConfig();
        checkHasLogin();
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public LauncherPresenter initPresenter() {
        return new LauncherPresenter(this);
    }

    private void checkHasLogin() {
        if (ShopApplication.userInfo == null) {
            //验证登录是否过期
            String userInfoStr = SharedPreferencesUtil.getString(this, ShopApplication.USER_INFO_KEY, "");
            ShopApplication.userInfo = new Gson().fromJson(userInfoStr, UserInfo.class);
        }
        ShopApplication.hasLogin = !(ShopApplication.userInfo == null || System.currentTimeMillis() > ShopApplication.userInfo.getExpired_time());
    }

    @Override
    public void onBackPressedSupport() {

    }

    @Override
    public void getConfigSuccess(ConfigInfo configInfo) {
        ShopApplication.configInfo = configInfo;
        long delayTime = 2000 - (System.currentTimeMillis() - beginTime);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(LauncherActivity.this, MainActivity.class));
                        LauncherActivity.this.finish();
                    }
                });
            }
        }, delayTime > 0 ? delayTime : 0);
    }

    @Override
    public void getConfigFail(String res) {
        ToastUtils.getInstance(LauncherActivity.this).showToast(res);
        finish();
    }
}
