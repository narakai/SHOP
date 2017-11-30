package wiki.scene.shop.ui.mine.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import wiki.scene.shop.entity.MineOrderResultInfo;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.base.LzyResponse;
import wiki.scene.shop.http.callback.JsonCallback;
import wiki.scene.shop.http.listener.HttpResultListener;

/**
 * 夺宝记录
 * Created by scene on 17-8-1.
 */

public class OthersIndianaRecordModel {
    public void getIndianaRecordData(HttpParams params, final HttpResultListener<MineOrderResultInfo> listener) {
        OkGo.<LzyResponse<MineOrderResultInfo>>get(ApiUtil.API_PRE + ApiUtil.OTHERS_INDIANA_RECORD)
                .tag(ApiUtil.OTHERS_INDIANA_RECORD_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<MineOrderResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<MineOrderResultInfo>> response) {
                        listener.onSuccess(response.body().data);
                    }

                    @Override
                    public void onError(Response<LzyResponse<MineOrderResultInfo>> response) {
                        super.onError(response);
                        if (response.getException() != null) {
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

    public void getWinIndianaRecordData(HttpParams params, final HttpResultListener<MineOrderResultInfo> listener) {
        OkGo.<LzyResponse<MineOrderResultInfo>>get(ApiUtil.API_PRE + ApiUtil.OTHERS_WIN_RECORD)
                .tag(ApiUtil.OTHERS_WIN_RECORD_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<MineOrderResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<MineOrderResultInfo>> response) {
                        listener.onSuccess(response.body().data);
                    }

                    @Override
                    public void onError(Response<LzyResponse<MineOrderResultInfo>> response) {
                        super.onError(response);
                        if (response.getException() != null) {
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
