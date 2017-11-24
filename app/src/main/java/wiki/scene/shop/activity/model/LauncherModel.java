package wiki.scene.shop.activity.model;

import android.text.TextUtils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import wiki.scene.shop.entity.ConfigInfo;
import wiki.scene.shop.entity.UserInfo;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.base.LzyResponse;
import wiki.scene.shop.http.callback.JsonCallback;
import wiki.scene.shop.http.listener.HttpResultListener;
import wiki.scene.shop.mvp.BaseHttpResultListener;
import wiki.scene.shop.utils.MD5Util;

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


    public void login(String phoneNumber, String password, final HttpResultListener<UserInfo> resultListener) {
        HttpParams params = new HttpParams();
        params.put("mobile", phoneNumber);
        params.put("password", MD5Util.string2Md5(password, "UTF-8"));
        OkGo.<LzyResponse<UserInfo>>post(ApiUtil.API_PRE + ApiUtil.LOGIN).tag(ApiUtil.LOGIN_TAG).params(params).execute(new JsonCallback<LzyResponse<UserInfo>>() {
            @Override
            public void onSuccess(Response<LzyResponse<UserInfo>> response) {
                LzyResponse<UserInfo> userInfoLzyResponse = response.body();
                UserInfo userInfo = userInfoLzyResponse.data;
                resultListener.onSuccess(userInfo);
            }

            @Override
            public void onError(Response<LzyResponse<UserInfo>> response) {
                super.onError(response);
                if (response.getException() != null && !TextUtils.isEmpty(response.getException().getMessage())) {
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
}
