package wiki.scene.shop.activity.presenter;

import wiki.scene.shop.activity.mvpview.ILauncherView;
import wiki.scene.shop.mvp.BasePresenter;

/**
 * Case By:
 * package:wiki.scene.shop.activity.presenter
 * Author：scene on 2017/7/6 15:23
 */

public class LauncherPresenter extends BasePresenter<ILauncherView> {
    private ILauncherView launcherView;

    public LauncherPresenter(ILauncherView launcherView) {
        this.launcherView = launcherView;
    }
}
