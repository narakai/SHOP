package wiki.scene.shop.ui.mine.presenter;

import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.model.AddBankModel;
import wiki.scene.shop.ui.mine.mvpview.IAddAlipayView;
import wiki.scene.shop.ui.mine.mvpview.IAddBankView;

/**
 * 添加银行卡
 * Created by scene on 2017/11/14.
 */

public class AddBankPresenter extends BasePresenter<IAddBankView> {
    private IAddBankView bankView;
    private AddBankModel model;

    public AddBankPresenter(IAddBankView bankView) {
        this.bankView = bankView;
        model=new AddBankModel();
    }
}
