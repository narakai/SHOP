package wiki.scene.shop.entity;

import java.io.Serializable;

/**
 * 银行卡信息
 * Created by scene on 2017/11/21.
 */

public class BankInfo implements Serializable {

    /**
     * type : 1
     * name : 测试
     * account : 1234567890123456
     * bank : 中国银行重庆分行
     */

    private int type;
    private int id;
    private String name;
    private String account;
    private String bank;
    private String open_bank;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getOpen_bank() {
        return open_bank;
    }

    public void setOpen_bank(String open_bank) {
        this.open_bank = open_bank;
    }
}
