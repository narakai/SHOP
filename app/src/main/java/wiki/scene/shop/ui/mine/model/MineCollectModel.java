package wiki.scene.shop.ui.mine.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import wiki.scene.shop.entity.MineCollectionResultInfo;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.base.LzyResponse;
import wiki.scene.shop.http.callback.JsonCallback;
import wiki.scene.shop.http.listener.HttpResultListener;

/**
 * 我的收藏
 * Created by scene on 17-8-14.
 */

public class MineCollectModel {
    public void getMineCollectData(HttpParams params, final HttpResultListener<MineCollectionResultInfo> listener) {
        OkGo.<LzyResponse<MineCollectionResultInfo>>get(ApiUtil.API_PRE + ApiUtil.MINE_COLLECTION)
                .tag(ApiUtil.MINE_COLLECTION_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<MineCollectionResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<MineCollectionResultInfo>> response) {
                        listener.onSuccess(response.body().data);
                    }

                    @Override
                    public void onError(Response<LzyResponse<MineCollectionResultInfo>> response) {
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
