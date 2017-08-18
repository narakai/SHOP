package wiki.scene.shop.activity.mvpview;

import android.support.annotation.StringRes;

import wiki.scene.shop.mvp.BaseView;

/**
 * 找回密码
 * Created by scene on 17-8-18.
 */

public interface IFindPasswordView extends BaseView {
    void showMessage(String message);

    void showMessage(@StringRes int resId);

    void showCountDownTimer();

    void resetPasswordSuccess();
}
