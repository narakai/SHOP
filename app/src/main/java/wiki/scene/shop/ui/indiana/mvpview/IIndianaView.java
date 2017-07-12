package wiki.scene.shop.ui.indiana.mvpview;

import java.util.List;

import wiki.scene.shop.entity.IndianaIndexInfo;
import wiki.scene.shop.entity.SliderInfo;
import wiki.scene.shop.entity.WinningNoticeInfo;
import wiki.scene.shop.mvp.BaseView;

/**
 * Case By:夺宝
 * package:wiki.scene.shop.fragment.indiana.mvpview
 * Author：scene on 2017/6/28 10:20
 */

public interface IIndianaView extends BaseView {
    void setTitlebarChoosed(int choosedPosition, int oldChoosedPosition);

    void getDataSuccess(IndianaIndexInfo indexInfo, boolean isRefresh);

    void showMessage(String msg);

    void showFailPage();

    void bindBannerData(List<SliderInfo> bannerList);

    void bindWinnerNotice(List<WinningNoticeInfo> noticeInfoList);
}
