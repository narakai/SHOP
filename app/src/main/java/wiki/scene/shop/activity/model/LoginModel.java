package wiki.scene.shop.activity.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.HashMap;
import java.util.Map;

import wiki.scene.shop.entity.UserInfo;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.base.LzyResponse;
import wiki.scene.shop.http.callback.JsonCallback;
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
                resultListener.onError(response.getException().getMessage());
            }

            @Override
            public void onFinish() {
                super.onFinish();
                resultListener.onFinish();
            }
        });
    }

}
