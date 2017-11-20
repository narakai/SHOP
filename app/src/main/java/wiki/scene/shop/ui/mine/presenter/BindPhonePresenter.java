package wiki.scene.shop.ui.mine.presenter;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.lzy.okgo.model.HttpParams;

import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.listener.HttpResultListener;
import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.model.BindPhoneModel;
import wiki.scene.shop.ui.mine.mvpview.IBindPhoneView;
import wiki.scene.shop.utils.MD5Util;

/**
 * 绑定手机号
 * Created by scene on 2017/11/15.
 */

public class BindPhonePresenter extends BasePresenter<IBindPhoneView> {
    private IBindPhoneView bindPhoneView;
    private BindPhoneModel bindPhoneModel;

    public BindPhonePresenter(IBindPhoneView bindPhoneView) {
        this.bindPhoneView = bindPhoneView;
        bindPhoneModel = new BindPhoneModel();
    }

    public void getSMS() {
        try {
            String phoneNumber = bindPhoneView.getPhoneNumber();
            if (StringUtils.isTrimEmpty(phoneNumber)) {
                bindPhoneView.showMessage("请输入手机号");
                return;
            }
            if (!RegexUtils.isMobileSimple(phoneNumber)) {
                bindPhoneView.showMessage("请输入正确的手机号");
                return;
            }
            HttpParams params = new HttpParams();
            params.put(ApiUtil.createParams());
            params.put("user_id", ShopApplication.userInfo.getUser_id());
            params.put("access_token", ShopApplication.userInfo.getAccess_token());
            params.put("target_mobile", bindPhoneView.getPhoneNumber());

            bindPhoneView.showLoading(R.string.loading);
            bindPhoneModel.getResetPhoneSMS(params, new HttpResultListener<String>() {
                @Override
                public void onSuccess(String data) {
                    try {
                        bindPhoneView.getSMSSuccess();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        bindPhoneView.showMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFinish() {
                    try {
                        bindPhoneView.hideLoading();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resetPhoneMumber() {
        try {
            String phoneNumber = bindPhoneView.getPhoneNumber();
            String password = bindPhoneView.getPassword().trim();
            String code = bindPhoneView.getCode();
            if (StringUtils.isTrimEmpty(phoneNumber)) {
                bindPhoneView.showMessage("请输入手机号");
                return;
            }
            if (!RegexUtils.isMobileSimple(phoneNumber)) {
                bindPhoneView.showMessage("请输入正确的手机号");
                return;
            }
            if (StringUtils.isTrimEmpty(password)) {
                bindPhoneView.showMessage("请输入密码");
                return;
            }
            if (StringUtils.isTrimEmpty(code)) {
                bindPhoneView.showMessage("请输入验证码");
                return;
            }
            HttpParams params = new HttpParams();
            params.put(ApiUtil.createParams());
            params.put("target_mobile", phoneNumber);
            params.put("user_id", ShopApplication.userInfo.getUser_id());
            params.put("code", code);
            params.put("password", MD5Util.string2Md5(password, "UTF-8"));
            bindPhoneView.showLoading(R.string.loading);
            bindPhoneModel.resetPhoneNumber(params, new HttpResultListener<String>() {
                @Override
                public void onSuccess(String data) {
                    bindPhoneView.resetPhoneNumberSuccess();
                }

                @Override
                public void onFail(String message) {
                    bindPhoneView.showMessage(message);
                }

                @Override
                public void onFinish() {
                    bindPhoneView.hideLoading();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
