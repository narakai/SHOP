package wiki.scene.shop.ui.mine.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.List;

import wiki.scene.shop.entity.AddressInfo;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.base.LzyResponse;
import wiki.scene.shop.http.callback.JsonCallback;
import wiki.scene.shop.http.listener.HttpResultListener;

/**
 * Case By:我的收货地址
 * package:wiki.scene.shop.ui.mine.model
 * Author：scene on 2017/7/10 10:27
 */

public class MineReceiverAddressModel {

    public void getAllReceiverAddress(HttpParams params, final HttpResultListener<List<AddressInfo>> resultListener) {
        OkGo.<LzyResponse<List<AddressInfo>>>get(ApiUtil.API_PRE + ApiUtil.GET_ALL_ADDRESS)
                .params(params)
                .tag(ApiUtil.GET_ALL_ADDRESS_TAG)
                .execute(new JsonCallback<LzyResponse<List<AddressInfo>>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<List<AddressInfo>>> response) {
                        resultListener.onSuccess(response.body().data);
                    }

                    @Override
                    public void onError(Response<LzyResponse<List<AddressInfo>>> response) {
                        super.onError(response);
                        resultListener.onFail(response.getException().getMessage());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        resultListener.onFinish();
                    }
                });
    }

    public void setAddressDefault(HttpParams params, final HttpResultListener<String> resultListener) {
        OkGo.<LzyResponse<List<AddressInfo>>>post(ApiUtil.API_PRE + ApiUtil.SET_DEFAULT_ADDRESS)
                .params(params)
                .tag(ApiUtil.SET_DEFAULT_ADDRESS_TAG)
                .execute(new JsonCallback<LzyResponse<List<AddressInfo>>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<List<AddressInfo>>> response) {
                        resultListener.onSuccess("");
                    }

                    @Override
                    public void onError(Response<LzyResponse<List<AddressInfo>>> response) {
                        super.onError(response);
                        if (response.getException() != null) {
                            resultListener.onFail(response.getException().getMessage());
                        } else {
                            resultListener.onFail(response.message());
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
