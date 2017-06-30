package wiki.scene.shop.ui.share;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import wiki.scene.shop.R;
import wiki.scene.shop.adapter.SharePagerFragmentAdapter;
import wiki.scene.shop.ui.share.mvpview.IShareView;
import wiki.scene.shop.ui.share.presenter.SharePrsenter;
import wiki.scene.shop.mvp.BaseMainMvpFragment;

/**
 * Case By:晒单
 * package:wiki.scene.shop.fragment.indiana
 * Author：scene on 2017/6/26 14:13
 */
public class ShareFragment extends BaseMainMvpFragment<IShareView, SharePrsenter> implements IShareView {

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    public static ShareFragment newInstance() {
        ShareFragment fragment = new ShareFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_share, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        tab.addTab(tab.newTab().setText(R.string.most_new));
        tab.addTab(tab.newTab().setText(R.string.most_hot));
        viewPager.setAdapter(new SharePagerFragmentAdapter(getChildFragmentManager()));
        tab.setupWithViewPager(viewPager);
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public SharePrsenter initPresenter() {
        return new SharePrsenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
