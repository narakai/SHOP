package wiki.scene.shop.ui.mine.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.List;

import wiki.scene.shop.entity.CreateOrderInfo;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.base.LzyResponse;
import wiki.scene.shop.http.callback.JsonCallback;
import wiki.scene.shop.http.listener.HttpResultListener;

/**
 * 我的红包
 * Created by scene on 17-8-15.
 */

public class MineRedModel {
    public void getMineRedData(HttpParams params, final HttpResultListener<List<CreateOrderInfo.CouponsBean>> listener) {
        OkGo.<LzyResponse<List<CreateOrderInfo.CouponsBean>>>get(ApiUtil.API_PRE + ApiUtil.MINE_RED)
                .tag(ApiUtil.MINE_RED_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<List<CreateOrderInfo.CouponsBean>>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<List<CreateOrderInfo.CouponsBean>>> response) {
                        listener.onSuccess(response.body().data);
                    }

                    @Override
                    public void onError(Response<LzyResponse<List<CreateOrderInfo.CouponsBean>>> response) {
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
