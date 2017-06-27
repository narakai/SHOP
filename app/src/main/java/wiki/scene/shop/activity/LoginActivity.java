package wiki.scene.shop.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import wiki.scene.shop.R;
import wiki.scene.shop.activity.presenter.LoginPresenter;
import wiki.scene.shop.activity.view.ILoginView;
import wiki.scene.shop.mvp.BaseMvpActivity;

/**
 * Case By:登录
 * package:wiki.scene.shop.activity
 * Author：scene on 2017/6/27 10:48
 */

public class LoginActivity extends BaseMvpActivity<ILoginView, LoginPresenter> implements ILoginView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showContent() {

    }

    @Override
    public void showFail() {

    }

    @Override
    public void showNoNetwork() {

    }

    @Override
    public void clearUserName() {

    }

    @Override
    public void clearPassword() {

    }

    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public void showFailInfo(String failInfo) {

    }

    @Override
    public LoginPresenter initPresenter() {
        return null;
    }
}
