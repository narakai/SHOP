package wiki.scene.shop.ui.mine.mvpview;

import wiki.scene.shop.mvp.BaseView;

/**
 * 绑定手机号
 * Created by scene on 2017/11/15.
 */

public interface IBindPhoneView extends BaseView {
    String getPhoneNumber();

    void showMessage(String message);

    void getSMSSuccess();

    String getPassword();

    String getCode();

    void resetPhoneNumberSuccess();
}
