package wiki.scene.shop.ui.mine.presenter;

import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.mvpview.IMineRedView;

/**
 * Case By:我的红包
 * package:wiki.scene.shop.ui.mine.presenter
 * Author：scene on 2017/6/30 13:24
 */

public class MineRedPresenter extends BasePresenter<IMineRedView> {
    private IMineRedView redView;

    public MineRedPresenter(IMineRedView redView) {
        this.redView = redView;
    }
}
