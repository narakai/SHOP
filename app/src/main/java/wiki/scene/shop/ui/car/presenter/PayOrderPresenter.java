package wiki.scene.shop.ui.car.presenter;

import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.car.mvpview.IPayOrderView;

/**
 * Case By:支付订单
 * package:wiki.scene.shop.ui.car.presenter
 * Author：scene on 2017/7/4 09:17
 */

public class PayOrderPresenter extends BasePresenter<IPayOrderView> {
    private IPayOrderView payOrderView;

    public PayOrderPresenter(IPayOrderView payOrderView) {
        this.payOrderView = payOrderView;
    }
}
