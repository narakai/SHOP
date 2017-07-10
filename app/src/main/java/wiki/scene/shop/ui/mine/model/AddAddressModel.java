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
 * Case By:新增或者修改收货地址
 * package:wiki.scene.shop.ui.mine.model
 * Author：scene on 2017/7/10 10:56
 */

public class AddAddressModel {

    public void addOrEditAddress(HttpParams params, final HttpResultListener<String> httpResultListener) {
        OkGo.<LzyResponse<List<String>>>post(ApiUtil.API_PRE + ApiUtil.ADD_OR_EDIT_ADDRESS)
                .tag(ApiUtil.ADD_OR_EDIT_ADDRESS_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<List<String>>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<List<String>>> response) {
                        httpResultListener.onSuccess("");
                    }

                    @Override
                    public void onError(Response<LzyResponse<List<String>>> response) {
                        super.onError(response);
                        if (response.getException() != null) {
                            httpResultListener.onFail(response.getException().getMessage());
                        } else {
                            httpResultListener.onFail(response.message());
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        httpResultListener.onFinish();
                    }
                });
    }
}
