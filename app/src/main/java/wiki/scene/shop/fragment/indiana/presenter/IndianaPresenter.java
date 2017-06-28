package wiki.scene.shop.fragment.indiana.presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;

import wiki.scene.loadmore.loadmore.GridViewWithHeaderAndFooter;
import wiki.scene.loadmore.utils.PtrLocalDisplay;
import wiki.scene.loadmore.utils.SceneLogUtil;
import wiki.scene.shop.fragment.indiana.mvpview.IIndianaView;
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

    public void setChoosedTitleBar(int choosedPosition) {
        if (indianaView != null) {
            indianaView.setTitlebarChoosed(choosedPosition);
        }
    }

}
