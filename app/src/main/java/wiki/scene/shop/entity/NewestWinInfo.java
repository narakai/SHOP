package wiki.scene.shop.entity;

import java.io.Serializable;

/**
 * 最新获奖
 * Created by scene on 2017/11/20.
 */

public class NewestWinInfo implements Serializable {

    /**
     * user_id : 18
     * create_time : 1510574867
     * number : 5
     * product_id : 1
     * nickname : 现在咋整
     * product_name : 中国电信话费50元
     */

    private int user_id;
    private long create_time;
    private int number;
    private int product_id;
    private String nickname;
    private String product_name;
    private String avatar;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
