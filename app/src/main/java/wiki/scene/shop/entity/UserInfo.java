package wiki.scene.shop.entity;

import java.io.Serializable;

/**
 * Case By:用户登录返回的信息
 * package:wiki.scene.shop.entity
 * Author：scene on 2017/6/27 11:56
 */

public class UserInfo implements Serializable {

    /**
     * user_id : 5
     * access_token : 6bbee4409745ff9f46ef39808f9f3b61
     * refresh_token : d0ac833867bd2ae06cba157cc3733739
     * create_time : 1499324855
     * expired_time : 1500620855
     * mobile : 13509419130
     * nickname :
     * level : 1
     * sex : 1
     * payed_times : 0
     * score : 0
     * avatar :
     * money : 0
     */

    private int user_id;
    private String access_token;
    private String refresh_token;
    private long create_time;
    private long expired_time;
    private String mobile;
    private String nickname;
    private int level;
    private int sex;
    private int payed_times;
    private int score;
    private String avatar;
    private int money;

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
        return create_time*1000;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public long getExpired_time() {
        return expired_time*1000;
    }

    public void setExpired_time(long expired_time) {
        this.expired_time = expired_time;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getPayed_times() {
        return payed_times;
    }

    public void setPayed_times(int payed_times) {
        this.payed_times = payed_times;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
