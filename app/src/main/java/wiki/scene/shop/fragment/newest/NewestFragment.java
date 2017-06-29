package wiki.scene.shop.fragment.newest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wiki.scene.loadmore.PtrClassicFrameLayout;
import wiki.scene.loadmore.PtrDefaultHandler;
import wiki.scene.loadmore.PtrFrameLayout;
import wiki.scene.loadmore.StatusViewLayout;
import wiki.scene.loadmore.recyclerview.RecyclerAdapterWithHF;
import wiki.scene.loadmore.utils.PtrLocalDisplay;
import wiki.scene.shop.R;
import wiki.scene.shop.adapter.NewestAdapter;
import wiki.scene.shop.fragment.newest.mvpview.INewestView;
import wiki.scene.shop.fragment.newest.presenter.NewestPresenter;
import wiki.scene.shop.itemDecoration.GridSpacingItemDecoration;
import wiki.scene.shop.mvp.BaseMainMvpFragment;

/**
 * Case By:最新揭晓
 * package:wiki.scene.shop.fragment.indiana
 * Author：scene on 2017/6/26 14:13
 */
public class NewestFragment extends BaseMainMvpFragment<INewestView, NewestPresenter> implements INewestView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptrLayout)
    PtrClassicFrameLayout ptrLayout;
    @BindView(R.id.status_layout)
    StatusViewLayout statusLayout;
    //adapter
    private NewestAdapter adapter;
    private RecyclerAdapterWithHF mAdapter;
    private List<String> list = new ArrayList<>();

    public static NewestFragment newInstance() {
        NewestFragment fragment = new NewestFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newest, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        hideLoading();
        initView();
    }

    private void initView() {
        ptrLayout.setLastUpdateTimeRelateObject(this);
        ptrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

            }
        });
        for (int i = 0; i < 20; i++) {
            list.add("开奖商品：" + (i + 1));
        }
        adapter = new NewestAdapter(_mActivity, list);
        mAdapter = new RecyclerAdapterWithHF(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(_mActivity, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, PtrLocalDisplay.designedDP2px(1), false));
        recyclerView.setAdapter(mAdapter);
    }


    @Override
    public void showLoading() {
        statusLayout.showLoading();
    }

    @Override
    public void hideLoading() {
        statusLayout.showContent();
    }

    @Override
    public NewestPresenter initPresenter() {
        return new NewestPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
