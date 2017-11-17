package wiki.scene.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wiki.scene.shop.MainActivity;
import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.activity.mvpview.ILoginView;
import wiki.scene.shop.activity.presenter.LoginPresenter;
import wiki.scene.shop.entity.UserInfo;
import wiki.scene.shop.event.RegisterSuccessEvent;
import wiki.scene.shop.mvp.BaseMvpActivity;
import wiki.scene.shop.utils.SharedPreferencesUtil;
import wiki.scene.shop.utils.ToastUtils;
import wiki.scene.shop.widgets.LoadingDialog;

/**
 * Case By:登录
 * package:wiki.scene.shop.activity
 * Author：scene on 2017/6/27 10:48
 */

public class LoginActivity extends BaseMvpActivity<ILoginView, LoginPresenter> implements ILoginView, UMAuthListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    private Unbinder unbinder;
    @BindView(R.id.phone_number)
    TextView phoneNumber;
    @BindView(R.id.password)
    TextView password;

    private LoadingDialog loadingDialog;
    private UMShareAPI shareAPI;


    private String unionid;
    private String nickName;
    private int sex;
    private String avaterPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        unbinder = ButterKnife.bind(this);
        initToolbar();
        initView();
    }

    @Override
    public LoginPresenter initPresenter() {
        return new LoginPresenter(this);
    }

    private void initToolbar() {
        toolbarTitle.setText(R.string.login);
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initView() {
        loadingDialog = LoadingDialog.getInstance(this);
        shareAPI = UMShareAPI.get(this);
    }

    @Override
    public void showLoading(@StringRes int resId) {
        try {
            loadingDialog.showLoadingDialog(getString(resId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hideLoading() {
        loadingDialog.cancelLoadingDialog();
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return password.getText().toString().trim();
    }

    @Override
    public void loginSuccess(UserInfo userInfo) {
        try {
            if (userInfo != null) {
                EventBus.getDefault().post(new RegisterSuccessEvent(userInfo));
                SharedPreferencesUtil.putString(this, ShopApplication.USER_INFO_KEY, new Gson().toJson(userInfo));
                ShopApplication.userInfo = userInfo;
                ShopApplication.hasLogin = true;
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showFailInfo(String msg) {
        ToastUtils.getInstance(LoginActivity.this).showToast(msg);
    }

    @Override
    public void showFailInfo(@StringRes int resId) {
        ToastUtils.getInstance(LoginActivity.this).showToast(resId);
    }

    @Override
    public void enterRegisterActivity() {
        Intent intent = new Intent(LoginActivity.this, Register1Activity.class);
        startActivity(intent);
    }

    @Override
    public void enterLosePasswordActivity() {
        startActivity(new Intent(LoginActivity.this, FindPasswordActivity.class));
    }

    @Override
    public void noRegister(int type) {
        Intent intent = new Intent(LoginActivity.this, Register1Activity.class);
        intent.putExtra("unionid", unionid);
        intent.putExtra("nickName", nickName);
        intent.putExtra("sex", sex);
        intent.putExtra("avaterPath", avaterPath);
        intent.putExtra("type", type);
        startActivity(intent);
    }

    @OnClick(R.id.login)
    public void onClickLogin() {
        presenter.login();
    }

    @OnClick(R.id.register)
    public void onClickRegister() {
        presenter.enterRegisterActivity();
    }

    @OnClick(R.id.lose_password)
    public void onClickLosePassword() {
        presenter.enterLosePassword();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRegisterSuccess(RegisterSuccessEvent event) {
        if (event != null) {
            onBackPressed();
        }
    }

    @OnClick(R.id.wechat_login)
    public void onClickWechatLogin() {
        if (shareAPI.isInstall(this, SHARE_MEDIA.WEIXIN)) {
            shareAPI.getPlatformInfo(this, SHARE_MEDIA.WEIXIN, this);
        } else {
            showFailInfo(R.string.please_install_wechat);
        }
    }

    @OnClick(R.id.qq_login)
    public void onClickQQLogin() {
        if (shareAPI.isInstall(this, SHARE_MEDIA.QQ)) {
            shareAPI.getPlatformInfo(this, SHARE_MEDIA.QQ, this);
        } else {
            showFailInfo(R.string.please_install_qq);
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideLoading();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        UMShareAPI.get(this).release();
    }

    /**
     * Case By:友盟登录开始监听
     * Author: scene on 2017/7/17 9:15
     */
    @Override
    public void onStart(SHARE_MEDIA share_media) {
        showLoading(R.string.loading);
    }

    /**
     * Case By:友盟登录成功监听
     * Author: scene on 2017/7/17 9:15
     */
    @Override
    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
        hideLoading();
        unionid = map.get("uid");
        nickName = map.get("name");
        sex = map.get("gender").equals("男") ? 1 : 2;
        avaterPath = map.get("iconurl");
        presenter.loginByOthers(unionid, share_media == SHARE_MEDIA.QQ ? 1 : 2);
    }

    /**
     * Case By:友盟登录失败监听
     * Author: scene on 2017/7/17 9:15
     */
    @Override
    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
        hideLoading();
        showFailInfo(R.string.login_fail_please_retry);
    }

    /**
     * Case By:友盟登录取消监听
     * Author: scene on 2017/7/17 9:15
     */
    @Override
    public void onCancel(SHARE_MEDIA share_media, int i) {
        hideLoading();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
