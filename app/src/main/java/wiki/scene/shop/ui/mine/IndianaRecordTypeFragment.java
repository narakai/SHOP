package wiki.scene.shop.ui.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import wiki.scene.shop.MainActivity;
import wiki.scene.shop.MainFragment;
import wiki.scene.shop.R;
import wiki.scene.shop.adapter.IndianaRecordAdapter;
import wiki.scene.shop.config.AppConfig;
import wiki.scene.shop.entity.CreateOrderInfo;
import wiki.scene.shop.entity.MineOrderInfo;
import wiki.scene.shop.entity.MineOrderResultInfo;
import wiki.scene.shop.entity.ResultPageInfo;
import wiki.scene.shop.event.TabSelectedEvent;
import wiki.scene.shop.event.ToIndexPageEvent;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.itemDecoration.SpacesItemDecoration;
import wiki.scene.shop.mvp.BaseBackMvpFragment;
import wiki.scene.shop.mvp.BaseMvpFragment;
import wiki.scene.shop.ui.car.PayOrderFragment;
import wiki.scene.shop.ui.indiana.IndianaFragment;
import wiki.scene.shop.ui.mine.mvpview.IIndianaRecordTypeView;
import wiki.scene.shop.ui.mine.presenter.IndianaRecordTypePresenter;
import wiki.scene.shop.utils.ToastUtils;
import wiki.scene.shop.widgets.LoadingDialog;

/**
 * Case By:夺宝记录
 * package:wiki.scene.shop.ui.mine
 * Author：scene on 2017/7/5 13:49
 */

public class IndianaRecordTypeFragment extends BaseMvpFragment<IIndianaRecordTypeView, IndianaRecordTypePresenter> implements IIndianaRecordTypeView {
    private final static String ARG_INDIANA_RECORD_TYPE = "arg_indiana_record_type";
    public final static int INDIANA_RECORD_TYPE_ALL = 0;
    public final static int INDIANA_RECORD_TYPE_WIN = 1;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptrLayout)
    PtrClassicFrameLayout ptrLayout;
    @BindView(R.id.status_layout)
    StatusViewLayout statusLayout;
    Unbinder unbinder;

    private int page = 1;
    //adapter
    private List<MineOrderInfo> list = new ArrayList<>();
    private IndianaRecordAdapter adapter;

    private LoadingDialog loadingDialog;
    private int type = INDIANA_RECORD_TYPE_ALL;

    public static IndianaRecordTypeFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt(ARG_INDIANA_RECORD_TYPE, type);
        IndianaRecordTypeFragment fragment = new IndianaRecordTypeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            type = args.getInt(ARG_INDIANA_RECORD_TYPE, INDIANA_RECORD_TYPE_ALL);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_indiana_record_type, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        initView();
        if (type == INDIANA_RECORD_TYPE_ALL) {
            presenter.getIndianaRecordData(page, true);
        } else {
            presenter.getWinIndianaRecordData(page, true);
        }
    }

    private void initView() {

        ptrLayout.setLastUpdateTimeRelateObject(this);

        ptrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                if (type == INDIANA_RECORD_TYPE_ALL) {
                    presenter.getIndianaRecordData(1, false);
                } else {
                    presenter.getWinIndianaRecordData(1, false);
                }
            }
        });
        ptrLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                if (type == INDIANA_RECORD_TYPE_ALL) {
                    presenter.getIndianaRecordData(page + 1, false);
                } else {
                    presenter.getWinIndianaRecordData(page + 1, false);
                }
            }
        });

        adapter = new IndianaRecordAdapter(_mActivity, list);
        RecyclerAdapterWithHF mAdapter = new RecyclerAdapterWithHF(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        recyclerView.addItemDecoration(new SpacesItemDecoration(PtrLocalDisplay.dp2px(10), true, false));
        recyclerView.setAdapter(mAdapter);
        ptrLayout.setLoadMoreEnable(true);
        ptrLayout.setNoMoreData();
        adapter.setIndianaRecordItemButtonClickListener(new IndianaRecordAdapter.IndianaRecordItemButtonClickListener() {
            @Override
            public void onClickGoonIndiana() {
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
    public IndianaRecordTypePresenter initPresenter() {
        return new IndianaRecordTypePresenter(this);
    }

    @Override
    public void onDestroyView() {
        try {
            OkGo.getInstance().cancelTag(ApiUtil.MINE_ORDER_TAG);
            OkGo.getInstance().cancelTag(ApiUtil.WIN_RECORD_TAG);
            adapter.cancelAllTimers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroyView();
        unbinder.unbind();
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
    public void refreshComplete() {
        try {
            ptrLayout.refreshComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getDataSuccess(MineOrderResultInfo resultInfo) {
        try {
            ResultPageInfo resultPageInfo = resultInfo.getInfo();
            if (page == 1) {
                list.clear();
            }
            list.addAll(resultInfo.getData());
            adapter.notifyDataSetChanged();
            ptrLayout.loadMoreComplete(resultPageInfo.getPage_total() > page);
            ptrLayout.setLoadMoreEnable(resultPageInfo.getPage_total() > page);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showProgressDialog(@StringRes int resId) {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog.getInstance(_mActivity);
        }
        loadingDialog.showLoadingDialog(getString(resId));
    }

    @Override
    public void hideProgessDialog() {
        if (loadingDialog != null) {
            loadingDialog.cancelLoadingDialog();
        }
    }

    @Override
    public void toPaySuccess(CreateOrderInfo createOrderInfo) {
        if (getParentFragment() instanceof BaseBackMvpFragment) {
            ((BaseBackMvpFragment) getParentFragment()).startForResult(PayOrderFragment.newInstance(createOrderInfo), AppConfig.ORDER_DETAIL_TO_PAY_REQUEST_CODE);
        }
    }

    @Override
    public void changePage(int page) {
        this.page = page;
    }

    @Override
    public void loadmoreFail() {
        try {
            ptrLayout.loadFail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener retryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (type == INDIANA_RECORD_TYPE_ALL) {
                presenter.getIndianaRecordData(page, true);
            } else {
                presenter.getWinIndianaRecordData(page, true);
            }
        }
    };

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
    }
}
