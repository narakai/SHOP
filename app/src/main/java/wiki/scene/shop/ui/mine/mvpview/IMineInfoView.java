package wiki.scene.shop.ui.mine.mvpview;

import android.support.annotation.StringRes;

import wiki.scene.shop.mvp.BaseView;

/**
 * Case By:个人资料
 * package:wiki.scene.shop.fragment.mine.mvpview
 * Author：scene on 2017/6/29 17:46
 */

public interface IMineInfoView extends BaseView {
    void showLoading(String msg);

    void showLoading(@StringRes int resId);

    String getNickName();

    int getSex();

    void showSuccess();

    void showFail(String str);

    void updateUserAvaterSuccess(String filePath);

    void updateUserInfoSuccess();

}
