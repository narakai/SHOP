package wiki.scene.shop.ui.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wiki.scene.shop.R;
import wiki.scene.shop.config.AppConfig;
import wiki.scene.shop.entity.BankInfo;
import wiki.scene.shop.mvp.BaseBackMvpFragment;
import wiki.scene.shop.ui.mine.mvpview.ICashResultView;
import wiki.scene.shop.ui.mine.presenter.CashResultPresenter;
import wiki.scene.shop.utils.PriceUtil;
import wiki.scene.shop.utils.UpdatePageUtils;

/**
 * 申请提现结果
 * Created by scene on 2017/11/14.
 */

public class CashResultFragment extends BaseBackMvpFragment<ICashResultView, CashResultPresenter> implements ICashResultView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    Unbinder unbinder;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.bank)
    TextView bank;
    @BindView(R.id.money)
    TextView tvMoney;

    private BankInfo bankInfo;
    private int money;

    public static CashResultFragment newInstance(BankInfo bankInfo, int money) {
        Bundle args = new Bundle();
        args.putInt("money", money);
        args.putSerializable("info", bankInfo);
        CashResultFragment fragment = new CashResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            bankInfo = (BankInfo) bundle.getSerializable("info");
            money = bundle.getInt("money");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cash_result, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        toolbarTitle.setText("账户提现");
        initToolbarNav(toolbar);
        initView();
        UpdatePageUtils.updatePagePosition(AppConfig.POSITION_CASH_RESULT, 0);
    }

    private void initView() {
        time.setText(TimeUtils.getNowString());
        if (bankInfo != null) {
            StringBuilder builder = new StringBuilder();
            if (bankInfo.getType() == 1) {
                builder.append(bankInfo.getBank());
                String account = bankInfo.getAccount();
                if (account.length() > 4) {
                    account = account.substring(account.length() - 4);
                }
                builder.append("\t");
                builder.append("尾号");
                builder.append(account);
            } else {
                builder.append("支付宝");
                builder.append("\t");
                String account = bankInfo.getAccount();
                if (account.length() > 8) {
                    account = account.replace(account.substring(4, account.length() - 4), "****");
                }
                builder.append(account);
            }
            bank.setText(builder.toString());
        }
        if (money != 0) {
            tvMoney.setText("￥" + PriceUtil.getPrice(money));
        }
    }

    @Override
    public void showLoading(int resId) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public CashResultPresenter initPresenter() {
        return new CashResultPresenter(this);
    }

    @OnClick(R.id.complite)
    public void onClickComplite() {
        _mActivity.onBackPressed();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
