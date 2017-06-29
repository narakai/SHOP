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

import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.iwgang.countdownview.CountdownView;
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
import wiki.scene.shop.widgets.RatioImageView;
import wiki.scene.shop.widgets.verticalrollingtextview.VerticalRollingTextView;

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
    IndianaHeaderView indianaHeaderView;

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
        indianaHeaderView = new IndianaHeaderView(headerView);
        mAdapter.addHeader(headerView);
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
        indianaHeaderView.bindNewestGoods();
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

        indianaHeaderView.textPopular1.setTextColor(getResources().getColor(R.color.text_color_title));
        indianaHeaderView.linePopular1.setBackgroundColor(getResources().getColor(R.color.transparent));
        indianaHeaderView.textNewest1.setTextColor(getResources().getColor(R.color.text_color_title));
        indianaHeaderView.lineNewest1.setBackgroundColor(getResources().getColor(R.color.transparent));
        indianaHeaderView.textFastest1.setTextColor(getResources().getColor(R.color.text_color_title));
        indianaHeaderView.lineFastest1.setBackgroundColor(getResources().getColor(R.color.transparent));
        indianaHeaderView.textPrice1.setTextColor(getResources().getColor(R.color.text_color_title));
        indianaHeaderView.linePrice1.setBackgroundColor(getResources().getColor(R.color.transparent));
        switch (choosedPosition) {
            case TITLE_BAR_popular:
                textPopular.setTextColor(getResources().getColor(R.color.colorPrimary));
                linePopular.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                indianaHeaderView.textPopular1.setTextColor(getResources().getColor(R.color.colorPrimary));
                indianaHeaderView.linePopular1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            case TITLE_BAR_NEWEST:
                textNewest.setTextColor(getResources().getColor(R.color.colorPrimary));
                lineNewest.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                indianaHeaderView.textNewest1.setTextColor(getResources().getColor(R.color.colorPrimary));
                indianaHeaderView.lineNewest1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            case TITLE_BAR_FASTEST:
                textFastest.setTextColor(getResources().getColor(R.color.colorPrimary));
                lineFastest.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                indianaHeaderView.textFastest1.setTextColor(getResources().getColor(R.color.colorPrimary));
                indianaHeaderView.lineFastest1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            case TITLE_BAR_PRICE:
                textPrice.setTextColor(getResources().getColor(R.color.colorPrimary));
                linePrice.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                indianaHeaderView.textPrice1.setTextColor(getResources().getColor(R.color.colorPrimary));
                indianaHeaderView.linePrice1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
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

    public class IndianaHeaderView {
        @BindView(R.id.banner)
        Banner banner;
        @BindView(R.id.indiana_price_10)
        TextView indianaPrice10;
        @BindView(R.id.indiana_fast)
        TextView indianaFast;
        @BindView(R.id.indiana_red88)
        TextView indianaRed88;
        @BindView(R.id.invite_money)
        TextView inviteMoney;
        @BindView(R.id.notice_textView)
        VerticalRollingTextView noticeTextView;
        @BindView(R.id.newest_title_image_text)
        TextView newestTitleImageText;
        @BindView(R.id.more_newest)
        TextView moreNewest;
        @BindView(R.id.newest_goods_image1)
        RatioImageView newestGoodsImage1;
        @BindView(R.id.newest_goods_name1)
        TextView newestGoodsName1;
        @BindView(R.id.text1)
        TextView text1;
        @BindView(R.id.newest_countDownView1)
        CountdownView newestCountDownView1;
        @BindView(R.id.newest_goods1)
        LinearLayout newestGoods1;
        @BindView(R.id.newest_goods_image2)
        RatioImageView newestGoodsImage2;
        @BindView(R.id.newest_goods_name2)
        TextView newestGoodsName2;
        @BindView(R.id.text2)
        TextView text2;
        @BindView(R.id.newest_countDownView2)
        CountdownView newestCountDownView2;
        @BindView(R.id.newest_goods2)
        LinearLayout newestGoods2;
        @BindView(R.id.newest_goods_image3)
        RatioImageView newestGoodsImage3;
        @BindView(R.id.newest_goods_name3)
        TextView newestGoodsName3;
        @BindView(R.id.text3)
        TextView text3;
        @BindView(R.id.newest_countDownView3)
        CountdownView newestCountDownView3;
        @BindView(R.id.newest_goods3)
        LinearLayout newestGoods3;
        @BindView(R.id.text_popular1)
        TextView textPopular1;
        @BindView(R.id.line_popular1)
        View linePopular1;
        @BindView(R.id.layout_popular1)
        RelativeLayout layoutPopular1;
        @BindView(R.id.text_newest1)
        TextView textNewest1;
        @BindView(R.id.line_newest1)
        View lineNewest1;
        @BindView(R.id.layout_newest1)
        RelativeLayout layoutNewest1;
        @BindView(R.id.text_fastest1)
        TextView textFastest1;
        @BindView(R.id.line_fastest1)
        View lineFastest1;
        @BindView(R.id.layout_fastest1)
        RelativeLayout layoutFastest1;
        @BindView(R.id.text_price1)
        TextView textPrice1;
        @BindView(R.id.line_price1)
        View linePrice1;
        @BindView(R.id.layout_price1)
        RelativeLayout layoutPrice1;
        @BindView(R.id.title_bar1)
        LinearLayout titleBar1;

        IndianaHeaderView(View view) {
            ButterKnife.bind(this, view);
            layoutPopular1.setOnClickListener(onClickListener);
            layoutNewest1.setOnClickListener(onClickListener);
            layoutFastest1.setOnClickListener(onClickListener);
            layoutPrice1.setOnClickListener(onClickListener);
        }

        void bindNewestGoods() {
            newestGoodsName1.setText("最新揭晓1");
            newestGoodsName2.setText("最新揭晓2");
            newestGoodsName3.setText("最新揭晓3");
            newestGoodsName1.setTag(R.id.newest_countDownView1);
            newestGoodsName2.setTag(R.id.newest_countDownView2);
            newestGoodsName3.setTag(R.id.newest_countDownView3);
            newestCountDownView1.start(10 * 1000);
            newestCountDownView2.start(20 * 1000);
            newestCountDownView3.start(30 * 1000);

        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
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
    };
}
