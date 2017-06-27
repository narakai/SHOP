package wiki.scene.shop.entity;

import java.io.Serializable;

/**
 * Case By:用户登录返回的信息
 * package:wiki.scene.shop.entity
 * Author：scene on 2017/6/27 11:56
 */

public class UserInfo implements Serializable {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
