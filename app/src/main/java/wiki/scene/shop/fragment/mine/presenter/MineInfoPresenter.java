package wiki.scene.shop.fragment.mine.presenter;

import wiki.scene.shop.fragment.mine.mvpview.IMineInfoView;
import wiki.scene.shop.mvp.BasePresenter;

/**
 * Case By:个人资料
 * package:wiki.scene.shop.fragment.mine.presenter
 * Author：scene on 2017/6/29 17:47
 */

public class MineInfoPresenter extends BasePresenter<IMineInfoView> {
    private IMineInfoView mineInfoView;

    public MineInfoPresenter(IMineInfoView mineInfoView) {
        this.mineInfoView = mineInfoView;
    }
}
