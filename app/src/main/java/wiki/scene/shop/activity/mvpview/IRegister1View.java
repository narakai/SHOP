package wiki.scene.shop.activity.mvpview;

import android.support.annotation.StringRes;

import wiki.scene.shop.entity.UserInfo;
import wiki.scene.shop.mvp.BaseView;

/**
 * Case By:注册第一步
 * package:wiki.scene.shop.activity.view
 * Author：scene on 2017/6/27 14:19
 */

public interface IRegister1View extends BaseView {
    void enterNextStep();

    String getPhoneNumber();

    String getPassword();

    String getCode();

    void showFail(String message);

    void showFail(@StringRes int resId);

    void showCountDownTimer();

    void showLoading(String str);

    void showLoading(@StringRes int resId);

    void registerSuccess(UserInfo userInfo);
}
