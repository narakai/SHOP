package wiki.scene.shop.ui.mine.presenter;

import com.lzy.okgo.model.HttpParams;

import java.util.List;

import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.entity.CreateOrderInfo;
import wiki.scene.shop.http.listener.HttpResultListener;
import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.model.MineRedModel;
import wiki.scene.shop.ui.mine.mvpview.IMineRedView;

/**
 * Case By:我的红包
 * package:wiki.scene.shop.ui.mine.presenter
 * Author：scene on 2017/6/30 13:24
 */

public class MineRedPresenter extends BasePresenter<IMineRedView> {
    private IMineRedView redView;
    private MineRedModel model;

    public MineRedPresenter(IMineRedView redView) {
        this.mView = redView;
        model = new MineRedModel();
    }

    public void getMineRedData(final boolean isLoading) {
        try {
            if (ShopApplication.hasLogin && ShopApplication.userInfo != null) {
                if (isLoading) {
                    mView.showLoading();
                }
                HttpParams params = new HttpParams();
                params.put("user_id", ShopApplication.userInfo.getUser_id());
                model.getMineRedData(params, new HttpResultListener<List<CreateOrderInfo.CouponsBean>>() {
                    @Override
                    public void onSuccess(List<CreateOrderInfo.CouponsBean> data) {
                        try {
                            if (isLoading) {
                                mView.showContent();
                            } else {
                                mView.refreshCompilt();
                            }
                            mView.bindData(data);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFail(String message) {
                        try {
                            if (isLoading) {
                                mView.showFail();
                            } else {
                                mView.refreshCompilt();
                            }
                            mView.showMessage(message);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFinish() {

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
