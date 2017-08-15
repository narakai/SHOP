package wiki.scene.shop.ui.mine.model;

import android.text.TextUtils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.base.LzyResponse;
import wiki.scene.shop.http.callback.JsonCallback;
import wiki.scene.shop.http.listener.HttpResultListener;

/**
 * 晒单界面
 * Created by scene on 17-8-14.
 */

public class ShareOrderModel {
    public void shareOrder(HttpParams params, final HttpResultListener<String> listener) {
        OkGo.<LzyResponse<String>>post(ApiUtil.API_PRE + ApiUtil.SHARE_ORDER)
                .tag(ApiUtil.SHARE_ORDER_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<String>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<String>> response) {
                        listener.onSuccess("");
                    }

                    @Override
                    public void onError(Response<LzyResponse<String>> response) {
                        super.onError(response);
                        if (response.getException() != null && !TextUtils.isEmpty(response.getException().getMessage())) {
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
