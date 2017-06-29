package wiki.scene.shop.fragment.share.presenter;

import wiki.scene.shop.fragment.share.mvpview.IShareView;
import wiki.scene.shop.mvp.BasePresenter;

/**
 * Case By:晒单
 * package:wiki.scene.shop.fragment.share.presenter
 * Author：scene on 2017/6/29 11:48
 */

public class SharePrsenter extends BasePresenter<IShareView> {
    private IShareView shareView;

    public SharePrsenter(IShareView shareView) {
        this.shareView = shareView;
    }
}
