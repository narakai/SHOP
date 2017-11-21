package wiki.scene.shop.entity;

/**
 * 添加银行卡
 * Created by scene on 2017/11/21.
 */

public class AddBankCardSuccess {
    private BankInfo bankInfo;

    public AddBankCardSuccess(BankInfo bankInfo) {
        this.bankInfo = bankInfo;
    }

    public BankInfo getBankInfo() {
        return bankInfo;
    }
}
