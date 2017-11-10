package wiki.scene.shop.ui.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lzy.okgo.OkGo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wiki.scene.loadmore.PtrClassicFrameLayout;
import wiki.scene.loadmore.PtrDefaultHandler;
import wiki.scene.loadmore.PtrFrameLayout;
import wiki.scene.loadmore.StatusViewLayout;
import wiki.scene.loadmore.loadmore.OnLoadMoreListener;
import wiki.scene.loadmore.recyclerview.RecyclerAdapterWithHF;
import wiki.scene.loadmore.utils.PtrLocalDisplay;
import wiki.scene.shop.R;
import wiki.scene.shop.adapter.MineCollectAdapter;
import wiki.scene.shop.adapter.WinRecordAdapter;
import wiki.scene.shop.entity.MineCollectionResultInfo;
import wiki.scene.shop.entity.ResultPageInfo;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.itemDecoration.SpacesItemDecoration;
import wiki.scene.shop.mvp.BaseBackMvpFragment;
import wiki.scene.shop.ui.indiana.GoodsDetailFragment;
import wiki.scene.shop.ui.mine.mvpview.IMineCollectView;
import wiki.scene.shop.ui.mine.presenter.MineCollectPresenter;
import wiki.scene.shop.utils.ToastUtils;

/**
 * Case By:中奖记录
 * package:wiki.scene.shop.ui.mine
 * Author：scene on 2017/7/5 11:36
 */

public class MineCollectFragment extends BaseBackMvpFragment<IMineCollectView, MineCollectPresenter> implements IMineCollectView {

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
    private List<MineCollectionResultInfo.MineCollectionInfo> list = new ArrayList<>();
    private MineCollectAdapter adapter;
    //分页
    private int page = 1;

    public static MineCollectFragment newInstance() {

        Bundle args = new Bundle();
        MineCollectFragment fragment = new MineCollectFragment();
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
        toolbarTitle.setText(R.string.mine_collect);
        initToolbarNav(toolbar);
        initView();
    }

    private void initView() {
        ptrLayout.setLastUpdateTimeRelateObject(this);

        ptrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.getMineCollectionData(false, 1);
            }
        });
        ptrLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                presenter.getMineCollectionData(false, page + 1);
            }
        });

        adapter = new MineCollectAdapter(_mActivity, list);
        RecyclerAdapterWithHF mAdapter = new RecyclerAdapterWithHF(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        recyclerView.addItemDecoration(new SpacesItemDecoration(PtrLocalDisplay.dp2px(1)));
        recyclerView.setAdapter(mAdapter);
        ptrLayout.setLoadMoreEnable(true);
        presenter.getMineCollectionData(true, page);
        mAdapter.setOnItemClickListener(new RecyclerAdapterWithHF.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerAdapterWithHF adapter, RecyclerView.ViewHolder vh, int position) {
                start(GoodsDetailFragment.newInstance(1));
            }
        });
    }

    @Override
    public MineCollectPresenter initPresenter() {
        return new MineCollectPresenter(this);
    }


    @Override
    public void showLoading(@StringRes int resId) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showLoadingPage() {
        try {
            statusLayout.showLoading();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener retryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.getMineCollectionData(true, page);
        }
    };

    @Override
    public void showErrorPage() {
        try {
            statusLayout.showFailed(retryListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showEmptyPage() {
        try {
            statusLayout.showNone();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showContentPage() {
        try {
            if (!statusLayout.isContent()) {
                statusLayout.showContent();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showMessage(String msg) {
        ToastUtils.getInstance(_mActivity).showToast(msg);
    }

    @Override
    public void showMessage(@StringRes int resId) {
        ToastUtils.getInstance(_mActivity).showToast(resId);
    }

    @Override
    public void refreshCompile() {
        try {
            ptrLayout.refreshComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changePage(int page) {
        this.page = page;
    }

    @Override
    public void bindMineCollectData(MineCollectionResultInfo resultInfo) {
        try {
            ResultPageInfo pageInfo = resultInfo.getInfo();
            if (page == 1) {
                list.clear();
            }
            list.addAll(resultInfo.getData());
            adapter.notifyDataSetChanged();
            if (list.size() == 0) {
                showEmptyPage();
            } else {
                showContentPage();
            }
            ptrLayout.refreshComplete();
            ptrLayout.setLoadMoreEnable(pageInfo.getPage_total() > page);
            ptrLayout.loadMoreComplete(pageInfo.getPage_total() > page);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadmoreFail() {
        try {
            ptrLayout.loadFail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        OkGo.getInstance().cancelTag(ApiUtil.MINE_WIN_LIST_TAG);
        super.onDestroyView();
        unbinder.unbind();
    }

}
