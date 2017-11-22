package wiki.scene.shop.ui.mine.presenter;

import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.model.OrderPkDetailModel;
import wiki.scene.shop.ui.mine.mvpview.IOrderPkDetailView;

/**
 * 订单pk
 * Created by scene on 2017/11/22.
 */

public class OrderPkDetailPresenter extends BasePresenter<IOrderPkDetailView> {
    private OrderPkDetailModel model;

    public OrderPkDetailPresenter(IOrderPkDetailView detailView) {
        this.mView = detailView;
        model = new OrderPkDetailModel();
    }
}
