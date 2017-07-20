package wiki.scene.shop.entity;

import java.io.Serializable;

/**
 * Case By:夺宝头条通知
 * package:wiki.scene.shop.entity
 * Author：scene on 2017/7/12 14:12
 */

public class WinningNoticeInfo implements Serializable {
    private String nickname;
    private int cycle_code;
    private int cost;
    private String product_name;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getCycle_code() {
        return cycle_code;
    }

    public void setCycle_code(int cycle_code) {
        this.cycle_code = cycle_code;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }
}
