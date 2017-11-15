package wiki.scene.shop.ui.mine.presenter;

import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.model.CashRecordModel;
import wiki.scene.shop.ui.mine.mvpview.ICashRecordView;

/**
 * 提现记录
 * Created by scene on 2017/11/15.
 */

public class CashRecordPresenter extends BasePresenter<ICashRecordView> {
    private ICashRecordView cashRecordView;
    private CashRecordModel cashRecordModel;

    public CashRecordPresenter(ICashRecordView cashRecordView) {
        this.cashRecordView = cashRecordView;
        cashRecordModel=new CashRecordModel();
    }
}
