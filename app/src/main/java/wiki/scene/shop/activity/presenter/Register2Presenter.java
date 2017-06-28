package wiki.scene.shop.activity.presenter;

import android.text.TextUtils;

import wiki.scene.shop.R;
import wiki.scene.shop.activity.mvpview.IRegister2View;
import wiki.scene.shop.mvp.BasePresenter;

/**
 * Case By:注册第二步
 * package:wiki.scene.shop.activity.presenter
 * Author：scene on 2017/6/27 15:39
 */

public class Register2Presenter extends BasePresenter<IRegister2View> {
    private IRegister2View register2View;

    public Register2Presenter(IRegister2View register2View) {
        this.register2View = register2View;
    }

    public void setPassword() {
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
            register2View.registerSuccess();
        }
    }
}
