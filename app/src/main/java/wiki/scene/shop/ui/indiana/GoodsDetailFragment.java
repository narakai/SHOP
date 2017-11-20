package wiki.scene.shop.ui.indiana;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.sunfusheng.glideimageview.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wiki.scene.loadmore.PtrClassicFrameLayout;
import wiki.scene.loadmore.PtrDefaultHandler;
import wiki.scene.loadmore.PtrFrameLayout;
import wiki.scene.loadmore.StatusViewLayout;
import wiki.scene.shop.MainActivity;
import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.adapter.GoodsDetailBuyAdapter;
import wiki.scene.shop.adapter.GoodsDetailWinCodeAdapter;
import wiki.scene.shop.entity.CreateOrderInfo;
import wiki.scene.shop.entity.GoodsDetailInfo;
import wiki.scene.shop.entity.NewestWinInfo;
import wiki.scene.shop.entity.WinCodeInfo;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.mvp.BaseBackMvpFragment;
import wiki.scene.shop.ui.indiana.mvpview.IGoodsDetailView;
import wiki.scene.shop.ui.indiana.presenter.GoodsDetailPresenter;
import wiki.scene.shop.utils.DateFormatUtils;
import wiki.scene.shop.utils.PriceUtil;
import wiki.scene.shop.utils.ThreadPoolUtils;
import wiki.scene.shop.utils.ViewUtils;
import wiki.scene.shop.widgets.CustomListView;
import wiki.scene.shop.widgets.NoTouchListView;
import wiki.scene.shop.widgets.RatioImageView;

/**
 * Case By:
 * package:wiki.scene.shop.ui.indiana
 * Author：scene on 2017/7/4 11:40
 */

public class GoodsDetailFragment extends BaseBackMvpFragment<IGoodsDetailView, GoodsDetailPresenter> implements IGoodsDetailView {
    private static final String ARG_CYCLE_ID = "cycle_id";
    Unbinder unbinder;
    @BindView(R.id.goods_image)
    RatioImageView goodsImage;
    @BindView(R.id.cycle_code)
    TextView cycleCode;
    @BindView(R.id.goods_name)
    TextView goodsName;
    @BindView(R.id.rule1)
    TextView rule1;
    @BindView(R.id.rule2)
    TextView rule2;
    @BindView(R.id.rule3)
    TextView rule3;
    @BindView(R.id.last_cycle_code)
    TextView lastCycleCode;
    @BindView(R.id.last_win_code)
    TextView lastWinCode;
    @BindView(R.id.last_open_result)
    TextView lastOpenResult;
    @BindView(R.id.buyListView)
    NoTouchListView buyListView;
    @BindView(R.id.rd_jieshao)
    RadioButton rdJieshao;
    @BindView(R.id.rd_guige)
    RadioButton rdGuige;
    @BindView(R.id.rd_shouhou)
    RadioButton rdShouhou;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.ptrLayout)
    PtrClassicFrameLayout ptrLayout;
    @BindView(R.id.buy)
    TextView buy;
    @BindView(R.id.status_layout)
    StatusViewLayout statusLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.layout_win_code_history)
    LinearLayout layoutWinCodeHistory;
    @BindView(R.id.win_code_history_listview)
    CustomListView winCodeHistoryListview;
    @BindView(R.id.two_price)
    TextView twoPrice;
    @BindView(R.id.four_price)
    TextView fourPrice;
    @BindView(R.id.ten_price)
    TextView tenPrice;
    @BindView(R.id.countdownView)
    TextView countdownView;
    private int goodsId;

    private GoodsDetailWinCodeAdapter winCodeAdapter;
    private List<WinCodeInfo> winCodeList = new ArrayList<>();

    private GoodsDetailBuyAdapter buyAdapter;
    private List<NewestWinInfo> buyList = new ArrayList<>();

    private ThreadPoolUtils threadPoolUtils;
    private ScheduledFuture scheduledFuture;
    private ScheduledFuture getDataScheduledFuture;
    private ThreadPoolUtils getDataThreadPoolUtils;
    private ThreadPoolUtils timeThreadPoolUtils;
    private ScheduledFuture timeScheduledFuture;

    private ChooseGoodsNumberPopupWindow popupWindow;

    private GoodsDetailInfo.GoodsInfo goodsInfo;

    public static GoodsDetailFragment newInstance(int goodsId) {
        Bundle args = new Bundle();
        args.putInt(ARG_CYCLE_ID, goodsId);
        GoodsDetailFragment fragment = new GoodsDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            goodsId = getArguments().getInt(ARG_CYCLE_ID);
        }
    }

    @Override
    public GoodsDetailPresenter initPresenter() {
        return new GoodsDetailPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        toolbarTitle.setText("商品详情");
        initToolbarNav(toolbar);
        hideLoading();
        initView();
        presenter.getGoodsDetailInfo(true, goodsId);
    }

    private void initView() {
        ptrLayout.setLastUpdateTimeRelateObject(this);
        ptrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.getGoodsDetailInfo(false, goodsId);
            }
        });

        winCodeAdapter = new GoodsDetailWinCodeAdapter(getContext(), winCodeList);
        winCodeHistoryListview.setAdapter(winCodeAdapter);

        View buyItemView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_goods_detail_buy_item, null);
        ViewUtils.setViewHeightByViewGroup(buyListView, ViewUtils.getViewHeight(buyItemView) * 4);
        presenter.getNewestBuyInfo(goodsId);
        getNewestBuyDataByWhile();
        threadPoolUtils = new ThreadPoolUtils(ThreadPoolUtils.SingleThread, 1);
        scheduledFuture = threadPoolUtils.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                _mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            buyListView.smoothScrollToPositionFromTop(buyListView.getFirstVisiblePosition() + 1, 1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }, 3, 3, TimeUnit.SECONDS);
        //倒计时
        startCountDown();
    }

    /**
     * 重试
     */
    private View.OnClickListener retryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.getGoodsDetailInfo(true, goodsId);
        }
    };

    @OnClick(R.id.layout_win_code_history)
    public void onClickWinCode() {
        winCodeHistoryListview.setVisibility(winCodeHistoryListview.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
    }

    @OnClick(R.id.buy)
    public void onClickBuy() {
        if (popupWindow == null) {
            popupWindow = new ChooseGoodsNumberPopupWindow(_mActivity);
        }
        popupWindow.show(buy);
    }

    @Override
    public void showLoading(int resId) {
        try {
            statusLayout.showLoading();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hideLoading() {
        try {
            statusLayout.showContent();
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
    public void showMessage(int resId) {
        try {
            ToastUtils.showShort(resId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showProgressDialog(int resId) {

    }

    @Override
    public void hideProgressDialog() {

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
    public void bindGoodsInfo(GoodsDetailInfo.GoodsInfo goodsInfo) {
        try {
            this.goodsInfo = goodsInfo;
            goodsName.setText(goodsInfo.getName());
            GlideImageLoader.create(goodsImage).loadImage(ShopApplication.configInfo.getFile_domain() + goodsInfo.getThumb(), R.drawable.ic_default_goods_image);
            cycleCode.setText("第" + ShopApplication.currentCycleInfo.getCycle_code() + "期");
            twoPrice.setText(PriceUtil.getPrice(goodsInfo.getTwo_price()));
            fourPrice.setText(PriceUtil.getPrice(goodsInfo.getFour_price()));
            tenPrice.setText(PriceUtil.getPrice(goodsInfo.getTen_price()));

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
    public void createOrderSuccess(CreateOrderInfo info) {

    }

    @Override
    public void bindWinCodeInfo(List<WinCodeInfo> winCodeInfoList) {
        try {
            if (winCodeInfoList.size() <= 0) {
                return;
            }
            winCodeList.clear();
            winCodeList.addAll(winCodeInfoList);
            lastWinCode.setText(winCodeList.get(0).getResult());
            lastCycleCode.setText(winCodeList.get(0).getCycle_code());
            lastOpenResult.setText("(" + winCodeList.get(0).getResult_text() + ")");
            winCodeList.remove(0);
            winCodeAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getNewestBuySuccess(List<NewestWinInfo> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        buyList.clear();
        buyList.addAll(list);
        if (buyAdapter == null) {
            buyAdapter = new GoodsDetailBuyAdapter(getContext(), buyList);
            buyListView.setAdapter(buyAdapter);
        } else {
            buyAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 循环获取数据
     */
    private void getNewestBuyDataByWhile() {
        getDataThreadPoolUtils = new ThreadPoolUtils(ThreadPoolUtils.SingleThread, 1);
        getDataScheduledFuture = getDataThreadPoolUtils.scheduleWithFixedRate(new Runnable() {
            @Override
            public void run() {
                _mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        presenter.getNewestBuyInfo(goodsId);
                    }
                });
            }
        }, 30 * 1000, 30 * 1000, TimeUnit.SECONDS);
    }

    private void cancelListAutoScroolThread() {
        try {
            if (scheduledFuture != null) {
                scheduledFuture.cancel(true);
            }
            if (threadPoolUtils != null && !threadPoolUtils.isShutDown()) {
                threadPoolUtils.shutDownNow();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cancelGetDataThread() {
        try {
            if (getDataScheduledFuture != null) {
                getDataScheduledFuture.cancel(true);
            }
            if (getDataThreadPoolUtils != null) {
                getDataThreadPoolUtils.shutDownNow();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cancelCountDown() {
        try {
            if (timeScheduledFuture != null) {
                timeScheduledFuture.cancel(true);
            }
            if (timeThreadPoolUtils != null) {
                timeThreadPoolUtils.shutDownNow();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startCountDown() {
        timeThreadPoolUtils = new ThreadPoolUtils(ThreadPoolUtils.SingleThread, 1);
        timeScheduledFuture = timeThreadPoolUtils.scheduleWithFixedRate(new Runnable() {
            @Override
            public void run() {
                _mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (ShopApplication.currentCycleInfo.getOpen_time() * 1000 <= System.currentTimeMillis()) {
                                if (!countdownView.getText().toString().equals("开奖中")) {
                                    ((MainActivity) _mActivity).getCurrentCycleData();
                                }
                                countdownView.setText("开奖中");
                            } else {
                                countdownView.setText(DateFormatUtils.getHoursByNow(ShopApplication.currentCycleInfo.getOpen_time()));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }, 1, 1, TimeUnit.SECONDS);
    }

    @Override
    public void onStop() {
        super.onStop();
        cancelGetDataThread();
        cancelListAutoScroolThread();
        cancelCountDown();
    }

    @Override
    public void onDestroyView() {
        try {
            OkGo.getInstance().cancelTag(ApiUtil.GOODS_DETAIL_TAG);
            OkGo.getInstance().cancelTag(ApiUtil.NEWEST_BUY_GOODS_TAG);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroyView();
        unbinder.unbind();
    }


}
