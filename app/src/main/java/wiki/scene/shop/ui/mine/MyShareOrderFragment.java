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
import wiki.scene.loadmore.loadmore.OnLoadMoreListener;
import wiki.scene.loadmore.recyclerview.RecyclerAdapterWithHF;
import wiki.scene.loadmore.utils.PtrLocalDisplay;
import wiki.scene.shop.MainFragment;
import wiki.scene.shop.R;
import wiki.scene.shop.adapter.ShareAdapter;
import wiki.scene.shop.entity.ResultPageInfo;
import wiki.scene.shop.entity.ShareListResultInfo;
import wiki.scene.shop.event.TabSelectedEvent;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.itemDecoration.SpacesItemDecoration;
import wiki.scene.shop.mvp.BaseBackMvpFragment;
import wiki.scene.shop.ui.share.mvpview.IShareTypeView;
import wiki.scene.shop.ui.share.presenter.ShareTypePrsenter;
import wiki.scene.shop.utils.ToastUtils;
import wiki.scene.shop.widgets.LoadingDialog;

/**
 * Case By:我的晒单 使用的是晒单-->热门的mvp模式信息
 * package:wiki.scene.shop.ui.mine
 * Author：scene on 2017/6/30 09:15
 */

public class MyShareOrderFragment extends BaseBackMvpFragment<IShareTypeView, ShareTypePrsenter> implements IShareTypeView {

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

    private LoadingDialog loadingDialog;

    private List<ShareListResultInfo.ShareListInfo> list = new ArrayList<>();
    private ShareAdapter adapter;
    //分页
    private int page = 1;
    private final int TYPE = 1;

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
    public ShareTypePrsenter initPresenter() {
        return new ShareTypePrsenter(this);
    }


    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        toolbarTitle.setText(R.string.my_share_order);
        initToolbarNav(toolbar);
        initView();
        presenter.getMyShareListData(true, TYPE, page);
    }

    private void initView() {
        ptrLayout.setLastUpdateTimeRelateObject(this);
        adapter = new ShareAdapter(_mActivity, list, true);
        RecyclerAdapterWithHF mAdapter = new RecyclerAdapterWithHF(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(PtrLocalDisplay.dp2px(1)));
        recyclerView.setAdapter(mAdapter);

        ptrLayout.setLoadMoreEnable(true);
        ptrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.getMyShareListData(false, TYPE, 1);
            }
        });
        ptrLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                presenter.getMyShareListData(false, TYPE, page + 1);
            }
        });
        adapter.setOnClickShareOrderItemListener(new ShareAdapter.OnClickShareOrderItemListener() {
            @Override
            public void onClickItemZan(int position) {
                presenter.zanShareOrder(list.get(position).getId(), position);
            }

            @Override
            public void onClikcItemTryLuck(int position) {
                EventBus.getDefault().post(new TabSelectedEvent(MainFragment.FIRST));
                _mActivity.onBackPressed();
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
        OkGo.getInstance().cancelTag(ApiUtil.SHARE_LIST_TAG);
        OkGo.getInstance().cancelTag(ApiUtil.ZAN_SHARE_ORDER_TAG);
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

    @Override
    public void loadmoreFail() {
        try {
            ptrLayout.loadFail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void zanSuccess(int position) {
        try {
            list.get(position).setLiked(true);
            list.get(position).setLike_number(list.get(position).getLike_number() + 1);
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private View.OnClickListener retryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.getMyShareListData(true, TYPE, page);
        }
    };
}
