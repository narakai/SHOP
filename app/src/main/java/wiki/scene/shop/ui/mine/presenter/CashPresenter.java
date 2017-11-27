package wiki.scene.shop.ui.mine.presenter;

import com.blankj.utilcode.util.StringUtils;
import com.lzy.okgo.model.HttpParams;

import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.config.AppConfig;
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

    public void applyCash() {
        try {
            final BankInfo bankInfo = new BankInfo();
            final int money = mView.getMoney();
            HttpParams params = new HttpParams();
            if (money == 0) {
                mView.showMessage("请先输入您要提现的金额");
                return;
            }
            if (money > ShopApplication.userInfo.getMoney()) {
                mView.showMessage("提现金额不能大于当前可用金额");
                return;
            }
            params.put("money", money);
            if (mView.getCashType() == AppConfig.BANK_TYPE_BANK_CARD) {
                if (StringUtils.isEmpty(mView.getBankName())) {
                    mView.showMessage("请输入银行名称");
                    return;
                }
                if (StringUtils.isEmpty(mView.getBankAccount())) {
                    mView.showMessage("请输入银行卡号");
                    return;
                }
                if (StringUtils.isEmpty(mView.getBankUser())) {
                    mView.showMessage("请输入开户人");
                    return;
                }
                params.put("name", mView.getBankName());
                params.put("bank", mView.getBankName());
                params.put("card_id", mView.getBankAccount());
                bankInfo.setBank(mView.getBankName());
                bankInfo.setAccount(mView.getBankAccount());
                bankInfo.setName(mView.getBankUser());
                bankInfo.setType(AppConfig.BANK_TYPE_BANK_CARD);
            } else {
                if (StringUtils.isEmpty(mView.getAlipayUser())) {
                    mView.showMessage("请输入支付宝姓名");
                    return;
                }
                if (StringUtils.isEmpty(mView.getAlipayAccount())) {
                    mView.showMessage("请输入支付宝账号");
                    return;
                }
                params.put("name", mView.getAlipayUser());
                params.put("alipay", mView.getAlipayAccount());
                bankInfo.setBank("支付宝");
                bankInfo.setName(mView.getAlipayUser());
                bankInfo.setAccount(mView.getAlipayAccount());
                bankInfo.setType(AppConfig.BANK_TYPE_ALIPAY);
            }
            mView.showLoading(R.string.loading);
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
