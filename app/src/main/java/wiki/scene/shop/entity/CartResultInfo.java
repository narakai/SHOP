package wiki.scene.shop.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Case By:购物车接口返回
 * package:wiki.scene.shop.entity
 * Author：scene on 2017/7/13 15:26
 */

public class CartResultInfo implements Serializable {
    private List<CartInfo> cycles;
    private List<ListGoodsInfo> like;

    public List<CartInfo> getCycles() {
        return cycles;
    }

    public void setCycles(List<CartInfo> cycles) {
        this.cycles = cycles;
    }

    public List<ListGoodsInfo> getLike() {
        return like;
    }

    public void setLike(List<ListGoodsInfo> like) {
        this.like = like;
    }
}
