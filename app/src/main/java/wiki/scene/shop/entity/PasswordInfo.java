package wiki.scene.shop.entity;

import java.io.Serializable;

/**
 * 用户，密码
 * Created by scene on 2017/11/24.
 */

public class PasswordInfo implements Serializable {
    private String account;
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
