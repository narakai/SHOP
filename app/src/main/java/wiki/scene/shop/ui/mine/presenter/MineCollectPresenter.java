package wiki.scene.shop.ui.mine.presenter;

import com.lzy.okgo.model.HttpParams;

import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.entity.MineCollectionResultInfo;
import wiki.scene.shop.http.listener.HttpResultListener;
import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.model.MineCollectModel;
import wiki.scene.shop.ui.mine.mvpview.IMineCollectView;

/**
 * 我的收藏
 * Created by scene on 17-8-14.
 */

public class MineCollectPresenter extends BasePresenter<IMineCollectView> {
    private IMineCollectView mineCollectView;
    private MineCollectModel model;

    public MineCollectPresenter(IMineCollectView mineCollectView) {
        this.mView = mineCollectView;
        model=new MineCollectModel();
    }
    public void getMineCollectionData(final boolean isLoading, final int page){
        try {
            if (ShopApplication.hasLogin && ShopApplication.userInfo != null) {
                if (isLoading) {
                    mView.showLoadingPage();
                }
                HttpParams params = new HttpParams();
                params.put("user_id", ShopApplication.userInfo.getUser_id());
                params.put("status", 0);
                params.put("page", page);
                model.getMineCollectData(params, new HttpResultListener<MineCollectionResultInfo>() {
                    @Override
                    public void onSuccess(MineCollectionResultInfo data) {
                        try {
                            mView.showContentPage();
                            mView.changePage(page);
                            mView.bindMineCollectData(data);
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
                                    mView.changePage(page - 1);
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
