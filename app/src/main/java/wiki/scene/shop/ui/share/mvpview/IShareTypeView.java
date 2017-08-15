package wiki.scene.shop.ui.share.mvpview;

import android.support.annotation.StringRes;

import java.util.List;

import wiki.scene.shop.entity.ResultPageInfo;
import wiki.scene.shop.entity.ShareListResultInfo;
import wiki.scene.shop.mvp.BaseView;

/**
 * Case By:最新晒单
 * package:wiki.scene.shop.fragment.share.mvpview
 * Author：scene on 2017/6/29 12:00
 */

public interface IShareTypeView extends BaseView {
    void showLoadingPage();

    void showErrorPage();

    void showEmptyPage();

    void showContentPage();

    void showProgressDialog(@StringRes int resId);

    void hideProgressDialog();

    void showMessage(@StringRes int resId);

    void showMessage(String msg);

    void getShareListDataSuccess(List<ShareListResultInfo.ShareListInfo> list);

    void changePageInfo(ResultPageInfo pageInfo, int page);

    void refreshCompile();

    void loadmoreFail();

    void zanSuccess(int position);
}
