package wiki.scene.shop.ui.indiana.presenter;

import android.content.Context;

import com.lzy.okgo.model.HttpParams;

import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.entity.CreateOrderInfo;
import wiki.scene.shop.entity.GoodsDetailInfo;
import wiki.scene.shop.http.listener.HttpResultListener;
import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.indiana.model.GoodsDetailModel;
import wiki.scene.shop.ui.indiana.mvpview.IGoodsDetailView;

/**
 * Case By:商品详情
 * package:wiki.scene.shop.ui.indiana.presenter
 * Author：scene on 2017/7/4 11:41
 */

public class GoodsDetailPresenter extends BasePresenter<IGoodsDetailView> {
    private GoodsDetailModel model;

    public GoodsDetailPresenter(IGoodsDetailView goodsDetailView) {
        this.mView = goodsDetailView;
        model = new GoodsDetailModel();
    }

    public void getGoodsDetailInfo(final boolean isFirst, int cycleId) {
        try {
            if (isFirst) {
                mView.showLoading(0);
            }
            HttpParams params = new HttpParams();
            params.put("cycle_id", cycleId);
            if (ShopApplication.hasLogin && ShopApplication.userInfo != null) {
                params.put("user_id", ShopApplication.userInfo.getUser_id());
            }
            model.getDetailInfo(params, new HttpResultListener<GoodsDetailInfo>() {
                @Override
                public void onSuccess(GoodsDetailInfo data) {
                    try {
                        if (isFirst) {
                            mView.hideLoading();
                        } else {
                            mView.refreshComplete();
                        }
                        mView.bindGoodsInfo(data.getData());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        if (isFirst) {
                            mView.showFailPage();
                        } else {
                            mView.showMessage(message);
                            mView.refreshComplete();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFinish() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createOrder(Context context, String cycleId, int number) {
        try {
            if (ShopApplication.hasLogin) {
                mView.showProgressDialog(R.string.loading);
                HttpParams params = new HttpParams();
                params.put("user_id", ShopApplication.userInfo.getUser_id());
                params.put("cycle_id", cycleId);
                params.put("mobile", ShopApplication.userInfo.getMobile());
                if (number != -1) {
                    params.put("number", number);
                } else {
                    params.put("baowei", 1);
                }
                model.createOrder(params, new HttpResultListener<CreateOrderInfo>() {
                    @Override
                    public void onSuccess(CreateOrderInfo data) {
                        mView.createOrderSuccess(data);
                    }

                    @Override
                    public void onFail(String message) {
                        mView.showMessage(message);
                    }

                    @Override
                    public void onFinish() {
                        mView.hideProgressDialog();
                    }
                });
            } else {
                mView.showMessage(context.getString(R.string.you_has_no_login_please_login));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
