package wiki.scene.shop.entity;

import java.io.Serializable;

/**
 * 检查支付结果
 * Created by scene on 2017/11/23.
 */

public class CheckPayResultInfo implements Serializable {

    /**
     * status : true
     */

    private boolean status;
    private long cost;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }
}
