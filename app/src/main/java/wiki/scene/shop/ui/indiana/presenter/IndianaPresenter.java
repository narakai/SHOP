package wiki.scene.shop.ui.indiana.presenter;

import java.util.List;

import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.entity.IndexInfo;
import wiki.scene.shop.entity.NewestWinInfo;
import wiki.scene.shop.http.listener.HttpResultListener;
import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.indiana.model.IndianaModel;
import wiki.scene.shop.ui.indiana.mvpview.IIndianaView;

/**
 * Case By:夺宝
 * package:wiki.scene.shop.fragment.indiana.presenter
 * Author：scene on 2017/6/28 10:20
 */

public class IndianaPresenter extends BasePresenter<IIndianaView> {
    private IIndianaView indianaView;
    private IndianaModel model;

    public IndianaPresenter(IIndianaView indianaView) {
        this.indianaView = indianaView;
        model = new IndianaModel();
    }

    public void getIndianaData(final boolean isFirst, final boolean isNeedRefreshGoods) {
        if (indianaView != null) {
            if (!isFirst) {
                indianaView.showLoading(R.string.loading);
            }
            model.getIndianaIndexData(new HttpResultListener<IndexInfo>() {
                @Override
                public void onSuccess(IndexInfo data) {
                    if (indianaView != null) {
                        if (!isFirst) {
                            indianaView.hideLoading();
                        } else {
                            indianaView.refreshComplete();
                        }
                        if(isNeedRefreshGoods){
                            indianaView. refreshGoodsInfo(data.getCurrent_cycle());
                        }else{
                            indianaView.getDataSuccess(data);
                        }
                    }
                }

                @Override
                public void onFail(String message) {
                    if (indianaView != null) {
                        if (isFirst) {
                            indianaView.showFailPage();
                        } else {
                            indianaView.refrshFail(message);
                        }
                    }
                }

                @Override
                public void onFinish() {
                }
            });
        }
    }

    /**
     * 获取最新中奖信息
     */
    public void getNewestWinInfo() {
        model.getNewestWin(new HttpResultListener<List<NewestWinInfo>>() {
            @Override
            public void onSuccess(List<NewestWinInfo> data) {
                try {
                    indianaView.getNewestWinSuccess(data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String message) {
//                try{
//                    indianaView.showMessage(message);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
            }

            @Override
            public void onFinish() {
            }
        });
    }

    /**
     * 获取最新参与信息
     */
    public void getNewestBuyInfo() {
        model.getNewestBuy(new HttpResultListener<List<NewestWinInfo>>() {
            @Override
            public void onSuccess(List<NewestWinInfo> data) {
                try {
                    indianaView.getNewestBuySuccess(data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String message) {
            }

            @Override
            public void onFinish() {

            }

        });
    }

    /**
     * 充值
     */
    public void clickRecharge() {
        try {
            if (indianaView != null) {
                if (ShopApplication.hasLogin) {
                    indianaView.enterRecharge();
                } else {
                    indianaView.enterLogin();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void clickExchange() {
        try {
            if (ShopApplication.userInfo != null && ShopApplication.hasLogin) {
                indianaView.enterExchange();
            } else {
                indianaView.enterLogin();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickDrawCash() {
        try {
            if (ShopApplication.userInfo != null && ShopApplication.hasLogin) {
                indianaView.enterDrawCash();
            } else {
                indianaView.enterLogin();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
