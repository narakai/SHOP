package wiki.scene.shop.ui.mine.mvpview;

import java.util.List;

import wiki.scene.shop.entity.BankInfo;
import wiki.scene.shop.mvp.BaseView;

/**
 * 提现
 * Created by scene on 2017/11/14.
 */

public interface ICashView extends BaseView {
    void showLoadingPage();

    void showContentPage();

    void showFailPage();

    void showMessage(String message);

    int getMoney();

    void applyCashFail(String message);

    void applyCashSuccess(BankInfo bankInfo, int money);

    String getBankName();

    String getBankUser();

    String getBankAccount();

    String getAlipayUser();

    String getAlipayAccount();

    int getCashType();
}
