package wiki.scene.shop.ui.mine.mvpview;

import android.support.annotation.StringRes;

import java.util.List;

import wiki.scene.shop.entity.CreateOrderInfo;
import wiki.scene.shop.mvp.BaseListView;

/**
 * Case By:我的红包
 * package:wiki.scene.shop.ui.mine.mvpview
 * Author：scene on 2017/6/30 13:23
 */

public interface IMineRedView extends BaseListView {
    void showMessage(@StringRes int resId);

    void showMessage(String message);

    void refreshCompilt();

    void bindData(List<CreateOrderInfo.CouponsBean> list);
}
