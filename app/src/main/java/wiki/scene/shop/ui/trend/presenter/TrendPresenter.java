package wiki.scene.shop.ui.trend.presenter;

import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.trend.model.TrendModel;
import wiki.scene.shop.ui.trend.view.ITrendView;

/**
 * 开奖走势
 * Created by scene on 2017/11/8.
 */

public class TrendPresenter extends BasePresenter<ITrendView> {
    private ITrendView trendView;
    private TrendModel model;

    public TrendPresenter(ITrendView trendView) {
        this.trendView = trendView;
        model = new TrendModel();
    }
}
