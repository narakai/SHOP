package wiki.scene.shop.entity;

import java.io.Serializable;

/**
 * Case By:banner
 * package:wiki.scene.shop.entity
 * Author：scene on 2017/7/12 13:47
 */

public class SliderInfo implements Serializable {

    /**
     * id : 1
     * position_id : 1
     * type : 1
     * link : http://news.163.com/photoview/00AP0001/2265791.html
     * src : /1.jpeg
     * status : 1
     * weight : 0
     * create_time : 0
     */

    private int id;
    private int position_id;
    private int type;
    private String link;
    private String src;
    private int status;
    private int weight;
    private long create_time;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosition_id() {
        return position_id;
    }

    public void setPosition_id(int position_id) {
        this.position_id = position_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
