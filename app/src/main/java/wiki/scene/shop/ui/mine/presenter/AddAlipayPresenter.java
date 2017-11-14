package wiki.scene.shop.ui.mine.presenter;

import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.model.AddAlipayModel;
import wiki.scene.shop.ui.mine.mvpview.IAddAlipayView;
import wiki.scene.shop.ui.mine.mvpview.IAddBankView;

/**
 * 添加支付宝
 * Created by scene on 2017/11/14.
 */

public class AddAlipayPresenter extends BasePresenter<IAddAlipayView> {
    private IAddAlipayView addAlipayView;
    private AddAlipayModel model;

    public AddAlipayPresenter(IAddAlipayView addAlipayView) {
        this.addAlipayView = addAlipayView;
        model=new AddAlipayModel();
    }
}
