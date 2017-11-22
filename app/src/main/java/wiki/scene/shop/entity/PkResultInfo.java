package wiki.scene.shop.entity;

import java.util.List;

/**
 * pk返回的信息
 * Created by scene on 2017/11/22.
 */

public class PkResultInfo {

    /**
     * number : 1
     * play_type : 1
     * nickname : 岳峰1
     * avatar : /avatar/eac76c93e5c8bd3a1d5df8fadbcc6047.jpeg
     * cycle : 20171122050
     * result : 58491
     */

    private int number;
    private int play_type;
    private String nickname;
    private String avatar;
    private String cycle;
    private String result;
    private int buy_type;
    private List<PKInfo> pk;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPlay_type() {
        return play_type;
    }

    public void setPlay_type(int play_type) {
        this.play_type = play_type;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getBuy_type() {
        return buy_type;
    }

    public void setBuy_type(int buy_type) {
        this.buy_type = buy_type;
    }

    public List<PKInfo> getPk() {
        return pk;
    }

    public void setPk(List<PKInfo> pk) {
        this.pk = pk;
    }
}
