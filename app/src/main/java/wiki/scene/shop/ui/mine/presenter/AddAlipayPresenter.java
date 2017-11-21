package wiki.scene.shop.ui.mine.presenter;

import com.lzy.okgo.model.HttpParams;

import wiki.scene.shop.R;
import wiki.scene.shop.config.AppConfig;
import wiki.scene.shop.entity.AddBankResultInfo;
import wiki.scene.shop.entity.BankInfo;
import wiki.scene.shop.http.listener.HttpResultListener;
import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.model.AddAlipayModel;
import wiki.scene.shop.ui.mine.mvpview.IAddAlipayView;

/**
 * 添加支付宝
 * Created by scene on 2017/11/14.
 */

public class AddAlipayPresenter extends BasePresenter<IAddAlipayView> {
    private IAddAlipayView addAlipayView;
    private AddAlipayModel model;

    public AddAlipayPresenter(IAddAlipayView addAlipayView) {
        this.addAlipayView = addAlipayView;
        model = new AddAlipayModel();
    }

    public void addAlipay(BankInfo bankInfo) {
        try {
            HttpParams params = new HttpParams();
            params.put("name", bankInfo.getName());
            params.put("account", bankInfo.getAccount());
            params.put("type", AppConfig.BANK_TYPE_ALIPAY);
            addAlipayView.showLoading(R.string.loading);
            model.addAlipayCount(params, new HttpResultListener<AddBankResultInfo>() {
                @Override
                public void onSuccess(AddBankResultInfo data) {
                    try {
                        if (data != null && data.getBank_id() != 0) {
                            addAlipayView.showMessage("支付宝账号绑定成功");
                            addAlipayView.bindOrUpdateSuccess();
                        } else {
                            addAlipayView.showMessage("支付宝账号绑定失败");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        addAlipayView.showMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFinish() {
                    try {
                        addAlipayView.hideLoading();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateAlipay(BankInfo bankInfo) {
        try {
            HttpParams params = new HttpParams();
            params.put("name", bankInfo.getName());
            params.put("account", bankInfo.getAccount());
            params.put("type", AppConfig.BANK_TYPE_ALIPAY);
            params.put("bank_id", bankInfo.getId());
            addAlipayView.showLoading(R.string.loading);
            model.addAlipayCount(params, new HttpResultListener<AddBankResultInfo>() {
                @Override
                public void onSuccess(AddBankResultInfo data) {
                    try {
                        if (data != null && data.getBank_id() != 0) {
                            addAlipayView.showMessage("支付宝账号修改成功");
                            addAlipayView.bindOrUpdateSuccess();
                        } else {
                            addAlipayView.showMessage("支付宝账号修改失败");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        addAlipayView.showMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFinish() {
                    try {
                        addAlipayView.hideLoading();
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
