package wiki.scene.shop.ui.indiana.presenter;

import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.indiana.mvpview.IOldAnnouncedView;

/**
 * Case By:往期揭晓
 * package:wiki.scene.shop.ui.indiana.presenter
 * Author：scene on 2017/7/5 12:42
 */

public class OldAnnouncedPresenter extends BasePresenter<IOldAnnouncedView> {
    private IOldAnnouncedView oldAnnouncedView;

    public OldAnnouncedPresenter(IOldAnnouncedView oldAnnouncedView) {
        this.oldAnnouncedView = oldAnnouncedView;
    }
}
