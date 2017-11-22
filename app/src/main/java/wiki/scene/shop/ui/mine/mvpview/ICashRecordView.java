package wiki.scene.shop.ui.mine.mvpview;

import wiki.scene.shop.entity.CashRecordResultInfo;
import wiki.scene.shop.mvp.BaseView;

/**
 * 提现记录
 * Created by scene on 2017/11/15.
 */

public interface ICashRecordView extends BaseView {
    void showLoadingPage();

    void showContentPage();

    void showFailPage();

    void refreshComplite();

    void loadmoreCompliteSuccess(boolean hasMore);

    void loadmoreCompliteFail();

    void getRecordDataSuccess(CashRecordResultInfo data, int currentPage);
}
