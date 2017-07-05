package wiki.scene.shop.ui.mine.presenter;

import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.mvpview.IWinRecordView;

/**
 * Case By:中奖记录
 * package:wiki.scene.shop.ui.mine.presenter
 * Author：scene on 2017/7/5 11:37
 */

public class WinRecordPresenter extends BasePresenter<IWinRecordView> {
    private IWinRecordView winRecordView;

    public WinRecordPresenter(IWinRecordView winRecordView) {
        this.winRecordView = winRecordView;
    }
}
