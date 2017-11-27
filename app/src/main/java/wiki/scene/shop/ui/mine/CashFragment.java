package wiki.scene.shop.ui.mine;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.zhl.cbdialog.CBDialogBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wiki.scene.loadmore.StatusViewLayout;
import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.adapter.CashBankAdapter;
import wiki.scene.shop.config.AppConfig;
import wiki.scene.shop.entity.BankInfo;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.mvp.BaseBackMvpFragment;
import wiki.scene.shop.ui.mine.mvpview.ICashView;
import wiki.scene.shop.ui.mine.presenter.CashPresenter;
import wiki.scene.shop.utils.PriceUtil;
import wiki.scene.shop.utils.SharedPreferencesUtil;
import wiki.scene.shop.utils.UpdatePageUtils;
import wiki.scene.shop.view.CustomRadioButton;
import wiki.scene.shop.widgets.LoadingDialog;

/**
 * 提现
 * Created by scene on 2017/11/14.
 */

public class CashFragment extends BaseBackMvpFragment<ICashView, CashPresenter> implements ICashView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.priceCustom)
    EditText priceCustom;
    @BindView(R.id.status_layout)
    StatusViewLayout statusLayout;
    Unbinder unbinder;
    @BindView(R.id.can_cash_money)
    TextView canCashMoney;
    @BindView(R.id.rd_alipay)
    CustomRadioButton rdAlipay;
    @BindView(R.id.rd_bank)
    CustomRadioButton rdBank;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.bank_name)
    EditText bankName;
    @BindView(R.id.bank_account)
    EditText bankAccount;
    @BindView(R.id.bank_user)
    EditText bankUser;
    @BindView(R.id.layout_bank)
    LinearLayout layoutBank;
    @BindView(R.id.alipay_user)
    EditText alipayUser;
    @BindView(R.id.alipay_account)
    EditText alipayAccount;
    @BindView(R.id.layout_alipay)
    LinearLayout layoutAlipay;

    private LoadingDialog loadingDialog;

    private List<BankInfo> list = new ArrayList<>();
    private CashBankAdapter adapter;

    public static CashFragment newInstance() {
        Bundle args = new Bundle();
        CashFragment fragment = new CashFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cash, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        toolbarTitle.setText("账户提现");
        initToolbarNav(toolbar);
        initView();
        UpdatePageUtils.updatePagePosition(AppConfig.POSITION_CASH, 0);
        statusLayout.showContent();
        canCashMoney.setText("￥" + PriceUtil.getPrice(ShopApplication.userInfo.getMoney() - 500 < 0 ? 0 : ShopApplication.userInfo.getMoney() - 500));
    }

    private void initView() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == rdAlipay.getId()) {
                    layoutBank.setVisibility(View.GONE);
                    layoutAlipay.setVisibility(View.VISIBLE);
                } else if (i == rdBank.getId()) {
                    layoutAlipay.setVisibility(View.GONE);
                    layoutBank.setVisibility(View.VISIBLE);
                }
            }
        });

        String bankInfoStr = SharedPreferencesUtil.getString(_mActivity, "bank", "");
        if (!StringUtils.isEmpty(bankInfoStr)) {
            BankInfo bankInfo = new Gson().fromJson(bankInfoStr, BankInfo.class);
            bankAccount.setText(bankInfo.getAccount());
            bankName.setText(bankInfo.getBank());
            bankUser.setText(bankInfo.getName());
        }
        String alipayInfoStr = SharedPreferencesUtil.getString(_mActivity, "alipay", "");
        if (!StringUtils.isEmpty(alipayInfoStr)) {
            BankInfo alipayInfo = new Gson().fromJson(alipayInfoStr, BankInfo.class);
            alipayAccount.setText(alipayInfo.getAccount());
            alipayUser.setText(alipayInfo.getName());
        }
    }

    @OnClick(R.id.submit)
    public void onClickSubmit() {
        presenter.applyCash();
    }

    @Override
    public void showLoading(int resId) {
        try {
            if (loadingDialog == null) {
                loadingDialog = LoadingDialog.getInstance(getContext());
            }
            loadingDialog.showLoadingDialog(getString(resId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hideLoading() {
        try {
            if (loadingDialog != null) {
                loadingDialog.cancelLoadingDialog();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public CashPresenter initPresenter() {
        return new CashPresenter(this);
    }

    @Override
    public void showLoadingPage() {
        try {
            statusLayout.showLoading();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showContentPage() {
        try {
            statusLayout.showContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showFailPage() {
        try {
            statusLayout.showFailed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showMessage(String message) {
        ToastUtils.showShort(message);
    }


    @Override
    public int getMoney() {
        try {
            String moneyStr = priceCustom.getText().toString().trim();
            int realMoney = Integer.parseInt(moneyStr);
            return realMoney * 100;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public void applyCashFail(String message) {
        try {
            showNoticeDialog(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void applyCashSuccess(BankInfo bankInfo, int money) {
        try {
            if (bankInfo.getType() == AppConfig.BANK_TYPE_BANK_CARD) {
                SharedPreferencesUtil.putString(_mActivity, "bank", new Gson().toJson(bankInfo));
            } else {
                SharedPreferencesUtil.putString(_mActivity, "alipay", new Gson().toJson(bankInfo));
            }
            start(CashResultFragment.newInstance(bankInfo, money));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBankName() {
        return bankName.getText().toString().trim();
    }

    @Override
    public String getBankUser() {
        return bankUser.getText().toString().trim();
    }

    @Override
    public String getBankAccount() {
        return bankAccount.getText().toString().trim();
    }

    @Override
    public String getAlipayUser() {
        return alipayUser.getText().toString().trim();
    }

    @Override
    public String getAlipayAccount() {
        return alipayAccount.getText().toString().trim();
    }

    @Override
    public int getCashType() {
        if (radioGroup.getCheckedRadioButtonId() == R.id.rd_alipay) {
            return AppConfig.BANK_TYPE_ALIPAY;
        } else if (radioGroup.getCheckedRadioButtonId() == R.id.rd_bank) {
            return AppConfig.BANK_TYPE_BANK_CARD;
        }
        return AppConfig.BANK_TYPE_ALIPAY;
    }

    private void showNoticeDialog(String message) {
        CBDialogBuilder builder = new CBDialogBuilder(getContext());
        TextView titleView = builder.getView(R.id.dialog_title);
        titleView.setSingleLine(false);
        builder.setTouchOutSideCancelable(false)
                .showCancelButton(false)
                .setTitle(message)
                .setMessage("")
                .setCustomIcon(0)
                .setConfirmButtonText("确定")
                .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                .setButtonClickListener(true, new CBDialogBuilder.onDialogbtnClickListener() {
                    @Override
                    public void onDialogbtnClick(Context context, Dialog dialog, int whichBtn) {
                        switch (whichBtn) {
                            case BUTTON_CONFIRM:
                                dialog.cancel();
                                break;
                            default:
                                break;
                        }
                    }
                })
                .create().show();
    }


    @Override
    public void onDestroyView() {
        try {
            OkGo.getInstance().cancelTag(ApiUtil.APPLY_CASH_TAG);
            OkGo.getInstance().cancelTag(ApiUtil.BANK_LIST_TAG);
            hideLoading();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroyView();
        unbinder.unbind();
    }
}
