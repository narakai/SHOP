package wiki.scene.shop.ui.indiana;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.GridLayoutManager;
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
import wiki.scene.shop.adapter.Price10IndianaAdapter;
import wiki.scene.shop.entity.Price10IndianaResultInfo;
import wiki.scene.shop.entity.ResultPageInfo;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.itemDecoration.GridSpacingItemDecoration;
import wiki.scene.shop.mvp.BaseBackMvpFragment;
import wiki.scene.shop.ui.indiana.mvpview.IPrice10IndianaView;
import wiki.scene.shop.ui.indiana.presenter.Price10IndianaPresenter;
import wiki.scene.shop.utils.ToastUtils;

/**
 * Case By:中奖记录
 * package:wiki.scene.shop.ui.mine
 * Author：scene on 2017/7/5 11:36
 */

public class Price10IndianaFragment extends BaseBackMvpFragment<IPrice10IndianaView, Price10IndianaPresenter> implements IPrice10IndianaView {

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
    private List<Price10IndianaResultInfo.Price10IndianaInfo> list = new ArrayList<>();
    private Price10IndianaAdapter adapter;
    //分页
    private int page = 1;
    //1:10元，2：秒开
    private int pageType = 1;

    public static Price10IndianaFragment newInstance(int type) {

        Bundle args = new Bundle();
        args.putInt("type", type);
        Price10IndianaFragment fragment = new Price10IndianaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            pageType = bundle.getInt("type", 1);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_price_10_indiana, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        if (pageType == 1) {
            toolbarTitle.setText(R.string.price_10_indiana);
        } else {
            toolbarTitle.setText(R.string.second_open_indiana);
        }
        initToolbarNav(toolbar);
        initView();
    }

    private void initView() {
        ptrLayout.setLastUpdateTimeRelateObject(this);

        ptrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                if (pageType == 1) {
                    presenter.getPrice10IndianaData(false, 1);
                } else {
                    presenter.getSecondIndianaData(false, 1);
                }
            }
        });
        ptrLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                if (pageType == 1) {
                    presenter.getPrice10IndianaData(false, page + 1);
                } else {
                    presenter.getSecondIndianaData(false, page + 1);
                }
            }
        });

        adapter = new Price10IndianaAdapter(getContext(), list, pageType);
        RecyclerAdapterWithHF mAdapter = new RecyclerAdapterWithHF(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(_mActivity, 2);
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
        if (pageType == 1) {
            presenter.getPrice10IndianaData(true, page);
        } else {
            presenter.getSecondIndianaData(true, page);
        }
        mAdapter.setOnItemClickListener(new RecyclerAdapterWithHF.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerAdapterWithHF adapter, RecyclerView.ViewHolder vh, int position) {
                start(GoodsDetailFragment.newInstance(list.get(position).getId()));
            }
        });
    }

    @Override
    public Price10IndianaPresenter initPresenter() {
        return new Price10IndianaPresenter(this);
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
            if (pageType == 1) {
                presenter.getPrice10IndianaData(true, page);
            } else {
                presenter.getSecondIndianaData(true, page);
            }
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
    public void bindData(Price10IndianaResultInfo resultInfo) {
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
