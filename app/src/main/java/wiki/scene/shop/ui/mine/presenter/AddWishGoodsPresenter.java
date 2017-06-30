package wiki.scene.shop.ui.mine.presenter;

import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.mvpview.IAddWishGoodsView;

/**
 * Case By:添加愿望商品
 * package:wiki.scene.shop.ui.mine.presenter
 * Author：scene on 2017/6/30 15:20
 */

public class AddWishGoodsPresenter extends BasePresenter<IAddWishGoodsView> {
    private IAddWishGoodsView wishGoodsView;

    public AddWishGoodsPresenter(IAddWishGoodsView wishGoodsView) {
        this.wishGoodsView = wishGoodsView;
    }
}
