package wiki.scene.shop.ui.indiana.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

import wiki.scene.shop.entity.IndexInfo;
import wiki.scene.shop.entity.WinningNoticeInfo;
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
    public void getIndianaIndexData(final HttpResultListener<IndexInfo> listener) {
        OkGo.<LzyResponse<IndexInfo>>get(ApiUtil.API_PRE + ApiUtil.INDEX)
                .tag(ApiUtil.INDEX_TAG)
                .execute(new JsonCallback<LzyResponse<IndexInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<IndexInfo>> response) {
                        listener.onSuccess(response.body().data);
                    }

                    @Override
                    public void onError(Response<LzyResponse<IndexInfo>> response) {
                        super.onError(response);
                        if (response.getException() != null) {
                            listener.onFail(response.getException().getMessage());
                        } else {
                            listener.onFail(response.message());
                        }
                    }
                });
    }

    /**
     * 获取夺宝头条的数据
     */
    public void getWinNotice(final HttpResultListener<List<WinningNoticeInfo>> listener) {
        OkGo.<LzyResponse<List<WinningNoticeInfo>>>get(ApiUtil.API_PRE + ApiUtil.WINNER_NOTICE)
                .tag(ApiUtil.WINNER_NOTICE_TAG)
                .execute(new JsonCallback<LzyResponse<List<WinningNoticeInfo>>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<List<WinningNoticeInfo>>> response) {
                        listener.onSuccess(response.body().data);
                    }

                    @Override
                    public void onError(Response<LzyResponse<List<WinningNoticeInfo>>> response) {
                        super.onError(response);
                        listener.onFail(response.getException() != null && response.getException().getMessage() != null ? response.getException().getMessage() : response.message());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        listener.onFinish();
                    }
                });
    }
}
