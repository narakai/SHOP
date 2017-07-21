package wiki.scene.shop.ui.car.mvpview;

import android.support.annotation.StringRes;

import wiki.scene.shop.mvp.BaseView;

/**
 * Case By:支付订单
 * package:wiki.scene.shop.ui.car.mvpview
 * Author：scene on 2017/7/4 09:17
 */

public interface IPayOrderView extends BaseView {
    void showMessage(@StringRes int resId);

    void showMessage(String msg);

    void hasNoLogin();

    void getPayOrderInfoSuccess();
}
