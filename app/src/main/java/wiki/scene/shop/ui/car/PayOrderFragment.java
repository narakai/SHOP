package wiki.scene.shop.ui.car;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wiki.scene.shop.R;
import wiki.scene.shop.activity.LoginActivity;
import wiki.scene.shop.adapter.PayOrderGoodsAdapter;
import wiki.scene.shop.config.AppConfig;
import wiki.scene.shop.entity.CreateOrderInfo;
import wiki.scene.shop.mvp.BaseBackMvpFragment;
import wiki.scene.shop.ui.car.mvpview.IPayOrderView;
import wiki.scene.shop.ui.car.presenter.PayOrderPresenter;
import wiki.scene.shop.ui.mine.MineRedFragment;
import wiki.scene.shop.utils.PriceUtil;
import wiki.scene.shop.utils.ToastUtils;
import wiki.scene.shop.widgets.CustomListView;
import wiki.scene.shop.widgets.LoadingDialog;

/**
 * Case By:支付订单
 * package:wiki.scene.shop.ui.car
 * Author：scene on 2017/7/4 09:17
 */

public class PayOrderFragment extends BaseBackMvpFragment<IPayOrderView, PayOrderPresenter> implements IPayOrderView {
    private static final String ARG_CREATE_ORDER_INFO = "arg_cart_order_info";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.goods_listview)
    CustomListView goodsListview;
    @BindView(R.id.radio_wechat_pay)
    TextView radioWechatPay;
    @BindView(R.id.radio_alipay)
    TextView radioAlipay;
    @BindView(R.id.comfire_pay)
    Button comfirePay;
    @BindView(R.id.total_goods_count)
    TextView totalGoodsCount;
    @BindView(R.id.total_price)
    TextView totalPrice;
    @BindView(R.id.red_name)
    TextView redName;
    Unbinder unbinder;
    @BindView(R.id.user_money)
    TextView userMoney;
    @BindView(R.id.radio_balance_pay_image)
    ImageView radioBalancePayImage;
    @BindView(R.id.radio_balance_pay)
    LinearLayout radioBalancePay;
    @BindView(R.id.need_price)
    TextView needPrice;

    private CreateOrderInfo createOrderInfo;

    private LoadingDialog loadingDialog;

    //选中的红包
    private CreateOrderInfo.CouponsBean choosedRed;
    //支付方式
    private int payType = 1;

    public static PayOrderFragment newInstance(CreateOrderInfo createOrderInfo) {
        Bundle args = new Bundle();
        PayOrderFragment fragment = new PayOrderFragment();
        args.putSerializable(ARG_CREATE_ORDER_INFO, createOrderInfo);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            createOrderInfo = (CreateOrderInfo) getArguments().getSerializable(ARG_CREATE_ORDER_INFO);
        }
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
        initView();
    }

    private void initView() {
        loadingDialog = LoadingDialog.getInstance(_mActivity);
        List<CreateOrderInfo.CyclesBean> cyclesBeanList = new ArrayList<>();
        cyclesBeanList.add(createOrderInfo.getCycle());
        PayOrderGoodsAdapter adapter = new PayOrderGoodsAdapter(_mActivity, cyclesBeanList);
        goodsListview.setAdapter(adapter);
        totalGoodsCount.setText(String.format(getString(R.string.total_xx_goods), cyclesBeanList.size()));
        totalPrice.setText(PriceUtil.getPrice(createOrderInfo.getCost()));
        userMoney.setText(PriceUtil.getPrice(createOrderInfo.getUser_money()));
        if (createOrderInfo.getUser_money() > createOrderInfo.getCost()) {
            isBalancePay(AppConfig.PAY_TYPE_BALANCE);
        } else {
            isBalancePay(AppConfig.DEFAULT_PAY_WAY);
        }

        needPrice.setText(PriceUtil.getPrice(createOrderInfo.getCost()));
    }

    private void isBalancePay(int type) {
        if (type == payType) {
            return;
        }
        if (type == AppConfig.PAY_TYPE_BALANCE) {
            radioBalancePayImage.setImageResource(R.drawable.ic_address_choosed_s);
            radioWechatPay.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_wechat), null, getResources().getDrawable(R.drawable.ic_address_choosed_d), null);
            radioAlipay.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_alipay), null, getResources().getDrawable(R.drawable.ic_address_choosed_d), null);
        } else if (type == AppConfig.PAY_TYPE_WECHAT) {
            radioBalancePayImage.setImageResource(R.drawable.ic_address_choosed_d);
            radioWechatPay.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_wechat), null, getResources().getDrawable(R.drawable.ic_address_choosed_s), null);
            radioAlipay.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_alipay), null, getResources().getDrawable(R.drawable.ic_address_choosed_d), null);
        } else if (type == AppConfig.PAY_TYPE_ALPAY) {
            radioBalancePayImage.setImageResource(R.drawable.ic_address_choosed_d);
            radioWechatPay.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_wechat), null, getResources().getDrawable(R.drawable.ic_address_choosed_d), null);
            radioAlipay.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_alipay), null, getResources().getDrawable(R.drawable.ic_address_choosed_s), null);
        }
        payType = type;
    }

    /**
     * 选择余额支付
     */
    @OnClick(R.id.radio_balance_pay)
    public void onClickRadioBalancePay() {
        if (createOrderInfo.getUser_money() > createOrderInfo.getCost()) {
            isBalancePay(AppConfig.PAY_TYPE_BALANCE);
        }
    }

    @OnClick(R.id.radio_wechat_pay)
    public void onClickRadioWeChatPay() {
        isBalancePay(AppConfig.PAY_TYPE_WECHAT);
    }

    @OnClick(R.id.radio_alipay)
    public void onClickRadioAliPay() {
        isBalancePay(AppConfig.PAY_TYPE_ALPAY);
    }

    @OnClick(R.id.choose_red)
    public void onClickChoosedRed() {
        startForResult(MineRedFragment.newInstance(false, (ArrayList<CreateOrderInfo.CouponsBean>) createOrderInfo.getCoupons()), 200);
    }

    @OnClick(R.id.comfire_pay)
    public void onClickComfirePay() {
        presenter.getPayOrderInfo(createOrderInfo.getOrder_id(), choosedRed == null ? "" : choosedRed.getId(), payType);
    }

    @Override
    public void showLoading(@StringRes int resId) {
        loadingDialog.showLoadingDialog(getString(resId));
    }

    @Override
    public void hideLoading() {
        loadingDialog.cancelLoadingDialog();
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

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (data != null && resultCode == RESULT_OK) {
            if (requestCode == 200) {
                choosedRed = (CreateOrderInfo.CouponsBean) data.getSerializable("red");
                if (choosedRed != null) {
                    redName.setText(choosedRed.getTitle());
                    needPrice.setText(PriceUtil.getPrice(createOrderInfo.getCost() - choosedRed.getCost() < 0 ? 0 : createOrderInfo.getCost() - choosedRed.getCost()));
                }
            }
        }
    }

    @Override
    public void showMessage(@StringRes int resId) {
        ToastUtils.getInstance(_mActivity).showToast(resId);
    }

    @Override
    public void showMessage(String msg) {
        ToastUtils.getInstance(_mActivity).showToast(msg);
    }

    @Override
    public void hasNoLogin() {
        showMessage(R.string.you_has_no_login_please_login);
        startActivity(new Intent(_mActivity, LoginActivity.class));
    }

    @Override
    public void getPayOrderInfoSuccess() {

    }
}
