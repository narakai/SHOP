package wiki.scene.shop.activity.model.listener;

import wiki.scene.shop.entity.UserInfo;

/**
 * Case By:登录回调
 * package:wiki.scene.shop.activity.model.listener
 * Author：scene on 2017/6/27 12:56
 */

public interface OnLoginListener {
    void onLoginSuccess(UserInfo userInfo);

    void onLoginFail();
}
