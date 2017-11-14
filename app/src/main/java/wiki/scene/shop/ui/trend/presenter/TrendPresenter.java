package wiki.scene.shop.ui.trend.presenter;

import com.lzy.okgo.model.HttpParams;

import java.util.List;

import wiki.scene.shop.entity.WinCodeInfo;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.listener.HttpResultListener;
import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.trend.model.TrendModel;
import wiki.scene.shop.ui.trend.view.ITrendView;

/**
 * 开奖走势
 * Created by scene on 2017/11/8.
 */

public class TrendPresenter extends BasePresenter<ITrendView> {
    private ITrendView trendView;
    private TrendModel model;

    public TrendPresenter(ITrendView trendView) {
        this.trendView = trendView;
        model = new TrendModel();
    }

    public void getTrendData(final boolean isFirst) {
        try {
            if (isFirst) {
                trendView.showLoadingPage();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpParams params = new HttpParams();
        params.put(ApiUtil.createParams());
        model.getTreandListData(params, new HttpResultListener<List<WinCodeInfo>>() {
            @Override
            public void onSuccess(List<WinCodeInfo> data) {
                try {
                    trendView.getTrendDataSuccess(data);
                    if (isFirst) {
                        trendView.hideLoading();
                    } else {
                        trendView.refreshComplete();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String message) {
                if (isFirst) {
                    trendView.showFailPage();
                } else {
                    trendView.refreshComplete();
                    trendView.showMessage(message);
                }
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
