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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wiki.scene.loadmore.StatusViewLayout;
import wiki.scene.loadmore.recyclerview.RecyclerAdapterWithHF;
import wiki.scene.loadmore.utils.PtrLocalDisplay;
import wiki.scene.shop.R;
import wiki.scene.shop.adapter.PkAdapter;
import wiki.scene.shop.config.AppConfig;
import wiki.scene.shop.entity.PkMineInfo;
import wiki.scene.shop.entity.PkResultInfo;
import wiki.scene.shop.itemDecoration.SpacesItemDecoration;
import wiki.scene.shop.mvp.BaseBackMvpFragment;
import wiki.scene.shop.ui.mine.mvpview.IOrderPkDetailView;
import wiki.scene.shop.ui.mine.presenter.OrderPkDetailPresenter;
import wiki.scene.shop.utils.UpdatePageUtils;

/**
 * pk详情
 * Created by scene on 2017/11/22.
 */

public class OrderPkDetailFragment extends BaseBackMvpFragment<IOrderPkDetailView, OrderPkDetailPresenter> implements IOrderPkDetailView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.status_layout)
    StatusViewLayout statusLayout;
    Unbinder unbinder;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private int orderId = 0;

    private RecyclerAdapterWithHF mAdapter;

    public static OrderPkDetailFragment newInstance(int orderId) {
        Bundle args = new Bundle();
        args.putInt("id", orderId);
        OrderPkDetailFragment fragment = new OrderPkDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            orderId = getArguments().getInt("id");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_pk_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        toolbarTitle.setText("PK详情");
        initToolbarNav(toolbar);
        initView();
        presenter.getPkDetailInfo(orderId);
        UpdatePageUtils.updatePagePosition(AppConfig.POSITION_INDIANA_PK, orderId);
    }

    private void initView() {

    }

    @Override
    public void showLoading(int resId) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public OrderPkDetailPresenter initPresenter() {
        return new OrderPkDetailPresenter(this);
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
    public void getPkInfoSuccess(PkResultInfo info) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new SpacesItemDecoration(PtrLocalDisplay.dp2px(1)));
        PkMineInfo mineInfo = new PkMineInfo();
        mineInfo.setAvatar(info.getAvatar());
        mineInfo.setUsername(info.getNickname());
        mineInfo.setBuy_type(info.getBuy_type());
        PkAdapter adapter = new PkAdapter(getContext(), info.getPk(), mineInfo);
        mAdapter = new RecyclerAdapterWithHF(adapter);
        recyclerView.setAdapter(mAdapter);
        initHeaderView(info.getCycle(), info.getResult());
    }

    private void initHeaderView(String cycleCode, String resultCode) {
        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_order_pk_detail_header, null);
        TextView tvCycleCode = (TextView) headerView.findViewById(R.id.cycle_code);
        TextView tvResult = (TextView) headerView.findViewById(R.id.result);
        tvCycleCode.setText("开奖期号：" + cycleCode);
        char[] numbers = resultCode.toCharArray();
        String result1 = Integer.parseInt(String.valueOf(numbers[4])) > 4 ? "大" : "小";
        String result2 = Integer.parseInt(String.valueOf(numbers[4])) % 2 == 0 ? "双" : "单";
        String result3 = Integer.parseInt(String.valueOf(numbers[3])) > 4 ? "大" : "小";
        String result = result1 + "|" + result2 + "|" + result3 + result2;
        tvResult.setText("开奖结果：" + resultCode + "(" + result + ")");
        mAdapter.addHeader(headerView);
    }

    private View.OnClickListener retryListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            presenter.getPkDetailInfo(orderId);
        }
    };
}
