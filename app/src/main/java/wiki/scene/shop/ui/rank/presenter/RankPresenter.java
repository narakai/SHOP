package wiki.scene.shop.ui.rank.presenter;

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
}
