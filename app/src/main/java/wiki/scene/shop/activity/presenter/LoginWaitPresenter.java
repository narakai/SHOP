package wiki.scene.shop.activity.presenter;

import wiki.scene.shop.activity.model.LoginModel;
import wiki.scene.shop.activity.model.LoginWaitModel;
import wiki.scene.shop.activity.mvpview.ILoginWaitView;
import wiki.scene.shop.mvp.BasePresenter;

/**
 * 选择登陆注册的界面
 * Created by scene on 2017/11/16.
 */

public class LoginWaitPresenter extends BasePresenter<ILoginWaitView> {
    private ILoginWaitView waitView;
    private LoginWaitModel model;

    public LoginWaitPresenter(ILoginWaitView waitView) {
        this.waitView = waitView;
        model = new LoginWaitModel();
    }
}
