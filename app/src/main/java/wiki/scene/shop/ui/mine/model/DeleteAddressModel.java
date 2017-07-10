package wiki.scene.shop.ui.mine.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.List;

import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.base.LzyResponse;
import wiki.scene.shop.http.callback.JsonCallback;
import wiki.scene.shop.http.listener.HttpResultListener;

/**
 * Case By:删除收货地址
 * package:wiki.scene.shop.ui.mine.model
 * Author：scene on 2017/7/10 13:01
 */

public class DeleteAddressModel {
    public void deleteAddress(HttpParams params, final HttpResultListener<String> resultListener) {
        OkGo.<LzyResponse<List<String>>>post(ApiUtil.API_PRE + ApiUtil.DELETE_RECEIVER_ADDRESS)
                .tag(ApiUtil.DELETE_RECEIVER_ADDRESS_ATG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<List<String>>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<List<String>>> response) {
                        resultListener.onSuccess("");
                    }

                    @Override
                    public void onError(Response<LzyResponse<List<String>>> response) {
                        super.onError(response);
                        if (response.getException() == null) {
                            resultListener.onFail(response.message());
                        } else {
                            resultListener.onFail(response.getException().getMessage());
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        resultListener.onFinish();
                    }
                });
    }
}
