package wiki.scene.shop.ui.mine.presenter;

import com.lzy.okgo.model.HttpParams;

import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.entity.WinRecordResultInfo;
import wiki.scene.shop.http.listener.HttpResultListener;
import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.model.MineWinRecordModel;
import wiki.scene.shop.ui.mine.mvpview.IWinRecordView;

/**
 * Case By:中奖记录
 * package:wiki.scene.shop.ui.mine.presenter
 * Author：scene on 2017/7/5 11:37
 */

public class WinRecordPresenter extends BasePresenter<IWinRecordView> {
    private IWinRecordView winRecordView;
    private MineWinRecordModel model;

    public WinRecordPresenter(IWinRecordView winRecordView) {
        this.mView = winRecordView;
        model = new MineWinRecordModel();
    }

    public void getMineRecordData(final boolean isLoading, final int page) {
        try {
            if (ShopApplication.hasLogin && ShopApplication.userInfo != null) {
                if (isLoading) {
                    mView.showLoadingPage();
                }
                HttpParams params = new HttpParams();
                params.put("user_id", ShopApplication.userInfo.getUser_id());
                params.put("status", 0);
                params.put("page", page);
                model.getWinRecordListData(params, new HttpResultListener<WinRecordResultInfo>() {
                    @Override
                    public void onSuccess(WinRecordResultInfo data) {
                        try {
                            mView.showContentPage();
                            mView.changePage(page);
                            mView.bindWinRecordData(data);
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
