package wiki.scene.shop.ui.trend;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wiki.scene.loadmore.PtrClassicFrameLayout;
import wiki.scene.loadmore.StatusViewLayout;
import wiki.scene.shop.R;
import wiki.scene.shop.mvp.BaseMainMvpFragment;
import wiki.scene.shop.ui.trend.presenter.TrendPresenter;
import wiki.scene.shop.ui.trend.view.ITrendView;

/**
 * 开奖走势
 * Created by scene on 2017/11/8.
 */

public class TrendFragment extends BaseMainMvpFragment<ITrendView, TrendPresenter> implements ITrendView {

//    @BindView(R.id.recyclerView)
//    RecyclerView recyclerView;
//    @BindView(R.id.ptrLayout)
//    PtrClassicFrameLayout ptrLayout;
//    @BindView(R.id.status_layout)
//    StatusViewLayout statusLayout;
    Unbinder unbinder;

    public static TrendFragment newInstance() {
        Bundle args = new Bundle();
        TrendFragment fragment = new TrendFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trend_header, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }

    @Override
    public void showLoading(int resId) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public TrendPresenter initPresenter() {
        return new TrendPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
