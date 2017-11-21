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
 * 收款账号
 * Created by scene on 2017/11/14.
 */

public class BankListModel {
    public void getBankList(HttpParams params, final HttpResultListener<List<BankInfo>> listener) {
        OkGo.<LzyResponse<List<BankInfo>>>get(ApiUtil.API_PRE + ApiUtil.BANK_LIST)
                .tag(ApiUtil.BANK_LIST_TAG)
                .params(params)
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
}
