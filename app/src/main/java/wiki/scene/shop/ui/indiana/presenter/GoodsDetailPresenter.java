package wiki.scene.shop.ui.indiana.presenter;

import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.indiana.mvpview.IGoodsDetailView;

/**
 * Case By:商品详情
 * package:wiki.scene.shop.ui.indiana.presenter
 * Author：scene on 2017/7/4 11:41
 */

public class GoodsDetailPresenter extends BasePresenter<IGoodsDetailView> {
    private IGoodsDetailView goodsDetailView;

    public GoodsDetailPresenter(IGoodsDetailView goodsDetailView) {
        this.goodsDetailView = goodsDetailView;
    }
}
