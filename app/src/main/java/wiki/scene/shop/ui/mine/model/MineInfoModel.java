package wiki.scene.shop.ui.mine.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.base.LzyResponse;
import wiki.scene.shop.http.callback.JsonCallback;
import wiki.scene.shop.http.listener.HttpResultListener;

/**
 * Case By:个人资料
 * package:wiki.scene.shop.ui.mine.model
 * Author：scene on 2017/7/6 17:10
 */

public class MineInfoModel {
    public void updateUserInfo(HttpParams params, final HttpResultListener<String> resultListener) {
        OkGo.<LzyResponse<String>>post(ApiUtil.API_PRE + ApiUtil.UPDATE_USERINFO).tag(ApiUtil.UPDATE_USERINFO_TAG).params(params).execute(new JsonCallback<LzyResponse<String>>() {
            @Override
            public void onSuccess(Response<LzyResponse<String>> response) {
                resultListener.onSuccess(response.message());
            }

            @Override
            public void onError(Response<LzyResponse<String>> response) {
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
}
