package wiki.scene.shop.activity.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import wiki.scene.shop.activity.model.listener.OnRegisterResultListener;
import wiki.scene.shop.entity.UserInfo;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.base.LzyResponse;
import wiki.scene.shop.http.callback.JsonCallback;

/**
 * Case By:注册第二步
 * package:wiki.scene.shop.activity.model
 * Author：scene on 2017/7/5 16:35
 */

public class Register2Model {
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
                resultListener.onRegisterFail(response.getException().getMessage());
            }

            @Override
            public void onFinish() {
                super.onFinish();
                resultListener.onRegisterFinish();
            }
        });
    }
}
