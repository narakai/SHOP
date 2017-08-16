package wiki.scene.shop.ui.mine.presenter;

import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.model.RechargeModel;
import wiki.scene.shop.ui.mine.mvpview.IRechargeView;

/**
 * 充值
 * Created by scene on 17-8-16.
 */

public class RechargePresenter extends BasePresenter<IRechargeView> {
    private IRechargeView rechargeView;
    private RechargeModel model;

    public RechargePresenter(IRechargeView rechargeView) {
        this.mView = rechargeView;
        this.model=new RechargeModel();
    }


}
