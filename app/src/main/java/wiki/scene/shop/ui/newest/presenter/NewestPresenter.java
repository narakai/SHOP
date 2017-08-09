package wiki.scene.shop.ui.newest.presenter;

import com.lzy.okgo.model.HttpParams;

import wiki.scene.shop.entity.NewestResultInfo;
import wiki.scene.shop.http.listener.HttpResultListener;
import wiki.scene.shop.ui.newest.model.NewestModel;
import wiki.scene.shop.ui.newest.mvpview.INewestView;
import wiki.scene.shop.mvp.BasePresenter;

/**
 * Case By:
 * package:wiki.scene.shop.fragment.newest.presenter
 * Author：scene on 2017/6/29 15:07
 */

public class NewestPresenter extends BasePresenter<INewestView> {
    private INewestView newestView;
    private NewestModel model;

    public NewestPresenter(INewestView newestView) {
        this.mView = newestView;
        model = new NewestModel();
    }

    public void getNewestOpenData(final boolean isLoading, final int page) {
        try {
            if (isLoading) {
                mView.showLoadingPage();
            }
            HttpParams params = new HttpParams();
            params.put("page", page);
            model.getNewestOpenData(params, new HttpResultListener<NewestResultInfo>() {
                @Override
                public void onSuccess(NewestResultInfo data) {
                    try {
                        mView.bindData(data.getData());
                        mView.changePage(page);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        if (isLoading) {
                            mView.showErrorPage();
                        }
                        mView.refreshComplete();
                        mView.changePage(page > 1 ? page - 1 : page);
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
