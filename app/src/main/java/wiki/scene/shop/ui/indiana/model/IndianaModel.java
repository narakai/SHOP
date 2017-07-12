package wiki.scene.shop.ui.indiana.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import wiki.scene.shop.entity.IndianaIndexInfo;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.base.LzyResponse;
import wiki.scene.shop.http.callback.JsonCallback;
import wiki.scene.shop.http.listener.HttpResultListener;

/**
 * Case By:夺宝首页
 * package:wiki.scene.shop.ui.indiana.model
 * Author：scene on 2017/7/12 13:41
 */

public class IndianaModel {
    public void getIndianaIndexData(final HttpResultListener<IndianaIndexInfo> listener) {
        OkGo.<LzyResponse<IndianaIndexInfo>>get(ApiUtil.API_PRE + ApiUtil.INDEX)
                .tag(ApiUtil.INDEX_TAG)
                .execute(new JsonCallback<LzyResponse<IndianaIndexInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<IndianaIndexInfo>> response) {
                        listener.onSuccess(response.body().data);
                    }

                    @Override
                    public void onError(Response<LzyResponse<IndianaIndexInfo>> response) {
                        super.onError(response);
                        if (response.getException() != null) {
                            listener.onFail(response.getException().getMessage());
                        } else {
                            listener.onFail(response.message());
                        }
                    }
                });
    }
}
