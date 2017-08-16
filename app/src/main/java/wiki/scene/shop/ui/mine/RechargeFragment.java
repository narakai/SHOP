package wiki.scene.shop.ui.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wiki.scene.shop.R;
import wiki.scene.shop.mvp.BaseBackMvpFragment;
import wiki.scene.shop.ui.mine.mvpview.IRechargeView;
import wiki.scene.shop.ui.mine.presenter.RechargePresenter;

/**
 * 充值
 * Created by scene on 17-8-16.
 */

public class RechargeFragment extends BaseBackMvpFragment<IRechargeView, RechargePresenter> implements IRechargeView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.price_20)
    TextView price20;
    @BindView(R.id.price_50)
    TextView price50;
    @BindView(R.id.price_100)
    TextView price100;
    @BindView(R.id.price_300)
    TextView price300;
    @BindView(R.id.price_500)
    TextView price500;
    @BindView(R.id.price_custom)
    EditText priceCustom;
    @BindView(R.id.radio_wechat_pay)
    RadioButton radioWechatPay;
    @BindView(R.id.radio_alipay)
    RadioButton radioAlipay;
    @BindView(R.id.rg_pay_type)
    RadioGroup rgPayType;
    @BindView(R.id.recharge)
    TextView recharge;
    Unbinder unbinder;

    public static RechargeFragment newInstance() {
        Bundle args = new Bundle();
        RechargeFragment fragment = new RechargeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recharge, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        toolbarTitle.setText(R.string.recharge);
        initToolbarNav(toolbar);
    }

    @Override
    public void showLoading(@StringRes int resId) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public RechargePresenter initPresenter() {
        return new RechargePresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
