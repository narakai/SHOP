package wiki.scene.shop.entity;

import java.io.Serializable;

/**
 * Case By:添加修改购物车返回
 * package:wiki.scene.shop.entity
 * Author：scene on 2017/7/13 15:08
 */

public class AddCartResultInfo implements Serializable {

    /**
     * user_id : 5
     * product_id : 1
     * cycle_id : 1
     * number : 1
     * create_time : 1499929750
     * id : 5
     */

    private int user_id;
    private int product_id;
    private int cycle_id;
    private int number;
    private int create_time;
    private int id;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getCycle_id() {
        return cycle_id;
    }

    public void setCycle_id(int cycle_id) {
        this.cycle_id = cycle_id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
