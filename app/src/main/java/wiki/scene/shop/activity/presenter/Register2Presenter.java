package wiki.scene.shop.activity.presenter;

import android.text.TextUtils;

import com.lzy.okgo.model.HttpParams;

import wiki.scene.shop.R;
import wiki.scene.shop.activity.model.Register2Model;
import wiki.scene.shop.activity.model.listener.OnRegisterResultListener;
import wiki.scene.shop.activity.mvpview.IRegister2View;
import wiki.scene.shop.entity.UserInfo;
import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.utils.MD5Util;

/**
 * Case By:注册第二步
 * package:wiki.scene.shop.activity.presenter
 * Author：scene on 2017/6/27 15:39
 */

public class Register2Presenter extends BasePresenter<IRegister2View> {
    private IRegister2View register2View;
    private Register2Model model;

    public Register2Presenter(IRegister2View register2View) {
        this.register2View = register2View;
        model = new Register2Model();
    }

    public void setPassword(String mobile, String code) {
        if (register2View != null) {
            if (TextUtils.isEmpty(register2View.getPassword())) {
                register2View.showFail(R.string.please_edit_password);
                return;
            }
            if (register2View.getPassword().length() < 6 || register2View.getPassword().length() > 20) {
                register2View.showFail(R.string.password_length_6_20);
                return;
            }
            if (TextUtils.isEmpty(register2View.getRePassword()) || !register2View.getPassword().equals(register2View.getRePassword())) {
                register2View.showFail(R.string.twice_password_different);
                return;
            }
            register2View.showLoading(R.string.is_registing);
            HttpParams params = new HttpParams();
            params.put("mobile", mobile);
            params.put("password", MD5Util.string2Md5(register2View.getRePassword(), "UTF-8"));
            params.put("code", code);
            model.register(params, new OnRegisterResultListener() {
                @Override
                public void onRegisterSuccess(UserInfo userInfo) {
                    register2View.registerSuccess(userInfo);
                }

                @Override
                public void onRegisterFail(String message) {
                    register2View.showFail(message);
                }

                @Override
                public void onRegisterFinish() {
                    if (register2View != null) {
                        register2View.hideLoading();
                    }
                }
            });
        }
    }


    public void registerByOthers(int type,String mobile, String code, String unionid, String nickname,String avater, int sex) {
        if (register2View != null) {
            if (TextUtils.isEmpty(register2View.getPassword())) {
                register2View.showFail(R.string.please_edit_password);
                return;
            }
            if (register2View.getPassword().length() < 6 || register2View.getPassword().length() > 20) {
                register2View.showFail(R.string.password_length_6_20);
                return;
            }
            if (TextUtils.isEmpty(register2View.getRePassword()) || !register2View.getPassword().equals(register2View.getRePassword())) {
                register2View.showFail(R.string.twice_password_different);
                return;
            }
            register2View.showLoading(R.string.is_registing);
            HttpParams params = new HttpParams();
            params.put("mobile", mobile);
            params.put("password", MD5Util.string2Md5(register2View.getRePassword(), "UTF-8"));
            params.put("code", code);
            params.put("unionid", unionid);
            if (!TextUtils.isEmpty(nickname)) {
                params.put("nickname", nickname);
            }
            if (sex != 0) {
                params.put("sex", sex);
            }
            if(avater!=null){
                params.put("avatar",avater);
            }
            model.registerByOthers(params, type,new OnRegisterResultListener() {
                @Override
                public void onRegisterSuccess(UserInfo userInfo) {
                    register2View.registerSuccess(userInfo);
                }

                @Override
                public void onRegisterFail(String message) {
                    register2View.showFail(message);
                }

                @Override
                public void onRegisterFinish() {
                    if (register2View != null) {
                        register2View.hideLoading();
                    }
                }
            });
        }
    }
}
