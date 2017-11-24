package wiki.scene.shop.activity.mvpview;

import wiki.scene.shop.entity.ConfigInfo;
import wiki.scene.shop.entity.UserInfo;
import wiki.scene.shop.mvp.BaseView;

/**
 * Case By:启动页
 * package:wiki.scene.shop.activity.mvpview
 * Author：scene on 2017/7/6 15:23
 */

public interface ILauncherView extends BaseView {
    void getConfigSuccess(ConfigInfo configInfo);

    void getConfigFail(String res);

    void loginFail();

    void loginSuccess(UserInfo userInfo);
}
