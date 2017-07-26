package wiki.scene.shop.ui.indiana.presenter;

import android.content.Context;

import java.util.List;

import wiki.scene.shop.R;
import wiki.scene.shop.entity.IndianaIndexInfo;
import wiki.scene.shop.entity.WinningNoticeInfo;
import wiki.scene.shop.http.listener.HttpResultListener;
import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.indiana.model.IndianaModel;
import wiki.scene.shop.ui.indiana.mvpview.IIndianaView;

/**
 * Case By:夺宝
 * package:wiki.scene.shop.fragment.indiana.presenter
 * Author：scene on 2017/6/28 10:20
 */

public class IndianaPresenter extends BasePresenter<IIndianaView> {
    private IIndianaView indianaView;
    private IndianaModel model;

    public IndianaPresenter(IIndianaView indianaView) {
        this.indianaView = indianaView;
        model = new IndianaModel();
    }

    public void setChoosedTitleBar(int choosedPosition, int oldChoosedPosition) {
        if (indianaView != null) {
            indianaView.setTitlebarChoosed(choosedPosition, oldChoosedPosition);
        }
    }

    public void getIndianaData(final boolean isRefresh) {
        if (indianaView != null) {
            if (!isRefresh) {
                indianaView.showLoading(R.string.loading);
            }
            model.getIndianaIndexData(new HttpResultListener<IndianaIndexInfo>() {
                @Override
                public void onSuccess(IndianaIndexInfo data) {
                    if (indianaView != null) {
                        if (!isRefresh) {
                            indianaView.hideLoading();
                        }
                        indianaView.getDataSuccess(data, isRefresh);
                        indianaView.bindBannerData(data.getSlider());
                        indianaView.bindWinnerNotice(data.getWinning_notice());
                    }
                }

                @Override
                public void onFail(String message) {
                    if (indianaView != null) {
                        if (isRefresh) {
                            indianaView.showMessage(message);
                        } else {
                            indianaView.showFailPage();
                        }
                    }
                }

                @Override
                public void onFinish() {
                }
            });
        }
    }

    /**
     * 获取中奖通知
     */
    public void getWinnerNotice() {
        try {
            model.getWinNotice(new HttpResultListener<List<WinningNoticeInfo>>() {
                @Override
                public void onSuccess(List<WinningNoticeInfo> data) {
                    indianaView.bindWinnerNotice(data);
                }

                @Override
                public void onFail(String message) {
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
