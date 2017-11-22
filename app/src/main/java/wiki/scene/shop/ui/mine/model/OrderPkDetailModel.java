package wiki.scene.shop.ui.mine.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.base.LzyResponse;
import wiki.scene.shop.http.callback.JsonCallback;
import wiki.scene.shop.http.listener.HttpResultListener;

/**
 * 订单pk
 * Created by scene on 2017/11/22.
 */

public class OrderPkDetailModel {
    public void getPKDetail(HttpParams params, final HttpResultListener<String> listener) {
        OkGo.<LzyResponse<String>>get(ApiUtil.API_PRE + ApiUtil.PK)
                .tag(ApiUtil.PAY_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<String>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<String>> response) {

                    }
                });
    }
}
