package wiki.scene.shop.activity.mvpview;

import android.support.annotation.StringRes;

import wiki.scene.shop.entity.UserInfo;
import wiki.scene.shop.mvp.BaseView;

/**
 * Case By:登录
 * package:wiki.scene.shop.activity.view
 * Author：scene on 2017/6/27 10:57
 */

public interface ILoginView extends BaseView {

    String getPhoneNumber();

    String getPassword();

    void loginSuccess(UserInfo userInfo);

    void showFailInfo(String msg);

    void showFailInfo(@StringRes int resId);

    void enterRegisterActivity();

    void enterLosePasswordActivity();

    void noRegister(int type);
}
