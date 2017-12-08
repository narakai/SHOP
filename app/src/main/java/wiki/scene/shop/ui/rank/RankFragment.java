package wiki.scene.shop.ui.rank;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sunfusheng.glideimageview.GlideImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wiki.scene.loadmore.PtrClassicFrameLayout;
import wiki.scene.loadmore.PtrDefaultHandler;
import wiki.scene.loadmore.PtrFrameLayout;
import wiki.scene.loadmore.StatusViewLayout;
import wiki.scene.loadmore.recyclerview.RecyclerAdapterWithHF;
import wiki.scene.loadmore.utils.PtrLocalDisplay;
import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.adapter.RankAdapter;
import wiki.scene.shop.config.AppConfig;
import wiki.scene.shop.entity.RankInfo;
import wiki.scene.shop.event.StartBrotherEvent;
import wiki.scene.shop.itemDecoration.SpacesItemDecoration;
import wiki.scene.shop.mvp.BaseMainMvpFragment;
import wiki.scene.shop.ui.mine.OthersIndianaRecordFragment;
import wiki.scene.shop.ui.rank.presenter.RankPresenter;
import wiki.scene.shop.ui.rank.view.IRankView;
import wiki.scene.shop.utils.ToastUtils;
import wiki.scene.shop.utils.UpdatePageUtils;
import wiki.scene.shop.utils.ViewUtils;

/**
 * 排行榜
 * Created by scene on 17-8-16.
 */

public class RankFragment extends BaseMainMvpFragment<IRankView, RankPresenter> implements IRankView {
    @BindView(R.id.status_layout)
    StatusViewLayout statusLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptrLayout)
    PtrClassicFrameLayout ptrLayout;
    Unbinder unbinder;

    private View headerView;
    private LinearLayout layoutRank1;
    private LinearLayout layoutRank2;
    private LinearLayout layoutRank3;
    private ImageView rank1Avater;
    private ImageView rank2Avater;
    private ImageView rank3Avater;
    private TextView rank1Nickname;
    private TextView rank2Nickname;
    private TextView rank3Nickname;
    private TextView rank1WinTime;
    private TextView rank2WinTime;
    private TextView rank3WinTime;

    private RankAdapter adapter;
    private List<RankInfo> list;
    private List<RankInfo> headerList;

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
        initView();
        UpdatePageUtils.updatePagePosition(AppConfig.POSITION_RANK, 0);
    }

    private void initView() {
        ptrLayout.setLastUpdateTimeRelateObject(this);
        list = new ArrayList<>();
        headerList = new ArrayList<>();
        adapter = new RankAdapter(getContext(), list);
        RecyclerAdapterWithHF mAdapter = new RecyclerAdapterWithHF(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new SpacesItemDecoration(PtrLocalDisplay.designedDP2px(1), true, false));
        headerView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_rank_header, null, false);
        initHeaderView();
        mAdapter.addHeader(headerView);
        mAdapter.setOnItemClickListener(new RecyclerAdapterWithHF.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerAdapterWithHF adapter, RecyclerView.ViewHolder vh, int position) {
                if (position < list.size()) {
                    try {
                        EventBus.getDefault().post(new StartBrotherEvent(OthersIndianaRecordFragment.newInstance(list.get(position).getUser_id())));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        recyclerView.setAdapter(mAdapter);
        presenter.getRankData(true);
        ptrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.getRankData(false);
            }
        });
    }

    private void initHeaderView() {
        layoutRank1 = (LinearLayout) headerView.findViewById(R.id.layout_rank1);
        layoutRank2 = (LinearLayout) headerView.findViewById(R.id.layout_rank2);
        layoutRank3 = (LinearLayout) headerView.findViewById(R.id.layout_rank3);
        rank1Avater = (ImageView) headerView.findViewById(R.id.rank1_avater);
        rank2Avater = (ImageView) headerView.findViewById(R.id.rank2_avater);
        rank3Avater = (ImageView) headerView.findViewById(R.id.rank3_avater);
        rank1Nickname = (TextView) headerView.findViewById(R.id.rank1_nickname);
        rank2Nickname = (TextView) headerView.findViewById(R.id.rank2_nickname);
        rank3Nickname = (TextView) headerView.findViewById(R.id.rank3_nickname);
        rank1WinTime = (TextView) headerView.findViewById(R.id.rank1_win_time);
        rank2WinTime = (TextView) headerView.findViewById(R.id.rank2_win_time);
        rank3WinTime = (TextView) headerView.findViewById(R.id.rank3_win_time);
        ViewUtils.setDialogViewWidth(layoutRank1, PtrLocalDisplay.SCREEN_WIDTH_PIXELS / 3);
        ViewUtils.setDialogViewWidth(layoutRank2, PtrLocalDisplay.SCREEN_WIDTH_PIXELS / 3);
        ViewUtils.setDialogViewWidth(layoutRank3, PtrLocalDisplay.SCREEN_WIDTH_PIXELS / 3);
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
    public void showErrorPage() {
        try {
            statusLayout.showFailed(retryListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showNonePage() {
        try {
            statusLayout.showNone();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refreshCompilt() {
        try {
            ptrLayout.refreshComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showMessage(@StringRes int resId) {
        ToastUtils.getInstance(_mActivity).showToast(resId);
    }

    @Override
    public void showMessage(String message) {
        ToastUtils.getInstance(_mActivity).showToast(message);
    }

    @Override
    public void bindData(List<RankInfo> data) {
        try {
            list.clear();
            headerList.clear();
            for (int i = 0; i < data.size(); i++) {
                if (i < 3) {
                    headerList.add(data.get(i));
                } else {
                    list.add(data.get(i));
                }
            }
            bindHeaderViewData();
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void bindHeaderViewData() {
        try {
            switch (headerList.size()) {
                case 3:
                    layoutRank3.setVisibility(View.VISIBLE);
                    GlideImageLoader.create(rank3Avater).loadCircleImage(ShopApplication.configInfo.getFile_domain() + headerList.get(2).getAvatar(), R.drawable.ic_default_avater);
                    rank3Nickname.setText(headerList.get(2).getNickname());
                    rank3WinTime.setText(String.valueOf(headerList.get(2).getWin_times()));
                case 2:
                    layoutRank2.setVisibility(View.VISIBLE);
                    GlideImageLoader.create(rank2Avater).loadCircleImage(ShopApplication.configInfo.getFile_domain() + headerList.get(1).getAvatar(), R.drawable.ic_default_avater);
                    rank2Nickname.setText(headerList.get(1).getNickname());
                    rank2WinTime.setText(String.valueOf(headerList.get(1).getWin_times()));
                case 1:
                    layoutRank1.setVisibility(View.VISIBLE);
                    GlideImageLoader.create(rank1Avater).loadCircleImage(ShopApplication.configInfo.getFile_domain() + headerList.get(0).getAvatar(), R.drawable.ic_default_avater);
                    rank1Nickname.setText(headerList.get(0).getNickname());
                    rank1WinTime.setText(String.valueOf(headerList.get(0).getWin_times()));
                    headerView.setVisibility(View.VISIBLE);
                    break;
            }
            switch (headerList.size()) {
                case 0:
                    headerView.setVisibility(View.GONE);
                    layoutRank1.setVisibility(View.GONE);
                    layoutRank2.setVisibility(View.GONE);
                    layoutRank3.setVisibility(View.GONE);
                    break;
                case 1:
                    layoutRank2.setVisibility(View.INVISIBLE);
                    layoutRank3.setVisibility(View.INVISIBLE);
                    break;
                case 2:
                    layoutRank3.setVisibility(View.INVISIBLE);
                    break;

            }
            layoutRank1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        EventBus.getDefault().post(new StartBrotherEvent(OthersIndianaRecordFragment.newInstance(list.get(0).getUser_id())));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            layoutRank2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        EventBus.getDefault().post(new StartBrotherEvent(OthersIndianaRecordFragment.newInstance(list.get(1).getUser_id())));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            layoutRank3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        EventBus.getDefault().post(new StartBrotherEvent(OthersIndianaRecordFragment.newInstance(list.get(2).getUser_id())));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    View.OnClickListener retryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.getRankData(true);
        }
    };
}
