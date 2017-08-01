package wiki.scene.shop.entity;

import java.io.Serializable;

/**
 * 分页信息返回
 * Created by scene on 17-8-1.
 */

public class ResultPageInfo implements Serializable {

    /**
     * count : 3
     * page_total : 1
     * per_page : 5
     * page : 1
     */

    private int count;
    private int page_total;
    private int per_page;
    private int page;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPage_total() {
        return page_total;
    }

    public void setPage_total(int page_total) {
        this.page_total = page_total;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
