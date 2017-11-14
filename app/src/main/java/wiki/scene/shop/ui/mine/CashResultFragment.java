package wiki.scene.shop.ui.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wiki.scene.shop.R;
import wiki.scene.shop.mvp.BaseBackMvpFragment;
import wiki.scene.shop.ui.mine.mvpview.ICashResultView;
import wiki.scene.shop.ui.mine.presenter.CashResultPresenter;

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

    public static CashResultFragment newInstance() {
        Bundle args = new Bundle();
        CashResultFragment fragment = new CashResultFragment();
        fragment.setArguments(args);
        return fragment;
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
