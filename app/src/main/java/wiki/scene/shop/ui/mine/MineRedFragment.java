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
import wiki.scene.loadmore.utils.PtrLocalDisplay;
import wiki.scene.shop.R;
import wiki.scene.shop.adapter.MineRedAdapter;
import wiki.scene.shop.itemDecoration.SpacesItemDecoration;
import wiki.scene.shop.mvp.BaseBackMvpFragment;
import wiki.scene.shop.ui.mine.mvpview.IMineRedView;
import wiki.scene.shop.ui.mine.presenter.MineRedPresenter;

/**
 * Case By:我的红包
 * package:wiki.scene.shop.ui.mine
 * Author：scene on 2017/6/30 13:30
 */

public class MineRedFragment extends BaseBackMvpFragment<IMineRedView, MineRedPresenter> implements IMineRedView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptrLayout)
    PtrClassicFrameLayout ptrLayout;
    @BindView(R.id.status_layout)
    StatusViewLayout statusLayout;

    private Unbinder unbinder;

    //adapter
    private List<String> list = new ArrayList<>();
    private MineRedAdapter adapter;

    public static MineRedFragment newInstance() {
        Bundle args = new Bundle();
        MineRedFragment fragment = new MineRedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine_red, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        toolbarTitle.setText(R.string.mine_red);
        initToolbarNav(toolbar);
        showContent();
        initView();
    }

    private void initView() {
        ptrLayout.setLastUpdateTimeRelateObject(this);
        ptrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

            }
        });

        for (int i = 0; i < 10; i++) {
            list.add("红包" + (i + 1));
        }
        adapter = new MineRedAdapter(_mActivity, list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(PtrLocalDisplay.dp2px(1)));
        recyclerView.setAdapter(adapter);
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
        statusLayout.showFailed(retryListener);
    }

    @Override
    public void showNoNetwork() {
        statusLayout.showNetError(retryListener);
    }

    @Override
    public MineRedPresenter initPresenter() {
        return new MineRedPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    View.OnClickListener retryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
}
