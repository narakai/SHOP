package wiki.scene.shop.activity.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import wiki.scene.shop.entity.ConfigInfo;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.base.LzyResponse;
import wiki.scene.shop.http.callback.JsonCallback;
import wiki.scene.shop.http.listener.HttpResultListener;

/**
 * Case By:
 * package:wiki.scene.shop.activity.model
 * Author：scene on 2017/7/12 13:55
 */

public class LauncherModel {
    public void getUserSetting(final HttpResultListener<ConfigInfo> resultListener) {
        OkGo.<LzyResponse<ConfigInfo>>get(ApiUtil.API_PRE + ApiUtil.APP_CONFIG)
                .tag(ApiUtil.APP_CONFIG_TAG)
                .execute(new JsonCallback<LzyResponse<ConfigInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<ConfigInfo>> response) {
                        resultListener.onSuccess(response.body().data);
                    }

                    @Override
                    public void onError(Response<LzyResponse<ConfigInfo>> response) {
                        super.onError(response);
                        if (response.getException() != null) {
                            resultListener.onFail(response.getException().getMessage());
                        } else {
                            resultListener.onFail(response.message());
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        resultListener.onFinish();
                    }
                });
    }

    public void startApp() {
        OkGo.<LzyResponse<String>>get(ApiUtil.API_PRE + ApiUtil.APP_START)
                .tag(ApiUtil.APP_START_TAG)
                .execute(new JsonCallback<LzyResponse<String>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<String>> response) {

                    }
                });
    }
}
