package wiki.scene.shop.activity.presenter;

import com.lzy.okgo.model.HttpParams;

import wiki.scene.shop.R;
import wiki.scene.shop.activity.model.Register1Model;
import wiki.scene.shop.activity.mvpview.IRegister1View;
import wiki.scene.shop.http.listener.HttpResultListener;
import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.utils.AppUtils;

/**
 * Case By:注册第一步
 * package:wiki.scene.shop.activity.presenter
 * Author：scene on 2017/6/27 14:20
 */

public class Register1Presenter extends BasePresenter<IRegister1View> {
    private IRegister1View register1View;
    private Register1Model model;

    public Register1Presenter(IRegister1View register1View) {
        this.register1View = register1View;
        model = new Register1Model();
    }

    public void getVerificationCode() {
        if (register1View != null) {
            String phoneNumber = register1View.getPhoneNumber();
            if (phoneNumber.isEmpty()) {
                register1View.showFail(R.string.please_edit_phone_munber);
                return;
            }
            if (!AppUtils.isMobileNO(phoneNumber)) {
                register1View.showFail(R.string.please_edit_right_phone_number);
                return;
            }
            register1View.showLoading();
            HttpParams params = new HttpParams();
            params.put("mobile", phoneNumber);
            model.getVerificationCode(params, new HttpResultListener<String>() {
                @Override
                public void onSuccess(String data) {
                    if (register1View != null) {
                        register1View.showCountDownTimer();
                        register1View.showFail(R.string.code_has_send);
                    }
                }

                @Override
                public void onFail(String message) {
                    if (register1View != null) {
                        register1View.showFail(message);
                    }
                }

                @Override
                public void onFinish() {
                    if (register1View != null) {
                        register1View.hideLoading();
                    }
                }
            });
        }
    }

    public void checkCode() {
        if (register1View != null) {
            String phoneNumber = register1View.getPhoneNumber();
            String code = register1View.getCode();
            if (phoneNumber.isEmpty()) {
                register1View.showFail(R.string.please_edit_phone_munber);
                return;
            }
            if (!AppUtils.isMobileNO(phoneNumber)) {
                register1View.showFail(R.string.please_edit_right_phone_number);
                return;
            }
            if (code.isEmpty()) {
                register1View.showFail(R.string.please_edit_verification);
                return;
            }
            register1View.showLoading(R.string.loading);
            HttpParams params = new HttpParams();
            params.put("mobile", phoneNumber);
            params.put("code", code);
            model.checkCode(params, new HttpResultListener<String>() {
                @Override
                public void onSuccess(String data) {
                    if (register1View != null) {
                        register1View.enterNextStep();
                    }
                }

                @Override
                public void onFail(String message) {
                    if (register1View != null) {
                        register1View.showFail(message);
                    }
                }

                @Override
                public void onFinish() {
                    if (register1View != null) {
                        register1View.hideLoading();
                    }
                }
            });
        }
    }

}
