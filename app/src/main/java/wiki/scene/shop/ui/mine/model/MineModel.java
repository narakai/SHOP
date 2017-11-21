package wiki.scene.shop.ui.mine.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import wiki.scene.shop.entity.MineInfo;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.base.LzyResponse;
import wiki.scene.shop.http.callback.JsonCallback;
import wiki.scene.shop.http.listener.HttpResultListener;

/**
 * 我的
 * Created by scene on 2017/11/21.
 */

public class MineModel {
    public void getMineInfo(final HttpResultListener<MineInfo> listener) {
        OkGo.<LzyResponse<MineInfo>>get(ApiUtil.API_PRE + ApiUtil.MINE_INFO)
                .tag(ApiUtil.MINE_INFO_TAG)
                .execute(new JsonCallback<LzyResponse<MineInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<MineInfo>> response) {
                        listener.onSuccess(response.body().data);
                    }

                    @Override
                    public void onError(Response<LzyResponse<MineInfo>> response) {
                        super.onError(response);
                        try {
                            listener.onFail(response.getException().getMessage() != null ? response.getException().getMessage() : response.message());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        listener.onFinish();
                    }
                });
    }
}
