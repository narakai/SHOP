package wiki.scene.shop.ui.indiana.presenter;

import com.lzy.okgo.model.HttpParams;

import wiki.scene.shop.entity.Price10IndianaResultInfo;
import wiki.scene.shop.http.listener.HttpResultListener;
import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.indiana.model.Price10IndianaModel;
import wiki.scene.shop.ui.indiana.mvpview.IPrice10IndianaView;

/**
 * 10元和秒开
 * Created by scene on 17-8-15.
 */

public class Price10IndianaPresenter extends BasePresenter<IPrice10IndianaView> {
    private IPrice10IndianaView price10IndianaView;
    private Price10IndianaModel model;

    public Price10IndianaPresenter(IPrice10IndianaView price10IndianaView) {
        this.mView = price10IndianaView;
        model = new Price10IndianaModel();
    }

    public void getPrice10IndianaData(final boolean isLoading, final int page) {
        try {
            if (isLoading) {
                mView.showLoadingPage();
            }
            HttpParams params = new HttpParams();
            params.put("status", 0);
            params.put("page", page);
            model.getPrice10IndianaData(params, new HttpResultListener<Price10IndianaResultInfo>() {
                @Override
                public void onSuccess(Price10IndianaResultInfo data) {
                    try {
                        mView.showContentPage();
                        mView.changePage(page);
                        mView.bindData(data);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getSecondIndianaData(final boolean isLoading, final int page) {
        try {
            if (isLoading) {
                mView.showLoadingPage();
            }
            HttpParams params = new HttpParams();
            params.put("status", 0);
            params.put("page", page);
            model.getSceondOpenData(params, new HttpResultListener<Price10IndianaResultInfo>() {
                @Override
                public void onSuccess(Price10IndianaResultInfo data) {
                    try {
                        mView.showContentPage();
                        mView.changePage(page);
                        mView.bindData(data);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
