package wiki.scene.shop.activity.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.HashMap;
import java.util.Map;

import wiki.scene.shop.entity.UserInfo;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.base.LzyResponse;
import wiki.scene.shop.http.callback.JsonCallback;

/**
 * Case By:登录界面
 * package:wiki.scene.shop.activity.model
 * Author：scene on 2017/6/27 11:18
 */

public class LoginModel {

    public void login(String username, String password) {
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        OkGo.<LzyResponse<UserInfo>>get("http://www.baidu.com").tag(ApiUtil.LOGIN_TAG).params(params).execute(new JsonCallback<LzyResponse<UserInfo>>() {
            @Override
            public void onSuccess(Response<LzyResponse<UserInfo>> response) {
                LzyResponse<UserInfo> userInfoLzyResponse = response.body();
                UserInfo userInfo = userInfoLzyResponse.data;
            }

            @Override
            public void onError(Response<LzyResponse<UserInfo>> response) {
                super.onError(response);
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }
        });
    }

}
