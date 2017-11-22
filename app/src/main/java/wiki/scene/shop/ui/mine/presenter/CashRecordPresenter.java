package wiki.scene.shop.ui.mine.presenter;

import com.lzy.okgo.model.HttpParams;

import wiki.scene.shop.entity.CashRecordResultInfo;
import wiki.scene.shop.http.listener.HttpResultListener;
import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.model.CashRecordModel;
import wiki.scene.shop.ui.mine.mvpview.ICashRecordView;

/**
 * 提现记录
 * Created by scene on 2017/11/15.
 */

public class CashRecordPresenter extends BasePresenter<ICashRecordView> {
    private CashRecordModel cashRecordModel;

    public CashRecordPresenter(ICashRecordView cashRecordView) {
        this.mView = cashRecordView;
        cashRecordModel = new CashRecordModel();
    }

    public void getExchangeRecord(final boolean isFirst, final int page) {
        try {
            if (isFirst) {
                mView.showLoadingPage();
            }
            HttpParams params = new HttpParams();
            params.put("page", page);
            cashRecordModel.getRecord(params, new HttpResultListener<CashRecordResultInfo>() {
                @Override
                public void onSuccess(CashRecordResultInfo data) {
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
