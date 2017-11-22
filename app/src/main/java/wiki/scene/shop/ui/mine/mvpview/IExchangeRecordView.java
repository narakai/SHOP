package wiki.scene.shop.ui.mine.mvpview;

import wiki.scene.shop.entity.ExchangeRecordResultInfo;
import wiki.scene.shop.mvp.BaseView;

/**
 * 兑换记录
 * Created by scene on 2017/11/15.
 */

public interface IExchangeRecordView extends BaseView {
    void showLoadingPage();

    void showContentPage();

    void showFailPage();

    void refreshComplite();

    void loadmoreCompliteSuccess(boolean hasMore);

    void loadmoreCompliteFail();

    void getRecordDataSuccess(ExchangeRecordResultInfo data, int currentPage);
}
