package wiki.scene.shop.ui.trend;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;

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
import wiki.scene.shop.adapter.TrendAdapter;
import wiki.scene.shop.entity.WinCodeInfo;
import wiki.scene.shop.itemDecoration.SpacesItemDecoration;
import wiki.scene.shop.mvp.BaseMainMvpFragment;
import wiki.scene.shop.ui.trend.presenter.TrendPresenter;
import wiki.scene.shop.ui.trend.view.ITrendView;

/**
 * 开奖走势
 * Created by scene on 2017/11/8.
 */

public class TrendFragment extends BaseMainMvpFragment<ITrendView, TrendPresenter> implements ITrendView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptrLayout)
    PtrClassicFrameLayout ptrLayout;
    @BindView(R.id.status_layout)
    StatusViewLayout statusLayout;
    Unbinder unbinder;

    private List<WinCodeInfo> list;
    private RecyclerAdapterWithHF mAdapter;
    private TextView countdownView;


    public static TrendFragment newInstance() {
        Bundle args = new Bundle();
        TrendFragment fragment = new TrendFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trend, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public TrendPresenter initPresenter() {
        return new TrendPresenter(this);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initView();
        presenter.getTrendData(true);
    }

    private void initView() {
        ptrLayout.setLastUpdateTimeRelateObject(this);
        ptrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.getTrendData(false);
            }
        });

        list = new ArrayList<>();
        TrendAdapter adapter = new TrendAdapter(getContext(), list);
        mAdapter = new RecyclerAdapterWithHF(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new SpacesItemDecoration(PtrLocalDisplay.dp2px(0.5f)));
        recyclerView.setAdapter(mAdapter);

        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_trend_header, null);
        countdownView = (TextView) headerView.findViewById(R.id.countdownView);
        mAdapter.addHeader(headerView);
    }


    @Override
    public void showLoading(int resId) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void getTrendDataSuccess(List<WinCodeInfo> dataList) {
        list.clear();
        list.addAll(dataList);
        mAdapter.notifyDataSetChangedHF();
    }

    @Override
    public void refreshComplete() {
        try {
            ptrLayout.refreshComplete();
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
    public void showMessage(String message) {
        try {
            ToastUtils.showShort(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        public void onClick(View view) {
            presenter.getTrendData(true);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
