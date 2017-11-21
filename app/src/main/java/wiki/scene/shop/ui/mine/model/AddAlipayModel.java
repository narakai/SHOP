package wiki.scene.shop.ui.mine.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import wiki.scene.shop.entity.AddBankResultInfo;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.base.LzyResponse;
import wiki.scene.shop.http.callback.JsonCallback;
import wiki.scene.shop.http.listener.HttpResultListener;

/**
 * 添加支付宝
 * Created by scene on 2017/11/14.
 */

public class AddAlipayModel {
    public void addAlipayCount(HttpParams params,final HttpResultListener<AddBankResultInfo> listener) {
        OkGo.<LzyResponse<AddBankResultInfo>>post(ApiUtil.API_PRE+ApiUtil.ADD_BANK_CARD)
                .tag(ApiUtil.ADD_BANK_CARD_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<AddBankResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<AddBankResultInfo>> response) {
                        listener.onSuccess(response.body().data);
                    }

                    @Override
                    public void onError(Response<LzyResponse<AddBankResultInfo>> response) {
                        super.onError(response);
                        try{
                            listener.onFail(response.getException().getMessage()!=null?response.getException().getMessage():response.message());
                        }catch (Exception e){
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
