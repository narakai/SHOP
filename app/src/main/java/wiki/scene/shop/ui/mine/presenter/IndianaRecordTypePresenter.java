package wiki.scene.shop.ui.mine.presenter;

import com.lzy.okgo.model.HttpParams;

import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.entity.MineOrderResultInfo;
import wiki.scene.shop.http.listener.HttpResultListener;
import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.model.IndianaRecordModel;
import wiki.scene.shop.ui.mine.mvpview.IIndianaRecordTypeView;

/**
 * Case By:夺宝记录
 * package:wiki.scene.shop.ui.mine.presenter
 * Author：scene on 2017/7/5 13:50
 */

public class IndianaRecordTypePresenter extends BasePresenter<IIndianaRecordTypeView> {
    private IndianaRecordModel model;

    public IndianaRecordTypePresenter(IIndianaRecordTypeView indianaRecordView) {
        this.mView = indianaRecordView;
        model = new IndianaRecordModel();
    }

    public void getIndianaRecordData(final int page, final boolean isLoading) {
        try {
            if (isLoading) {
                mView.showLoading();
            }
            HttpParams params = new HttpParams();
            if (ShopApplication.hasLogin) {
                params.put("page", page);
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
            } else {
                mView.showMessage(R.string.you_has_no_login_please_login);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getWinIndianaRecordData(final int page, final boolean isLoading) {
        try {
            if (isLoading) {
                mView.showLoading();
            }
            HttpParams params = new HttpParams();
            if (ShopApplication.hasLogin) {
                params.put("page", page);
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
            } else {
                mView.showMessage(R.string.you_has_no_login_please_login);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
