package wiki.scene.shop.ui.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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
import wiki.scene.shop.adapter.BankAdapter;
import wiki.scene.shop.config.AppConfig;
import wiki.scene.shop.event.AddBankCardSuccessEvent;
import wiki.scene.shop.entity.BankInfo;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.itemDecoration.SpacesItemDecoration;
import wiki.scene.shop.mvp.BaseBackMvpFragment;
import wiki.scene.shop.ui.mine.mvpview.IBankListView;
import wiki.scene.shop.ui.mine.presenter.BankListPresenter;
import wiki.scene.shop.utils.UpdatePageUtils;

/**
 * 收款账号
 * Created by scene on 2017/11/14.
 */

public class BankListFragment extends BaseBackMvpFragment<IBankListView, BankListPresenter> implements IBankListView {
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

    private List<BankInfo> list = new ArrayList<>();
    private RecyclerAdapterWithHF mAdapter;

    public static BankListFragment newInstance() {
        Bundle args = new Bundle();
        BankListFragment fragment = new BankListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bank_list, container, false);
        EventBus.getDefault().register(this);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        toolbarTitle.setText("我的收款账号");
        initToolbarNav(toolbar);
        initView();
        presenter.getBankListData(true);
        UpdatePageUtils.updatePagePosition(AppConfig.POSITION_MINE_BANK_CARD, 0);
    }

    private void initView() {
        ptrLayout.setLastUpdateTimeRelateObject(this);
        ptrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.getBankListData(false);
            }
        });
        BankAdapter adapter = new BankAdapter(getContext(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new SpacesItemDecoration(PtrLocalDisplay.dp2px(10), true, false));
        mAdapter = new RecyclerAdapterWithHF(adapter);
        recyclerView.setAdapter(mAdapter);
        initFooter();
        mAdapter.setOnItemClickListener(new RecyclerAdapterWithHF.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerAdapterWithHF adapter, RecyclerView.ViewHolder vh, int position) {
                if (position < list.size()) {
                    if (list.get(position).getType() == AppConfig.BANK_TYPE_BANK_CARD) {
                        start(AddBankFragment.newInstance(list.get(position)));
                    } else {
                        start(AddAlipayFragment.newInstance(list.get(position)));
                    }
                }
            }
        });
    }

    private void initFooter() {
        View footerView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_bank_list_footer, null);
        LinearLayout addBankCard = (LinearLayout) footerView.findViewById(R.id.add_bank_card);
        LinearLayout addAlipay = (LinearLayout) footerView.findViewById(R.id.add_alipay);
        addBankCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start(AddBankFragment.newInstance(null));
            }
        });

        addAlipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start(AddAlipayFragment.newInstance(null));
            }
        });
        mAdapter.addFooter(footerView);
    }

    @Override
    public void showLoading(int resId) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public BankListPresenter initPresenter() {
        return new BankListPresenter(this);
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
    public void showContentPage() {
        try {
            statusLayout.showContent();
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

    @Override
    public void showMessage(String message) {
        ToastUtils.showShort(message);
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
    public void getBankListDataSuccess(List<BankInfo> list) {
        this.list.clear();
        this.list.addAll(list);
        mAdapter.notifyDataSetChangedHF();
    }

    private View.OnClickListener retryListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            presenter.getBankListData(true);
        }
    };

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        OkGo.getInstance().cancelTag(ApiUtil.BANK_LIST_TAG);
        super.onDestroyView();
        unbinder.unbind();
    }

    @Subscribe
    public void addBankCarSuccess(AddBankCardSuccessEvent addBankCardSuccess) {
        ptrLayout.autoRefresh();
    }
}
