package wiki.scene.shop.ui.mine.presenter;

import com.lzy.okgo.model.HttpParams;

import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.entity.RechargeInfo;
import wiki.scene.shop.http.listener.HttpResultListener;
import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.model.RechargeModel;
import wiki.scene.shop.ui.mine.mvpview.IRechargeView;

/**
 * 充值
 * Created by scene on 17-8-16.
 */

public class RechargePresenter extends BasePresenter<IRechargeView> {
    private IRechargeView rechargeView;
    private RechargeModel model;

    public RechargePresenter(IRechargeView rechargeView) {
        this.mView = rechargeView;
        this.model = new RechargeModel();
    }

    public void recharge(int cost, int payType) {
        try {
            if (ShopApplication.hasLogin && ShopApplication.userInfo != null) {
                mView.showLoading(R.string.loading);
                HttpParams params = new HttpParams();
                params.put("cost", cost);
                params.put("pay_type", payType);
                model.rechage(params, new HttpResultListener<RechargeInfo>() {
                    @Override
                    public void onSuccess(RechargeInfo data) {
                        try {
                            mView.getRechargeOrderSuccess(data);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(String message) {
                        try {
                            mView.showMessage(message);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFinish() {
                        try {
                            mView.hideLoading();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            } else {
                mView.showMessage(R.string.you_has_no_login_please_login);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
