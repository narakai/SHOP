package wiki.scene.shop.ui.mine.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.List;

import wiki.scene.shop.entity.PrizeInfo;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.base.LzyResponse;
import wiki.scene.shop.http.callback.JsonCallback;
import wiki.scene.shop.http.listener.HttpResultListener;

/**
 * 兑换
 * Created by scene on 2017/11/21.
 */

public class ExchangeModel {
    public void getPrizeData(final HttpResultListener<List<PrizeInfo>> listener) {
        OkGo.<LzyResponse<List<PrizeInfo>>>get(ApiUtil.API_PRE + ApiUtil.PRIZE_LIST)
                .tag(ApiUtil.PRIZE_LIST_TAG)
                .execute(new JsonCallback<LzyResponse<List<PrizeInfo>>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<List<PrizeInfo>>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<List<PrizeInfo>>> response) {
                        super.onError(response);
                        try {
                            listener.onFail(response.getException().getMessage() != null ? response.getException().getMessage() : response.message());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        listener.onFinish();
                    }
                });
    }

    /**
     * 兑换
     */
    public void exchangePrize(HttpParams params, final HttpResultListener<String> listener) {
        OkGo.<LzyResponse<String>>post(ApiUtil.API_PRE + ApiUtil.EXCHANGE)
                .tag(ApiUtil.EXCHANGE_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<String>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<String>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<String>> response) {
                        super.onError(response);
                        try {
                            listener.onFail(response.getException().getMessage() != null ? response.getException().getMessage() : response.message());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        try {
                            listener.onFinish();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
