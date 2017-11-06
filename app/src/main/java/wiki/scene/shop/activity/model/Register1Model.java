package wiki.scene.shop.activity.model;

import android.text.TextUtils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.List;

import wiki.scene.shop.activity.model.listener.OnRegisterResultListener;
import wiki.scene.shop.entity.UserInfo;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.base.LzyResponse;
import wiki.scene.shop.http.callback.JsonCallback;
import wiki.scene.shop.http.listener.HttpResultListener;

/**
 * Case By:注册第一步 获取验证码
 * package:wiki.scene.shop.activity.model
 * Author：scene on 2017/7/6 11:51
 */

public class Register1Model {

    public void getVerificationCode(HttpParams httpParams, final HttpResultListener<String> resultListener) {
        OkGo.<LzyResponse<List<String>>>get(ApiUtil.API_PRE + ApiUtil.GET_VERIFICATION_CODE).params(httpParams).tag(ApiUtil.GET_VERIFICATION_CODE_TAG).execute(new JsonCallback<LzyResponse<List<String>>>() {
            @Override
            public void onSuccess(Response<LzyResponse<List<String>>> response) {
                resultListener.onSuccess(null);
            }

            @Override
            public void onError(Response<LzyResponse<List<String>>> response) {
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

    public void checkCode(HttpParams params, final HttpResultListener<String> resultListener) {
        OkGo.<LzyResponse<List<String>>>get(ApiUtil.API_PRE + ApiUtil.CHECK_CODE).params(params).tag(ApiUtil.CHECK_CODE_TAG).execute(new JsonCallback<LzyResponse<List<String>>>() {
            @Override
            public void onSuccess(Response<LzyResponse<List<String>>> response) {
                resultListener.onSuccess(null);
            }

            @Override
            public void onError(Response<LzyResponse<List<String>>> response) {
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

    public void register(HttpParams params, final OnRegisterResultListener resultListener) {
        OkGo.<LzyResponse<UserInfo>>post(ApiUtil.API_PRE + ApiUtil.REGISTER).tag(ApiUtil.REGISTER_TAG).params(params).execute(new JsonCallback<LzyResponse<UserInfo>>() {
            @Override
            public void onSuccess(Response<LzyResponse<UserInfo>> response) {
                LzyResponse<UserInfo> userInfoLzyResponse = response.body();
                UserInfo userInfo = userInfoLzyResponse.data;
                resultListener.onRegisterSuccess(userInfo);
            }

            @Override
            public void onError(Response<LzyResponse<UserInfo>> response) {
                super.onError(response);
                if (response.getException() != null && !TextUtils.isEmpty(response.getException().getMessage())) {
                    resultListener.onRegisterFail(response.getException().getMessage());
                } else {
                    resultListener.onRegisterFail(response.message());
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                resultListener.onRegisterFinish();
            }
        });
    }
}
