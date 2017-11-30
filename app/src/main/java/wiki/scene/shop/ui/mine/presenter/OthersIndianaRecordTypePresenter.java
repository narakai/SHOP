package wiki.scene.shop.ui.mine.presenter;

import com.lzy.okgo.model.HttpParams;

import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.entity.MineOrderResultInfo;
import wiki.scene.shop.http.listener.HttpResultListener;
import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.model.OthersIndianaRecordModel;
import wiki.scene.shop.ui.mine.mvpview.IOthersIndianaRecordTypeView;

/**
 * Case By:别人的夺宝记录
 * package:wiki.scene.shop.ui.mine.presenter
 * Author：scene on 2017/7/5 13:50
 */

public class OthersIndianaRecordTypePresenter extends BasePresenter<IOthersIndianaRecordTypeView> {
    private OthersIndianaRecordModel model;

    public OthersIndianaRecordTypePresenter(IOthersIndianaRecordTypeView indianaRecordView) {
        this.mView = indianaRecordView;
        model = new OthersIndianaRecordModel();
    }

    public void getIndianaRecordData(final int targetUserId, final int page, final boolean isLoading) {
        try {
            if (isLoading) {
                mView.showLoading();
            }
            HttpParams params = new HttpParams();
            params.put("page", page);
            params.put("target_user_id", targetUserId);
            model.getIndianaRecordData(params, new HttpResultListener<MineOrderResultInfo>() {
                @Override
                public void onSuccess(MineOrderResultInfo data) {
                    try {
                        mView.changePage(page);
                        if (isLoading) {
                            mView.showContent();
                        } else {
                            mView.refreshComplete();
                        }
                        mView.getDataSuccess(data);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        if (isLoading) {
                            mView.showFail();
                        } else {
                            mView.showMessage(message);

                        }
                        if (page == 1) {
                            mView.refreshComplete();
                        } else {
                            mView.changePage(page - 1);
                            mView.loadmoreFail();
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

    public void getWinIndianaRecordData(final int targetUserId, final int page, final boolean isLoading) {
        try {
            if (isLoading) {
                mView.showLoading();
            }
            HttpParams params = new HttpParams();
            params.put("page", page);
            params.put("target_user_id", targetUserId);
            model.getWinIndianaRecordData(params, new HttpResultListener<MineOrderResultInfo>() {
                @Override
                public void onSuccess(MineOrderResultInfo data) {
                    try {
                        mView.changePage(page);
                        if (isLoading) {
                            mView.showContent();
                        } else {
                            mView.refreshComplete();
                        }
                        mView.getDataSuccess(data);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        if (isLoading) {
                            mView.showFail();
                        } else {
                            mView.showMessage(message);

                        }
                        if (page == 1) {
                            mView.refreshComplete();
                        } else {
                            mView.changePage(page - 1);
                            mView.loadmoreFail();
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

}
