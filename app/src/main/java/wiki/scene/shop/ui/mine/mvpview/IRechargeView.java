package wiki.scene.shop.ui.mine.mvpview;

import android.support.annotation.StringRes;

import wiki.scene.shop.mvp.BaseView;

/**
 * 充值
 * Created by scene on 17-8-16.
 */

public interface IRechargeView extends BaseView {
    void showMessage(String message);

    void showMessage(@StringRes int resId);

    void getRechargeOrderSuccess();
}
