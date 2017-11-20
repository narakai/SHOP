package wiki.scene.shop.ui.indiana.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.List;

import wiki.scene.shop.entity.AddCartResultInfo;
import wiki.scene.shop.entity.CreateOrderInfo;
import wiki.scene.shop.entity.GoodsDetailInfo;
import wiki.scene.shop.entity.NewestWinInfo;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.base.LzyResponse;
import wiki.scene.shop.http.callback.JsonCallback;
import wiki.scene.shop.http.listener.HttpResultListener;

/**
 * Case By:商品详情
 * package:wiki.scene.shop.ui.indiana.model
 * Author：scene on 2017/7/13 14:39
 */

public class GoodsDetailModel {
    public void getDetailInfo(HttpParams params, final HttpResultListener<GoodsDetailInfo> listener) {
        OkGo.<LzyResponse<GoodsDetailInfo>>get(ApiUtil.API_PRE + ApiUtil.GOODS_DETAIL)
                .tag(ApiUtil.GOODS_DETAIL_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<GoodsDetailInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<GoodsDetailInfo>> response) {
                        listener.onSuccess(response.body().data);
                    }

                    @Override
                    public void onError(Response<LzyResponse<GoodsDetailInfo>> response) {
                        super.onError(response);
                        listener.onFail(response.getException() != null ? response.getException().getMessage() : response.message());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        listener.onFinish();
                    }
                });
    }

    //加入购物车
    public void joinCar(HttpParams params, final HttpResultListener<AddCartResultInfo> resultListener) {
        OkGo.<LzyResponse<AddCartResultInfo>>
                post(ApiUtil.API_PRE + ApiUtil.JOIN_CAR)
                .tag(ApiUtil.JOIN_CAR_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<AddCartResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<AddCartResultInfo>> response) {
                        resultListener.onSuccess(response.body().data);
                    }

                    @Override
                    public void onError(Response<LzyResponse<AddCartResultInfo>> response) {
                        super.onError(response);
                        if (response.getException() != null) {
                            resultListener.onFail(response.getException().getMessage());
                        } else {
                            resultListener.onFail(response.message());
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        resultListener.onFinish();
                    }
                });
    }

    /**
     * 创建订单
     */
    public void createOrder(HttpParams params, final HttpResultListener<CreateOrderInfo> resultListener) {
        OkGo.<LzyResponse<CreateOrderInfo>>post(ApiUtil.API_PRE + ApiUtil.CREATE_ORDER)
                .tag(ApiUtil.CREATE_ORDER_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<CreateOrderInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<CreateOrderInfo>> response) {
                        resultListener.onSuccess(response.body().data);
                    }

                    @Override
                    public void onError(Response<LzyResponse<CreateOrderInfo>> response) {
                        super.onError(response);
                        if (response.getException().getMessage().isEmpty()) {
                            resultListener.onFail(response.message());
                        } else {
                            resultListener.onFail(response.getException().getMessage());
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        resultListener.onFinish();
                    }
                });
    }

    /**
     * 最新参与
     */
    public void getNewestBuy(HttpParams params, final HttpResultListener<List<NewestWinInfo>> listener) {
        OkGo.<LzyResponse<List<NewestWinInfo>>>get(ApiUtil.API_PRE + ApiUtil.NEWEST_BUY_GOODS)
                .tag(ApiUtil.NEWEST_BUY_GOODS_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<List<NewestWinInfo>>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<List<NewestWinInfo>>> response) {
                        listener.onSuccess(response.body().data);
                    }

                    @Override
                    public void onError(Response<LzyResponse<List<NewestWinInfo>>> response) {
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
