package wiki.scene.shop.entity;

import java.io.Serializable;

/**
 * pk信息
 * Created by scene on 2017/11/22.
 */

public class PKInfo implements Serializable {

    /**
     * target_number : 1
     * target_buy_type : 2
     * avatar : null
     * nickname : 用户63
     * mobile : 13000000063
     */

    private int target_number;
    private int target_buy_type;
    private String avatar;
    private String nickname;
    private String mobile;
    private String target_order_id;

    public int getTarget_number() {
        return target_number;
    }

    public void setTarget_number(int target_number) {
        this.target_number = target_number;
    }

    public int getTarget_buy_type() {
        return target_buy_type;
    }

    public void setTarget_buy_type(int target_buy_type) {
        this.target_buy_type = target_buy_type;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTarget_order_id() {
        return target_order_id;
    }

    public void setTarget_order_id(String target_order_id) {
        this.target_order_id = target_order_id;
    }
}
