package wiki.scene.shop.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 兑换记录返回
 * Created by scene on 2017/11/22.
 */

public class ExchangeRecordResultInfo implements Serializable{
    private List<ExchangeRecordInfo> data;
    private ResultPageInfo info;

    public List<ExchangeRecordInfo> getData() {
        return data;
    }

    public void setData(List<ExchangeRecordInfo> data) {
        this.data = data;
    }

    public ResultPageInfo getInfo() {
        return info;
    }

    public void setInfo(ResultPageInfo info) {
        this.info = info;
    }
}
