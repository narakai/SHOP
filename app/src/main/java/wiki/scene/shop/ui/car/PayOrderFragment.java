package wiki.scene.shop.ui.car;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wiki.scene.shop.R;
import wiki.scene.shop.mvp.BaseBackMvpFragment;
import wiki.scene.shop.ui.car.mvpview.IPayOrderView;
import wiki.scene.shop.ui.car.presenter.PayOrderPresenter;
import wiki.scene.shop.widgets.CustomListView;

/**
 * Case By:支付订单
 * package:wiki.scene.shop.ui.car
 * Author：scene on 2017/7/4 09:17
 */

public class PayOrderFragment extends BaseBackMvpFragment<IPayOrderView, PayOrderPresenter> implements IPayOrderView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.goods_listview)
    CustomListView goodsListview;
    @BindView(R.id.radio_wechat_pay)
    RadioButton radioWechatPay;
    @BindView(R.id.radio_alipay)
    RadioButton radioAlipay;
    @BindView(R.id.comfire_pay)
    Button comfirePay;
    Unbinder unbinder;

    public static PayOrderFragment newInstance() {
        Bundle args = new Bundle();
        PayOrderFragment fragment = new PayOrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car_pay_order, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        toolbarTitle.setText(R.string.pay_order);
        initToolbarNav(toolbar);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public PayOrderPresenter initPresenter() {
        return new PayOrderPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
