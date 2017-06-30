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
import wiki.scene.shop.adapter.ShareAdapter;
import wiki.scene.shop.itemDecoration.SpacesItemDecoration;
import wiki.scene.shop.mvp.BaseBackMvpFragment;
import wiki.scene.shop.ui.share.mvpview.IShareHotestView;
import wiki.scene.shop.ui.share.presenter.ShareHotestPrsenter;

/**
 * Case By:我的晒单 使用的是晒单-->热门的mvp模式信息
 * package:wiki.scene.shop.ui.mine
 * Author：scene on 2017/6/30 09:15
 */

public class MyShareOrderFragment extends BaseBackMvpFragment<IShareHotestView, ShareHotestPrsenter> implements IShareHotestView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptrLayout)
    PtrClassicFrameLayout ptrLayout;
    @BindView(R.id.status_layout)
    StatusViewLayout statusLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    Unbinder unbinder;

    private List<String> list = new ArrayList<>();
    private ShareAdapter adapter;
    private RecyclerAdapterWithHF mAdapter;

    public static MyShareOrderFragment newInstance() {
        Bundle args = new Bundle();
        MyShareOrderFragment fragment = new MyShareOrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_share_order, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public ShareHotestPrsenter initPresenter() {
        return new ShareHotestPrsenter(this);
    }


    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        toolbarTitle.setText(R.string.my_share_order);
        initToolbarNav(toolbar);
        initView();
        hideLoading();
    }

    private void initView() {
        ptrLayout.setLastUpdateTimeRelateObject(this);
        for (int i = 0; i < 10; i++) {
            list.add(String.valueOf(i));
        }
        adapter = new ShareAdapter(_mActivity, list);
        mAdapter = new RecyclerAdapterWithHF(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(PtrLocalDisplay.dp2px(1)));
        recyclerView.setAdapter(mAdapter);
        ptrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
        statusLayout.showContent();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
