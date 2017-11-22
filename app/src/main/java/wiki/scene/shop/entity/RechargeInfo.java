package wiki.scene.shop.entity;

import java.io.Serializable;

/**
 * 充值返回
 * Created by scene on 2017/11/22.
 */

public class RechargeInfo implements Serializable {
    /**
     * url : https://statecheck.swiftpass.cn/pay/wappay?token_id=2cb4dca12793bc6a247d42ef0b4fb92e1&service=pay.weixin.wappayv2
     * code_url :
     * pay_type : 2
     * api : 1
     * real_pay_type : 4
     * order_id : 59
     */

    private String url;
    private String code_url;
    private int pay_type;
    private int api;
    private int real_pay_type;
    private int order_id;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCode_url() {
        return code_url;
    }

    public void setCode_url(String code_url) {
        this.code_url = code_url;
    }

    public int getPay_type() {
        return pay_type;
    }

    public void setPay_type(int pay_type) {
        this.pay_type = pay_type;
    }

    public int getApi() {
        return api;
    }

    public void setApi(int api) {
        this.api = api;
    }

    public int getReal_pay_type() {
        return real_pay_type;
    }

    public void setReal_pay_type(int real_pay_type) {
        this.real_pay_type = real_pay_type;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    @Override
    public String toString() {
        return "RechargeInfo{" +
                "url='" + url + '\'' +
                ", code_url='" + code_url + '\'' +
                ", pay_type=" + pay_type +
                ", api=" + api +
                ", real_pay_type=" + real_pay_type +
                ", order_id=" + order_id +
                '}';
    }
}
