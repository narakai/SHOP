package wiki.scene.shop.ui.mine.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import wiki.scene.shop.entity.CashRecordResultInfo;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.base.LzyResponse;
import wiki.scene.shop.http.callback.JsonCallback;
import wiki.scene.shop.http.listener.HttpResultListener;

/**
 * 提现记录
 * Created by scene on 2017/11/15.
 */

public class CashRecordModel {
    public void getRecord(HttpParams params, final HttpResultListener<CashRecordResultInfo> listener) {
        OkGo.<LzyResponse<CashRecordResultInfo>>get(ApiUtil.API_PRE + ApiUtil.CASH_RECORD)
                .tag(ApiUtil.CASH_RECORD_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<CashRecordResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<CashRecordResultInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<CashRecordResultInfo>> response) {
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
                        try {
                            listener.onFinish();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
