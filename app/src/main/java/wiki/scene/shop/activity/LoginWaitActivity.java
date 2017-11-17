package wiki.scene.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

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
import wiki.scene.shop.event.RegisterSuccessEvent;
import wiki.scene.shop.mvp.BaseMvpActivity;

/**
 * 选择登陆注册的界面
 * Created by scene on 2017/11/16.
 */

public class LoginWaitActivity extends BaseMvpActivity<ILoginWaitView, LoginWaitPresenter> implements ILoginWaitView {
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
}
