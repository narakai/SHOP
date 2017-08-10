package wiki.scene.shop.ui.mine.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import wiki.scene.shop.entity.WinRecordResultInfo;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.base.LzyResponse;
import wiki.scene.shop.http.callback.JsonCallback;
import wiki.scene.shop.http.listener.HttpResultListener;

/**
 * 我的中奖记录
 * Created by scene on 17-8-10.
 */

public class MineWinRecordModel {
    public void getWinRecordListData(HttpParams params, final HttpResultListener<WinRecordResultInfo> listener) {
        OkGo.<LzyResponse<WinRecordResultInfo>>get(ApiUtil.API_PRE + ApiUtil.MINE_WIN_LIST)
                .tag(ApiUtil.MINE_WIN_LIST_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<WinRecordResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<WinRecordResultInfo>> response) {
                        listener.onSuccess(response.body().data);
                    }

                    @Override
                    public void onError(Response<LzyResponse<WinRecordResultInfo>> response) {
                        super.onError(response);
                        listener.onFail(response.getException()==null?response.message():response.getException().getMessage());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        listener.onFinish();
                    }
                });
    }
}
