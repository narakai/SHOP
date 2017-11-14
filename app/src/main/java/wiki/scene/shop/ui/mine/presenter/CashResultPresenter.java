package wiki.scene.shop.ui.mine.presenter;

import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.model.CashResultModel;
import wiki.scene.shop.ui.mine.mvpview.ICashResultView;

/**
 * 申请提现结果
 * Created by scene on 2017/11/14.
 */

public class CashResultPresenter extends BasePresenter<ICashResultView> {
    private ICashResultView resultView;
    private CashResultModel model;

    public CashResultPresenter(ICashResultView resultView) {
        this.resultView = resultView;
        model=new CashResultModel();
    }
}
