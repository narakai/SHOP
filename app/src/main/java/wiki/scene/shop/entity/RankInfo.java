package wiki.scene.shop.entity;

import java.io.Serializable;

/**
 * 排行榜
 * Created by scene on 17-8-16.
 */

public class RankInfo implements Serializable {

    /**
     * user_id : 5
     * win_times : 1
     * total_cost : 588800
     * avatar : /avatar/85dfa5b6b7063be625c29c87377305df.jpeg
     * nickname : scene1
     */

    private String user_id;
    private int win_times;
    private String total_cost;
    private String avatar;
    private String nickname;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getWin_times() {
        return win_times;
    }

    public void setWin_times(int win_times) {
        this.win_times = win_times;
    }

    public String getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(String total_cost) {
        this.total_cost = total_cost;
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
}
