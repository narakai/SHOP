package wiki.scene.shop.entity;

import java.util.List;

/**
 * 我的收藏
 * Created by scene on 17-8-14.
 */

public class MineCollectionResultInfo {
    private List<MineCollectionInfo> data;
    private ResultPageInfo info;

    public List<MineCollectionInfo> getData() {
        return data;
    }

    public void setData(List<MineCollectionInfo> data) {
        this.data = data;
    }

    public ResultPageInfo getInfo() {
        return info;
    }

    public void setInfo(ResultPageInfo info) {
        this.info = info;
    }

    public class MineCollectionInfo {
        /**
         * cycle_id : 31
         * type : 3
         * thumb : /img/2017/08/abc7f3f9bfc99248ce221d45d684d495.jpeg
         * title : 保时捷-卡宴 3.6升 双涡轮增压 420马力
         * cycle_code : 2665718
         * status : 1
         * open_time : 0
         * lucky_code : 0
         * lucky_user_id : 0
         * need_source : 138000
         * current_source : 0
         * product_id : 3
         * my_codes : []
         */

        private String cycle_id;
        private int type;
        private String thumb;
        private String title;
        private String cycle_code;
        private int status;
        private long open_time;
        private String lucky_code;
        private String lucky_user_id;
        private int need_source;
        private int current_source;
        private String product_id;
        private List<?> my_codes;

        public String getCycle_id() {
            return cycle_id;
        }

        public void setCycle_id(String cycle_id) {
            this.cycle_id = cycle_id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCycle_code() {
            return cycle_code;
        }

        public void setCycle_code(String cycle_code) {
            this.cycle_code = cycle_code;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public long getOpen_time() {
            return open_time;
        }

        public void setOpen_time(long open_time) {
            this.open_time = open_time;
        }

        public String getLucky_code() {
            return lucky_code;
        }

        public void setLucky_code(String lucky_code) {
            this.lucky_code = lucky_code;
        }

        public String getLucky_user_id() {
            return lucky_user_id;
        }

        public void setLucky_user_id(String lucky_user_id) {
            this.lucky_user_id = lucky_user_id;
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

        public List<?> getMy_codes() {
            return my_codes;
        }

        public void setMy_codes(List<?> my_codes) {
            this.my_codes = my_codes;
        }
    }
}
