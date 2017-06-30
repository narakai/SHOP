package wiki.scene.shop.ui.mine.presenter;

import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.mvpview.ISettingView;

/**
 * Case By:设置
 * package:wiki.scene.shop.ui.mine.presenter
 * Author：scene on 2017/6/30 14:22
 */

public class SettingPresenter extends BasePresenter<ISettingView> {
    private ISettingView settingView;

    public SettingPresenter(ISettingView settingView) {
        this.settingView = settingView;
    }
}
