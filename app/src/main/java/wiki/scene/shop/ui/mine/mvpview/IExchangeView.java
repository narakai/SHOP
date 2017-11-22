package wiki.scene.shop.ui.mine.mvpview;

import java.util.List;

import wiki.scene.shop.entity.PrizeInfo;
import wiki.scene.shop.mvp.BaseView;

/**
 * 兑换
 * Created by scene on 2017/11/21.
 */

public interface IExchangeView extends BaseView {

    void showLoadingPage();

    void showContentPage();

    void showFailPage();

    void refreshComplite();

    void showMessage(String message);

    void getPrizeInfoListSuccess(List<PrizeInfo> data);
}
