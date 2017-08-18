package wiki.scene.shop.activity.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.List;

import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.base.LzyResponse;
import wiki.scene.shop.http.callback.JsonCallback;
import wiki.scene.shop.http.listener.HttpResultListener;

/**
 * 找回密码
 * Created by scene on 17-8-18.
 */

public class FindPasswordModel {
    /**
     * 获取验证码
     */
    public void getFindPasswordCode(HttpParams params, final HttpResultListener<String> listener) {
        OkGo.<LzyResponse<List<String>>>get(ApiUtil.API_PRE + ApiUtil.FIND_PASSWORD_CODE)
                .params(params)
                .tag(ApiUtil.FIND_PASSWORD_CODE_TAG)
                .execute(new JsonCallback<LzyResponse<List<String>>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<List<String>>> response) {
                        listener.onSuccess(null);
                    }

                    @Override
                    public void onError(Response<LzyResponse<List<String>>> response) {
                        super.onError(response);
                        listener.onFail(response.getException().getMessage());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        listener.onFinish();
                    }
                });
    }

    /**
     * 修改密码
     */
    public void updatePassword(HttpParams params, final HttpResultListener<String> listener) {
        OkGo.<LzyResponse<List<String>>>post(ApiUtil.API_PRE + ApiUtil.FIND_PASSWORD_RESET)
                .params(params)
                .tag(ApiUtil.FIND_PASSWORD_RESET_TAG)
                .execute(new JsonCallback<LzyResponse<List<String>>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<List<String>>> response) {
                        listener.onSuccess(null);
                    }

                    @Override
                    public void onError(Response<LzyResponse<List<String>>> response) {
                        super.onError(response);
                        listener.onFail(response.getException().getMessage());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        listener.onFinish();
                    }
                });
    }
}
