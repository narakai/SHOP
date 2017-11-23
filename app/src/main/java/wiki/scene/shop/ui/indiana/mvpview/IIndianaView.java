package wiki.scene.shop.ui.indiana.mvpview;

import java.util.List;

import wiki.scene.shop.entity.IndexInfo;
import wiki.scene.shop.entity.NewestWinInfo;
import wiki.scene.shop.mvp.BaseView;

/**
 * Case By:夺宝
 * package:wiki.scene.shop.fragment.indiana.mvpview
 * Author：scene on 2017/6/28 10:20
 */

public interface IIndianaView extends BaseView {

    void getDataSuccess(IndexInfo indexInfo);

    void showMessage(String msg);

    void showFailPage();

    void refreshComplete();

    void refrshFail(String message);

    void getNewestWinSuccess(List<NewestWinInfo> list);

    void getNewestBuySuccess(List<NewestWinInfo> list);

    void enterRecharge();

    void enterExchange();

    void enterDrawCash();

    void enterLogin();
}
