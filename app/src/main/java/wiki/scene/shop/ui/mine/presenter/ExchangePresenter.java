package wiki.scene.shop.ui.mine.presenter;

import java.util.List;

import wiki.scene.shop.entity.PrizeInfo;
import wiki.scene.shop.http.listener.HttpResultListener;
import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.model.ExchangeModel;
import wiki.scene.shop.ui.mine.mvpview.IExchangeView;

/**
 * 兑换
 * Created by scene on 2017/11/21.
 */

public class ExchangePresenter extends BasePresenter<IExchangeView> {
    private ExchangeModel model;

    public ExchangePresenter(IExchangeView exchangeView) {
        this.mView = exchangeView;
        model = new ExchangeModel();
    }

    /**
     * 获取奖品列表
     */
    public void getPrizeData(final boolean isFirst) {
        try {
            if (isFirst) {
                mView.showLoadingPage();
            }
            model.getPrizeData(new HttpResultListener<List<PrizeInfo>>() {
                @Override
                public void onSuccess(List<PrizeInfo> data) {
                    try {
                        if (isFirst) {
                            mView.showContentPage();
                        } else {
                            mView.refreshComplite();
                        }
                        mView.getPrizeInfoListSuccess(data);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        if (isFirst) {
                            mView.showFailPage();
                        } else {
                            mView.refreshComplite();
                            mView.showMessage(message);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
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
