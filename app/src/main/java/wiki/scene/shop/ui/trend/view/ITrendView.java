package wiki.scene.shop.ui.trend.view;

import java.util.List;

import wiki.scene.shop.entity.WinCodeInfo;
import wiki.scene.shop.mvp.BaseView;

/**
 * 开奖走势
 * Created by scene on 2017/11/8.
 */

public interface ITrendView extends BaseView {
    void getTrendDataSuccess(List<WinCodeInfo> list);

    void refreshComplete();

    void showFailPage();

    void showMessage(String message);

    void showLoadingPage();
}
