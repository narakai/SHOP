package wiki.scene.shop.activity.presenter;

import android.text.TextUtils;

import com.lzy.okgo.model.HttpParams;

import wiki.scene.shop.R;
import wiki.scene.shop.activity.model.FindPasswordModel;
import wiki.scene.shop.activity.mvpview.IFindPasswordView;
import wiki.scene.shop.http.listener.HttpResultListener;
import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.utils.AppUtils;

/**
 * 找回密码
 * Created by scene on 17-8-18.
 */

public class FindPasswordPresenter extends BasePresenter<IFindPasswordView> {
    private IFindPasswordView findPasswordView;
    private FindPasswordModel model;

    public FindPasswordPresenter(IFindPasswordView findPasswordView) {
        this.mView = findPasswordView;
        model = new FindPasswordModel();
    }

    public void getFindPasswordCode(String mobile) {
        try {
            if (mobile.isEmpty()) {
                mView.showMessage(R.string.please_edit_phone_munber);
                return;
            }
            if (!AppUtils.isMobileNO(mobile)) {
                mView.showMessage(R.string.please_edit_right_phone_number);
                return;
            }
            mView.showLoading(R.string.is_get_verification);
            HttpParams params = new HttpParams();
            params.put("mobile", mobile);
            model.getFindPasswordCode(params, new HttpResultListener<String>() {
                @Override
                public void onSuccess(String data) {
                    try {
                        mView.showCountDownTimer();
                        mView.showMessage(R.string.code_has_send);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        mView.showMessage(message);
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

    public void resetPassword(String mobile, String code, String password, String rePassword) {
        try {
            if (mobile.isEmpty()) {
                mView.showMessage(R.string.please_edit_phone_munber);
                return;
            }
            if (!AppUtils.isMobileNO(mobile)) {
                mView.showMessage(R.string.please_edit_right_phone_number);
                return;
            }
            if (TextUtils.isEmpty(code)) {
                mView.showMessage(R.string.please_edit_verification);
                return;
            }
            if (TextUtils.isEmpty(password)) {
                mView.showMessage(R.string.please_edit_password);
                return;
            }
            if (password.length() < 6 || password.length() > 20) {
                mView.showMessage(R.string.password_length_6_20);
                return;
            }
            if (!password.equals(rePassword)) {
                mView.showMessage(R.string.twice_password_different);
                return;
            }
            mView.showLoading(R.string.loading);
            HttpParams params = new HttpParams();
            params.put("mobile", mobile);
            params.put("code", code);
            params.put("password", password);
            model.updatePassword(params, new HttpResultListener<String>() {
                @Override
                public void onSuccess(String data) {
                    try {
                        mView.resetPasswordSuccess();
                        mView.showMessage(R.string.password_reset_success);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        mView.showMessage(message);
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
