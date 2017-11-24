package wiki.scene.shop.activity.presenter;

import wiki.scene.shop.activity.model.LauncherModel;
import wiki.scene.shop.activity.mvpview.ILauncherView;
import wiki.scene.shop.entity.ConfigInfo;
import wiki.scene.shop.entity.UserInfo;
import wiki.scene.shop.http.listener.HttpResultListener;
import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.utils.NetTimeUtils;

/**
 * Case By:
 * package:wiki.scene.shop.activity.presenter
 * Author：scene on 2017/7/6 15:23
 */

public class LauncherPresenter extends BasePresenter<ILauncherView> {
    private LauncherModel model;

    public LauncherPresenter(ILauncherView launcherView) {
        this.mView = launcherView;
        model = new LauncherModel();
    }

    public void getAppConfig() {
        model.getUserSetting(new HttpResultListener<ConfigInfo>() {
            @Override
            public void onSuccess(ConfigInfo data) {
                if (mView != null) {
                    mView.getConfigSuccess(data);
                    if (data != null && data.getServer_time() != 0) {
                        NetTimeUtils.setErrorTime(data.getServer_time());
                    }
                }
            }

            @Override
            public void onFail(String message) {
                if (mView != null)
                    mView.getConfigFail(message);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void startApp() {
        model.startApp();
    }

    public void login(String account, String password) {
        try {
            model.login(account, password, new HttpResultListener<UserInfo>() {
                @Override
                public void onSuccess(UserInfo data) {
                    try {
                        if (data != null && data.getUser_id() != 0) {
                            mView.loginSuccess(data);
                        } else {
                            mView.loginFail();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        try {
                            mView.loginFail();
                        } catch (Exception e1) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        mView.loginFail();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFinish() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            try {
                mView.loginFail();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
