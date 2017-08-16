package wiki.scene.shop.ui.rank.model;

import android.text.TextUtils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

import wiki.scene.shop.entity.RankInfo;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.base.LzyResponse;
import wiki.scene.shop.http.callback.JsonCallback;
import wiki.scene.shop.http.listener.HttpResultListener;

/**
 * 排行榜
 * Created by scene on 17-8-16.
 */

public class RankModel {
    public void getRankData(final HttpResultListener<List<RankInfo>> listener) {
        OkGo.<LzyResponse<List<RankInfo>>>get(ApiUtil.API_PRE + ApiUtil.RANK)
                .tag(ApiUtil.RANK_TAG)
                .execute(new JsonCallback<LzyResponse<List<RankInfo>>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<List<RankInfo>>> response) {
                        listener.onSuccess(response.body().data);
                    }

                    @Override
                    public void onError(Response<LzyResponse<List<RankInfo>>> response) {
                        super.onError(response);
                        if (response.getException() != null && !TextUtils.isEmpty(response.getException().getMessage())) {
                            listener.onFail(response.getException().getMessage());
                        } else {
                            listener.onFail(response.message());
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
