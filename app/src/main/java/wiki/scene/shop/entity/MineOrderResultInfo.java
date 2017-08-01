package wiki.scene.shop.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 我的夺宝记录返回
 * Created by scene on 17-8-1.
 */

public class MineOrderResultInfo implements Serializable {
    List<MineOrderInfo> data;
    ResultPageInfo info;

    public List<MineOrderInfo> getData() {
        return data;
    }

    public void setData(List<MineOrderInfo> data) {
        this.data = data;
    }

    public ResultPageInfo getInfo() {
        return info;
    }

    public void setInfo(ResultPageInfo info) {
        this.info = info;
    }
}
