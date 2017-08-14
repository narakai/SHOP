package wiki.scene.shop.ui.mine.mvpview;

import android.support.annotation.StringRes;

import wiki.scene.shop.entity.MineCollectionResultInfo;
import wiki.scene.shop.mvp.BaseView;

/**
 * 我的收藏
 * Created by scene on 17-8-14.
 */

public interface IMineCollectView extends BaseView {
    void showLoadingPage();

    void showErrorPage();

    void showEmptyPage();

    void showContentPage();

    void showMessage(String msg);

    void showMessage(@StringRes int resId);

    void refreshCompile();

    void loadmoreFail();

    void changePage(int page);

    void bindMineCollectData(MineCollectionResultInfo resultInfo);
}
