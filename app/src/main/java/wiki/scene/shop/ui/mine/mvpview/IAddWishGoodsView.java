package wiki.scene.shop.ui.mine.mvpview;

import android.support.annotation.StringRes;

import wiki.scene.shop.mvp.BaseView;

/**
 * Case By:添加愿望商品
 * package:wiki.scene.shop.ui.mine.mvpview
 * Author：scene on 2017/6/30 15:19
 */

public interface IAddWishGoodsView extends BaseView {
    void showMessage(@StringRes int resId);

    void showMessage(String message);

    void addGoodsSuccess();
}
