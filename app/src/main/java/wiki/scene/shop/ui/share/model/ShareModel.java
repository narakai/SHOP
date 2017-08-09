package wiki.scene.shop.ui.share.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import wiki.scene.shop.entity.ShareListResultInfo;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.base.LzyResponse;
import wiki.scene.shop.http.callback.JsonCallback;
import wiki.scene.shop.http.listener.HttpResultListener;

/**
 * 晒单
 * Created by scene on 17-8-9.
 */

public class ShareModel {
    public void getShareListData(HttpParams params, final HttpResultListener<ShareListResultInfo> listener) {
        OkGo.<LzyResponse<ShareListResultInfo>>get(ApiUtil.API_PRE + ApiUtil.SHARE_LIST)
                .tag(ApiUtil.SHARE_LIST_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<ShareListResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<ShareListResultInfo>> response) {
                        listener.onSuccess(response.body().data);
                    }

                    @Override
                    public void onError(Response<LzyResponse<ShareListResultInfo>> response) {
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
    public void getMyShareListData(HttpParams params, final HttpResultListener<ShareListResultInfo> listener) {
        OkGo.<LzyResponse<ShareListResultInfo>>get(ApiUtil.API_PRE + ApiUtil.MINE_SHARE_LIST)
                .tag(ApiUtil.MINE_SHARE_LIST_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<ShareListResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<ShareListResultInfo>> response) {
                        listener.onSuccess(response.body().data);
                    }

                    @Override
                    public void onError(Response<LzyResponse<ShareListResultInfo>> response) {
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
