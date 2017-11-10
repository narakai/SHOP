package wiki.scene.shop.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 主页接口
 * Created by scene on 2017/11/10.
 */

public class IndexInfo implements Serializable {

    /**
     * last_ssc_result : {"id":71,"cycle_code":"20171108085","result":0,"open_time":1510143000,"actual_open_time":0,"status":1,"create_time":1510142804}
     * current_cycle : {"cycle_code":"20171108087","open_time":1510144200,"create_time":1510143804,"id":72}
     * products : [{"id":1,"name":"中国电信话费50元","thumb":"/img/1.jpg","price_type":1,"description":"","min_price":"2800"},{"id":2,"name":"中国电信话费100元","thumb":"/img/2.jpg","price_type":2,"description":"","min_price":"5500"},{"id":3,"name":"500元京东购物卡","thumb":"/img/3.jpg","price_type":3,"description":"","min_price":"28000"},{"id":4,"name":"1000元中石油加油卡","thumb":"/img/3.jpg","price_type":4,"description":"","min_price":"55000"}]
     */

    private LastSscResultBean last_ssc_result;
    private CurrentCycleBean current_cycle;
    private List<ProductsBean> products;

    public LastSscResultBean getLast_ssc_result() {
        return last_ssc_result;
    }

    public void setLast_ssc_result(LastSscResultBean last_ssc_result) {
        this.last_ssc_result = last_ssc_result;
    }

    public CurrentCycleBean getCurrent_cycle() {
        return current_cycle;
    }

    public void setCurrent_cycle(CurrentCycleBean current_cycle) {
        this.current_cycle = current_cycle;
    }

    public List<ProductsBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsBean> products) {
        this.products = products;
    }

    public static class LastSscResultBean {
        /**
         * id : 71
         * cycle_code : 20171108085
         * result : 0
         * open_time : 1510143000
         * actual_open_time : 0
         * status : 1
         * create_time : 1510142804
         */

        private int id;
        private String cycle_code;
        private int result;
        private int open_time;
        private int actual_open_time;
        private int status;
        private int create_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCycle_code() {
            return cycle_code;
        }

        public void setCycle_code(String cycle_code) {
            this.cycle_code = cycle_code;
        }

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }

        public int getOpen_time() {
            return open_time;
        }

        public void setOpen_time(int open_time) {
            this.open_time = open_time;
        }

        public int getActual_open_time() {
            return actual_open_time;
        }

        public void setActual_open_time(int actual_open_time) {
            this.actual_open_time = actual_open_time;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }
    }

    public static class CurrentCycleBean {
        /**
         * cycle_code : 20171108087
         * open_time : 1510144200
         * create_time : 1510143804
         * id : 72
         */

        private String cycle_code;
        private int open_time;
        private int create_time;
        private int id;

        public String getCycle_code() {
            return cycle_code;
        }

        public void setCycle_code(String cycle_code) {
            this.cycle_code = cycle_code;
        }

        public int getOpen_time() {
            return open_time;
        }

        public void setOpen_time(int open_time) {
            this.open_time = open_time;
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

    public static class ProductsBean {
        /**
         * id : 1
         * name : 中国电信话费50元
         * thumb : /img/1.jpg
         * price_type : 1
         * description :
         * min_price : 2800
         */

        private int id;
        private String name;
        private String thumb;
        private int price_type;
        private String description;
        private int two_price;
        private int four_price;
        private int ten_price;
        private long open_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public int getPrice_type() {
            return price_type;
        }

        public void setPrice_type(int price_type) {
            this.price_type = price_type;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getTwo_price() {
            return two_price;
        }

        public void setTwo_price(int two_price) {
            this.two_price = two_price;
        }

        public int getFour_price() {
            return four_price;
        }

        public void setFour_price(int four_price) {
            this.four_price = four_price;
        }

        public int getTen_price() {
            return ten_price;
        }

        public void setTen_price(int ten_price) {
            this.ten_price = ten_price;
        }

        public long getOpen_time() {
            return open_time;
        }

        public void setOpen_time(long open_time) {
            this.open_time = open_time;
        }
    }
}
