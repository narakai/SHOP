package wiki.scene.shop.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 提现记录返回
 * Created by scene on 2017/11/22.
 */

public class CashRecordResultInfo implements Serializable {
    private List<CashRecordInfo> data;
    private ResultPageInfo info;

    public List<CashRecordInfo> getData() {
        return data;
    }

    public void setData(List<CashRecordInfo> data) {
        this.data = data;
    }

    public ResultPageInfo getInfo() {
        return info;
    }

    public void setInfo(ResultPageInfo info) {
        this.info = info;
    }
}
