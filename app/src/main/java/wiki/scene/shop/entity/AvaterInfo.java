package wiki.scene.shop.entity;

import wiki.scene.shop.ShopApplication;

/**
 * Case By:头像
 * package:wiki.scene.shop.entity
 * Author：scene on 2017/7/7 13:26
 */

public class AvaterInfo {
    private String url;

    public String getUrl() {
        if (url.startsWith("http:")) {
            return url;
        } else {
            return ShopApplication.configInfo.getFile_domain() + url;
        }
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
