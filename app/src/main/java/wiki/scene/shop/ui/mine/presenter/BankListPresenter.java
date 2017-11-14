package wiki.scene.shop.ui.mine.presenter;

import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.model.BankListModel;
import wiki.scene.shop.ui.mine.mvpview.IBankListView;

/**
 * 收款账号
 * Created by scene on 2017/11/14.
 */

public class BankListPresenter extends BasePresenter<IBankListView> {
    private IBankListView bankListView;
    private BankListModel model;

    public BankListPresenter(IBankListView bankListView) {
        this.bankListView = bankListView;
        model = new BankListModel();
    }
}
