package wiki.scene.shop.ui.share;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import wiki.scene.shop.adapter.ShareAdapter;
import wiki.scene.shop.entity.ResultPageInfo;
import wiki.scene.shop.entity.ShareListResultInfo;
import wiki.scene.shop.itemDecoration.SpacesItemDecoration;
import wiki.scene.shop.mvp.BaseMvpFragment;
import wiki.scene.shop.ui.share.mvpview.IShareTypeView;
import wiki.scene.shop.ui.share.presenter.ShareTypePrsenter;
import wiki.scene.shop.utils.ToastUtils;
import wiki.scene.shop.widgets.LoadingDialog;

/**
 * Case By:最热
 * package:wiki.scene.shop.fragment.share
 * Author：scene on 2017/6/29 11:57
 */

public class ShareHotestFragment extends BaseMvpFragment<IShareTypeView, ShareTypePrsenter> implements IShareTypeView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptrLayout)
    PtrClassicFrameLayout ptrLayout;
    @BindView(R.id.status_layout)
    StatusViewLayout statusLayout;
    Unbinder unbinder;

    private LoadingDialog loadingDialog;

    private List<ShareListResultInfo.ShareListInfo> list = new ArrayList<>();
    private ShareAdapter adapter;
    //分页
    private int page = 1;
    private final int TYPE = 2;

    public static ShareHotestFragment newInstance() {
        Bundle args = new Bundle();
        ShareHotestFragment fragment = new ShareHotestFragment();
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
        View view = inflater.inflate(R.layout.fragment_share_hotest, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public ShareTypePrsenter initPresenter() {
        return new ShareTypePrsenter(this);
    }


    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        initView();
        presenter.getShareListData(true, TYPE, page);
    }

    private void initView() {
        ptrLayout.setLastUpdateTimeRelateObject(this);
        adapter = new ShareAdapter(_mActivity, list);
        RecyclerAdapterWithHF mAdapter = new RecyclerAdapterWithHF(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(PtrLocalDisplay.dp2px(1)));
        recyclerView.setAdapter(mAdapter);

        ptrLayout.setLoadMoreEnable(true);
        ptrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.getShareListData(false, TYPE, 1);
            }
        });
        ptrLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                presenter.getShareListData(false, TYPE, page + 1);
            }
        });
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
            statusLayout.showContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showProgressDialog(@StringRes int resId) {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog.getInstance(getContext());
        }
        loadingDialog.showLoadingDialog(getString(resId));
    }

    @Override
    public void hideProgressDialog() {
        if (loadingDialog != null) {
            loadingDialog.cancelLoadingDialog();
        }
    }

    @Override
    public void showMessage(@StringRes int resId) {
        ToastUtils.getInstance(_mActivity).showToast(resId);
    }

    @Override
    public void showMessage(String msg) {
        ToastUtils.getInstance(_mActivity).showToast(msg);
    }

    @Override
    public void getShareListDataSuccess(List<ShareListResultInfo.ShareListInfo> dataList) {
        try {
            if (page == 1) {
                list.clear();
            }
            list.addAll(dataList);
            adapter.notifyDataSetChanged();
            if (list.size() == 0) {
                showEmptyPage();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changePageInfo(ResultPageInfo pageInfo, int page) {
        try {
            this.page = page;
            ptrLayout.loadMoreComplete(pageInfo.getPage_total() > page);
            ptrLayout.setLoadMoreEnable(pageInfo.getPage_total() > page);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refreshCompile() {
        try {
            ptrLayout.refreshComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private View.OnClickListener retryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.getShareListData(true, TYPE, page);
        }
    };
}
