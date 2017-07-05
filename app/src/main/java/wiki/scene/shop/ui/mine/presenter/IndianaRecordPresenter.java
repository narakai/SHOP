package wiki.scene.shop.ui.mine.presenter;

import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.mvpview.IIdianaRecordView;
import wiki.scene.shop.ui.mine.mvpview.IIndianaRecordTypeView;

/**
 * Case By:夺宝记录主界面
 * package:wiki.scene.shop.ui.mine.presenter
 * Author：scene on 2017/7/5 13:50
 */

public class IndianaRecordPresenter extends BasePresenter<IIdianaRecordView> {
    private IIdianaRecordView indianaRecordView;

    public IndianaRecordPresenter(IIdianaRecordView indianaRecordView) {
        this.indianaRecordView = indianaRecordView;
    }
}
