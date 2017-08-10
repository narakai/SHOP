package wiki.scene.shop.ui.mine.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.base.LzyResponse;
import wiki.scene.shop.http.callback.JsonCallback;
import wiki.scene.shop.http.listener.HttpResultListener;

/**
 * 问题反馈
 * Created by scene on 17-8-10.
 */

public class BugFeedBackModel {
    public void bugFeedBack(HttpParams params, final HttpResultListener<String> listener) {
        OkGo.<LzyResponse<String>>post(ApiUtil.API_PRE + ApiUtil.BUG_FEED_BACK)
                .tag(ApiUtil.BUG_FEED_BACK_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<String>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<String>> response) {
                        listener.onSuccess("");
                    }

                    @Override
                    public void onError(Response<LzyResponse<String>> response) {
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
