package wiki.scene.shop.ui.mine.presenter;

import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.mvpview.IMineReceiverAddressView;

/**
 * Case By:我的收货地址
 * package:wiki.scene.shop.ui.mine.presenter
 * Author：scene on 2017/6/30 10:21
 */

public class MineReceiverAddressPresenter extends BasePresenter<IMineReceiverAddressView> {
    private IMineReceiverAddressView addressView;

    public MineReceiverAddressPresenter(IMineReceiverAddressView addressView) {
        this.addressView = addressView;
    }


}
