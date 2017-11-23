package wiki.scene.shop.ui.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wiki.scene.shop.R;
import wiki.scene.shop.config.AppConfig;
import wiki.scene.shop.entity.BankInfo;
import wiki.scene.shop.event.AddBankCardSuccessEvent;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.mvp.BaseBackMvpFragment;
import wiki.scene.shop.ui.mine.mvpview.IAddBankView;
import wiki.scene.shop.ui.mine.presenter.AddBankPresenter;
import wiki.scene.shop.utils.UpdatePageUtils;
import wiki.scene.shop.widgets.LoadingDialog;

/**
 * 添加银行卡
 * Created by scene on 2017/11/14.
 */

public class AddBankFragment extends BaseBackMvpFragment<IAddBankView, AddBankPresenter> implements IAddBankView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.real_name)
    EditText realName;
    @BindView(R.id.account)
    EditText account;
    @BindView(R.id.bank)
    EditText bank;
    Unbinder unbinder;
    @BindView(R.id.add)
    TextView add;
    @BindView(R.id.open_bank)
    EditText openBank;

    private LoadingDialog loadingDialog;

    private BankInfo bankInfo = null;

    public static AddBankFragment newInstance(BankInfo bankInfo) {
        Bundle args = new Bundle();
        if (bankInfo != null) {
            args.putSerializable("info", bankInfo);
        }
        AddBankFragment fragment = new AddBankFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            bankInfo = (BankInfo) getArguments().getSerializable("info");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_bank, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        toolbarTitle.setText(bankInfo == null ? "添加银行卡" : "修改银行卡");
        initToolbarNav(toolbar);
        add.setText(bankInfo == null ? "添加" : "修改");
        initView();
        UpdatePageUtils.updatePagePosition(AppConfig.POSITION_BIND_BANK_CARD, 0);
    }

    private void initView() {
        if (bankInfo != null) {
            realName.setText(bankInfo.getName());
            account.setText(bankInfo.getAccount());
            bank.setText(bankInfo.getBank());
            openBank.setText(bankInfo.getOpen_bank());
        }
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
    public AddBankPresenter initPresenter() {
        return new AddBankPresenter(this);
    }

    @Override
    public void showMessage(String message) {
        ToastUtils.showShort(message);
    }

    @Override
    public void bindOrUpdateSuccess() {
        try {
            EventBus.getDefault().post(new AddBankCardSuccessEvent());
            _mActivity.onBackPressed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.add)
    public void onClickAdd() {
        BankInfo info = new BankInfo();

        String realNameStr = realName.getText().toString().trim();
        String accountStr = account.getText().toString().trim();
        String bankStr = bank.getText().toString().trim();
        String openBankStr = openBank.getText().toString().trim();
        if (StringUtils.isEmpty(bankStr)) {
            showMessage("请输入银行名称");
            return;
        }
        if (StringUtils.isEmpty(realNameStr)) {
            showMessage("请输入持卡人姓名");
            return;
        }
        if (StringUtils.isEmpty(accountStr)) {
            showMessage("请输入卡号");
            return;
        }

        if (StringUtils.isEmpty(openBankStr)) {
            showMessage("请输入开户行");
            return;
        }
        info.setName(realNameStr);
        info.setAccount(accountStr);
        info.setBank(bankStr);
        info.setOpen_bank(openBankStr);
        if (bankInfo == null) {
            presenter.addBankCard(info);
        } else {
            info.setId(bankInfo.getId());
            presenter.updateBankCard(info);
        }
    }


    @Override
    public void onStop() {
        super.onStop();
        hideLoading();
    }

    @Override
    public void onDestroyView() {
        OkGo.getInstance().cancelTag(ApiUtil.ADD_BANK_CARD_TAG);
        super.onDestroyView();
        unbinder.unbind();
    }
}
