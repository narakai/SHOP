package wiki.scene.shop.ui.mine.mvpview;

import wiki.scene.shop.mvp.BaseView;

/**
 * 添加支付宝
 * Created by scene on 2017/11/14.
 */

public interface IAddAlipayView extends BaseView {
    void showMessage(String message);

    void bindOrUpdateSuccess();
}
