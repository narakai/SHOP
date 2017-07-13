package wiki.scene.shop.ui.car.mvpview;

import java.util.List;

import wiki.scene.shop.entity.CartInfo;
import wiki.scene.shop.entity.ListGoodsInfo;
import wiki.scene.shop.mvp.BaseView;

/**
 * Case By:购物车
 * package:wiki.scene.shop.fragment.car.mvpview
 * Author：scene on 2017/6/29 14:28
 */

public interface ICarView extends BaseView {
    void refreshComplete();

    void loadDataFail();

    void loadDataSuccess();

    void showEmptyCart();

    void showMessage(String message);

    void bindCartData(List<CartInfo> list);

    void bindGuessLikeData(List<ListGoodsInfo> list);

    void showTotalPrice(int totalPrice);
}
