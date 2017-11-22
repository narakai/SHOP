package wiki.scene.shop.ui.mine.presenter;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.lzy.okgo.model.HttpParams;

import java.util.List;

import wiki.scene.shop.R;
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

    /**
     * 兑换
     */
    public void exchange() {
        try {
            if (StringUtils.isEmpty(mView.getPrizeIds()) || StringUtils.isEmpty(mView.getNumbers())) {
                mView.showMessage("请选择你要兑换的奖品");
                return;
            }
            mView.showLoading(R.string.loading);
            HttpParams params = new HttpParams();
            params.put("prize_ids", mView.getPrizeIds());
            params.put("numbers", mView.getNumbers());
            model.exchangePrize(params, new HttpResultListener<String>() {
                @Override
                public void onSuccess(String data) {
                    try {
                        mView.exchangeSuccess();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        mView.exchangeFail();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFinish() {
                    try {
                        mView.hideLoading();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
