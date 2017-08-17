package wiki.scene.shop.activity.model;

import android.text.TextUtils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.HashMap;
import java.util.Map;

import wiki.scene.shop.entity.UserInfo;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.base.LzyResponse;
import wiki.scene.shop.http.callback.JsonCallback;
import wiki.scene.shop.http.listener.HttpResultListener;
import wiki.scene.shop.mvp.BaseHttpResultListener;
import wiki.scene.shop.utils.MD5Util;

/**
 * Case By:登录界面
 * package:wiki.scene.shop.activity.model
 * Author：scene on 2017/6/27 11:18
 */

public class LoginModel {

    public void login(String phoneNumber, String password, final BaseHttpResultListener<UserInfo> resultListener) {
        Map<String, String> params = new HashMap<>();
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
                    resultListener.onError(response.getException().getMessage());
                } else {
                    resultListener.onError(response.message());
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                resultListener.onFinish();
            }
        });
    }


    public void loginByOthers(String unionid, int type, final HttpResultListener<UserInfo> resultListener) {
        Map<String, String> params = new HashMap<>();
        params.put("unionid", unionid);
        String url;
        String tag;
        if (type == 1) {
            url = ApiUtil.API_PRE + ApiUtil.LOGIN_QQ;
            tag = ApiUtil.LOGIN_QQ_TAG;
        } else {
            url = ApiUtil.API_PRE + ApiUtil.LOGIN_WX;
            tag = ApiUtil.LOGIN_WX_TAG;
        }

        OkGo.<LzyResponse<UserInfo>>post(url).tag(tag)
                .params(params)
                .execute(new JsonCallback<LzyResponse<UserInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<UserInfo>> response) {
                        LzyResponse<UserInfo> userInfoLzyResponse = response.body();
                        UserInfo userInfo = userInfoLzyResponse.data;
                        userInfo.setResgistered(true);
                        resultListener.onSuccess(userInfo);
                    }

                    @Override
                    public void onError(Response<LzyResponse<UserInfo>> response) {
                        super.onError(response);
                        if (response.getException() != null && !TextUtils.isEmpty(response.getException().getMessage())) {
                            if (response.getException().getMessage().equals("unRegister")) {
                                UserInfo userInfo = new UserInfo();
                                userInfo.setResgistered(false);
                                resultListener.onSuccess(userInfo);
                            } else {
                                resultListener.onFail(response.getException().getMessage());
                            }
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
