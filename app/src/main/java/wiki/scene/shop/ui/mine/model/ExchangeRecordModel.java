package wiki.scene.shop.ui.mine.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.List;

import wiki.scene.shop.entity.ExchangeRecordResultInfo;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.base.LzyResponse;
import wiki.scene.shop.http.callback.JsonCallback;
import wiki.scene.shop.http.listener.HttpResultListener;

/**
 * 兑换记录
 * Created by scene on 2017/11/15.
 */

public class ExchangeRecordModel {
    public void getRecord(HttpParams params, final HttpResultListener<ExchangeRecordResultInfo> listener) {
        OkGo.<LzyResponse<ExchangeRecordResultInfo>>get(ApiUtil.API_PRE + ApiUtil.EXCHANGE_RECORD)
                .tag(ApiUtil.EXCHANGE_RECORD_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<ExchangeRecordResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<ExchangeRecordResultInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<ExchangeRecordResultInfo>> response) {
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
