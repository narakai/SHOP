package wiki.scene.shop.ui.mine.mvpview;

import android.support.annotation.StringRes;

import wiki.scene.shop.mvp.BaseView;

/**
 * Case By:添加地址
 * package:wiki.scene.shop.ui.mine.mvpview
 * Author：scene on 2017/6/30 12:45
 */

public interface IAddAddressView extends BaseView {
    void showProgressDialog(@StringRes int message);

    void hideProgressDialog();

    String getReceiverName();

    String getReceiverPhone();

    String getReceiverAddress();

    int getIsDefault();

    void showFail(String message);

    void showFail(@StringRes int message);

    void addSuccess();

    void updateSuccess();

    void deleteSuccess();

}
