package wiki.scene.shop.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 最新揭晓返回的数据
 * Created by scene on 17-8-9.
 */

public class NewestResultInfo implements Serializable {
    private List<NewestInfo> data;
    private ResultPageInfo info;

    public List<NewestInfo> getData() {
        return data;
    }

    public void setData(List<NewestInfo> data) {
        this.data = data;
    }

    public ResultPageInfo getInfo() {
        return info;
    }

    public void setInfo(ResultPageInfo info) {
        this.info = info;
    }

    public class NewestInfo {
        /**
         * id : 2
         * title : iphone6s 64g
         * second_title : iphone6s 64g
         * cycle_code : 2665689
         * need_source : 5888
         * current_source : 5888
         * product_id : 2
         * status : 2
         * lucky_user_id : 0
         * lucky_code : 0
         * open_time : 1502094060
         */
        private String id;
        private String title;
        private String second_title;
        private String cycle_code;
        private int need_source;
        private int current_source;
        private String product_id;
        private int status;
        private String lucky_user_id;
        private String lucky_code;
        private long open_time;
        private String thumb;
        private GoodsDetailInfo.WinnerInfo winner;

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

        public String getSecond_title() {
            return second_title;
        }

        public void setSecond_title(String second_title) {
            this.second_title = second_title;
        }

        public String getCycle_code() {
            return cycle_code;
        }

        public void setCycle_code(String cycle_code) {
            this.cycle_code = cycle_code;
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

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getLucky_user_id() {
            return lucky_user_id;
        }

        public void setLucky_user_id(String lucky_user_id) {
            this.lucky_user_id = lucky_user_id;
        }

        public String getLucky_code() {
            return lucky_code;
        }

        public void setLucky_code(String lucky_code) {
            this.lucky_code = lucky_code;
        }

        public long getOpen_time() {
            return open_time;
        }

        public void setOpen_time(long open_time) {
            this.open_time = open_time;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public GoodsDetailInfo.WinnerInfo getWinner() {
            return winner;
        }

        public void setWinner(GoodsDetailInfo.WinnerInfo winner) {
            this.winner = winner;
        }
    }

    public class WinnerInfo {

        /**
         * count : 2
         * page_total : 1
         * per_page : 5
         * page : 1
         */

        private int count;
        private int page_total;
        private int per_page;
        private int page;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getPage_total() {
            return page_total;
        }

        public void setPage_total(int page_total) {
            this.page_total = page_total;
        }

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }
    }

}
