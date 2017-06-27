package wiki.scene.shop.activity.view;

import wiki.scene.shop.mvp.BaseView;

/**
 * Case By:登录
 * package:wiki.scene.shop.activity.view
 * Author：scene on 2017/6/27 10:57
 */

public interface ILoginView extends BaseView {
    void clearUserName();

    void clearPassword();

    String getUserName();

    String getPassword();

    void loginSuccess();

    void showFailInfo(String failInfo);

    void enterRegisterActivity();

    void enterLosePasswordActivity();
}
