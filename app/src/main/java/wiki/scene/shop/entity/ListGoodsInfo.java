package wiki.scene.shop.entity;

import java.io.Serializable;

/**
 * Case By:
 * package:wiki.scene.shop.entity
 * Author：scene on 2017/7/12 14:17
 */

public class ListGoodsInfo implements Serializable {

    /**
     * title : 秒开电信话费
     * thumb : /img/hf.jpg
     * type : 1
     * open_time : 0
     * cycle_code : 2665460
     * need_source : 90
     * current_source : 0
     * id : 1
     * price : 0
     */

    private String title;
    private String thumb;
    private int type;
    private int open_time;
    private int cycle_code;
    private int need_source;
    private int current_source;
    private int id;
    private int price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getOpen_time() {
        return open_time;
    }

    public void setOpen_time(int open_time) {
        this.open_time = open_time;
    }

    public int getCycle_code() {
        return cycle_code;
    }

    public void setCycle_code(int cycle_code) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
