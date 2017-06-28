package wiki.scene.shop.fragment.indiana;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wiki.scene.loadmore.PtrClassicFrameLayout;
import wiki.scene.loadmore.PtrDefaultHandler;
import wiki.scene.loadmore.PtrFrameLayout;
import wiki.scene.loadmore.StatusViewLayout;
import wiki.scene.loadmore.recyclerview.RecyclerAdapterWithHF;
import wiki.scene.loadmore.utils.PtrLocalDisplay;
import wiki.scene.shop.R;
import wiki.scene.shop.adapter.IndianaAdapter;
import wiki.scene.shop.fragment.indiana.mvpview.IIndianaView;
import wiki.scene.shop.fragment.indiana.presenter.IndianaPresenter;
import wiki.scene.shop.itemDecoration.IndianaItemDecoration;
import wiki.scene.shop.mvp.BaseMainMvpFragment;

/**
 * Case By:
 * package:wiki.scene.shop.fragment.indiana
 * Author：scene on 2017/6/28 17:36
 */

public class Indiana2Fragment extends BaseMainMvpFragment<IIndianaView, IndianaPresenter> implements IIndianaView, View.OnClickListener {
    private final static int TITLE_BAR_popular = 0;
    private final static int TITLE_BAR_NEWEST = 1;
    private final static int TITLE_BAR_FASTEST = 2;
    private final static int TITLE_BAR_PRICE = 3;
    @BindView(R.id.status_layout)
    StatusViewLayout statusLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.title_bar)
    LinearLayout titleBar;
    @BindView(R.id.ptrLayout)
    PtrClassicFrameLayout ptrLayout;
    @BindView(R.id.text_popular)
    TextView textPopular;
    @BindView(R.id.line_popular)
    View linePopular;
    @BindView(R.id.layout_popular)
    RelativeLayout layoutPopular;
    @BindView(R.id.text_newest)
    TextView textNewest;
    @BindView(R.id.line_newest)
    View lineNewest;
    @BindView(R.id.layout_newest)
    RelativeLayout layoutNewest;
    @BindView(R.id.text_fastest)
    TextView textFastest;
    @BindView(R.id.line_fastest)
    View lineFastest;
    @BindView(R.id.layout_fastest)
    RelativeLayout layoutFastest;
    @BindView(R.id.text_price)
    TextView textPrice;
    @BindView(R.id.line_price)
    View linePrice;
    @BindView(R.id.layout_price)
    RelativeLayout layoutPrice;
    //headerView
    TextView textPopular1;
    View linePopular1;
    RelativeLayout layoutPopular1;
    TextView textNewest1;
    View lineNewest1;
    RelativeLayout layoutNewest1;
    TextView textFastest1;
    View lineFastest1;
    RelativeLayout layoutFastest1;
    TextView textPrice1;
    View linePrice1;
    RelativeLayout layoutPrice1;
    //adapter
    private List<String> list = new ArrayList<>();
    private RecyclerAdapterWithHF mAdapter;
    private IndianaAdapter adapter;
    //滚动距离
    private int totalDy = 0;

    public static Indiana2Fragment newInstance() {
        Indiana2Fragment fragment = new Indiana2Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_indiana2, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initView();
        hideLoading();
    }


    private void initView() {
        for (int i = 0; i < 30; i++) {
            list.add("数据：" + (i + 1));
        }

        GridLayoutManager layoutManager = new GridLayoutManager(_mActivity, 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new IndianaItemDecoration());
        adapter = new IndianaAdapter(_mActivity, list);
        mAdapter = new RecyclerAdapterWithHF(adapter);
        View headerView = LayoutInflater.from(_mActivity).inflate(R.layout.fragment_indiana_header_view, null);
        mAdapter.addHeader(headerView);
        initHeaderView(headerView);
        recyclerView.setAdapter(mAdapter);

        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        headerView.measure(width, height);
        final int headerViewHeight = headerView.getMeasuredHeight();
        ptrLayout.setLastUpdateTimeRelateObject(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalDy += dy;
                if (totalDy >= headerViewHeight - PtrLocalDisplay.dp2px(41)) {
                    titleBar.setVisibility(View.VISIBLE);
                } else {
                    titleBar.setVisibility(View.GONE);
                }
            }
        });
        ptrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return super.checkCanDoRefresh(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

            }
        });
    }

    private void initHeaderView(View headerView) {
        textPopular1 = (TextView) headerView.findViewById(R.id.text_popular1);
        textNewest1 = (TextView) headerView.findViewById(R.id.text_newest1);
        textFastest1 = (TextView) headerView.findViewById(R.id.text_fastest1);
        textPrice1 = (TextView) headerView.findViewById(R.id.text_price1);
        linePopular1 = headerView.findViewById(R.id.line_popular1);
        lineNewest1 = headerView.findViewById(R.id.line_newest1);
        lineFastest1 = headerView.findViewById(R.id.line_fastest1);
        linePrice1 = headerView.findViewById(R.id.line_price1);
        layoutPopular1 = (RelativeLayout) headerView.findViewById(R.id.layout_popular1);
        layoutNewest1 = (RelativeLayout) headerView.findViewById(R.id.layout_newest1);
        layoutFastest1 = (RelativeLayout) headerView.findViewById(R.id.layout_fastest1);
        layoutPrice1 = (RelativeLayout) headerView.findViewById(R.id.layout_price1);

        layoutPopular1.setOnClickListener(this);
        layoutNewest1.setOnClickListener(this);
        layoutFastest1.setOnClickListener(this);
        layoutPrice1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_popular1:
                presenter.setChoosedTitleBar(TITLE_BAR_popular);
                break;
            case R.id.layout_newest1:
                presenter.setChoosedTitleBar(TITLE_BAR_NEWEST);
                break;
            case R.id.layout_fastest1:
                presenter.setChoosedTitleBar(TITLE_BAR_FASTEST);
                break;
            case R.id.layout_price1:
                presenter.setChoosedTitleBar(TITLE_BAR_PRICE);
                break;
        }
    }

    @Override
    public void showLoading() {
        statusLayout.showLoading();
    }

    @Override
    public void hideLoading() {
        statusLayout.showContent();
    }

    @OnClick({R.id.layout_popular, R.id.layout_newest, R.id.layout_fastest, R.id.layout_price})
    public void onClickTitleBarItem(View view) {
        switch (view.getId()) {
            case R.id.layout_popular:
                presenter.setChoosedTitleBar(TITLE_BAR_popular);
                break;
            case R.id.layout_newest:
                presenter.setChoosedTitleBar(TITLE_BAR_NEWEST);
                break;
            case R.id.layout_fastest:
                presenter.setChoosedTitleBar(TITLE_BAR_FASTEST);
                break;
            case R.id.layout_price:
                presenter.setChoosedTitleBar(TITLE_BAR_PRICE);
                break;
        }
    }

    @Override
    public void setTitlebarChoosed(int choosedPosition) {
        textPopular.setTextColor(getResources().getColor(R.color.text_color_title));
        linePopular.setBackgroundColor(getResources().getColor(R.color.transparent));
        textNewest.setTextColor(getResources().getColor(R.color.text_color_title));
        lineNewest.setBackgroundColor(getResources().getColor(R.color.transparent));
        textFastest.setTextColor(getResources().getColor(R.color.text_color_title));
        lineFastest.setBackgroundColor(getResources().getColor(R.color.transparent));
        textPrice.setTextColor(getResources().getColor(R.color.text_color_title));
        linePrice.setBackgroundColor(getResources().getColor(R.color.transparent));

        textPopular1.setTextColor(getResources().getColor(R.color.text_color_title));
        linePopular1.setBackgroundColor(getResources().getColor(R.color.transparent));
        textNewest1.setTextColor(getResources().getColor(R.color.text_color_title));
        lineNewest1.setBackgroundColor(getResources().getColor(R.color.transparent));
        textFastest1.setTextColor(getResources().getColor(R.color.text_color_title));
        lineFastest1.setBackgroundColor(getResources().getColor(R.color.transparent));
        textPrice1.setTextColor(getResources().getColor(R.color.text_color_title));
        linePrice1.setBackgroundColor(getResources().getColor(R.color.transparent));
        switch (choosedPosition) {
            case TITLE_BAR_popular:
                textPopular.setTextColor(getResources().getColor(R.color.colorPrimary));
                linePopular.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                textPopular1.setTextColor(getResources().getColor(R.color.colorPrimary));
                linePopular1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            case TITLE_BAR_NEWEST:
                textNewest.setTextColor(getResources().getColor(R.color.colorPrimary));
                lineNewest.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                textNewest1.setTextColor(getResources().getColor(R.color.colorPrimary));
                lineNewest1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            case TITLE_BAR_FASTEST:
                textFastest.setTextColor(getResources().getColor(R.color.colorPrimary));
                lineFastest.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                textFastest1.setTextColor(getResources().getColor(R.color.colorPrimary));
                lineFastest1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            case TITLE_BAR_PRICE:
                textPrice.setTextColor(getResources().getColor(R.color.colorPrimary));
                linePrice.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                textPrice1.setTextColor(getResources().getColor(R.color.colorPrimary));
                linePrice1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
        }
    }

    @Override
    public IndianaPresenter initPresenter() {
        return new IndianaPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
