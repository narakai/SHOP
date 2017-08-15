package wiki.scene.shop.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 10元和秒开
 * Created by scene on 17-8-15.
 */

public class Price10IndianaResultInfo implements Serializable {

    private List<Price10IndianaInfo> data;
    private ResultPageInfo info;

    public List<Price10IndianaInfo> getData() {
        return data;
    }

    public void setData(List<Price10IndianaInfo> data) {
        this.data = data;
    }

    public ResultPageInfo getInfo() {
        return info;
    }

    public void setInfo(ResultPageInfo info) {
        this.info = info;
    }

    public class Price10IndianaInfo {
        private String id;
        private String title;
        private String second_title;
        private String cycle_code;
        private int need_source;
        private int current_source;
        private String product_id;
        private int status;
        private long open_time;
        private String thumb;

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
    }
}
