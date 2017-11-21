package wiki.scene.shop.ui.mine.presenter;

import com.lzy.okgo.model.HttpParams;

import wiki.scene.shop.R;
import wiki.scene.shop.config.AppConfig;
import wiki.scene.shop.entity.AddBankResultInfo;
import wiki.scene.shop.entity.BankInfo;
import wiki.scene.shop.http.listener.HttpResultListener;
import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.model.AddBankModel;
import wiki.scene.shop.ui.mine.mvpview.IAddBankView;

/**
 * 添加银行卡
 * Created by scene on 2017/11/14.
 */

public class AddBankPresenter extends BasePresenter<IAddBankView> {
    private IAddBankView bankView;
    private AddBankModel model;

    public AddBankPresenter(IAddBankView bankView) {
        this.bankView = bankView;
        model = new AddBankModel();
    }

    public void addBankCard(BankInfo bankInfo) {
        try {
            HttpParams params = new HttpParams();
            params.put("name", bankInfo.getName());
            params.put("account", bankInfo.getAccount());
            params.put("type", AppConfig.BANK_TYPE_BANK_CARD);
            params.put("bank", bankInfo.getBank());
            params.put("open_bank", bankInfo.getOpen_bank());
            bankView.showLoading(R.string.loading);
            model.addBankCard(params, new HttpResultListener<AddBankResultInfo>() {
                @Override
                public void onSuccess(AddBankResultInfo data) {
                    try {
                        if (data != null && data.getBank_id() != 0) {
                            bankView.showMessage("银行卡绑定成功");
                            bankView.bindOrUpdateSuccess();
                        } else {
                            bankView.showMessage("银行卡绑定失败");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        bankView.showMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFinish() {
                    try {
                        bankView.hideLoading();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateBankCard(BankInfo bankInfo) {
        try {
            HttpParams params = new HttpParams();
            params.put("name", bankInfo.getName());
            params.put("account", bankInfo.getAccount());
            params.put("type", AppConfig.BANK_TYPE_BANK_CARD);
            params.put("bank_id", bankInfo.getId());
            params.put("bank", bankInfo.getBank());
            params.put("open_bank", bankInfo.getOpen_bank());
            bankView.showLoading(R.string.loading);
            model.addBankCard(params, new HttpResultListener<AddBankResultInfo>() {
                @Override
                public void onSuccess(AddBankResultInfo data) {
                    try {
                        if (data != null && data.getBank_id() != 0) {
                            bankView.showMessage("银行卡修改成功");
                            bankView.bindOrUpdateSuccess();
                        } else {
                            bankView.showMessage("银行卡修改失败");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        bankView.showMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFinish() {
                    try {
                        bankView.hideLoading();
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
