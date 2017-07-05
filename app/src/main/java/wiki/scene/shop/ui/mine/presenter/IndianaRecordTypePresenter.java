package wiki.scene.shop.ui.mine.presenter;

import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.mvpview.IIndianaRecordTypeView;

/**
 * Case By:夺宝记录
 * package:wiki.scene.shop.ui.mine.presenter
 * Author：scene on 2017/7/5 13:50
 */

public class IndianaRecordTypePresenter extends BasePresenter<IIndianaRecordTypeView> {
    private IIndianaRecordTypeView indianaRecordView;

    public IndianaRecordTypePresenter(IIndianaRecordTypeView indianaRecordView) {
        this.indianaRecordView = indianaRecordView;
    }
}
