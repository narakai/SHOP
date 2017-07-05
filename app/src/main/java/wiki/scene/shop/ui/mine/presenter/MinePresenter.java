package wiki.scene.shop.ui.mine.presenter;

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
            //需要判断是否登录
            mineView.enterLogin();
        }
    }

    public void clickIndianaRecord() {

    }

    public void clickWinRecord() {
        if(mineView!=null){
            mineView.enterWinRecord();
        }
    }

    public void clickIndianaRaiders() {

    }

    public void clickMineShare() {
        if (mineView != null) {
            mineView.enterMineShare();
        }
    }

    public void clickMineRed() {
        if(mineView!=null){
            mineView.enterMineRed();
        }
    }

    public void clickMineReceiverAddress() {
        if(mineView!=null){
            mineView.enterReceiverAddress();
        }
    }

    public void clickServiceCenter() {

    }

    public void clickShareApp() {

    }

    public void clickSetting() {
        if(mineView!=null){
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
