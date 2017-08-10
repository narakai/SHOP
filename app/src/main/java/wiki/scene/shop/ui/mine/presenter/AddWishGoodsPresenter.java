package wiki.scene.shop.ui.mine.presenter;

import android.text.TextUtils;

import com.lzy.okgo.model.HttpParams;

import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.http.listener.HttpResultListener;
import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.model.AddWishGoodsModel;
import wiki.scene.shop.ui.mine.mvpview.IAddWishGoodsView;

/**
 * Case By:添加愿望商品
 * package:wiki.scene.shop.ui.mine.presenter
 * Author：scene on 2017/6/30 15:20
 */

public class AddWishGoodsPresenter extends BasePresenter<IAddWishGoodsView> {
    private IAddWishGoodsView wishGoodsView;
    private AddWishGoodsModel model;

    public AddWishGoodsPresenter(IAddWishGoodsView wishGoodsView) {
        this.mView = wishGoodsView;
        model = new AddWishGoodsModel();
    }

    public void addWishGoods(String goodsName) {
        try {
            if (TextUtils.isEmpty(goodsName)) {
                mView.showMessage(R.string.please_edit_your_wish_goods);
                return;
            }
            if (ShopApplication.hasLogin && ShopApplication.userInfo != null) {
                mView.showLoading(R.string.loading);
                HttpParams params = new HttpParams();
                params.put("user_id", ShopApplication.userInfo.getUser_id());
                params.put("content", goodsName);
                params.put("contact", ShopApplication.userInfo.getMobile());
                model.addWishGoods(params, new HttpResultListener<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            mView.addGoodsSuccess();
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
