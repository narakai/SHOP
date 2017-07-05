package wiki.scene.shop.entity;

import java.io.Serializable;

/**
 * Case By:用户登录返回的信息
 * package:wiki.scene.shop.entity
 * Author：scene on 2017/6/27 11:56
 */

public class UserInfo implements Serializable {
    private int user_id;
    private String access_token;
    private String refresh_token;
    private long create_time;
    private long expired_time;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public long getExpired_time() {
        return expired_time;
    }

    public void setExpired_time(long expired_time) {
        this.expired_time = expired_time;
    }
}
