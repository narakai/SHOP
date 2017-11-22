package wiki.scene.shop.ui.mine.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import wiki.scene.shop.entity.PkResultInfo;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.base.LzyResponse;
import wiki.scene.shop.http.callback.JsonCallback;
import wiki.scene.shop.http.listener.HttpResultListener;

/**
 * 订单pk
 * Created by scene on 2017/11/22.
 */

public class OrderPkDetailModel {
    public void getPKDetail(HttpParams params, final HttpResultListener<PkResultInfo> listener) {
        OkGo.<LzyResponse<PkResultInfo>>get(ApiUtil.API_PRE + ApiUtil.PK)
                .tag(ApiUtil.PAY_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<PkResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<PkResultInfo>> response) {
                        listener.onSuccess(response.body().data);
                    }

                    @Override
                    public void onError(Response<LzyResponse<PkResultInfo>> response) {
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
}
