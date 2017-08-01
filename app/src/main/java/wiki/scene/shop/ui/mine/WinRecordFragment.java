package wiki.scene.shop.ui.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import wiki.scene.loadmore.PtrClassicFrameLayout;
import wiki.scene.loadmore.PtrDefaultHandler;
import wiki.scene.loadmore.PtrFrameLayout;
import wiki.scene.loadmore.StatusViewLayout;
import wiki.scene.loadmore.recyclerview.RecyclerAdapterWithHF;
import wiki.scene.loadmore.utils.PtrLocalDisplay;
import wiki.scene.shop.R;
import wiki.scene.shop.adapter.WinRecordAdapter;
import wiki.scene.shop.itemDecoration.SpacesItemDecoration;
import wiki.scene.shop.mvp.BaseBackMvpFragment;
import wiki.scene.shop.ui.mine.mvpview.IWinRecordView;
import wiki.scene.shop.ui.mine.presenter.WinRecordPresenter;

/**
 * Case By:中奖记录
 * package:wiki.scene.shop.ui.mine
 * Author：scene on 2017/7/5 11:36
 */

public class WinRecordFragment extends BaseBackMvpFragment<IWinRecordView, WinRecordPresenter> implements IWinRecordView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptrLayout)
    PtrClassicFrameLayout ptrLayout;
    @BindView(R.id.status_layout)
    StatusViewLayout statusLayout;
    Unbinder unbinder;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

    //adapter
    private List<String> list = new ArrayList<>();
    private RecyclerAdapterWithHF mAdapter;
    private WinRecordAdapter adapter;

    public static WinRecordFragment newInstance() {

        Bundle args = new Bundle();
        WinRecordFragment fragment = new WinRecordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_win_record, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        toolbarTitle.setText(R.string.win_record);
        initToolbarNav(toolbar);
        initView();
    }

    private void initView() {
        ptrLayout.setLastUpdateTimeRelateObject(this);
        ptrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

            }
        });
        showContent();
        for (int i = 1; i < 10; i++) {
            list.add("中奖商品" + i);
        }

        adapter = new WinRecordAdapter(_mActivity, list);
        mAdapter = new RecyclerAdapterWithHF(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        recyclerView.addItemDecoration(new SpacesItemDecoration(PtrLocalDisplay.dp2px(1)));
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void showLoading() {
        statusLayout.showLoading();
    }

    @Override
    public void showContent() {
        statusLayout.showContent();
    }

    @Override
    public void showFail() {

    }

    @Override
    public WinRecordPresenter initPresenter() {
        return new WinRecordPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
