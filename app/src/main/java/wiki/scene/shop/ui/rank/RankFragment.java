package wiki.scene.shop.ui.rank;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wiki.scene.loadmore.StatusViewLayout;
import wiki.scene.shop.R;
import wiki.scene.shop.mvp.BaseMainMvpFragment;
import wiki.scene.shop.ui.rank.presenter.RankPresenter;
import wiki.scene.shop.ui.rank.view.IRankView;

/**
 * 排行榜
 * Created by scene on 17-8-16.
 */

public class RankFragment extends BaseMainMvpFragment<IRankView, RankPresenter> implements IRankView {
    @BindView(R.id.status_layout)
    StatusViewLayout statusLayout;
    Unbinder unbinder;

    public static RankFragment newInstance() {
        Bundle args = new Bundle();
        RankFragment fragment = new RankFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rank, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }

    @Override
    public void showLoading(@StringRes int resId) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public RankPresenter initPresenter() {
        return new RankPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
