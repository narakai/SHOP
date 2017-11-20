package wiki.scene.shop.ui.indiana.mvpview;

import android.support.annotation.StringRes;

import java.util.List;

import wiki.scene.shop.entity.CreateOrderInfo;
import wiki.scene.shop.entity.GoodsDetailInfo;
import wiki.scene.shop.entity.ListGoodsInfo;
import wiki.scene.shop.mvp.BaseView;

/**
 * Case By:商品详情
 * package:wiki.scene.shop.ui.indiana.mvpview
 * Author：scene on 2017/7/4 11:40
 */

public interface IGoodsDetailView extends BaseView {
    void showMessage(String message);

    void showMessage(@StringRes int resId);

    void addCartSuccess();

    void showProgressDialog(@StringRes int resId);

    void hideProgressDialog();

    void refreshComplete();

    void bindGoodsInfo(GoodsDetailInfo.GoodsInfo goodsInfo);

    void showFailPage();

    void createOrderSuccess(CreateOrderInfo info);

    void hasCollected();

    void noCollected();

    void showCollectionStatus(boolean collectionStatus);
}
