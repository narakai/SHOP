package wiki.scene.shop.entity;

import java.io.Serializable;

/**
 * 提现记录
 * Created by scene on 2017/11/22.
 */

public class CashRecordInfo implements Serializable {

    /**
     * money : 10000
     * spend : 10200
     * done_time : 1234567890
     */

    private long money;
    private long spend;
    private long done_time;

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public long getSpend() {
        return spend;
    }

    public void setSpend(long spend) {
        this.spend = spend;
    }

    public long getDone_time() {
        return done_time;
    }

    public void setDone_time(long done_time) {
        this.done_time = done_time;
    }
}
