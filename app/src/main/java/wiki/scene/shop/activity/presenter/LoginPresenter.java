package wiki.scene.shop.activity.presenter;

import android.text.TextUtils;

import wiki.scene.shop.R;
import wiki.scene.shop.activity.model.LoginModel;
import wiki.scene.shop.activity.view.ILoginView;
import wiki.scene.shop.entity.UserInfo;
import wiki.scene.shop.mvp.BaseHttpResultListener;
import wiki.scene.shop.mvp.BasePresenter;

/**
 * Case By:登录
 * package:wiki.scene.shop.activity.presenter
 * Author：scene on 2017/6/27 11:02
 */

public class LoginPresenter extends BasePresenter<ILoginView> {
    private ILoginView loginView;
    private LoginModel loginModel;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
        loginModel = new LoginModel();
    }

    /**
     * 登录
     */
    public void login() {
        if (loginView != null) {
            if (TextUtils.isEmpty(loginView.getPhoneNumber())) {
                loginView.showFailInfo(R.string.please_edit_phone_munber);
                return;
            }
            if (TextUtils.isEmpty(loginView.getPassword())) {
                loginView.showFailInfo(R.string.please_edit_password);
                return;
            }

            loginView.showLoading();
            loginModel.login(loginView.getPhoneNumber(), loginView.getPassword(), new BaseHttpResultListener<UserInfo>() {
                @Override
                public void onSuccess(UserInfo data) {
                    if (loginView != null) {
                        loginView.loginSuccess();
                    }
                }

                @Override
                public void onError(String msg) {
                    if (loginView != null) {
                        loginView.showFailInfo(msg);
                    }
                }

                @Override
                public void onFinish() {
                }
            });
        }
    }

    /**
     * 进入注册界面
     */
    public void enterRegisterActivity() {
        if (loginView != null) {
            loginView.enterRegisterActivity();
        }
    }

    /**
     * 进入忘记密码的界面
     */
    public void enterLosePassword() {
        if (loginView != null) {
            loginView.enterRegisterActivity();
        }
    }

}
