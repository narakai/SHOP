package wiki.scene.shop.ui.trend.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.List;

import wiki.scene.shop.entity.WinCodeInfo;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.base.LzyResponse;
import wiki.scene.shop.http.callback.JsonCallback;
import wiki.scene.shop.http.listener.HttpResultListener;

/**
 * 走势
 * Created by scene on 2017/11/8.
 */

public class TrendModel {

    public void getTreandListData(HttpParams params, final HttpResultListener<List<WinCodeInfo>> resultListener) {
        OkGo.<LzyResponse<List<WinCodeInfo>>>get(ApiUtil.API_PRE + ApiUtil.TREND_LIST)
                .tag(ApiUtil.TREND_LIST_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<List<WinCodeInfo>>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<List<WinCodeInfo>>> response) {
                        resultListener.onSuccess(response.body().data);
                    }

                    @Override
                    public void onError(Response<LzyResponse<List<WinCodeInfo>>> response) {
                        super.onError(response);
                        resultListener.onFail(response.getException() == null ? response.message() : response.getException().getMessage());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        resultListener.onFinish();
                    }
                });
    }
}
