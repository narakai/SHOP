package wiki.scene.shop.ui.mine.mvpview;

import wiki.scene.shop.mvp.BaseView;

/**
 * 订单pk
 * Created by scene on 2017/11/22.
 */

public interface IOrderPkDetailView extends BaseView {
    void showLoadingPage();

    void showContentPage();

    void showFailPage();

    void getPkInfoSuccess();
}
