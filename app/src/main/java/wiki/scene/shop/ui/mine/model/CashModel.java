package wiki.scene.shop.ui.mine.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.List;

import wiki.scene.shop.entity.BankInfo;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.base.LzyResponse;
import wiki.scene.shop.http.callback.JsonCallback;
import wiki.scene.shop.http.listener.HttpResultListener;

/**
 * 提现
 * Created by scene on 2017/11/14.
 */

public class CashModel {
    public void getBankList(final HttpResultListener<List<BankInfo>> listener) {
        OkGo.<LzyResponse<List<BankInfo>>>get(ApiUtil.API_PRE + ApiUtil.BANK_LIST)
                .tag(ApiUtil.BANK_LIST_TAG)
                .execute(new JsonCallback<LzyResponse<List<BankInfo>>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<List<BankInfo>>> response) {
                        listener.onSuccess(response.body().data);
                    }

                    @Override
                    public void onError(Response<LzyResponse<List<BankInfo>>> response) {
                        try {
                            listener.onFail(response.getException().getMessage() != null ? response.getException().getMessage() : response.message());
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("银行卡信息获取失败");
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        listener.onFinish();
                    }
                });
    }

    public void applyCash(HttpParams params, final HttpResultListener<String> listener) {
        OkGo.<LzyResponse<String>>post(ApiUtil.API_PRE + ApiUtil.APPPY_CASH)
                .tag(ApiUtil.APPLY_CASH_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<String>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<String>> response) {
                        listener.onSuccess("");
                    }

                    @Override
                    public void onError(Response<LzyResponse<String>> response) {
                        try {
                            listener.onFail(response.getException().getMessage() != null ? response.getException().getMessage() : response.message());
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("银行卡信息获取失败");
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
