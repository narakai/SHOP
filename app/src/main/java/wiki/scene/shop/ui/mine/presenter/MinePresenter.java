package wiki.scene.shop.ui.mine.presenter;

import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.entity.MineInfo;
import wiki.scene.shop.http.listener.HttpResultListener;
import wiki.scene.shop.ui.mine.model.MineModel;
import wiki.scene.shop.ui.mine.mvpview.IMineView;
import wiki.scene.shop.mvp.BasePresenter;

/**
 * Case By:
 * package:wiki.scene.shop.fragment.mine.presenter
 * Author：scene on 2017/6/29 11:22
 */

public class MinePresenter extends BasePresenter<IMineView> {
    private IMineView mineView;
    private MineModel model;

    public MinePresenter(IMineView mineView) {
        this.mineView = mineView;
        model = new MineModel();
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
                mineView.enterLogin();
            }
        }
    }

    public void clickMyCollect() {
        if (mineView != null) {
            if (ShopApplication.userInfo != null && ShopApplication.hasLogin) {
                mineView.enterMyCollect();
            } else {
                mineView.enterLogin();
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
                mineView.enterLogin();
            }
        }
    }

    public void clickMineRed() {
        if (mineView != null) {
            if (ShopApplication.userInfo != null && ShopApplication.hasLogin) {
                mineView.enterMineRed();
            } else {
                mineView.enterLogin();
            }
        }
    }

    public void clickMineReceiverAddress() {
        if (mineView != null) {
            if (ShopApplication.userInfo != null && ShopApplication.hasLogin) {
                mineView.enterReceiverAddress();
            } else {
                mineView.enterLogin();
            }
        }
    }

    public void clickMineBankCard() {
        try {
            if (ShopApplication.userInfo != null && ShopApplication.hasLogin) {
                mineView.enterMineBankCard();
            } else {
                mineView.enterLogin();
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public void clickDrawCashRecord() {
        try {
            if (ShopApplication.userInfo != null && ShopApplication.hasLogin) {
                mineView.enterDrawCashRecord();
            } else {
                mineView.enterLogin();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickExchangeRecord() {
        try {
            if (ShopApplication.userInfo != null && ShopApplication.hasLogin) {
                mineView.enterExchangeRecord();
            } else {
                mineView.enterLogin();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickMinePhone() {
        try {
            if (ShopApplication.userInfo != null && ShopApplication.hasLogin) {
                mineView.enterBindPhone();
            } else {
                mineView.enterLogin();
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    /**
     * 获取个人资料
     */
    public void getMineInfo(final boolean isFirst) {
        try {
            if (isFirst) {
                mineView.showLoadingPage();
            }
            model.getMineInfo(new HttpResultListener<MineInfo>() {
                @Override
                public void onSuccess(MineInfo data) {
                    try {
                        if (isFirst) {
                            mineView.showContentPage();
                        } else {
                            mineView.refreshComplite();
                        }
                        mineView.bindMineInfo(data);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    if (isFirst) {
                        mineView.showFailPage();
                    } else {
                        mineView.refreshComplite();
                        mineView.showMessage(message);
                    }
                }

                @Override
                public void onFinish() {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
