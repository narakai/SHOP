package wiki.scene.shop.ui.share.presenter;

import wiki.scene.shop.ui.share.mvpview.IShareNewestView;
import wiki.scene.shop.mvp.BasePresenter;

/**
 * Case By:最热晒单
 * package:wiki.scene.shop.fragment.share.presenter
 * Author：scene on 2017/6/29 11:48
 */

public class ShareNewestPrsenter extends BasePresenter<IShareNewestView> {
    private IShareNewestView shareNewestView;

    public ShareNewestPrsenter(IShareNewestView shareNewestView) {
        this.shareNewestView = shareNewestView;
    }
}
