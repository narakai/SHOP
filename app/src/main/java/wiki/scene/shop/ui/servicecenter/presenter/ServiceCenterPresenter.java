package wiki.scene.shop.ui.servicecenter.presenter;

import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.servicecenter.model.ServiceCenterModel;
import wiki.scene.shop.ui.servicecenter.view.IServiceCenterView;

/**
 * 客服中心
 * Created by scene on 2017/11/6.
 */

public class ServiceCenterPresenter extends BasePresenter<IServiceCenterView> {
    private IServiceCenterView serviceCenterView;
    private ServiceCenterModel model;

    public ServiceCenterPresenter(IServiceCenterView serviceCenterView) {
        this.serviceCenterView = serviceCenterView;
        model = new ServiceCenterModel();
    }
}
