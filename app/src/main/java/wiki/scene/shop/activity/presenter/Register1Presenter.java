package wiki.scene.shop.activity.presenter;

import wiki.scene.shop.activity.mvpview.IRegister1View;
import wiki.scene.shop.mvp.BasePresenter;

/**
 * Case By:注册第一步
 * package:wiki.scene.shop.activity.presenter
 * Author：scene on 2017/6/27 14:20
 */

public class Register1Presenter extends BasePresenter<IRegister1View> {
    private IRegister1View register1View;

    public Register1Presenter(IRegister1View register1View) {
        this.register1View = register1View;
    }

    public void enterNextStep() {
        if (register1View != null) {
            register1View.enterNextStep();
        }
    }
}
