package wiki.scene.shop.ui.indiana.presenter;

import android.content.Context;

import com.lzy.okgo.model.HttpParams;

import java.util.List;

import wiki.scene.loadmore.utils.SceneLogUtil;
import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.entity.AddCartResultInfo;
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

    public void getGoodsDetailInfo(final boolean isFirst, String cycleId) {
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
                    if (isFirst) {
                        mView.hideLoading();
                    } else {
                        mView.refreshComplete();
                    }
                    mView.bindGoodsInfo(data.getData());
                    mView.bindTuhaoRank(data.getBuyers());
                    mView.bindJoinRecord(data.getLog());
                    mView.bindGuessLike(data.getData().getHot());
                }

                @Override
                public void onFail(String message) {
                    if (isFirst) {
                        mView.showFailPage();
                    } else {
                        mView.showMessage(message);
                        mView.refreshComplete();
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

    public void addGoods2Car(String cycleId) {
        if (mView == null) {
            SceneLogUtil.e("mView=null");
            return;
        }
        mView.showProgressDialog(R.string.loading);
        HttpParams params = new HttpParams();
        params.put("cycle_id", cycleId);
        params.put("number", 1);
        params.put("user_id", ShopApplication.userInfo.getUser_id());
        model.joinCar(params, new HttpResultListener<AddCartResultInfo>() {
            @Override
            public void onSuccess(AddCartResultInfo data) {
                if (mView != null) {
                    mView.addCartSuccess();
                }
            }

            @Override
            public void onFail(String message) {
                if (mView != null) {
                    mView.showMessage(message);
                }
            }

            @Override
            public void onFinish() {
                mView.hideProgressDialog();
            }
        });
    }

    public void createOrder(Context context,String cycleId,int number) {
        try {
            mView.showProgressDialog(R.string.loading);
            HttpParams params = new HttpParams();
            if (ShopApplication.hasLogin) {
                params.put("user_id", ShopApplication.userInfo.getUser_id());
                params.put("cycle_id",cycleId);
                params.put("mobile",ShopApplication.userInfo.getMobile());
                params.put("number",number);
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

    public void getDanmu(String cycleId) {
        try {
            HttpParams params = new HttpParams();
            params.put("cycle_id", cycleId);
            model.getDanmu(params, new HttpResultListener<List<GoodsDetailInfo.LogInfo>>() {
                @Override
                public void onSuccess(List<GoodsDetailInfo.LogInfo> data) {
                    try {
                        mView.bindJoinRecord(data);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {

                }

                @Override
                public void onFinish() {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
