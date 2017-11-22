package wiki.scene.shop.entity;

import java.io.Serializable;

/**
 * 自己的pk信息
 * Created by scene on 2017/11/22.
 */

public class PkMineInfo implements Serializable {
    private String username;
    private String avatar;
    private int buy_type;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getBuy_type() {
        return buy_type;
    }

    public void setBuy_type(int buy_type) {
        this.buy_type = buy_type;
    }
}
