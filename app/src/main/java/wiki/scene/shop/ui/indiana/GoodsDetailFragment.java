package wiki.scene.shop.ui.indiana;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
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
import wiki.scene.shop.activity.LoginActivity;
import wiki.scene.shop.adapter.GoodsDetailBuyAdapter;
import wiki.scene.shop.adapter.GoodsDetailWinCodeAdapter;
import wiki.scene.shop.config.AppConfig;
import wiki.scene.shop.entity.CreateOrderInfo;
import wiki.scene.shop.entity.GoodsDetailInfo;
import wiki.scene.shop.entity.NewestWinInfo;
import wiki.scene.shop.entity.OrderBuyResultInfo;
import wiki.scene.shop.entity.WinCodeInfo;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.mvp.BaseBackMvpFragment;
import wiki.scene.shop.ui.indiana.mvpview.IGoodsDetailView;
import wiki.scene.shop.ui.indiana.presenter.GoodsDetailPresenter;
import wiki.scene.shop.ui.mine.IndianaRecordFragment;
import wiki.scene.shop.ui.mine.RechargeFragment;
import wiki.scene.shop.utils.DateFormatUtils;
import wiki.scene.shop.utils.NetTimeUtils;
import wiki.scene.shop.utils.PriceUtil;
import wiki.scene.shop.utils.SharedPreferencesUtil;
import wiki.scene.shop.utils.ThreadPoolUtils;
import wiki.scene.shop.utils.UpdatePageUtils;
import wiki.scene.shop.utils.ViewUtils;
import wiki.scene.shop.widgets.BuyGoodsSuccessDialog;
import wiki.scene.shop.widgets.CustomListView;
import wiki.scene.shop.widgets.LoadingDialog;
import wiki.scene.shop.widgets.NoTouchListView;
import wiki.scene.shop.widgets.RatioImageView;

/**
 * Case By:商品详情
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
    @BindView(R.id.danmuUserAvatar)
    ImageView danmuUserAvatar;
    @BindView(R.id.danmuUsername)
    TextView danmuUsername;
    @BindView(R.id.danmuBuyNumber)
    TextView danmuBuyNumber;
    @BindView(R.id.danmuGoodsName)
    TextView danmuGoodsName;
    @BindView(R.id.danmuLayout)
    LinearLayout danmuLayout;
    @BindView(R.id.goods_jieshao)
    ImageView goodsJieshao;
    @BindView(R.id.goods_guige)
    ImageView goodsGuige;
    @BindView(R.id.goods_shouhou)
    ImageView goodsShouhou;
    @BindView(R.id.service_center)
    TextView serviceCenter;
    @BindView(R.id.layout_shouhou)
    LinearLayout layoutShouhou;
    private int goodsId;
    @BindView(R.id.goods_price)
    TextView goodsPrice;

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
    private ThreadPoolUtils danmuThreadPoolUtils;
    private ScheduledFuture danmuScheduledFuture;

    private ChooseGoodsNumberPopupWindow popupWindow;

    private GoodsDetailInfo.GoodsInfo goodsInfo;

    private int danmuPosition = 0;

    private LoadingDialog loadingDialog;

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
        UpdatePageUtils.updatePagePosition(AppConfig.POSITION_GOODS_DETAIL, goodsId);
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
                            buyListView.smoothScrollToPositionFromTop(buyListView.getFirstVisiblePosition() + 1, 0);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }, 1, 1, TimeUnit.SECONDS);
        //倒计时
        startCountDown();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rd_jieshao) {
                    goodsJieshao.setVisibility(View.VISIBLE);
                    goodsGuige.setVisibility(View.GONE);
                    layoutShouhou.setVisibility(View.GONE);
                } else if (i == R.id.rd_guige) {
                    goodsJieshao.setVisibility(View.GONE);
                    goodsGuige.setVisibility(View.VISIBLE);
                    layoutShouhou.setVisibility(View.GONE);
                } else if (i == R.id.rd_shouhou) {
                    goodsJieshao.setVisibility(View.GONE);
                    goodsGuige.setVisibility(View.GONE);
                    layoutShouhou.setVisibility(View.VISIBLE);
                }
            }
        });
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

    @OnClick(R.id.service_center)
    public void onClickServiceCenter() {
        try {
            String qqUrl = "mqqwpa://im/chat?chat_type=wpa&uin=170059106&version=1";
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(qqUrl)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.buy)
    public void onClickBuy() {
        if (goodsInfo != null && ShopApplication.currentCycleInfo != null) {
            if (ShopApplication.userInfo != null) {
                if (popupWindow == null) {
                    popupWindow = new ChooseGoodsNumberPopupWindow(_mActivity);
                }
                popupWindow.setTwoPrice(goodsInfo.getTwo_price());
                popupWindow.setFourPrice(goodsInfo.getFour_price());
                popupWindow.setTenPrice(goodsInfo.getTen_price());
                popupWindow.setCycleCode(ShopApplication.currentCycleInfo.getCycle_code());
                popupWindow.setAccountBalance(ShopApplication.userInfo.getMoney());
                popupWindow.setOnClickPopWindowPayListener(new ChooseGoodsNumberPopupWindow.OnClickPopWindowPayListener() {
                    @Override
                    public void onClickToPay(int playType, int buyType, int buyNumber) {
                        if (goodsId != 0) {
                            presenter.orderBuy(goodsId, buyNumber, playType, buyType);
                        }
                    }

                    @Override
                    public void onClickToRecharge() {
                        start(RechargeFragment.newInstance());
                        if (popupWindow != null) {
                            popupWindow.dismiss();
                        }
                    }
                });
                popupWindow.show(buy);
            } else {
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        }

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
        try {
            if (loadingDialog == null) {
                loadingDialog = LoadingDialog.getInstance(getContext());
            }
            loadingDialog.showLoadingDialog(getString(resId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hideProgressDialog() {
        try {
            if (loadingDialog != null) {
                loadingDialog.cancelLoadingDialog();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            goodsPrice.setText("￥" + PriceUtil.getPrice(goodsInfo.getTen_price()) + "/" + PriceUtil.getPrice(goodsInfo.getFour_price()) + "/" + PriceUtil.getPrice(goodsInfo.getTwo_price()));
            GlideImageLoader.create(goodsJieshao).loadImage(ShopApplication.configInfo.getFile_domain() + goodsInfo.getThumb_js(), 0);
            GlideImageLoader.create(goodsGuige).loadImage(ShopApplication.configInfo.getFile_domain() + goodsInfo.getThumb_gg(), 0);
            GlideImageLoader.create(goodsShouhou).loadImage(ShopApplication.configInfo.getFile_domain() + goodsInfo.getThumb_sh(), 0);
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
        try {
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
            showDanmu(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private BuyGoodsSuccessDialog successDialog;
    private BuyGoodsSuccessDialog.Builder builder;

    @Override
    public void orderBuySuccess(OrderBuyResultInfo info) {
        try {
            ShopApplication.userInfo.setMoney(info.getMoney());
            SharedPreferencesUtil.putString(_mActivity, ShopApplication.USER_INFO_KEY, new Gson().toJson(ShopApplication.userInfo));
            if (popupWindow != null) {
                popupWindow.dismiss();
            }
            if (builder == null) {
                builder = new BuyGoodsSuccessDialog.Builder(getContext());
                builder.setOnClickBuyGoodsSuccessDialogListener(new BuyGoodsSuccessDialog.OnClickBuyGoodsSuccessDialogListener() {
                    @Override
                    public void onClickGoonBuy() {
                        cancelDialog();
                    }

                    @Override
                    public void onClickSeeOrder() {
                        start(IndianaRecordFragment.newInstance());
                        cancelDialog();
                    }
                });
            }
            if (successDialog == null) {
                successDialog = builder.create();
            }
            successDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
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
                            if (ShopApplication.currentCycleInfo.getOpen_time() * 1000 <= NetTimeUtils.getWebsiteDatetime()) {
                                if (!countdownView.getText().toString().equals("开奖中")) {
                                    ((MainActivity) _mActivity).getCurrentCycleData();
                                }
                                countdownView.setText("开奖中");
                                if (popupWindow != null) {
                                    popupWindow.setCountDownView("开奖中");
                                }

                            } else {
                                countdownView.setText(DateFormatUtils.getHoursByNow(ShopApplication.currentCycleInfo.getOpen_time()));
                                if (popupWindow != null) {
                                    popupWindow.setCountDownView(DateFormatUtils.getHoursByNow(ShopApplication.currentCycleInfo.getOpen_time()));
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }, 1, 1, TimeUnit.SECONDS);
    }

    private void showDanmu(final List<NewestWinInfo> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        danmuThreadPoolUtils = new ThreadPoolUtils(ThreadPoolUtils.SingleThread, 1);
        danmuScheduledFuture = danmuThreadPoolUtils.scheduleWithFixedRate(new Runnable() {
            @Override
            public void run() {
                _mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (danmuLayout.getVisibility() == View.GONE) {
                                danmuPosition += 1;
                                NewestWinInfo info = list.get(danmuPosition % list.size());
                                danmuBuyNumber.setText(String.valueOf(info.getNumber()));
                                danmuUsername.setText(info.getNickname());
                                danmuGoodsName.setText(info.getProduct_name());
                                GlideImageLoader.create(danmuUserAvatar).loadCircleImage(ShopApplication.configInfo.getFile_domain() + info.getAvatar(), R.drawable.ic_default_avater);
                                danmuLayout.setVisibility(View.VISIBLE);
                            } else {
                                danmuLayout.setVisibility(View.GONE);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }, 3, 3, TimeUnit.SECONDS);
    }

    private void cancelDanmuThread() {
        try {
            if (danmuScheduledFuture != null) {
                danmuScheduledFuture.cancel(true);
            }
            if (danmuThreadPoolUtils != null) {
                danmuThreadPoolUtils.shutDownNow();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void cancelDialog() {
        try {
            if (successDialog != null) {
                successDialog.cancel();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        cancelGetDataThread();
        cancelListAutoScroolThread();
        cancelCountDown();
        cancelDanmuThread();
    }

    @Override
    public void onDestroyView() {
        try {
            OkGo.getInstance().cancelTag(ApiUtil.GOODS_DETAIL_TAG);
            OkGo.getInstance().cancelTag(ApiUtil.NEWEST_BUY_GOODS_TAG);
            hideProgressDialog();
            cancelDialog();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroyView();
        unbinder.unbind();
    }

}
