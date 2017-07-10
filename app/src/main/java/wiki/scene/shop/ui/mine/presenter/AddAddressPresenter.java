package wiki.scene.shop.ui.mine.presenter;

import com.lzy.okgo.model.HttpParams;

import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.http.listener.HttpResultListener;
import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.model.AddAddressModel;
import wiki.scene.shop.ui.mine.model.DeleteAddressModel;
import wiki.scene.shop.ui.mine.mvpview.IAddAddressView;
import wiki.scene.shop.utils.AppUtils;

/**
 * Case By:添加或者修改
 * package:wiki.scene.shop.ui.mine.presenter
 * Author：scene on 2017/6/30 12:45
 */

public class AddAddressPresenter extends BasePresenter<IAddAddressView> {
    private IAddAddressView addAddressView;
    private AddAddressModel model;
    private DeleteAddressModel deleteAddressModel;

    public AddAddressPresenter(IAddAddressView addAddressView) {
        this.addAddressView = addAddressView;
        this.model = new AddAddressModel();
        deleteAddressModel = new DeleteAddressModel();
    }

    public void addAddress() {
        if (addAddressView != null) {
            if (addAddressView.getReceiverName().isEmpty()) {
                addAddressView.showFail(R.string.please_edit_receiver_name);
                return;
            }
            if (addAddressView.getReceiverPhone().isEmpty()) {
                addAddressView.showFail(R.string.please_edit_receiver_phone);
                return;
            }
            if (!AppUtils.isMobileNO(addAddressView.getReceiverPhone())) {
                addAddressView.showFail(R.string.please_edit_right_phone_number);
                return;
            }
            if (addAddressView.getReceiverAddress().isEmpty()) {
                addAddressView.showFail(R.string.please_edit_receiver_address);
                return;
            }
            addAddressView.showProgressDialog(R.string.loading);
            HttpParams httpParams = new HttpParams();
            httpParams.put("user_id", ShopApplication.userInfo.getUser_id());
            httpParams.put("name", addAddressView.getReceiverName());
            httpParams.put("mobile", addAddressView.getReceiverPhone());
            httpParams.put("address", addAddressView.getReceiverAddress());
            httpParams.put("is_default", addAddressView.getIsDefault());
            model.addOrEditAddress(httpParams, new HttpResultListener<String>() {
                @Override
                public void onSuccess(String data) {
                    if (addAddressView != null) {
                        addAddressView.addSuccess();
                    }
                }

                @Override
                public void onFail(String message) {
                    if (addAddressView != null) {
                        addAddressView.showFail(R.string.add_fail);
                    }
                }

                @Override
                public void onFinish() {
                    if (addAddressView != null) {
                        addAddressView.hideProgressDialog();
                    }
                }
            });
        }
    }

    public void updateAddress(int addressId) {
        if (addAddressView != null) {
            if (addAddressView.getReceiverName().isEmpty()) {
                addAddressView.showFail(R.string.please_edit_receiver_name);
                return;
            }
            if (addAddressView.getReceiverPhone().isEmpty()) {
                addAddressView.showFail(R.string.please_edit_receiver_phone);
                return;
            }
            if (!AppUtils.isMobileNO(addAddressView.getReceiverPhone())) {
                addAddressView.showFail(R.string.please_edit_right_phone_number);
                return;
            }
            if (addAddressView.getReceiverAddress().isEmpty()) {
                addAddressView.showFail(R.string.please_edit_receiver_address);
                return;
            }
            addAddressView.showProgressDialog(R.string.loading);
            HttpParams httpParams = new HttpParams();
            httpParams.put("user_id", ShopApplication.userInfo.getUser_id());
            httpParams.put("name", addAddressView.getReceiverName());
            httpParams.put("mobile", addAddressView.getReceiverPhone());
            httpParams.put("address", addAddressView.getReceiverAddress());
            httpParams.put("is_default", addAddressView.getIsDefault());
            httpParams.put("address_id", addressId);
            model.addOrEditAddress(httpParams, new HttpResultListener<String>() {
                @Override
                public void onSuccess(String data) {
                    if (addAddressView != null) {
                        addAddressView.updateSuccess();
                    }
                }

                @Override
                public void onFail(String message) {
                    if (addAddressView != null) {
                        addAddressView.showFail(R.string.update_fail);
                    }
                }

                @Override
                public void onFinish() {
                    if (addAddressView != null) {
                        addAddressView.hideProgressDialog();
                    }
                }
            });
        }
    }

    public void deleteAddress(int addressId) {
        if(mView!=null){
            HttpParams params = new HttpParams();
            params.put("address_id", addressId);
            params.put("user_id", ShopApplication.userInfo.getUser_id());
            mView.showProgressDialog(R.string.deleting);
            deleteAddressModel.deleteAddress(params, new HttpResultListener<String>() {
                @Override
                public void onSuccess(String data) {
                    if(mView!=null){
                        mView.deleteSuccess();
                    }
                }

                @Override
                public void onFail(String message) {
                    if(mView!=null){
                        mView.showFail(message);
                    }
                }

                @Override
                public void onFinish() {
                    if(mView!=null){
                        mView.hideProgressDialog();
                    }
                }
            });
        }

    }
}
