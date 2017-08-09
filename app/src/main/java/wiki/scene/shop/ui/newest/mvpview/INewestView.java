package wiki.scene.shop.ui.newest.mvpview;

import java.util.List;

import wiki.scene.shop.entity.NewestResultInfo;
import wiki.scene.shop.entity.ResultPageInfo;
import wiki.scene.shop.mvp.BaseView;

/**
 * Case By:最新揭晓
 * package:wiki.scene.shop.fragment.newest.mvpview
 * Author：scene on 2017/6/29 15:06
 */

public interface INewestView extends BaseView {
    void showLoadingPage();

    void showEmptyPage();

    void showErrorPage();

    void showContentPage();

    void refreshComplete();

    void bindData(List<NewestResultInfo.NewestInfo> data);

    void changePage(int page);

    void bindPageInfo(ResultPageInfo pageInfo);
}
