package wiki.scene.shop.entity;

import java.io.Serializable;

/**
 * 奖品信息
 * Created by scene on 2017/11/22.
 */

public class PrizeInfo implements Serializable {

    /**
     * id : 5
     * product_id : 1
     * number : 5
     * product_name : 中国电信话费50元
     * thumb : /img/1.jpg
     * cost :   5000
     */

    private int id;
    private int product_id;
    private int number;
    private String product_name;
    private String thumb;
    private int cost;
    private boolean isChecked;
    private int checkedNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getCheckedNumber() {
        return checkedNumber;
    }

    public void setCheckedNumber(int checkedNumber) {
        this.checkedNumber = checkedNumber;
    }
}
