package wiki.scene.shop.ui.car.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.base.LzyResponse;
import wiki.scene.shop.http.callback.JsonCallback;
import wiki.scene.shop.http.listener.HttpResultListener;

/**
 * 订单支付
 * Created by scene on 17-7-21.
 */

public class PayOrderModel {
    /**
     * 获取订单支付信息
     */
    public void getPayInfo(HttpParams params, final HttpResultListener<String> listener) {
        OkGo.<LzyResponse<String>>post(ApiUtil.API_PRE + ApiUtil.PAY)
                .tag(ApiUtil.PAY_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<String>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<String>> response) {
                        listener.onSuccess(response.body().data);
                    }

                    @Override
                    public void onError(Response<LzyResponse<String>> response) {
                        super.onError(response);
                        if (response.getException() != null) {
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
