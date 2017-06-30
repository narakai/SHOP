package wiki.scene.shop.ui.newest.presenter;

import wiki.scene.shop.ui.newest.mvpview.INewestView;
import wiki.scene.shop.mvp.BasePresenter;

/**
 * Case By:
 * package:wiki.scene.shop.fragment.newest.presenter
 * Author：scene on 2017/6/29 15:07
 */

public class NewestPresenter extends BasePresenter<INewestView> {
    private INewestView newestView;

    public NewestPresenter(INewestView newestView) {
        this.newestView = newestView;
    }
}
