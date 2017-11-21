package wiki.scene.shop.ui.mine.presenter;

import com.lzy.okgo.model.HttpParams;

import java.util.List;

import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.entity.BankInfo;
import wiki.scene.shop.http.listener.HttpResultListener;
import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.model.BankListModel;
import wiki.scene.shop.ui.mine.mvpview.IBankListView;

/**
 * 收款账号
 * Created by scene on 2017/11/14.
 */

public class BankListPresenter extends BasePresenter<IBankListView> {
    private IBankListView bankListView;
    private BankListModel model;

    public BankListPresenter(IBankListView bankListView) {
        this.bankListView = bankListView;
        model = new BankListModel();
    }

    public void getBankListData(final boolean isFirst) {
        try {
            HttpParams params = new HttpParams();
            params.put("user_id", ShopApplication.userInfo.getUser_id());
            if (isFirst) {
                bankListView.showLoadingPage();
            }
            model.getBankList(params, new HttpResultListener<List<BankInfo>>() {
                @Override
                public void onSuccess(List<BankInfo> data) {
                    try {
                        getDataSuccess(isFirst);
                        bankListView.getBankListDataSuccess(data);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        getDataFail(isFirst);
                        bankListView.showMessage(message);
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
            bankListView.showFailPage();
        }
    }

    private void getDataSuccess(boolean isFirst) {
        if (isFirst) {
            bankListView.showContentPage();
        } else {
            bankListView.refreshComplite();
        }
    }

    private void getDataFail(boolean isFirst) {
        if (isFirst) {
            bankListView.showFailPage();
        } else {
            bankListView.refreshComplite();
        }
    }
}
