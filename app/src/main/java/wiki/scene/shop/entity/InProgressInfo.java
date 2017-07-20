package wiki.scene.shop.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Case By:首页下面的tab列表
 * package:wiki.scene.shop.entity
 * Author：scene on 2017/7/12 14:17
 */

public class InProgressInfo implements Serializable {
    private List<ListGoodsInfo> hot;
    private List<ListGoodsInfo> newest;
    private List<ListGoodsInfo> price_asc;
    private List<ListGoodsInfo> price_desc;
    private List<ListGoodsInfo> source;

    public List<ListGoodsInfo> getHot() {
        return hot;
    }

    public void setHot(List<ListGoodsInfo> hot) {
        this.hot = hot;
    }

    public List<ListGoodsInfo> getNewest() {
        return newest;
    }

    public void setNewest(List<ListGoodsInfo> newest) {
        this.newest = newest;
    }

    public List<ListGoodsInfo> getPrice_asc() {
        return price_asc;
    }

    public void setPrice_asc(List<ListGoodsInfo> price_asc) {
        this.price_asc = price_asc;
    }

    public List<ListGoodsInfo> getPrice_desc() {
        return price_desc;
    }

    public void setPrice_desc(List<ListGoodsInfo> price_desc) {
        this.price_desc = price_desc;
    }

    public List<ListGoodsInfo> getSource() {
        return source;
    }

    public void setSource(List<ListGoodsInfo> source) {
        this.source = source;
    }
}
