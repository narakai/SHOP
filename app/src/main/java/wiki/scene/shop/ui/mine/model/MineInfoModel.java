package wiki.scene.shop.ui.mine.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.io.File;
import java.util.List;

import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.entity.AvaterInfo;
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
        OkGo.<LzyResponse<List<String>>>post(ApiUtil.API_PRE + ApiUtil.UPDATE_USERINFO)
                .tag(ApiUtil.UPDATE_USERINFO_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<List<String>>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<List<String>>> response) {
                        resultListener.onSuccess(response.message());
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

    public void updateUserAvater(final String filePath, final HttpResultListener<String> resultListener) {
        HttpParams params = new HttpParams();
        params.put("avatar", new File(filePath));
        OkGo.<LzyResponse<AvaterInfo>>post(ApiUtil.API_PRE + ApiUtil.UPDATE_USER_AVATER + ShopApplication.userInfo.getUser_id())
                .tag(ApiUtil.UPDATE_USER_AVATER_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<AvaterInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<AvaterInfo>> response) {
                        resultListener.onSuccess(response.body().data.getUrl());
                    }

                    @Override
                    public void onError(Response<LzyResponse<AvaterInfo>> response) {
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
