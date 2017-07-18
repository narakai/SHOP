package wiki.scene.shop.ui.mine.presenter;

import android.content.Context;

import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.mvpview.ISettingView;
import wiki.scene.shop.utils.SharedPreferencesUtil;

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

    public void loginOut(Context context) {
        try {
            SharedPreferencesUtil.deleteByKey(context, ShopApplication.USER_INFO_KEY);
            ShopApplication.hasLogin = false;
            mView.loginOut();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
