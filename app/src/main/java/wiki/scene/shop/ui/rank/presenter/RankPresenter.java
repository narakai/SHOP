package wiki.scene.shop.ui.rank.presenter;

import java.util.List;

import wiki.scene.shop.entity.RankInfo;
import wiki.scene.shop.http.listener.HttpResultListener;
import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.rank.model.RankModel;
import wiki.scene.shop.ui.rank.view.IRankView;

/**
 * 排行榜
 * Created by scene on 17-8-16.
 */

public class RankPresenter extends BasePresenter<IRankView> {
    private IRankView rankView;
    private RankModel model;

    public RankPresenter(IRankView rankView) {
        this.mView = rankView;
        model = new RankModel();
    }

    public void getRankData(final boolean isLoading) {
        try {
            if (isLoading) {
                mView.showLoadingPage();
            }
            model.getRankData(new HttpResultListener<List<RankInfo>>() {
                @Override
                public void onSuccess(List<RankInfo> data) {
                    try {
                        if (isLoading) {
                            mView.showContentPage();
                        } else {
                            mView.refreshCompilt();
                        }
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
                            mView.refreshCompilt();
                            mView.showMessage(message);
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
