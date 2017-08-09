package wiki.scene.shop.ui.newest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
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
import wiki.scene.loadmore.loadmore.OnLoadMoreListener;
import wiki.scene.loadmore.recyclerview.RecyclerAdapterWithHF;
import wiki.scene.loadmore.utils.PtrLocalDisplay;
import wiki.scene.shop.R;
import wiki.scene.shop.adapter.NewestAdapter;
import wiki.scene.shop.entity.NewestResultInfo;
import wiki.scene.shop.entity.ResultPageInfo;
import wiki.scene.shop.itemDecoration.GridSpacingItemDecoration;
import wiki.scene.shop.mvp.BaseMainMvpFragment;
import wiki.scene.shop.ui.newest.mvpview.INewestView;
import wiki.scene.shop.ui.newest.presenter.NewestPresenter;

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
    private List<NewestResultInfo.NewestInfo> list = new ArrayList<>();
    //分页
    private int page = 1;

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
        presenter.getNewestOpenData(true, page);
    }

    private void initView() {
        ptrLayout.setLastUpdateTimeRelateObject(this);
        ptrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.getNewestOpenData(false, 1);
            }
        });
        ptrLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                presenter.getNewestOpenData(false, page + 1);
            }
        });
        adapter = new NewestAdapter(_mActivity, list);
        final RecyclerAdapterWithHF mAdapter = new RecyclerAdapterWithHF(adapter);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(_mActivity, 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == recyclerView.getAdapter().getItemCount() - 1 ? 2 : 1;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, PtrLocalDisplay.designedDP2px(1), false));
        recyclerView.setAdapter(mAdapter);
        ptrLayout.setLoadMoreEnable(true);
        ptrLayout.setNoMoreData();
    }


    @Override
    public void showLoading(@StringRes int resId) {
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

    @Override
    public void showLoadingPage() {
        try {
            statusLayout.showLoading();
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
    public void showErrorPage() {
        try {
            statusLayout.showFailed(retryListener);
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
    public void refreshComplete() {
        try {
            ptrLayout.refreshComplete();
            ptrLayout.loadMoreComplete(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void bindData(List<NewestResultInfo.NewestInfo> data) {
        try {
            if (page == 1) {
                list.clear();
                refreshComplete();
            }
            list.addAll(data);
            adapter.notifyDataSetChanged();
            if (list.size() == 0) {
                showEmptyPage();
            } else {
                showContentPage();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void changePage(int page) {
        this.page = page;
    }

    @Override
    public void bindPageInfo(ResultPageInfo pageInfo) {
        try {
            ptrLayout.loadMoreComplete(pageInfo.getPage_total() > pageInfo.getPage());
            ptrLayout.setLoadMoreEnable(pageInfo.getPage_total() > pageInfo.getPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener retryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.getNewestOpenData(true, page);
        }
    };
}
