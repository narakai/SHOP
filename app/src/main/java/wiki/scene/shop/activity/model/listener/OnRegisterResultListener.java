package wiki.scene.shop.activity.model.listener;

import wiki.scene.shop.entity.UserInfo;

/**
 * Case By:注册返回的监听器
 * package:wiki.scene.shop.activity.model.listener
 * Author：scene on 2017/7/5 16:36
 */

public interface OnRegisterResultListener {
    void onRegisterSuccess(UserInfo userInfo);

    void onRegisterFail(String message);
}
