package wiki.scene.shop.ui.car.presenter;

import android.text.TextUtils;

import com.lzy.okgo.model.HttpParams;

import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.http.listener.HttpResultListener;
import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.car.model.PayOrderModel;
import wiki.scene.shop.ui.car.mvpview.IPayOrderView;

/**
 * Case By:支付订单
 * package:wiki.scene.shop.ui.car.presenter
 * Author：scene on 2017/7/4 09:17
 */

public class PayOrderPresenter extends BasePresenter<IPayOrderView> {
    private PayOrderModel model;

    public PayOrderPresenter(IPayOrderView payOrderView) {
        this.mView = payOrderView;
        model = new PayOrderModel();
    }

    public void getPayOrderInfo(String orderId, String couponId, int payType) {
        try {
            mView.showLoading(R.string.loading);
            HttpParams params = new HttpParams();
            if (ShopApplication.hasLogin && ShopApplication.userInfo != null) {
                params.put("user_id", ShopApplication.userInfo.getUser_id());
                params.put("order_id", orderId);
                if (TextUtils.isEmpty(couponId)) {
                    params.put("coupon_id", couponId);
                }
                params.put("pay_type", payType);
                model.getPayInfo(params, new HttpResultListener<String>() {
                    @Override
                    public void onSuccess(String data) {
                        mView.getPayOrderInfoSuccess();
                    }

                    @Override
                    public void onFail(String message) {
                        mView.showMessage(message);
                    }

                    @Override
                    public void onFinish() {
                        mView.hideLoading();
                    }
                });
            } else {
                mView.hasNoLogin();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
