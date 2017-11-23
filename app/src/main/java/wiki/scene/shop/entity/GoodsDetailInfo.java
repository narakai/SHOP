package wiki.scene.shop.entity;

import java.util.List;

/**
 * 产品详情
 * Created by scene on 17-7-26.
 */

public class GoodsDetailInfo {
    private GoodsInfo data;
    private List<WinCodeInfo> cycle_history;

    public GoodsInfo getData() {
        return data;
    }

    public void setData(GoodsInfo data) {
        this.data = data;
    }

    public List<WinCodeInfo> getCycle_history() {
        return cycle_history;
    }

    public void setCycle_history(List<WinCodeInfo> cycle_history) {
        this.cycle_history = cycle_history;
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
        private int two_price;
        private int four_price;
        private int ten_price;
        private String thumb_js;
        private String thumb_gg;
        private String thumb_sh;

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

        public String getThumb_js() {
            return thumb_js;
        }

        public void setThumb_js(String thumb_js) {
            this.thumb_js = thumb_js;
        }

        public String getThumb_gg() {
            return thumb_gg;
        }

        public void setThumb_gg(String thumb_gg) {
            this.thumb_gg = thumb_gg;
        }

        public String getThumb_sh() {
            return thumb_sh;
        }

        public void setThumb_sh(String thumb_sh) {
            this.thumb_sh = thumb_sh;
        }
    }
}
