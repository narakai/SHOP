package wiki.scene.shop.entity;

/**
 * 兑换记录
 * Created by scene on 2017/11/22.
 */

public class ExchangeRecordInfo {

    /**
     * product_id : 1
     * prize_id : 5
     * number : 3
     * cost : 15000
     * create_time : 1510821910
     * price : 5000
     */

    private int product_id;
    private int prize_id;
    private int number;
    private int cost;
    private long create_time;
    private int price;
    private String thumb;
    private String prodct_name;

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getPrize_id() {
        return prize_id;
    }

    public void setPrize_id(int prize_id) {
        this.prize_id = prize_id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getProdct_name() {
        return prodct_name;
    }

    public void setProdct_name(String prodct_name) {
        this.prodct_name = prodct_name;
    }
}
