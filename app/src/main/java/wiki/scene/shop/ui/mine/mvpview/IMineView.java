package wiki.scene.shop.ui.mine.mvpview;

import wiki.scene.shop.mvp.BaseView;

/**
 * Case By:我的
 * package:wiki.scene.shop.fragment.mine.mvpview
 * Author：scene on 2017/6/29 11:22
 */

public interface IMineView extends BaseView {
    void enterLogin();

    void enterIndianaRecord();

    void enterWinRecord();

    void enterIndianaRaiders();

    void enterMineShare();

    void enterMineRed();

    void enterReceiverAddress();

    void enterServiceCenter();

    void enterShareApp();

    void enterSetting();

    void hasLogin();

    void hasNoLogin();

    void enterRecharge();

    void enterMyCollect();

    void enterMineBankCard();

    void enterDrawCashRecord();
}
