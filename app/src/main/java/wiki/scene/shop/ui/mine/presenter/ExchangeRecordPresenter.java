package wiki.scene.shop.ui.mine.presenter;

import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.model.ExchangeRecordModel;
import wiki.scene.shop.ui.mine.mvpview.IExchangeRecordView;

/**
 * 兑换记录
 * Created by scene on 2017/11/15.
 */

public class ExchangeRecordPresenter extends BasePresenter<IExchangeRecordView> {
    private IExchangeRecordView recordView;
    private ExchangeRecordModel recordModel;

    public ExchangeRecordPresenter(IExchangeRecordView recordView) {
        this.recordView = recordView;
        recordModel=new ExchangeRecordModel();
    }
}
