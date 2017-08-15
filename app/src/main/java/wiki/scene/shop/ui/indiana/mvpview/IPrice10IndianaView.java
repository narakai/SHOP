package wiki.scene.shop.ui.indiana.mvpview;

import android.support.annotation.StringRes;

import wiki.scene.shop.entity.Price10IndianaResultInfo;
import wiki.scene.shop.mvp.BaseView;

/**
 * 10元和秒开
 * Created by scene on 17-8-15.
 */

public interface IPrice10IndianaView extends BaseView {
    void showLoadingPage();

    void showErrorPage();

    void showEmptyPage();

    void showContentPage();

    void showMessage(String msg);

    void showMessage(@StringRes int resId);

    void refreshCompile();

    void loadmoreFail();

    void changePage(int page);

    void bindData(Price10IndianaResultInfo resultInfo);
}
