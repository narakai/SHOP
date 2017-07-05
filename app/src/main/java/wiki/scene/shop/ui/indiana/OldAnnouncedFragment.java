package wiki.scene.shop.ui.indiana;

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
import wiki.scene.shop.adapter.OldAnnouncedAdapter;
import wiki.scene.shop.itemDecoration.SpacesItemDecoration;
import wiki.scene.shop.mvp.BaseBackMvpFragment;
import wiki.scene.shop.ui.indiana.mvpview.IOldAnnouncedView;
import wiki.scene.shop.ui.indiana.presenter.OldAnnouncedPresenter;

/**
 * Case By:往期揭晓
 * package:wiki.scene.shop.ui
 * Author：scene on 2017/7/5 12:41
 */

public class OldAnnouncedFragment extends BaseBackMvpFragment<IOldAnnouncedView, OldAnnouncedPresenter> implements IOldAnnouncedView {

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
    Unbinder unbinder;
    //adapter
    private List<String> list=new ArrayList<>();
    private OldAnnouncedAdapter adapter;
    private RecyclerAdapterWithHF mAdapter;

    public static OldAnnouncedFragment newInstance() {
        Bundle args = new Bundle();
        OldAnnouncedFragment fragment = new OldAnnouncedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_back_recyclerview, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        toolbarTitle.setText(R.string.old_announced);
        initToolbarNav(toolbar);
        initView();

    }

    private void initView() {
        showContent();
        ptrLayout.setLastUpdateTimeRelateObject(this);
        ptrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

            }
        });
        for (int i=0;i<10;i++){
            list.add("中奖名称"+i);
        }
        adapter=new OldAnnouncedAdapter(_mActivity,list);
        mAdapter=new RecyclerAdapterWithHF(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        recyclerView.addItemDecoration(new SpacesItemDecoration(PtrLocalDisplay.dp2px(3.33f)));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showContent() {
        statusLayout.showContent();
    }

    @Override
    public void showFail() {

    }

    @Override
    public void showNoNetwork() {

    }

    @Override
    public OldAnnouncedPresenter initPresenter() {
        return new OldAnnouncedPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
