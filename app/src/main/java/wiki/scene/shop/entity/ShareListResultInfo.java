package wiki.scene.shop.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 晒单列表
 * Created by scene on 17-8-9.
 */

public class ShareListResultInfo implements Serializable {
    private List<ShareListInfo> data;
    private ResultPageInfo info;

    public List<ShareListInfo> getData() {
        return data;
    }

    public void setData(List<ShareListInfo> data) {
        this.data = data;
    }

    public ResultPageInfo getInfo() {
        return info;
    }

    public void setInfo(ResultPageInfo info) {
        this.info = info;
    }

    public class ShareListInfo implements Serializable {

    }
}
