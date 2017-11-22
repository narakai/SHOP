package wiki.scene.shop.ui.mine.presenter;

import com.lzy.okgo.model.HttpParams;

import wiki.scene.shop.entity.ExchangeRecordResultInfo;
import wiki.scene.shop.http.listener.HttpResultListener;
import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.model.ExchangeRecordModel;
import wiki.scene.shop.ui.mine.mvpview.IExchangeRecordView;

/**
 * 兑换记录
 * Created by scene on 2017/11/15.
 */

public class ExchangeRecordPresenter extends BasePresenter<IExchangeRecordView> {
    private ExchangeRecordModel recordModel;

    public ExchangeRecordPresenter(IExchangeRecordView recordView) {
        this.mView = recordView;
        recordModel = new ExchangeRecordModel();
    }

    public void getExchangeRecord(final boolean isFirst, final int page) {
        try {
            if (isFirst) {
                mView.showLoadingPage();
            }
            HttpParams params = new HttpParams();
            params.put("page", page);
            recordModel.getRecord(params, new HttpResultListener<ExchangeRecordResultInfo>() {
                @Override
                public void onSuccess(ExchangeRecordResultInfo data) {
                    try {
                        if (isFirst) {
                            mView.showContentPage();
                        } else {
                            mView.refreshComplite();
                        }
                        mView.loadmoreCompliteSuccess(data.getInfo().getPage_total() > page);
                        mView.getRecordDataSuccess(data, page);
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
                            mView.loadmoreCompliteFail();
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
