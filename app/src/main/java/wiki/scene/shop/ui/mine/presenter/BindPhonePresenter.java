package wiki.scene.shop.ui.mine.presenter;

import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.model.BindPhoneModel;
import wiki.scene.shop.ui.mine.mvpview.IBindPhoneView;

/**
 * 绑定手机号
 * Created by scene on 2017/11/15.
 */

public class BindPhonePresenter extends BasePresenter<IBindPhoneView>{
    private IBindPhoneView bindPhoneView;
    private BindPhoneModel bindPhoneModel;

    public BindPhonePresenter(IBindPhoneView bindPhoneView) {
        this.bindPhoneView = bindPhoneView;
        bindPhoneModel=new BindPhoneModel();
    }
}
