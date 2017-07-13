package wiki.scene.shop.ui.indiana;

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
import com.youth.banner.BannerConfig;

import org.greenrobot.eventbus.EventBus;

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
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.adapter.IndianaAdapter;
import wiki.scene.shop.entity.IndianaIndexInfo;
import wiki.scene.shop.entity.ListGoodsInfo;
import wiki.scene.shop.entity.SliderInfo;
import wiki.scene.shop.entity.WinningNoticeInfo;
import wiki.scene.shop.event.StartBrotherEvent;
import wiki.scene.shop.itemDecoration.IndianaItemDecoration;
import wiki.scene.shop.mvp.BaseMainMvpFragment;
import wiki.scene.shop.ui.indiana.mvpview.IIndianaView;
import wiki.scene.shop.ui.indiana.presenter.IndianaPresenter;
import wiki.scene.shop.utils.GlideImageLoader;
import wiki.scene.shop.utils.ToastUtils;
import wiki.scene.shop.widgets.RatioImageView;
import wiki.scene.shop.widgets.VerticalTextview;

/**
 * Case By:
 * package:wiki.scene.shop.fragment.indiana
 * Author：scene on 2017/6/28 17:36
 */

public class IndianaFragment extends BaseMainMvpFragment<IIndianaView, IndianaPresenter> implements IIndianaView, View.OnClickListener {
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
    private List<ListGoodsInfo> list = new ArrayList<>();
    private IndianaAdapter adapter;
    //滚动距离
    private int totalDy = 0;

    private int currentChoosedPosition = TITLE_BAR_popular;
    private boolean priceUp2Down = true;
    //数据
    private IndianaIndexInfo indianaIndexInfo;

    public static IndianaFragment newInstance() {
        IndianaFragment fragment = new IndianaFragment();
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
    }


    private void initView() {
        presenter.getIndianaData(false);
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
        RecyclerAdapterWithHF mAdapter = new RecyclerAdapterWithHF(adapter);
        View headerView = LayoutInflater.from(_mActivity).inflate(R.layout.fragment_indiana_header_view, null);
        indianaHeaderView = new IndianaHeaderView(headerView);
        mAdapter.addHeader(headerView);
        recyclerView.setAdapter(mAdapter);

        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        headerView.measure(width, height);
        titleBar.measure(width, height);

        final int headerViewHeight = headerView.getMeasuredHeight();
        ptrLayout.setLastUpdateTimeRelateObject(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalDy += dy;
                if (totalDy >= headerViewHeight + PtrLocalDisplay.dp2px(31)) {
                    titleBar.setVisibility(View.VISIBLE);
                } else {
                    titleBar.setVisibility(View.GONE);
                }
            }
        });
        ptrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.getIndianaData(true);
            }
        });
        indianaHeaderView.bindNewestGoods();
        mAdapter.setOnItemClickListener(new RecyclerAdapterWithHF.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerAdapterWithHF adapter, RecyclerView.ViewHolder vh, int position) {
                EventBus.getDefault().post(new StartBrotherEvent(GoodsDetailFragment.newInstance(list.get(position).getId())));
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_popular1:
                presenter.setChoosedTitleBar(TITLE_BAR_popular, currentChoosedPosition);
                break;
            case R.id.layout_newest1:
                presenter.setChoosedTitleBar(TITLE_BAR_NEWEST, currentChoosedPosition);
                break;
            case R.id.layout_fastest1:
                presenter.setChoosedTitleBar(TITLE_BAR_FASTEST, currentChoosedPosition);
                break;
            case R.id.layout_price1:
                presenter.setChoosedTitleBar(TITLE_BAR_PRICE, currentChoosedPosition);
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
                presenter.setChoosedTitleBar(TITLE_BAR_popular, currentChoosedPosition);
                break;
            case R.id.layout_newest:
                presenter.setChoosedTitleBar(TITLE_BAR_NEWEST, currentChoosedPosition);
                break;
            case R.id.layout_fastest:
                presenter.setChoosedTitleBar(TITLE_BAR_FASTEST, currentChoosedPosition);
                break;
            case R.id.layout_price:
                presenter.setChoosedTitleBar(TITLE_BAR_PRICE, currentChoosedPosition);
                break;
        }
    }

    @Override
    public void setTitlebarChoosed(int choosedPosition, int oldChoosedPosition) {
        if (choosedPosition == oldChoosedPosition && choosedPosition != TITLE_BAR_PRICE) {
            return;
        }
        if (choosedPosition == oldChoosedPosition) {
            if (priceUp2Down) {
                textPrice.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_price_up), null);
                indianaHeaderView.textPrice1.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_price_up), null);
            } else {
                textPrice.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_price_down), null);
                indianaHeaderView.textPrice1.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_price_down), null);
            }
            priceUp2Down = !priceUp2Down;
            return;
        }
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
        currentChoosedPosition = choosedPosition;
        getDataSuccess(indianaIndexInfo, false);
    }

    @Override
    public void getDataSuccess(IndianaIndexInfo indexInfo, boolean isRefresh) {
        indianaIndexInfo = indexInfo;
        if (isRefresh) {
            ptrLayout.refreshComplete();
        }
        list.clear();
        if (currentChoosedPosition == TITLE_BAR_popular) {
            list.addAll(indexInfo.getIn_progress().getHot());
        } else if (currentChoosedPosition == TITLE_BAR_NEWEST) {
            list.addAll(indexInfo.getIn_progress().getNewest());
        } else if (currentChoosedPosition == TITLE_BAR_FASTEST) {
            list.addAll(indexInfo.getIn_progress().getSource());
        } else if (currentChoosedPosition == TITLE_BAR_PRICE) {
            if (priceUp2Down) {
                list.addAll(indexInfo.getIn_progress().getPrice_desc());
            } else {
                list.addAll(indexInfo.getIn_progress().getPrice_asc());
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String msg) {
        ToastUtils.getInstance(_mActivity).showToast(msg);
    }

    @Override
    public void showFailPage() {
        statusLayout.showFailed(retryListener);
    }

    @Override
    public void bindBannerData(List<SliderInfo> bannerList) {
        indianaHeaderView.bindBanner(bannerList);
    }

    @Override
    public void bindWinnerNotice(List<WinningNoticeInfo> noticeInfoList) {
        if (noticeInfoList != null && noticeInfoList.size() > 0)
            indianaHeaderView.bindIndianaNotice(noticeInfoList);
    }


    private View.OnClickListener retryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.getIndianaData(false);
        }
    };

    @Override
    public IndianaPresenter initPresenter() {
        return new IndianaPresenter(this);
    }

    @Override
    public void onDestroyView() {
        if (indianaHeaderView != null && indianaHeaderView.noticeTextView != null) {
            indianaHeaderView.noticeTextView.stopAutoScroll();
        }
        super.onDestroyView();
    }

    class IndianaHeaderView {
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
        VerticalTextview noticeTextView;
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
        List<String> bannerImageUrls = new ArrayList<>();
        List<String> bannerTitles = new ArrayList<>();

        IndianaHeaderView(View view) {
            ButterKnife.bind(this, view);
            layoutPopular1.setOnClickListener(onClickListener);
            layoutNewest1.setOnClickListener(onClickListener);
            layoutFastest1.setOnClickListener(onClickListener);
            layoutPrice1.setOnClickListener(onClickListener);
            //banner
            banner.setImageLoader(new GlideImageLoader());
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
            banner.setDelayTime(2000);
            banner.setBannerTitles(bannerTitles);
            banner.setImages(bannerImageUrls);
            banner.start();
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

        void bindBanner(List<SliderInfo> bannerList) {
            bannerImageUrls.clear();
            bannerTitles.clear();
            for (SliderInfo info : bannerList) {
                bannerImageUrls.add(ShopApplication.configInfo.getFile_domain() + info.getSrc());
                bannerTitles.add(info.getTitle());
            }
            banner.setImages(bannerImageUrls);
            banner.setBannerTitles(bannerTitles);
            banner.start();
        }

        void bindIndianaNotice(List<WinningNoticeInfo> list) {
            List<String> stringList = new ArrayList<>();
            for (WinningNoticeInfo info : list) {
                String str = "恭喜<font color='#2FACFF'>" + info.getNickname() + "</font>花费" +
                        "<font color='#2FACFF'>" + info.getCost() + "元</font>" + "夺得<font color='#2FACFF'>" + info.getProduct_name() + "</font>";
                stringList.add(str);
            }
            noticeTextView.setTextList(stringList);//加入显示内容,集合类型
            noticeTextView.setText(11, 0, getResources().getColor(R.color.text_color_des));//设置属性,具体跟踪源码
            noticeTextView.setTextStillTime(5000);//设置停留时长间隔
            noticeTextView.setAnimTime(300);//设置进入和退出的时间间隔
            noticeTextView.startAutoScroll();
        }

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.layout_popular1:
                    presenter.setChoosedTitleBar(TITLE_BAR_popular, currentChoosedPosition);
                    break;
                case R.id.layout_newest1:
                    presenter.setChoosedTitleBar(TITLE_BAR_NEWEST, currentChoosedPosition);
                    break;
                case R.id.layout_fastest1:
                    presenter.setChoosedTitleBar(TITLE_BAR_FASTEST, currentChoosedPosition);
                    break;
                case R.id.layout_price1:
                    presenter.setChoosedTitleBar(TITLE_BAR_PRICE, currentChoosedPosition);
                    break;
            }
        }
    };


}
