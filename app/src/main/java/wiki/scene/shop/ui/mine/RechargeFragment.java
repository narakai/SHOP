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
    Unbinder unbinder;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.money_1)
    RadioButton money1;
    @BindView(R.id.money_2)
    RadioButton money2;
    @BindView(R.id.money_3)
    RadioButton money3;
    @BindView(R.id.money_4)
    RadioButton money4;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.radio_wechat_pay)
    RadioButton radioWechatPay;
    @BindView(R.id.radio_alipay)
    RadioButton radioAlipay;
    @BindView(R.id.rg_pay_type)
    RadioGroup rgPayType;
    @BindView(R.id.recharge)
    TextView recharge;
    @BindView(R.id.priceCustom)
    EditText priceCustom;

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
                try {
                    if (v == priceCustom && hasFocus) {
                        radioGroup.clearCheck();
                    }
                } catch (Exception e) {
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
