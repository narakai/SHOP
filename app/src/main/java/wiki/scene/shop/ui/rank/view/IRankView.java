package wiki.scene.shop.ui.rank.view;

import android.support.annotation.StringRes;

import java.util.List;

import wiki.scene.shop.entity.RankInfo;
import wiki.scene.shop.mvp.BaseView;

/**
 * 排行榜
 * Created by scene on 17-8-16.
 */

public interface IRankView extends BaseView {
    void showLoadingPage();

    void showContentPage();

    void showErrorPage();

    void showNonePage();

    void refreshCompilt();

    void showMessage(@StringRes int resId);

    void showMessage(String message);

    void bindData(List<RankInfo> data);
}
