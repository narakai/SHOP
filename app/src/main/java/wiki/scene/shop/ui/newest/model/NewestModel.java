package wiki.scene.shop.ui.newest.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import wiki.scene.shop.entity.NewestResultInfo;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.base.LzyResponse;
import wiki.scene.shop.http.callback.JsonCallback;
import wiki.scene.shop.http.listener.HttpResultListener;

/**
 * 最新揭晓
 * Created by scene on 17-8-9.
 */

public class NewestModel {
    public void getNewestOpenData(HttpParams params, final HttpResultListener<NewestResultInfo> listener) {
        OkGo.<LzyResponse<NewestResultInfo>>get(ApiUtil.API_PRE + ApiUtil.NEWEST_OPEN)
                .tag(ApiUtil.NEWEST_OPEN_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<NewestResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<NewestResultInfo>> response) {
                        listener.onSuccess(response.body().data);
                    }

                    @Override
                    public void onError(Response<LzyResponse<NewestResultInfo>> response) {
                        super.onError(response);
                        listener.onFail(response.getException() == null ? response.message() : response.getException().getMessage());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        listener.onFinish();
                    }
                });
    }
}
