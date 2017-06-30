package wiki.scene.shop.ui.mine.presenter;

import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.mvpview.IAddAddressView;

/**
 * Case By:
 * package:wiki.scene.shop.ui.mine.presenter
 * Author：scene on 2017/6/30 12:45
 */

public class AddAddressPresenter extends BasePresenter<IAddAddressView> {
    private IAddAddressView addAddressView;

    public AddAddressPresenter(IAddAddressView addAddressView) {
        this.addAddressView = addAddressView;
    }
}
