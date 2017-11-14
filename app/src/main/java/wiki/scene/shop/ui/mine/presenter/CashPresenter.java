package wiki.scene.shop.ui.mine.presenter;

import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.model.CashModel;
import wiki.scene.shop.ui.mine.mvpview.ICashView;

/**
 * 提现
 * Created by scene on 2017/11/14.
 */

public class CashPresenter extends BasePresenter<ICashView> {
    private ICashView cashView;
    private CashModel model;

    public CashPresenter(ICashView cashView) {
        this.cashView = cashView;
        model=new CashModel();
    }
}
