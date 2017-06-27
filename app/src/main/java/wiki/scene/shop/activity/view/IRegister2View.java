package wiki.scene.shop.activity.view;

import android.support.annotation.StringRes;

import wiki.scene.shop.mvp.BaseView;

/**
 * Case By:注册第二步
 * package:wiki.scene.shop.activity.view
 * Author：scene on 2017/6/27 15:38
 */

public interface IRegister2View extends BaseView {

    String getPassword();

    String getRePassword();

    void showFail(@StringRes int resId);

    void showFail(String msg);

    void registerSuccess();
}
