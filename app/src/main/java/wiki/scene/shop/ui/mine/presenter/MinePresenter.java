package wiki.scene.shop.ui.mine.presenter;

import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.ui.mine.mvpview.IMineView;
import wiki.scene.shop.mvp.BasePresenter;

/**
 * Case By:
 * package:wiki.scene.shop.fragment.mine.presenter
 * Author：scene on 2017/6/29 11:22
 */

public class MinePresenter extends BasePresenter<IMineView> {
    private IMineView mineView;

    public MinePresenter(IMineView mineView) {
        this.mineView = mineView;
    }

    /**
     * 充值
     */
    public void recharge() {
        if (mineView != null) {
            if (ShopApplication.hasLogin) {
                mineView.enterRecharge();
            } else {
                mineView.enterLogin();
            }
        }
    }

    public void clickIndianaRecord() {
        if (mineView != null) {
            if (ShopApplication.userInfo != null && ShopApplication.hasLogin) {
                mineView.enterIndianaRecord();
            } else {
                mView.enterLogin();
            }
        }
    }

    public void clickWinRecord() {
        if (mineView != null) {
            if (ShopApplication.userInfo != null && ShopApplication.hasLogin) {
                mineView.enterWinRecord();
            } else {
                mView.enterLogin();
            }
        }
    }
    public void clickMyCollect() {
        if (mineView != null) {
            if (ShopApplication.userInfo != null && ShopApplication.hasLogin) {
                mineView.enterMyCollect();
            } else {
                mView.enterLogin();
            }
        }
    }

    public void clickIndianaRaiders() {

    }

    public void clickMineShare() {
        if (mineView != null) {
            if (ShopApplication.userInfo != null && ShopApplication.hasLogin) {
                mineView.enterMineShare();
            } else {
                mView.enterLogin();
            }
        }
    }

    public void clickMineRed() {
        if (mineView != null) {
            if (ShopApplication.userInfo != null && ShopApplication.hasLogin) {
                mineView.enterMineRed();
            } else {
                mView.enterLogin();
            }
        }
    }

    public void clickMineReceiverAddress() {
        if (mineView != null) {
            if (ShopApplication.userInfo != null && ShopApplication.hasLogin) {
                mineView.enterReceiverAddress();
            } else {
                mView.enterLogin();
            }
        }
    }

    public void clickServiceCenter() {

    }

    public void clickShareApp() {

    }

    public void clickSetting() {
        if (mineView != null) {
            mineView.enterSetting();
        }
    }

    /**
     * 夺宝记录
     */
    public void enterIndianaRecord() {
        if (mineView != null) {
            //需要判断是否登录
            mineView.enterLogin();
        }
    }

}
