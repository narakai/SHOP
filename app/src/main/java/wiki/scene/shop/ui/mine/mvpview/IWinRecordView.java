package wiki.scene.shop.ui.mine.mvpview;

import android.support.annotation.StringRes;

import wiki.scene.shop.entity.ResultPageInfo;
import wiki.scene.shop.entity.WinRecordResultInfo;
import wiki.scene.shop.mvp.BaseView;

/**
 * Case By:中奖记录
 * package:wiki.scene.shop.ui.mine.mvpview
 * Author：scene on 2017/7/5 11:36
 */

public interface IWinRecordView extends BaseView {
    void showLoadingPage();

    void showErrorPage();

    void showEmptyPage();

    void showContentPage();

    void showMessage(String msg);

    void showMessage(@StringRes int resId);

    void refreshCompile();

    void loadmoreFail();

    void changePage(int page);

    void bindWinRecordData(WinRecordResultInfo resultInfo);
}
