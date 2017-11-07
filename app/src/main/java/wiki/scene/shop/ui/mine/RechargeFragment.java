package wiki.scene.shop.ui.mine;

import android.graphics.Color;
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

import com.lzy.okgo.OkGo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wiki.scene.shop.R;
import wiki.scene.shop.config.AppConfig;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.mvp.BaseBackMvpFragment;
import wiki.scene.shop.ui.mine.mvpview.IRechargeView;
import wiki.scene.shop.ui.mine.presenter.RechargePresenter;
import wiki.scene.shop.utils.ToastUtils;
import wiki.scene.shop.widgets.LoadingDialog;

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

    private int currentChoosedPosition = 0;

    private LoadingDialog loadingDialog;

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
        loadingDialog = LoadingDialog.getInstance(getContext());
        initView();
    }

    private void initView() {
        priceCustom.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try{
                    if (v == priceCustom && hasFocus) {
                        priceCustom.setBackgroundResource(R.drawable.item_recharge_choosed_s);
                        priceCustom.setTextColor(Color.parseColor("#FF183F"));
                        choosedNumber(5);
                    } else {
                        priceCustom.setBackgroundResource(R.drawable.item_recharge_choosed_d);
                        priceCustom.setTextColor(getResources().getColor(R.color.text_color_title));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        if (AppConfig.DEFAULT_PAY_WAY == AppConfig.PAY_TYPE_WECHAT) {
            radioWechatPay.setChecked(true);
        } else {
            radioAlipay.setChecked(true);
        }
    }

    private void choosedNumber(int position) {
        if (position != 5) {
            toolbarTitle.setFocusable(true);
            toolbarTitle.setFocusableInTouchMode(true);
            toolbarTitle.requestFocus();
            hideSoftInput();
        }
        switch (currentChoosedPosition) {
            case 0:
                price20.setTextColor(getResources().getColor(R.color.text_color_title));
                price20.setBackgroundResource(R.drawable.item_recharge_choosed_d);
                break;
            case 1:
                price50.setTextColor(getResources().getColor(R.color.text_color_title));
                price50.setBackgroundResource(R.drawable.item_recharge_choosed_d);
                break;
            case 2:
                price100.setTextColor(getResources().getColor(R.color.text_color_title));
                price100.setBackgroundResource(R.drawable.item_recharge_choosed_d);
                break;
            case 3:
                price300.setTextColor(getResources().getColor(R.color.text_color_title));
                price300.setBackgroundResource(R.drawable.item_recharge_choosed_d);
                break;
            case 4:
                price500.setTextColor(getResources().getColor(R.color.text_color_title));
                price500.setBackgroundResource(R.drawable.item_recharge_choosed_d);
                break;
        }
        switch (position) {
            case 0:
                price20.setTextColor(Color.parseColor("#FF183F"));
                price20.setBackgroundResource(R.drawable.item_recharge_choosed_s);
                break;
            case 1:
                price50.setTextColor(Color.parseColor("#FF183F"));
                price50.setBackgroundResource(R.drawable.item_recharge_choosed_s);
                break;
            case 2:
                price100.setTextColor(Color.parseColor("#FF183F"));
                price100.setBackgroundResource(R.drawable.item_recharge_choosed_s);
                break;
            case 3:
                price300.setTextColor(Color.parseColor("#FF183F"));
                price300.setBackgroundResource(R.drawable.item_recharge_choosed_s);
                break;
            case 4:
                price500.setTextColor(Color.parseColor("#FF183F"));
                price500.setBackgroundResource(R.drawable.item_recharge_choosed_s);
                break;
        }
        currentChoosedPosition = position;
    }

    @Override
    public void showLoading(@StringRes int resId) {
        if (loadingDialog != null) {
            loadingDialog.showLoadingDialog(getString(resId));
        }
    }

    @Override
    public void hideLoading() {
        if (loadingDialog != null) {
            loadingDialog.cancelLoadingDialog();
        }
    }

    @Override
    public RechargePresenter initPresenter() {
        return new RechargePresenter(this);
    }

    @Override
    public void onDestroyView() {
        hideLoading();
        OkGo.getInstance().cancelTag(ApiUtil.RECHARGE_TAG);
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.price_20)
    public void onClickPrice20() {
        choosedNumber(0);
    }

    @OnClick(R.id.price_50)
    public void onClickPrice50() {
        choosedNumber(1);
    }

    @OnClick(R.id.price_100)
    public void onClickPrice100() {
        choosedNumber(2);
    }

    @OnClick(R.id.price_300)
    public void onClickPrice300() {
        choosedNumber(3);
    }

    @OnClick(R.id.price_500)
    public void onClickPrice500() {
        choosedNumber(4);
    }

    @OnClick(R.id.recharge)
    public void onClickRecharge() {
        int cost = 0;
        int pay_type;
        switch (currentChoosedPosition) {
            case 0:
                cost = 20 * 100;
                break;
            case 1:
                cost = 50 * 100;
                break;
            case 2:
                cost = 100 * 100;
                break;
            case 3:
                cost = 300 * 100;
                break;
            case 4:
                cost = 500 * 100;
                break;
            case 5:
                try {
                    cost = Integer.valueOf(priceCustom.getText().toString().trim());
                } catch (Exception e) {
                    ToastUtils.getInstance(getContext()).showToast(R.string.input_right_number);
                    return;
                }
                break;
        }
        if (cost == 0) {
            ToastUtils.getInstance(getContext()).showToast(R.string.input_right_number);
            return;
        }
        if (rgPayType.getCheckedRadioButtonId() == R.id.radio_wechat_pay) {
            pay_type = 2;
        } else {
            pay_type = 3;
        }

        presenter.recharge(cost, pay_type);
    }

    @Override
    public void showMessage(String message) {
        ToastUtils.getInstance(_mActivity).showToast(message);
    }

    @Override
    public void showMessage(@StringRes int resId) {
        ToastUtils.getInstance(_mActivity).showToast(resId);
    }

    @Override
    public void getRechargeOrderSuccess() {

    }
}
