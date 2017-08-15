package wiki.scene.shop.ui.indiana.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import wiki.scene.shop.entity.Price10IndianaResultInfo;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.base.LzyResponse;
import wiki.scene.shop.http.callback.JsonCallback;
import wiki.scene.shop.http.listener.HttpResultListener;

/**
 * 10元夺宝 秒开夺宝
 * Created by scene on 17-8-15.
 */

public class Price10IndianaModel {

    public void getPrice10IndianaData(HttpParams params, final HttpResultListener<Price10IndianaResultInfo> listener) {
        OkGo.<LzyResponse<Price10IndianaResultInfo>>get(ApiUtil.API_PRE + ApiUtil.PRICE_10_INDIANA)
                .tag(ApiUtil.PRICE_10_INDIANA_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<Price10IndianaResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<Price10IndianaResultInfo>> response) {
                        listener.onSuccess(response.body().data);
                    }

                    @Override
                    public void onError(Response<LzyResponse<Price10IndianaResultInfo>> response) {
                        super.onError(response);
                        if (response.getException() != null && response.getException().getMessage() != null) {
                            listener.onFail(response.getException().getMessage());
                        } else {
                            listener.onFail(response.message());
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        listener.onFinish();
                    }
                });
    }


    public void getSceondOpenData(HttpParams params, final HttpResultListener<Price10IndianaResultInfo> listener) {
        OkGo.<LzyResponse<Price10IndianaResultInfo>>get(ApiUtil.API_PRE + ApiUtil.SECOND_INDIANA)
                .tag(ApiUtil.SECOND_INDIANA_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<Price10IndianaResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<Price10IndianaResultInfo>> response) {
                        listener.onSuccess(response.body().data);
                    }

                    @Override
                    public void onError(Response<LzyResponse<Price10IndianaResultInfo>> response) {
                        super.onError(response);
                        if (response.getException() != null && response.getException().getMessage() != null) {
                            listener.onFail(response.getException().getMessage());
                        } else {
                            listener.onFail(response.message());
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        listener.onFinish();
                    }
                });
    }
}
