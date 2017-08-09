package wiki.scene.shop.entity;

import java.io.Serializable;

/**
 * Case By:最新开奖
 * package:wiki.scene.shop.entity
 * Author：scene on 2017/7/12 14:15
 */

public class NewWaitInfo implements Serializable {

    /**
     * title : iphone6s 64g
     * thumb : /img/iphone.jpg
     * type : 2
     * open_time : 1502094060
     * cycle_code : 2665689
     * id : 2
     */

    private String title;
    private String thumb;
    private int type;
    private long open_time;
    private String cycle_code;
    private String id;

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

    public long getOpen_time() {
        return open_time;
    }

    public void setOpen_time(long open_time) {
        this.open_time = open_time;
    }

    public String getCycle_code() {
        return cycle_code;
    }

    public void setCycle_code(String cycle_code) {
        this.cycle_code = cycle_code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
