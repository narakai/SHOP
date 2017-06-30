package wiki.scene.shop.ui.indiana.presenter;

import wiki.scene.shop.ui.indiana.mvpview.IIndianaView;
import wiki.scene.shop.mvp.BasePresenter;

/**
 * Case By:夺宝
 * package:wiki.scene.shop.fragment.indiana.presenter
 * Author：scene on 2017/6/28 10:20
 */

public class IndianaPresenter extends BasePresenter<IIndianaView> {
    private IIndianaView indianaView;

    public IndianaPresenter(IIndianaView indianaView) {
        this.indianaView = indianaView;
    }

    public void setChoosedTitleBar(int choosedPosition,int oldChoosedPosition) {
        if (indianaView != null) {
            indianaView.setTitlebarChoosed(choosedPosition,oldChoosedPosition);
        }
    }

}
