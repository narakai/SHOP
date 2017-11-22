package wiki.scene.shop.ui.mine;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wiki.scene.shop.R;
import wiki.scene.shop.config.AppConfig;
import wiki.scene.shop.entity.RechargeInfo;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.mvp.BaseBackMvpFragment;
import wiki.scene.shop.ui.mine.mvpview.IRechargeView;
import wiki.scene.shop.ui.mine.presenter.RechargePresenter;
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

    private int cost = 100;

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
                        priceCustom.setTextColor(ContextCompat.getColor(getContext(), R.color.color_theme));
                        cost = 0;
                    } else {
                        hideSoftInput();
                        priceCustom.setTextColor(Color.parseColor("#999999"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        priceCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioGroup.clearCheck();
                priceCustom.setTextColor(ContextCompat.getColor(getContext(), R.color.color_theme));
                cost = 0;
            }
        });

        if (AppConfig.DEFAULT_PAY_WAY == AppConfig.PAY_TYPE_WECHAT) {
            radioWechatPay.setChecked(true);
        } else {
            radioAlipay.setChecked(true);
        }
        money1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideSoftInput();
                priceCustom.setTextColor(Color.parseColor("#999999"));
                cost = 100;
            }
        });
        money2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideSoftInput();
                priceCustom.setTextColor(Color.parseColor("#999999"));
                cost = 200;
            }
        });
        money3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideSoftInput();
                priceCustom.setTextColor(Color.parseColor("#999999"));
                cost = 500;
            }
        });
        money4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideSoftInput();
                priceCustom.setTextColor(Color.parseColor("#999999"));
                cost = 1000;
            }
        });
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


    @OnClick(R.id.recharge)
    public void onClickRecharge() {
        try {
            int payType = 0;
            if (rgPayType.getCheckedRadioButtonId() == R.id.radio_wechat_pay) {
                payType = AppConfig.PAY_TYPE_WECHAT;
            } else if (rgPayType.getCheckedRadioButtonId() == R.id.radio_alipay) {
                payType = AppConfig.PAY_TYPE_ALPAY;
            }
            if (payType == 0) {
                showMessage("请选择支付方式");
                return;
            }
            int realCost;
            if (cost == 0) {
                String realCostStr = priceCustom.getText().toString().trim();
                realCost = Integer.parseInt(realCostStr);
            } else {
                realCost = cost;
            }
            if (realCost == 0) {
                showMessage("请输入你要充值的金额");
                return;
            }
            presenter.recharge(realCost * 100, payType);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void showMessage(String message) {
        ToastUtils.showShort(message);
    }

    @Override
    public void showMessage(@StringRes int resId) {
        ToastUtils.showShort(resId);
    }

    @Override
    public void getRechargeOrderSuccess(RechargeInfo data) {
        LogUtils.e(data.toString());
    }

    @Override
    public void onDestroyView() {
        hideLoading();
        OkGo.getInstance().cancelTag(ApiUtil.RECHARGE_TAG);
        super.onDestroyView();
        unbinder.unbind();
    }
}
