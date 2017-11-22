package wiki.scene.shop.ui.mine.presenter;

import com.lzy.okgo.model.HttpParams;

import wiki.scene.shop.http.listener.HttpResultListener;
import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.model.OrderPkDetailModel;
import wiki.scene.shop.ui.mine.mvpview.IOrderPkDetailView;

/**
 * 订单pk
 * Created by scene on 2017/11/22.
 */

public class OrderPkDetailPresenter extends BasePresenter<IOrderPkDetailView> {
    private OrderPkDetailModel model;

    public OrderPkDetailPresenter(IOrderPkDetailView detailView) {
        this.mView = detailView;
        model = new OrderPkDetailModel();
    }

    public void getPkDetailInfo(int id){
        try{
            if(id==0){
                mView.showFailPage();
                return;
            }
            HttpParams params=new HttpParams();
            params.put("order_id",id);
            model.getPKDetail(params, new HttpResultListener<String>() {
                @Override
                public void onSuccess(String data) {

                }

                @Override
                public void onFail(String message) {

                }

                @Override
                public void onFinish() {

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
