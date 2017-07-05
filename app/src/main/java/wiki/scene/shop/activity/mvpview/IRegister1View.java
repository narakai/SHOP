package wiki.scene.shop.activity.mvpview;

import wiki.scene.shop.mvp.BaseView;

/**
 * Case By:注册第一步
 * package:wiki.scene.shop.activity.view
 * Author：scene on 2017/6/27 14:19
 */

public interface IRegister1View extends BaseView {
    void enterNextStep();
    String getPhoneNumber();
}
