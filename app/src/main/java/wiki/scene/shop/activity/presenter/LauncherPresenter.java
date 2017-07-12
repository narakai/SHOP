package wiki.scene.shop.activity.presenter;

import wiki.scene.shop.activity.model.LauncherModel;
import wiki.scene.shop.activity.mvpview.ILauncherView;
import wiki.scene.shop.entity.ConfigInfo;
import wiki.scene.shop.http.listener.HttpResultListener;
import wiki.scene.shop.mvp.BasePresenter;

/**
 * Case By:
 * package:wiki.scene.shop.activity.presenter
 * Author：scene on 2017/7/6 15:23
 */

public class LauncherPresenter extends BasePresenter<ILauncherView> {
    private ILauncherView launcherView;
    private LauncherModel model;

    public LauncherPresenter(ILauncherView launcherView) {
        this.launcherView = launcherView;
        model=new LauncherModel();
    }
    public void getAppConfig(){
        model.getUserSetting(new HttpResultListener<ConfigInfo>() {
            @Override
            public void onSuccess(ConfigInfo data) {
                if(mView!=null){
                    mView.getConfigSuccess(data);
                }
            }

            @Override
            public void onFail(String message) {
                mView.getConfigFail(message);
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
