package wiki.scene.shop.ui.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import wiki.scene.loadmore.recyclerview.RecyclerAdapterWithHF;
import wiki.scene.loadmore.utils.PtrLocalDisplay;
import wiki.scene.shop.R;
import wiki.scene.shop.adapter.IndianaRecordAdapter;
import wiki.scene.shop.itemDecoration.SpacesItemDecoration;
import wiki.scene.shop.mvp.BaseMvpFragment;
import wiki.scene.shop.ui.mine.mvpview.IIndianaRecordTypeView;
import wiki.scene.shop.ui.mine.presenter.IndianaRecordTypePresenter;

/**
 * Case By:夺宝记录
 * package:wiki.scene.shop.ui.mine
 * Author：scene on 2017/7/5 13:49
 */

public class IndianaRecordTypeFragment extends BaseMvpFragment<IIndianaRecordTypeView, IndianaRecordTypePresenter> implements IIndianaRecordTypeView {
    private final static String ARG_INDIANA_RECORD_TYPE = "arg_indiana_record_type";
    public final static int INDIANA_RECORD_TYPE_ALL = 0;
    public final static int INDIANA_RECORD_TYPE_ONGOING = 1;
    public final static int INDIANA_RECORD_TYPE_ANNOUNDCED = 2;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptrLayout)
    PtrClassicFrameLayout ptrLayout;
    @BindView(R.id.status_layout)
    StatusViewLayout statusLayout;
    Unbinder unbinder;

    private int type = INDIANA_RECORD_TYPE_ALL;
    //adapter
    private List<String> list = new ArrayList<>();
    private IndianaRecordAdapter adapter;
    private RecyclerAdapterWithHF mAdapter;

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
    }

    private void initView() {
        showContent();
        ptrLayout.setLastUpdateTimeRelateObject(this);
        ptrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

            }
        });

        for (int i = 0; i < 10; i++) {
            list.add("商品" + i);
        }
        adapter = new IndianaRecordAdapter(_mActivity, list);
        mAdapter = new RecyclerAdapterWithHF(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        recyclerView.addItemDecoration(new SpacesItemDecoration(PtrLocalDisplay.dp2px(1)));
        recyclerView.setAdapter(mAdapter);
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

    }

    @Override
    public void showNoNetwork() {

    }

    @Override
    public IndianaRecordTypePresenter initPresenter() {
        return new IndianaRecordTypePresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
