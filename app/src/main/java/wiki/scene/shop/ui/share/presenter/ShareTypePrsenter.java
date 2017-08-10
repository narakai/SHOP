package wiki.scene.shop.ui.share.presenter;

import com.lzy.okgo.model.HttpParams;

import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.entity.ShareListResultInfo;
import wiki.scene.shop.http.listener.HttpResultListener;
import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.share.model.ShareModel;
import wiki.scene.shop.ui.share.mvpview.IShareTypeView;

/**
 * Case By:最热晒单
 * package:wiki.scene.shop.fragment.share.presenter
 * Author：scene on 2017/6/29 11:48
 */

public class ShareTypePrsenter extends BasePresenter<IShareTypeView> {
    private IShareTypeView shareTypeView;
    private ShareModel model;

    public ShareTypePrsenter(IShareTypeView shareTypeView) {
        this.mView = shareTypeView;
        model = new ShareModel();
    }

    public void getShareListData(final boolean isLoading, int type, final int page) {
        try {
            if (isLoading) {
                mView.showLoadingPage();
            }
            HttpParams params = new HttpParams();
            if (ShopApplication.hasLogin && ShopApplication.userInfo != null) {
                params.put("user_id", ShopApplication.userInfo.getUser_id());
            }
            params.put("type", type);
            params.put("page", page);
            model.getShareListData(params, new HttpResultListener<ShareListResultInfo>() {
                @Override
                public void onSuccess(ShareListResultInfo data) {
                    try {

                        if (isLoading) {
                            mView.showContentPage();
                        } else {
                            mView.refreshCompile();
                        }
                        mView.getShareListDataSuccess(data.getData());
                        mView.changePageInfo(data.getInfo(), page);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        if (isLoading) {
                            mView.showErrorPage();
                        } else {
                            if (page == 1) {
                                mView.refreshCompile();
                            } else {
                                mView.loadmoreFail();
                            }
                        }

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
        }
    }

    public void getMyShareListData(final boolean isLoading, int type, final int page) {
        try {
            if (isLoading) {
                mView.showLoadingPage();
            }
            if (ShopApplication.hasLogin && ShopApplication.userInfo != null) {
                HttpParams params = new HttpParams();
                params.put("page", page);
                model.getMyShareListData(params, new HttpResultListener<ShareListResultInfo>() {
                    @Override
                    public void onSuccess(ShareListResultInfo data) {
                        try {

                            if (isLoading) {
                                mView.showContentPage();
                            } else {
                                mView.refreshCompile();
                            }
                            mView.getShareListDataSuccess(data.getData());
                            mView.changePageInfo(data.getInfo(), page);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(String message) {
                        try {
                            if (isLoading) {
                                mView.showErrorPage();
                            } else {
                                if (page == 1) {
                                    mView.refreshCompile();
                                } else {
                                    mView.loadmoreFail();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFinish() {

                    }
                });
            } else {
                mView.showMessage(R.string.you_has_no_login_please_login);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
