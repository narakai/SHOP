package wiki.scene.shop.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 创建订单返回
 * Created by scene on 17-7-20.
 */

public class CreateOrderInfo implements Serializable {

    /**
     * order_id : 19
     * cost : 200
     * cycle : {"title":"秒开电信话费","number":2,"price":100,"cost":200,"cycle_code":2665460}
     * coupons : [{"id":2,"title":"注册送88红包","type":1,"cost":8800,"mini_money":1000,"expired_time":0}]
     * user_money : 0
     */

    private String order_id;
    private int cost;
    private int user_money;
    private CyclesBean cycle;
    private List<CouponsBean> coupons;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getUser_money() {
        return user_money;
    }

    public void setUser_money(int user_money) {
        this.user_money = user_money;
    }

    public CyclesBean getCycle() {
        return cycle;
    }

    public void setCycle(CyclesBean cycle) {
        this.cycle = cycle;
    }

    public List<CouponsBean> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<CouponsBean> coupons) {
        this.coupons = coupons;
    }

    public static class CyclesBean implements Serializable {
        /**
         * title : 秒开电信话费
         * number : 2
         * price : 100
         * cost : 200
         * cycle_code : 2665460
         */

        private String title;
        private int number;
        private int price;
        private int cost;
        private String cycle_code;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
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

        public String getCycle_code() {
            return cycle_code;
        }

        public void setCycle_code(String cycle_code) {
            this.cycle_code = cycle_code;
        }
    }

    public static class CouponsBean implements Serializable {
        /**
         * id : 2
         * title : 注册送88红包
         * type : 1
         * cost : 8800
         * mini_money : 1000
         * expired_time : 0
         */

        private String id;
        private String title;
        private int type;
        private int cost;
        private int mini_money;
        private int expired_time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

        public int getMini_money() {
            return mini_money;
        }

        public void setMini_money(int mini_money) {
            this.mini_money = mini_money;
        }

        public int getExpired_time() {
            return expired_time;
        }

        public void setExpired_time(int expired_time) {
            this.expired_time = expired_time;
        }
    }
}
