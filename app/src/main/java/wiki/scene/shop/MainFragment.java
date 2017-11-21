package wiki.scene.shop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;
import wiki.scene.loadmore.utils.SceneLogUtil;
import wiki.scene.shop.event.StartBrotherEvent;
import wiki.scene.shop.event.TabSelectedEvent;
import wiki.scene.shop.ui.indiana.IndianaFragment;
import wiki.scene.shop.ui.mine.MineFragment;
import wiki.scene.shop.ui.rank.RankFragment;
import wiki.scene.shop.ui.trend.TrendFragment;
import wiki.scene.shop.view.BottomBar;
import wiki.scene.shop.view.BottomBarTab;

/**
 * Case By: 主Fragment
 * package:
 * Author：scene on 2017/6/26 14:09
 */
public class MainFragment extends SupportFragment {
    private static final int REQ_MSG = 10;

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOUR = 3;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_message)
    ImageView toolbarMessage;
    @BindView(R.id.toolbar_publish)
    TextView toolbarPublish;
    @BindView(R.id.toolbarLayout)
    View toolbarLayout;
    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;

    Unbinder unbinder;

    private SupportFragment[] mFragments = new SupportFragment[4];
    private List<String> tabNames = new ArrayList<>();

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SupportFragment firstFragment = findChildFragment(IndianaFragment.class);
        tabNames.add(getString(R.string.indiana_toolbar_text));
        tabNames.add(getString(R.string.bottom_tab_trend));
        tabNames.add(getString(R.string.bottom_tab_rank));
        tabNames.add(getString(R.string.bottom_tab_mine));
        if (firstFragment == null) {
            mFragments[FIRST] = IndianaFragment.newInstance();
            mFragments[SECOND] = TrendFragment.newInstance();
            mFragments[THIRD] = RankFragment.newInstance();
            mFragments[FOUR] = MineFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_tab_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOUR]);
        } else {
            // 这里我们需要拿到mFragments的引用,也可以通过getChildFragmentManager.findFragmentByTag自行进行判断查找(效率更高些),用下面的方法查找更方便些
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findChildFragment(TrendFragment.class);
            mFragments[THIRD] = findChildFragment(RankFragment.class);
            mFragments[FOUR] = findChildFragment(MineFragment.class);
        }
        try {
            toolbarTitle.setText(tabNames.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView(View view) {
        EventBus.getDefault().register(this);

        mBottomBar
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_tab_index_d, R.drawable.ic_tab_index_s, getString(R.string.bottom_tab_indiana)))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_tab_trend_d, R.drawable.ic_tab_trend_s, getString(R.string.bottom_tab_trend)))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_tab_rank_d, R.drawable.ic_tab_rank_s, getString(R.string.bottom_tab_rank)))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_tab_mine_d, R.drawable.ic_tab_mine_s, getString(R.string.bottom_tab_mine)));


        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);
                toolbarTitle.setText(tabNames.get(position));
                toolbarMessage.setVisibility(View.GONE);
//                if (position != tabNames.size() - 1) {
//                    toolbarLayout.setVisibility(View.VISIBLE);
//
//                    toolbarPublish.setVisibility(View.GONE);
//                } else {
//                    toolbarLayout.setVisibility(View.GONE);
//                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
                if (position == FOUR) {
                    EventBus.getDefault().post(new TabSelectedEvent(FOUR));
                }
            }
        });
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (requestCode == REQ_MSG && resultCode == RESULT_OK) {
            SceneLogUtil.e("返回");
        }
    }

    /**
     * start other BrotherFragment
     */
    @Subscribe
    public void startBrother(StartBrotherEvent event) {
        start(event.targetFragment);
    }

    @Subscribe
    public void toIndexPage(TabSelectedEvent event) {
        mBottomBar.setCurrentItem(event.position);
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
        unbinder.unbind();
    }
}
