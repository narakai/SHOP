package wiki.scene.shop.event;

import wiki.scene.shop.entity.UserInfo;

/**
 * Case By:
 * package:wiki.scene.shop.event
 * Author：scene on 2017/6/27 16:27
 */

public class RegisterSuccessEvent {
    private UserInfo userInfo;

    public RegisterSuccessEvent(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }
}
