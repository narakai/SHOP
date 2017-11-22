package wiki.scene.shop.ui.mine;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
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
import wiki.scene.shop.adapter.CashBankAdapter;
import wiki.scene.shop.entity.BankInfo;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.mvp.BaseBackMvpFragment;
import wiki.scene.shop.ui.mine.mvpview.ICashView;
import wiki.scene.shop.ui.mine.presenter.CashPresenter;
import wiki.scene.shop.widgets.CustomListView;
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
    @BindView(R.id.listview)
    CustomListView listview;
    @BindView(R.id.priceCustom)
    EditText priceCustom;
    @BindView(R.id.status_layout)
    StatusViewLayout statusLayout;
    Unbinder unbinder;

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
        presenter.getBankData();
    }

    private void initView() {
        adapter = new CashBankAdapter(getContext(), list);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    list.get(i).setChecked(i == position);
                }
                adapter.notifyDataSetChanged();
            }
        });
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
            statusLayout.showFailed(retryListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showMessage(String message) {
        ToastUtils.showShort(message);
    }

    @Override
    public void getBankDataSuccess(List<BankInfo> data) {
        try {
            list.clear();
            list.addAll(data);
            if (list.size() > 0) {
                list.get(0).setChecked(true);
            }
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public BankInfo getBankInfo() {
        for (BankInfo info : list) {
            if (info.isChecked()) {
                return info;
            }
        }
        return null;
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
            start(CashResultFragment.newInstance(bankInfo, money));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener retryListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            presenter.getBankData();
        }
    };

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
