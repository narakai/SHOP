package wiki.scene.shop.ui.mine.model;

import android.text.TextUtils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import wiki.scene.shop.entity.RechargeInfo;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.base.LzyResponse;
import wiki.scene.shop.http.callback.JsonCallback;
import wiki.scene.shop.http.listener.HttpResultListener;

/**
 * 充值
 * Created by scene on 17-8-16.
 */

public class RechargeModel {
    public void rechage(HttpParams params, final HttpResultListener<RechargeInfo> listener) {
        OkGo.<LzyResponse<RechargeInfo>>post(ApiUtil.API_PRE + ApiUtil.RECHARGE)
                .tag(ApiUtil.RECHARGE_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<RechargeInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<RechargeInfo>> response) {
                        listener.onSuccess(response.body().data);
                    }

                    @Override
                    public void onError(Response<LzyResponse<RechargeInfo>> response) {
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
