package wiki.scene.shop.ui.car.presenter;

import com.lzy.okgo.model.HttpParams;

import java.util.List;

import wiki.scene.loadmore.utils.SceneLogUtil;
import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.entity.CartInfo;
import wiki.scene.shop.entity.CartResultInfo;
import wiki.scene.shop.http.listener.HttpResultListener;
import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.car.model.CarModel;
import wiki.scene.shop.ui.car.mvpview.ICarView;

/**
 * Case By:购物车
 * package:wiki.scene.shop.fragment.car.presenter
 * Author：scene on 2017/6/29 14:29
 */

public class CarPresenter extends BasePresenter<ICarView> {
    private CarModel model;

    public CarPresenter(ICarView carView) {
        this.mView = carView;
        model = new CarModel();
    }

    public void getCarList(final boolean isFirst) {
        try {
            if (isFirst) {
                mView.showLoading();
            }
            HttpParams params = new HttpParams();
            if (ShopApplication.hasLogin) {
                params.put("user_id", ShopApplication.userInfo.getUser_id());
            }
            model.getCarDataList(params, new HttpResultListener<CartResultInfo>() {
                @Override
                public void onSuccess(CartResultInfo data) {
                    mView.loadDataSuccess();
                    mView.bindGuessLikeData(data.getLike());
                    if (data.getCycles().size() > 0) {
                        mView.bindCartData(data.getCycles());
                    } else {
                        mView.showEmptyCart();
                    }
                }

                @Override
                public void onFail(String message) {
                    if (isFirst) {
                        mView.loadDataFail();
                    } else {
                        mView.showMessage(message);
                    }
                }

                @Override
                public void onFinish() {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            SceneLogUtil.e("出异常了");
        }
    }

    public void deleteCartGoods(int cartId, final int position) {
        try {
            mView.showProgress(R.string.loading);
            HttpParams httpParams = new HttpParams();
            httpParams.put("user_id", ShopApplication.userInfo.getUser_id());
            httpParams.put("cart_id", cartId);
            model.deleteCartGoods(httpParams, new HttpResultListener<String>() {
                @Override
                public void onSuccess(String data) {
                    mView.onDeleteSuccess(position);
                }

                @Override
                public void onFail(String message) {
                    mView.showMessage(message);
                }

                @Override
                public void onFinish() {
                    mView.hideProgress();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            SceneLogUtil.e("出异常了");
        }

    }

    public void showTotalPrice(List<CartInfo> list) {
        int totalPrice = 0;
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).isChecked()) {
                    totalPrice += (list.get(i).getPrice() * list.get(i).getNumber());
                }
            }
        }
        if (mView != null) {
            mView.showTotalPrice(totalPrice);
        }
    }
}
