package wiki.scene.shop.ui.mine.presenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;

import java.util.List;

import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.entity.BankInfo;
import wiki.scene.shop.http.listener.HttpResultListener;
import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.model.CashModel;
import wiki.scene.shop.ui.mine.mvpview.ICashView;

/**
 * 提现
 * Created by scene on 2017/11/14.
 */

public class CashPresenter extends BasePresenter<ICashView> {
    private CashModel model;

    public CashPresenter(ICashView cashView) {
        this.mView = cashView;
        model = new CashModel();
    }

    public void getBankData() {
        try {
            mView.showLoadingPage();
            model.getBankList(new HttpResultListener<List<BankInfo>>() {
                @Override
                public void onSuccess(List<BankInfo> data) {
                    try {
                        mView.showContentPage();
                        mView.getBankDataSuccess(data);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        mView.showFailPage();
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
        }
    }

    public void applyCash() {
        try {
            final BankInfo bankInfo = mView.getBankInfo();
            final int money = mView.getMoney();
            if (bankInfo == null) {
                mView.showMessage("请先选择您要提现的账户");
                return;
            }
            if (money == 0) {
                mView.showMessage("请先输入您要提现的金额");
                return;
            }
            if (money > ShopApplication.userInfo.getMoney()) {
                mView.showMessage("提现金额不能大于当前可用金额");
                return;
            }
            mView.showLoading(R.string.loading);
            HttpParams params = new HttpParams();
            params.put("bank_id", bankInfo.getId());
            params.put("money", money);
            model.applyCash(params, new HttpResultListener<String>() {
                @Override
                public void onSuccess(String data) {
                    try {
                        mView.applyCashSuccess(bankInfo, money);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        mView.applyCashFail(message);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
