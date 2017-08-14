package wiki.scene.shop.ui.mine.presenter;

import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.model.ShareOrderModel;
import wiki.scene.shop.ui.mine.mvpview.IShareOrderView;

/**
 * Created by scene on 17-8-14.
 */

public class ShareOrderPresenter extends BasePresenter<IShareOrderView> {
    private IShareOrderView shareOrderView;
    private ShareOrderModel model;

    public ShareOrderPresenter(IShareOrderView shareOrderView) {
        this.shareOrderView = shareOrderView;
        model = new ShareOrderModel();
    }
}
