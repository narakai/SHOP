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

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wiki.scene.loadmore.PtrClassicFrameLayout;
import wiki.scene.loadmore.PtrDefaultHandler;
import wiki.scene.loadmore.PtrFrameLayout;
import wiki.scene.loadmore.StatusViewLayout;
import wiki.scene.loadmore.utils.PtrLocalDisplay;
import wiki.scene.shop.MainFragment;
import wiki.scene.shop.R;
import wiki.scene.shop.adapter.MineRedAdapter;
import wiki.scene.shop.entity.CreateOrderInfo;
import wiki.scene.shop.event.TabSelectedEvent;
import wiki.scene.shop.itemDecoration.SpacesItemDecoration;
import wiki.scene.shop.mvp.BaseBackMvpFragment;
import wiki.scene.shop.ui.mine.mvpview.IMineRedView;
import wiki.scene.shop.ui.mine.presenter.MineRedPresenter;

/**
 * Case By:我的红包
 * package:wiki.scene.shop.ui.mine
 * Author：scene on 2017/6/30 13:30
 */

public class MineRedFragment extends BaseBackMvpFragment<IMineRedView, MineRedPresenter> implements IMineRedView {
    private static final String ARG_RED_LIST = "arg_red_list";
    private static final String ARG_IS_ENTER_FROM = "arg_is_enter_from";
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

    private Unbinder unbinder;

    private boolean isEnterFromMine = false;
    //adapter
    private ArrayList<CreateOrderInfo.CouponsBean> list = new ArrayList<>();
    private MineRedAdapter adapter;


    public static MineRedFragment newInstance(boolean isEnterFromMine, ArrayList<CreateOrderInfo.CouponsBean> list) {
        Bundle args = new Bundle();
        MineRedFragment fragment = new MineRedFragment();
        args.putSerializable(ARG_RED_LIST, list);
        args.putBoolean(ARG_IS_ENTER_FROM, isEnterFromMine);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            isEnterFromMine = getArguments().getBoolean(ARG_IS_ENTER_FROM);
            list = (ArrayList<CreateOrderInfo.CouponsBean>) getArguments().getSerializable(ARG_RED_LIST);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine_red, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        toolbarTitle.setText(R.string.mine_red);
        initToolbarNav(toolbar);
        showContent();
        initView();
    }

    private void initView() {
        if (list != null) {
            ptrLayout.setLoadMoreEnable(false);
            ptrLayout.setPullToRefresh(false);
        }
        ptrLayout.setLastUpdateTimeRelateObject(this);
        ptrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return super.checkCanDoRefresh(frame, content, header) && isEnterFromMine;
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

            }
        });
        adapter = new MineRedAdapter(_mActivity, list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(PtrLocalDisplay.dp2px(1)));
        recyclerView.setAdapter(adapter);
        adapter.setOnMineRedItemClickListener(new MineRedAdapter.OnMineRedItemClickListener() {
            @Override
            public void onMineRedItemClick(int position) {
                if (isEnterFromMine) {
                    EventBus.getDefault().post(new TabSelectedEvent(MainFragment.FIRST));
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("red", list.get(position));
                    setFragmentResult(RESULT_OK, bundle);
                }
                _mActivity.onBackPressed();
            }
        });
    }

    @Override
    public void showLoading() {
        statusLayout.showLoading();
    }

    @Override
    public void showContent() {
        statusLayout.showContent();
    }

    @Override
    public void showFail() {
        statusLayout.showFailed(retryListener);
    }

    @Override
    public void showNoNetwork() {
        statusLayout.showNetError(retryListener);
    }

    @Override
    public MineRedPresenter initPresenter() {
        return new MineRedPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    View.OnClickListener retryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
}
