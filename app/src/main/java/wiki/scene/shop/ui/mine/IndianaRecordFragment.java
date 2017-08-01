package wiki.scene.shop.ui.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wiki.scene.shop.R;
import wiki.scene.shop.adapter.IndiaRecordPagerFragmentAdapter;
import wiki.scene.shop.mvp.BaseBackMvpFragment;
import wiki.scene.shop.ui.mine.mvpview.IIdianaRecordView;
import wiki.scene.shop.ui.mine.presenter.IndianaRecordPresenter;

/**
 * Case By:夺宝记录主界面
 * package:wiki.scene.shop.ui.mine
 * Author：scene on 2017/7/5 14:37
 */

public class IndianaRecordFragment extends BaseBackMvpFragment<IIdianaRecordView, IndianaRecordPresenter> implements IIdianaRecordView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Unbinder unbinder;

    public static IndianaRecordFragment newInstance() {
        Bundle args = new Bundle();
        IndianaRecordFragment fragment = new IndianaRecordFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_indiana_record, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        toolbarTitle.setText(R.string.indiana_record);
        initToolbarNav(toolbar);
        initView();
    }

    private void initView() {
        String tabTitle[] = {getString(R.string.all), getString(R.string.gooning), getString(R.string.announced)};
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(IndianaRecordTypeFragment.newInstance(IndianaRecordTypeFragment.INDIANA_RECORD_TYPE_ALL));
        fragmentList.add(IndianaRecordTypeFragment.newInstance(IndianaRecordTypeFragment.INDIANA_RECORD_TYPE_ONGOING));
        fragmentList.add(IndianaRecordTypeFragment.newInstance(IndianaRecordTypeFragment.INDIANA_RECORD_TYPE_ANNOUNDCED));
        tab.addTab(tab.newTab().setText(tabTitle[0]));
        tab.addTab(tab.newTab().setText(tabTitle[1]));
        tab.addTab(tab.newTab().setText(tabTitle[2]));
        viewPager.setAdapter(new IndiaRecordPagerFragmentAdapter(getChildFragmentManager(), tabTitle, fragmentList));
        viewPager.setOffscreenPageLimit(3);
        tab.setupWithViewPager(viewPager);
    }

    @Override
    public IndianaRecordPresenter initPresenter() {
        return new IndianaRecordPresenter(this);
    }

    @Override
    public void showLoading(@StringRes int resId) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
