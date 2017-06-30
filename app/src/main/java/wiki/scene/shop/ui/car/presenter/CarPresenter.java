package wiki.scene.shop.ui.car.presenter;

import wiki.scene.shop.ui.car.mvpview.ICarView;
import wiki.scene.shop.mvp.BasePresenter;

/**
 * Case By:购物车
 * package:wiki.scene.shop.fragment.car.presenter
 * Author：scene on 2017/6/29 14:29
 */

public class CarPresenter extends BasePresenter<ICarView> {
    private ICarView carView;

    public CarPresenter(ICarView carView) {
        this.carView = carView;
    }
}
