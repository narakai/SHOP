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
import wiki.scene.shop.adapter.ExchangeRecordAdapter;
import wiki.scene.shop.entity.ExchangeRecordInfo;
import wiki.scene.shop.entity.ExchangeRecordResultInfo;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.itemDecoration.SpacesItemDecoration;
import wiki.scene.shop.mvp.BaseBackMvpFragment;
import wiki.scene.shop.ui.mine.mvpview.IExchangeRecordView;
import wiki.scene.shop.ui.mine.presenter.ExchangeRecordPresenter;

/**
 * 兑换记录
 * Created by scene on 2017/11/15.
 */

public class ExchangeRecordFragment extends BaseBackMvpFragment<IExchangeRecordView, ExchangeRecordPresenter> implements IExchangeRecordView {
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

    private List<ExchangeRecordInfo> list = new ArrayList<>();
    private RecyclerAdapterWithHF mAdapter;

    private int page = 1;

    public static ExchangeRecordFragment newInstance() {
        Bundle args = new Bundle();
        ExchangeRecordFragment fragment = new ExchangeRecordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exchange_record, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        toolbarTitle.setText("兑换记录");
        initToolbarNav(toolbar);
        initView();
        presenter.getExchangeRecord(true, 1);
    }

    private void initView() {
        ptrLayout.setLastUpdateTimeRelateObject(true);
        ptrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.getExchangeRecord(false, 1);
            }
        });
        ptrLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                presenter.getExchangeRecord(false, page + 1);
            }
        });
        ExchangeRecordAdapter adapter = new ExchangeRecordAdapter(getContext(), list);
        mAdapter = new RecyclerAdapterWithHF(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new SpacesItemDecoration(PtrLocalDisplay.dp2px(10)));
        recyclerView.setAdapter(mAdapter);
        ptrLayout.setLoadMoreEnable(true);
    }

    @Override
    public void showLoading(int resId) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public ExchangeRecordPresenter initPresenter() {
        return new ExchangeRecordPresenter(this);
    }

    @Override
    public void onDestroyView() {
        try {
            OkGo.getInstance().cancelTag(ApiUtil.EXCHANGE_RECORD_TAG);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public void showContentPage() {
        try {
            statusLayout.showContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showFailPage() {
        try {
            statusLayout.showFailed(retryListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refreshComplite() {
        try {
            ptrLayout.refreshComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadmoreCompliteSuccess(boolean hasMore) {
        try {
            ptrLayout.setLoadMoreEnable(hasMore);
            ptrLayout.loadMoreComplete(hasMore);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadmoreCompliteFail() {
        try {
            ptrLayout.setLoadMoreEnable(true);
            ptrLayout.loadMoreComplete(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getRecordDataSuccess(ExchangeRecordResultInfo data, int currentPage) {
        try {
            if (currentPage == 1) {
                list.clear();
            }
            list.addAll(data.getData());
            mAdapter.notifyDataSetChangedHF();
            page = currentPage;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private View.OnClickListener retryListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            presenter.getExchangeRecord(true, 1);
        }
    };
}
