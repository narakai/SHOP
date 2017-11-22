package wiki.scene.shop.entity;

import java.io.Serializable;

/**
 * 订单信息
 * Created by scene on 17-8-1.
 */

public class MineOrderInfo implements Serializable {

    /**
     * product_id : 1
     * play_type : 1
     * buy_type : 2
     * number : 5
     * price_type : 1
     * price : 2800
     * cost : 14000
     * cycle_id : 80
     * cycle_code : 20171109043
     * open_time : 1510204200
     * done_time : 0
     * status : 2   1 => '待支付',2 => '已支付',3 => '中奖',4 => '未中奖'
     * name : 中国电信话费50元
     * thumb : /img/1.jpg
     * ssc_result : 0
     * ssc_status : 1
     */
    private int order_id;
    private int product_id;
    private int play_type;
    private int buy_type;
    private int number;
    private int price_type;
    private int price;
    private int cost;
    private int cycle_id;
    private String cycle_code;
    private long open_time;
    private long done_time;
    private int status;
    private String name;
    private String thumb;
    private String ssc_result;
    private int ssc_status;
    private long create_time;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getPlay_type() {
        return play_type;
    }

    public void setPlay_type(int play_type) {
        this.play_type = play_type;
    }

    public int getBuy_type() {
        return buy_type;
    }

    public void setBuy_type(int buy_type) {
        this.buy_type = buy_type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPrice_type() {
        return price_type;
    }

    public void setPrice_type(int price_type) {
        this.price_type = price_type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCycle_id() {
        return cycle_id;
    }

    public void setCycle_id(int cycle_id) {
        this.cycle_id = cycle_id;
    }

    public String getCycle_code() {
        return cycle_code;
    }

    public void setCycle_code(String cycle_code) {
        this.cycle_code = cycle_code;
    }

    public long getOpen_time() {
        return open_time;
    }

    public void setOpen_time(long open_time) {
        this.open_time = open_time;
    }

    public long getDone_time() {
        return done_time;
    }

    public void setDone_time(long done_time) {
        this.done_time = done_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getSsc_result() {
        return ssc_result;
    }

    public void setSsc_result(String ssc_result) {
        this.ssc_result = ssc_result;
    }

    public int getSsc_status() {
        return ssc_status;
    }

    public void setSsc_status(int ssc_status) {
        this.ssc_status = ssc_status;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }
}
