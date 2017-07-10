package wiki.scene.shop.ui.mine.mvpview;

import android.support.annotation.StringRes;

import java.util.List;

import wiki.scene.shop.entity.AddressInfo;
import wiki.scene.shop.mvp.BaseView;

/**
 * Case By:我的收货地址
 * package:wiki.scene.shop.ui.mine.mvpview
 * Author：scene on 2017/6/30 10:19
 */

public interface IMineReceiverAddressView extends BaseView {
    void getAddressSuccess(List<AddressInfo> list, boolean isRefresh);

    void refreshFail();

    void loadFail();

    void deleteFail();

    void deleteSuccess(int position);

    void showProgressDialog(@StringRes int resId);

    void hideProgressDialog();

    void setDefaultFail();

    void setDefaultSuccess(int position);
}
