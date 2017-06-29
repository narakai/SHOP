package wiki.scene.shop.fragment.share.presenter;

import wiki.scene.shop.fragment.share.mvpview.IShareHotestView;
import wiki.scene.shop.fragment.share.mvpview.IShareView;
import wiki.scene.shop.mvp.BasePresenter;

/**
 * Case By:最热晒单
 * package:wiki.scene.shop.fragment.share.presenter
 * Author：scene on 2017/6/29 11:48
 */

public class ShareHotestPrsenter extends BasePresenter<IShareHotestView> {
    private IShareHotestView shareHotestView;

    public ShareHotestPrsenter(IShareHotestView shareHotestView) {
        this.shareHotestView = shareHotestView;
    }
}
