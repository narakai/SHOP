package wiki.scene.shop.entity;

/**
 * 我的信息
 * Created by scene on 2017/11/21.
 */

public class MineInfo {

    /**
     * id : 18
     * uuid : 12345678
     * mobile : 13389628382
     * money : 70980
     * nickname : 现在咋整
     * sex : 1
     * avatar : null
     * today_buy : 0
     * today_win : 0
     * total_win : 14000
     */

    private int id;
    private String uuid;
    private long mobile;
    private int money;
    private String nickname;
    private int sex;
    private Object avatar;
    private int today_buy;
    private int today_win;
    private String total_win;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Object getAvatar() {
        return avatar;
    }

    public void setAvatar(Object avatar) {
        this.avatar = avatar;
    }

    public int getToday_buy() {
        return today_buy;
    }

    public void setToday_buy(int today_buy) {
        this.today_buy = today_buy;
    }

    public int getToday_win() {
        return today_win;
    }

    public void setToday_win(int today_win) {
        this.today_win = today_win;
    }

    public String getTotal_win() {
        return total_win;
    }

    public void setTotal_win(String total_win) {
        this.total_win = total_win;
    }
}
