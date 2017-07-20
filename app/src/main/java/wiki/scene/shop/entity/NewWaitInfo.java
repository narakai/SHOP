package wiki.scene.shop.entity;

import java.io.Serializable;

/**
 * Case By:最新开奖
 * package:wiki.scene.shop.entity
 * Author：scene on 2017/7/12 14:15
 */

public class NewWaitInfo implements Serializable {
    private int cycle_id;
    private String thumb;
    private String title;
    private String publish_time;

    public int getCycle_id() {
        return cycle_id;
    }

    public void setCycle_id(int cycle_id) {
        this.cycle_id = cycle_id;
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

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }
}
