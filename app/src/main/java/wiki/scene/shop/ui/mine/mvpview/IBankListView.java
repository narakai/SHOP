package wiki.scene.shop.ui.mine.mvpview;

import java.util.List;

import wiki.scene.shop.entity.BankInfo;
import wiki.scene.shop.mvp.BaseView;

/**
 * 收款账号
 * Created by scene on 2017/11/14.
 */

public interface IBankListView extends BaseView {

    void showFailPage();

    void showContentPage();

    void showLoadingPage();

    void showMessage(String message);

    void refreshComplite();

    void getBankListDataSuccess(List<BankInfo> list);
}
