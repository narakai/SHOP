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
import wiki.scene.shop.adapter.IndianaRecordAdapter;
import wiki.scene.shop.entity.MineOrderInfo;
import wiki.scene.shop.entity.MineOrderResultInfo;
import wiki.scene.shop.entity.ResultPageInfo;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.itemDecoration.SpacesItemDecoration;
import wiki.scene.shop.mvp.BaseMvpFragment;
import wiki.scene.shop.ui.mine.mvpview.IIndianaRecordTypeView;
import wiki.scene.shop.ui.mine.presenter.IndianaRecordTypePresenter;
import wiki.scene.shop.utils.ToastUtils;

/**
 * Case By:夺宝记录
 * package:wiki.scene.shop.ui.mine
 * Author：scene on 2017/7/5 13:49
 */

public class IndianaRecordTypeFragment extends BaseMvpFragment<IIndianaRecordTypeView, IndianaRecordTypePresenter> implements IIndianaRecordTypeView {
    private final static String ARG_INDIANA_RECORD_TYPE = "arg_indiana_record_type";
    public final static int INDIANA_RECORD_TYPE_ALL = 0;
    public final static int INDIANA_RECORD_TYPE_ONGOING = 1;
    public final static int INDIANA_RECORD_TYPE_ANNOUNDCED = 3;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptrLayout)
    PtrClassicFrameLayout ptrLayout;
    @BindView(R.id.status_layout)
    StatusViewLayout statusLayout;
    Unbinder unbinder;

    private int type = INDIANA_RECORD_TYPE_ALL;
    private int page = 1;
    //adapter
    private List<MineOrderInfo> list = new ArrayList<>();
    private IndianaRecordAdapter adapter;

    private ResultPageInfo resultPageInfo;

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
            type = args.getInt(ARG_INDIANA_RECORD_TYPE);
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
        presenter.getIndianaRecordData(type, page, true);
    }

    private void initView() {
        showContent();
        ptrLayout.setLastUpdateTimeRelateObject(this);

        ptrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                page = 1;
                presenter.getIndianaRecordData(type, page, false);
            }
        });
        ptrLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                page += 1;
                presenter.getIndianaRecordData(type, page, false);
            }
        });

        adapter = new IndianaRecordAdapter(_mActivity, list);
        RecyclerAdapterWithHF mAdapter = new RecyclerAdapterWithHF(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        recyclerView.addItemDecoration(new SpacesItemDecoration(PtrLocalDisplay.dp2px(1)));
        recyclerView.setAdapter(mAdapter);
        ptrLayout.setLoadMoreEnable(true);
        ptrLayout.setNoMoreData();
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
        OkGo.getInstance().cancelTag(ApiUtil.MINE_ORDER_TAG);
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
            resultPageInfo = resultInfo.getInfo();
            if (page == 1) {
                list.clear();
            }
            list.addAll(resultInfo.getData());
            adapter.notifyDataSetChanged();
            ptrLayout.loadMoreComplete(resultPageInfo.getPage_total() > page);
            if (list.size() == 0) {
                statusLayout.showNone();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener retryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.getIndianaRecordData(type, page, true);
        }
    };
}
