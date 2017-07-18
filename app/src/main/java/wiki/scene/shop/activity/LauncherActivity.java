package wiki.scene.shop.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.telephony.TelephonyManager;

import com.google.gson.Gson;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.util.List;

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
 * Case By:启动页面
 * package:wiki.scene.shop.activity
 * Author：scene on 2017/7/6 15:22
 */

public class LauncherActivity extends BaseMvpActivity<ILauncherView, LauncherPresenter> implements ILauncherView {
    private long beginTime = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        applyPermission();
    }


    @Override
    public void showLoading(@StringRes int resId) {

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

    private void applyPermission() {
        AndPermission.with(this)
                .requestCode(100)
                .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_COARSE_LOCATION)
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                        AndPermission.rationaleDialog(LauncherActivity.this, rationale).show();
                    }
                })
                .callback(new PermissionListener() {
                    @Override
                    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                        try {
                            ShopApplication.UUID = getUUID();
                            ShopApplication.versionCode = LauncherActivity.this.getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        beginTime = System.currentTimeMillis();
                        presenter.getAppConfig();
                        checkHasLogin();
                    }

                    @Override
                    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                        if (AndPermission.hasAlwaysDeniedPermission(LauncherActivity.this, deniedPermissions)) {
                            AndPermission.defaultSettingDialog(LauncherActivity.this, 400)
                                    .setTitle(R.string.permission_fail)
                                    .setMessage(R.string.permission_fail_notice)
                                    .setPositiveButton(R.string.go_to_setting)
                                    .show();
                        } else {
                            applyPermission();
                        }
                    }
                })
                .start();
    }

    /**
     * 获取UUID
     *
     * @return uuid
     */
    private String getUUID() {
        String uuid;
        TelephonyManager tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        uuid = tm.getDeviceId();
        if (uuid.isEmpty()) {
            uuid = SharedPreferencesUtil.getString(this, ShopApplication.UUID_KEY, "");
            if (uuid.isEmpty()) {
                String imei = createRandomUUID(false, 64);
                SharedPreferencesUtil.putString(this, ShopApplication.UUID_KEY, imei);
                uuid = imei;
            }
        }
        return uuid;
    }

    private String createRandomUUID(boolean numberFlag, int length) {
        String retStr;
        String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
        int len = strTable.length();
        boolean bDone = true;
        do {
            retStr = "";
            int count = 0;
            for (int i = 0; i < length; i++) {
                double dblR = Math.random() * len;
                int intR = (int) Math.floor(dblR);
                char c = strTable.charAt(intR);
                if (('0' <= c) && (c <= '9')) {
                    count++;
                }
                retStr += strTable.charAt(intR);
            }
            if (count >= 20) {
                bDone = false;
            }
        } while (bDone);

        return retStr;
    }
}
