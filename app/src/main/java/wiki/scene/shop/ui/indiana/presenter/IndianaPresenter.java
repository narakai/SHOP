package wiki.scene.shop.ui.indiana.presenter;

import java.util.ArrayList;
import java.util.List;

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
                indianaView.showLoading();
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
                        //测试的
                        List<WinningNoticeInfo> list = new ArrayList<>();
                        for (int i = 0; i < 3; i++) {
                            WinningNoticeInfo winningNoticeInfo = new WinningNoticeInfo();
                            winningNoticeInfo.setCost(10 + i);
                            winningNoticeInfo.setCycle_code(12345612);
                            winningNoticeInfo.setNickname("南极仙人");
                            winningNoticeInfo.setProduct_name("秒开话费50元");
                            list.add(winningNoticeInfo);
                        }
                        data.setWinning_notice(list);
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

}
