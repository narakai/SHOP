package wiki.scene.shop.entity;

import java.io.Serializable;

/**
 * Case By:收货地址
 * package:wiki.scene.shop.entity
 * Author：scene on 2017/7/10 11:47
 */

public class AddressInfo implements Serializable {

    /**
     * id : 1
     * user_id : 5
     * name : 任俊家
     * mobile : 13509419130
     * address : 重庆南坪
     * is_default : 0
     * create_time : 1499658127
     */

    private int id;
    private int user_id;
    private String name;
    private String mobile;
    private String address;
    private int is_default;
    private int create_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIs_default() {
        return is_default;
    }

    public void setIs_default(int is_default) {
        this.is_default = is_default;
    }

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }
}
