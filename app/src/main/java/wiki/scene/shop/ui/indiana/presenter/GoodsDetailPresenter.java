package wiki.scene.shop.ui.indiana.presenter;

import com.lzy.okgo.model.HttpParams;

import wiki.scene.loadmore.utils.SceneLogUtil;
import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.entity.AddCartResultInfo;
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

    public void addGoods2Car(int cycleId) {
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
}
