package wiki.scene.shop.entity;

import java.io.Serializable;

/**
 * Case By:App配置文件
 * package:wiki.scene.shop.entity
 * Author：scene on 2017/7/12 14:00
 */

public class ConfigInfo implements Serializable {

    /**
     * user_agreement : 用户协议
     * intro : 玩法说明
     * file_domain : http://119.23.110.78:8081/
     */

    private String user_agreement;
    private String intro;
    private String file_domain;
    private String withdraw_fee;
    private long server_time;
    private String play_method;

    public String getUser_agreement() {
        return user_agreement;
    }

    public void setUser_agreement(String user_agreement) {
        this.user_agreement = user_agreement;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getFile_domain() {
        return file_domain;
    }

    public void setFile_domain(String file_domain) {
        this.file_domain = file_domain;
    }

    public String getWithdraw_fee() {
        return withdraw_fee;
    }

    public void setWithdraw_fee(String withdraw_fee) {
        this.withdraw_fee = withdraw_fee;
    }

    public long getServer_time() {
        return server_time;
    }

    public void setServer_time(long server_time) {
        this.server_time = server_time;
    }

    public String getPlay_method() {
        return play_method;
    }

    public void setPlay_method(String play_method) {
        this.play_method = play_method;
    }
}
