package wiki.scene.shop.entity;

import java.io.Serializable;

/**
 * Case By:购物车
 * package:wiki.scene.shop.entity
 * Author：scene on 2017/7/13 15:24
 */

public class CartInfo  implements Serializable {

    /**
     * id : 8
     * user_id : 5
     * product_id : 1
     * cycle_id : 1
     * number : 1
     * status : 1
     * remark : null
     * create_time : 1499930569
     * need_source : 100
     * current_source : 0
     * title : 秒开电信话费
     * thumb : /img/hf.jpg
     */

    private int id;
    private int user_id;
    private String product_id;
    private int cycle_id;
    private int number;
    private int status;
    private String remark;
    private long create_time;
    private int need_source;
    private int current_source;
    private String title;
    private String thumb;
    private int price;
    private boolean checked = true;

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

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public int getNeed_source() {
        return need_source;
    }

    public void setNeed_source(int need_source) {
        this.need_source = need_source;
    }

    public int getCurrent_source() {
        return current_source;
    }

    public void setCurrent_source(int current_source) {
        this.current_source = current_source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
