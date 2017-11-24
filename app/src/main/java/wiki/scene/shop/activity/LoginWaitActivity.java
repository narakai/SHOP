package wiki.scene.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wiki.scene.shop.MainActivity;
import wiki.scene.shop.R;
import wiki.scene.shop.activity.mvpview.ILoginWaitView;
import wiki.scene.shop.activity.presenter.LoginWaitPresenter;
import wiki.scene.shop.config.AppConfig;
import wiki.scene.shop.event.RegisterSuccessEvent;
import wiki.scene.shop.mvp.BaseMvpNoBackActivity;
import wiki.scene.shop.utils.NetTimeUtils;
import wiki.scene.shop.utils.UpdatePageUtils;

/**
 * 选择登陆注册的界面
 * Created by scene on 2017/11/16.
 */

public class LoginWaitActivity extends BaseMvpNoBackActivity<ILoginWaitView, LoginWaitPresenter> implements ILoginWaitView {
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.register)
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_login_wait);
        ButterKnife.bind(this);
        UpdatePageUtils.updatePagePosition(AppConfig.POSITION_LOGIN_AND_REGISTER, 0);
    }

    @Override
    public void showLoading(int resId) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public LoginWaitPresenter initPresenter() {
        return new LoginWaitPresenter(this);
    }

    @OnClick(R.id.to_main_page)
    public void onClickToMainPage() {
        startActivity(new Intent(LoginWaitActivity.this, MainActivity.class));
        LoginWaitActivity.this.finish();
    }

    @OnClick(R.id.login)
    public void onClickToLogin() {
        startActivity(new Intent(LoginWaitActivity.this, LoginActivity.class));
    }

    @OnClick(R.id.register)
    public void onClickToRegister() {
        startActivity(new Intent(LoginWaitActivity.this, Register1Activity.class));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRegisterSuccess(RegisterSuccessEvent event) {
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    /**
     * 处理回退事件
     *
     * @return
     */
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;
    @Override
    public void onBackPressedSupport() {
        if (NetTimeUtils.getWebsiteDatetime() - TOUCH_TIME < WAIT_TIME) {
            finish();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        } else {
            TOUCH_TIME = NetTimeUtils.getWebsiteDatetime();
            ToastUtils.showShort(R.string.press_again_exit);
        }
    }
}
