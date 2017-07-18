package wiki.scene.shop.ui.mine.presenter;

import com.lzy.okgo.model.HttpParams;

import java.util.List;

import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.entity.AddressInfo;
import wiki.scene.shop.http.listener.HttpResultListener;
import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.model.DeleteAddressModel;
import wiki.scene.shop.ui.mine.model.MineReceiverAddressModel;
import wiki.scene.shop.ui.mine.mvpview.IMineReceiverAddressView;

/**
 * Case By:我的收货地址
 * package:wiki.scene.shop.ui.mine.presenter
 * Author：scene on 2017/6/30 10:21
 */

public class MineReceiverAddressPresenter extends BasePresenter<IMineReceiverAddressView> {
    private IMineReceiverAddressView addressView;
    private MineReceiverAddressModel model;
    private DeleteAddressModel deleteAddressModel;

    public MineReceiverAddressPresenter(IMineReceiverAddressView addressView) {
        this.addressView = addressView;
        model = new MineReceiverAddressModel();
        deleteAddressModel = new DeleteAddressModel();
    }

    public void getAddressList(final boolean isRefresh) {
        if (mView != null) {
            if (!isRefresh) {
                mView.showLoading(R.string.loading);
            }
            HttpParams params = new HttpParams();
            params.put("user_id", ShopApplication.userInfo.getUser_id());
            model.getAllReceiverAddress(params, new HttpResultListener<List<AddressInfo>>() {

                @Override
                public void onSuccess(List<AddressInfo> data) {
                    if (addressView != null) {
                        addressView.getAddressSuccess(data, isRefresh);
                    }
                }

                @Override
                public void onFail(String message) {
                    if (addressView != null) {
                        if (isRefresh) {
                            addressView.refreshFail();
                        } else {
                            addressView.loadFail();
                        }
                    }
                }

                @Override
                public void onFinish() {

                }
            });
        }
    }

    public void deleteAddress(int addressId, final int position) {
        if (mView != null) {
            mView.showProgressDialog(R.string.deleting);
            HttpParams params = new HttpParams();
            params.put("user_id", ShopApplication.userInfo.getUser_id());
            params.put("address_id", addressId);
            deleteAddressModel.deleteAddress(params, new HttpResultListener<String>() {
                @Override
                public void onSuccess(String data) {
                    if (mView != null) {
                        mView.deleteSuccess(position);
                    }
                }

                @Override
                public void onFail(String message) {
                    if (mView != null) {
                        mView.deleteFail();
                    }
                }

                @Override
                public void onFinish() {
                    if (mView != null) {
                        mView.hideProgressDialog();
                    }
                }
            });
        }

    }

    public void setDefaultAddress(int addressId, final int position){
        if (mView != null) {
            mView.showProgressDialog(R.string.loading);
            HttpParams params = new HttpParams();
            params.put("user_id", ShopApplication.userInfo.getUser_id());
            params.put("address_id", addressId);
            model.setAddressDefault(params, new HttpResultListener<String>() {
                @Override
                public void onSuccess(String data) {
                    if (mView != null) {
                        mView.setDefaultSuccess(position);
                    }
                }

                @Override
                public void onFail(String message) {
                    if (mView != null) {
                        mView.setDefaultFail();
                    }
                }

                @Override
                public void onFinish() {
                    if (mView != null) {
                        mView.hideProgressDialog();
                    }
                }
            });
        }
    }
}
