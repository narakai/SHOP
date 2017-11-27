package wiki.scene.shop.activity.presenter;

import android.text.TextUtils;

import com.lzy.okgo.model.HttpParams;

import wiki.scene.shop.R;
import wiki.scene.shop.activity.model.Register1Model;
import wiki.scene.shop.activity.model.listener.OnRegisterResultListener;
import wiki.scene.shop.activity.mvpview.IRegister1View;
import wiki.scene.shop.entity.UserInfo;
import wiki.scene.shop.http.listener.HttpResultListener;
import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.utils.AppUtils;
import wiki.scene.shop.utils.MD5Util;

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
            register1View.showLoading(R.string.is_get_verification);
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

    public void setPassword() {
        if (register1View != null) {
            if (mView.getPhoneNumber().isEmpty()) {
                register1View.showFail(R.string.please_edit_phone_munber);
                return;
            }
            if (!AppUtils.isMobileNO(mView.getPhoneNumber())) {
                register1View.showFail(R.string.please_edit_right_phone_number);
                return;
            }
            if (TextUtils.isEmpty(register1View.getPassword())) {
                register1View.showFail(R.string.please_edit_password);
                return;
            }
            if (register1View.getPassword().length() < 6 || register1View.getPassword().length() > 20) {
                register1View.showFail(R.string.password_length_6_20);
                return;
            }
            if (mView.getCode().isEmpty()) {
                register1View.showFail(R.string.please_edit_verification);
                return;
            }
            register1View.showLoading(R.string.is_registing);
            HttpParams params = new HttpParams();
            params.put("mobile", mView.getPhoneNumber());
            params.put("password", MD5Util.string2Md5(register1View.getPassword(), "UTF-8"));
            params.put("code", mView.getCode());
            model.register(params, new OnRegisterResultListener() {
                @Override
                public void onRegisterSuccess(UserInfo userInfo) {
                    register1View.registerSuccess(userInfo);
                }

                @Override
                public void onRegisterFail(String message) {
                    register1View.showFail(message);
                }

                @Override
                public void onRegisterFinish() {
                    if (register1View != null) {
                        register1View.hideLoading();
                    }
                }
            });
        }
    }

    public void registerByOthers(int type, String mobile, String code, String unionid, String nickname, String avater, int sex) {
        if (register1View != null) {
            if (TextUtils.isEmpty(register1View.getPassword())) {
                register1View.showFail(R.string.please_edit_password);
                return;
            }
            if (register1View.getPassword().length() < 6 || register1View.getPassword().length() > 20) {
                register1View.showFail(R.string.password_length_6_20);
                return;
            }
            register1View.showLoading(R.string.is_registing);
            HttpParams params = new HttpParams();
            params.put("mobile", mobile);
            params.put("password", MD5Util.string2Md5(register1View.getPassword(), "UTF-8"));
            params.put("code", code);
            params.put("unionid", unionid);
            if (!TextUtils.isEmpty(nickname)) {
                params.put("nickname", nickname);
            }
            if (sex != 0) {
                params.put("sex", sex);
            }
            if (avater != null) {
                params.put("avatar", avater);
            }
            model.registerByOthers(params, type, new OnRegisterResultListener() {
                @Override
                public void onRegisterSuccess(UserInfo userInfo) {
                    register1View.registerSuccess(userInfo);
                }

                @Override
                public void onRegisterFail(String message) {
                    register1View.showFail(message);
                }

                @Override
                public void onRegisterFinish() {
                    if (register1View != null) {
                        register1View.hideLoading();
                    }
                }
            });
        }
    }
}
