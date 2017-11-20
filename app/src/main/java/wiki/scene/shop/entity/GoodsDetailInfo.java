package wiki.scene.shop.entity;

import java.util.List;

/**
 * 产品详情
 * Created by scene on 17-7-26.
 */

public class GoodsDetailInfo {
    private GoodsInfo data;
    private List<WinCodeInfo> current_cycle;

    public GoodsInfo getData() {
        return data;
    }

    public void setData(GoodsInfo data) {
        this.data = data;
    }

    public List<WinCodeInfo> getCurrent_cycle() {
        return current_cycle;
    }

    public void setCurrent_cycle(List<WinCodeInfo> current_cycle) {
        this.current_cycle = current_cycle;
    }

    public static class GoodsInfo {

        /**
         * id : 1
         * name : 中国电信话费50元
         * price_type : 1
         * thumb : /img/1.jpg
         * description :
         * created_at : 2017-08-08 10:37:33
         * updated_at : 2017-08-08 10:37:33
         * two_price : 2800
         * four_price : 1400
         * ten_price : 600
         */

        private int id;
        private String name;
        private int price_type;
        private String thumb;
        private String description;
        private String created_at;
        private String updated_at;
        private String two_price;
        private String four_price;
        private String ten_price;

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

        public int getPrice_type() {
            return price_type;
        }

        public void setPrice_type(int price_type) {
            this.price_type = price_type;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getTwo_price() {
            return two_price;
        }

        public void setTwo_price(String two_price) {
            this.two_price = two_price;
        }

        public String getFour_price() {
            return four_price;
        }

        public void setFour_price(String four_price) {
            this.four_price = four_price;
        }

        public String getTen_price() {
            return ten_price;
        }

        public void setTen_price(String ten_price) {
            this.ten_price = ten_price;
        }
    }
}
