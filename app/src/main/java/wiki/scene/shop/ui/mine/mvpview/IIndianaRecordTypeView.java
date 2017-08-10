package wiki.scene.shop.ui.mine.mvpview;

import android.support.annotation.StringRes;

import wiki.scene.shop.entity.CreateOrderInfo;
import wiki.scene.shop.entity.MineOrderResultInfo;
import wiki.scene.shop.mvp.BaseListView;

/**
 * Case By:夺宝记录
 * package:wiki.scene.shop.ui.mine.mvpview
 * Author：scene on 2017/7/5 13:49
 */

public interface IIndianaRecordTypeView extends BaseListView {
    void showMessage(String msg);

    void showMessage(@StringRes int resId);

    void refreshComplete();

    void getDataSuccess(MineOrderResultInfo resultInfo);

    void showProgressDialog(@StringRes int resId);

    void hideProgessDialog();

    void toPaySuccess(CreateOrderInfo createOrderInfo);

    void changePage(int page);

    void loadmoreFail();
}
