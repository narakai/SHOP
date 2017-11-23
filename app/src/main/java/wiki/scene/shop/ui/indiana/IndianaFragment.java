package wiki.scene.shop.ui.indiana;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wiki.scene.loadmore.PtrClassicFrameLayout;
import wiki.scene.loadmore.PtrDefaultHandler;
import wiki.scene.loadmore.PtrFrameLayout;
import wiki.scene.loadmore.StatusViewLayout;
import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.adapter.IndianaCanyuAdapter;
import wiki.scene.shop.adapter.IndianaGoodsAdapter;
import wiki.scene.shop.adapter.IndianaWinAdapter;
import wiki.scene.shop.entity.IndexInfo;
import wiki.scene.shop.entity.NewestWinInfo;
import wiki.scene.shop.event.StartBrotherEvent;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.mvp.BaseMainMvpFragment;
import wiki.scene.shop.ui.indiana.mvpview.IIndianaView;
import wiki.scene.shop.ui.indiana.presenter.IndianaPresenter;
import wiki.scene.shop.utils.ThreadPoolUtils;
import wiki.scene.shop.utils.ViewUtils;
import wiki.scene.shop.widgets.CustomeGridView;
import wiki.scene.shop.widgets.MarqueeView;
import wiki.scene.shop.widgets.NoTouchListView;

/**
 * Case By:
 * package:wiki.scene.shop.fragment.indiana
 * Author：scene on 2017/6/28 17:36
 */

public class IndianaFragment extends BaseMainMvpFragment<IIndianaView, IndianaPresenter> implements IIndianaView {

    @BindView(R.id.gridView)
    CustomeGridView gridView;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.rd_canyu)
    RadioButton rdCanyu;
    @BindView(R.id.rd_huojiang)
    RadioButton rdHuojiang;
    @BindView(R.id.canyuListView)
    NoTouchListView canyuListView;
    @BindView(R.id.huojiangListView)
    NoTouchListView huojiangListView;
    @BindView(R.id.ptrLayout)
    PtrClassicFrameLayout ptrLayout;
    @BindView(R.id.status_layout)
    StatusViewLayout statusLayout;
    Unbinder unbinder;
    @BindView(R.id.notice)
    MarqueeView notice;
    @BindView(R.id.cycle_code)
    TextView cycleCode;
    @BindView(R.id.cycle_number_1)
    TextView cycleNumber1;
    @BindView(R.id.cycle_number_2)
    TextView cycleNumber2;
    @BindView(R.id.cycle_number_3)
    TextView cycleNumber3;
    @BindView(R.id.cycle_number_4)
    TextView cycleNumber4;
    @BindView(R.id.cycle_number_5)
    TextView cycleNumber5;

    private List<IndexInfo.ProductsBean> goodsList = new ArrayList<>();
    private IndianaGoodsAdapter goodsAdapter;

    private ScheduledFuture scheduledFuture;
    private ThreadPoolUtils threadPoolUtils;
    private ScheduledFuture getDataScheduledFuture;
    private ThreadPoolUtils getDataThreadPoolUtils;

    private List<NewestWinInfo> newestBuyList = new ArrayList<>();
    private List<NewestWinInfo> newestWinList = new ArrayList<>();
    private IndianaCanyuAdapter canyuAdapter;
    private IndianaWinAdapter winAdapter;

    public static IndianaFragment newInstance() {
        Bundle args = new Bundle();
        IndianaFragment fragment = new IndianaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_indiana, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        statusLayout.showContent();
        initView();
        presenter.getIndianaData(false);
    }

    private void initView() {
        View canyuItemview = LayoutInflater.from(getContext()).inflate(R.layout.fragment_indiana_canyu_item, null);
        ViewUtils.setViewHeightByViewGroup(canyuListView, ViewUtils.getViewHeight(canyuItemview) * 4);
        ViewUtils.setViewHeightByViewGroup(huojiangListView, ViewUtils.getViewHeight(canyuItemview) * 4);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == rdCanyu.getId()) {
                    canyuListView.setVisibility(View.VISIBLE);
                    huojiangListView.setVisibility(View.GONE);
                } else if (checkedId == rdHuojiang.getId()) {
                    canyuListView.setVisibility(View.GONE);
                    huojiangListView.setVisibility(View.VISIBLE);
                }
            }
        });

        ptrLayout.setLastUpdateTimeRelateObject(this);
        ptrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.getIndianaData(true);
            }
        });

        threadPoolUtils = new ThreadPoolUtils(ThreadPoolUtils.SingleThread, 1);
        scheduledFuture = threadPoolUtils.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                _mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            canyuListView.smoothScrollToPositionFromTop(canyuListView.getFirstVisiblePosition() + 1, 1);
                            huojiangListView.smoothScrollToPositionFromTop(huojiangListView.getFirstVisiblePosition() + 1, 1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }, 3, 3, TimeUnit.SECONDS);

        goodsAdapter = new IndianaGoodsAdapter(getContext(), goodsList);
        gridView.setAdapter(goodsAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                EventBus.getDefault().post(new StartBrotherEvent(GoodsDetailFragment.newInstance(goodsList.get(i).getId())));
            }
        });
        notice.setFocusable(true);
        notice.setFocusableInTouchMode(true);
        notice.requestFocus();
        getWinAndBuyDataByWhile();
        presenter.getNewestBuyInfo();
        presenter.getNewestWinInfo();
    }

    /**
     * 循环获取数据
     */
    private void getWinAndBuyDataByWhile() {
        getDataThreadPoolUtils = new ThreadPoolUtils(ThreadPoolUtils.SingleThread, 1);
        getDataScheduledFuture = getDataThreadPoolUtils.scheduleWithFixedRate(new Runnable() {
            @Override
            public void run() {
                _mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        presenter.getNewestBuyInfo();
                        presenter.getNewestWinInfo();
                    }
                });
            }
        }, 30 * 1000, 30 * 1000, TimeUnit.SECONDS);
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
    public void getDataSuccess(IndexInfo indexInfo) {
        if (goodsAdapter != null) {
            goodsAdapter.cancelAllTimers();
        }
        goodsList.clear();
        goodsList.addAll(indexInfo.getProducts());
        int size = goodsList.size();
        for (int i = 0; i < size; i++) {
            if (indexInfo.getCurrent_cycle() != null && indexInfo.getCurrent_cycle().getOpen_time() != 0) {
                goodsList.get(i).setOpen_time(indexInfo.getCurrent_cycle().getOpen_time());
            } else {
                if (ShopApplication.currentCycleInfo != null && ShopApplication.currentCycleInfo.getOpen_time() != 0) {
                    goodsList.get(i).setOpen_time(ShopApplication.currentCycleInfo.getOpen_time());
                } else {
                    goodsList.get(i).setOpen_time(0L);
                }
            }
        }
        goodsAdapter.notifyDataSetChanged();
        refreshCurrentCycleCode(indexInfo.getLast_ssc_result());
    }

    @Override
    public void showMessage(String msg) {
        ToastUtils.showShort(msg);
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
    public void refreshComplete() {
        try {
            ptrLayout.refreshComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refrshFail(String message) {
        try {
            ptrLayout.refreshComplete();
            ToastUtils.showShort(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取最新获奖信息成功
     *
     * @param list 集合
     */
    @Override
    public void getNewestWinSuccess(List<NewestWinInfo> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        newestWinList.clear();
        newestWinList.addAll(list);
        if (winAdapter == null) {
            winAdapter = new IndianaWinAdapter(getContext(), newestWinList);
            huojiangListView.setAdapter(winAdapter);
        } else {
            winAdapter.notifyDataSetChanged();
        }
        //绑定跑马灯的数据
        StringBuilder text = new StringBuilder();
        for (NewestWinInfo info : list) {
            text.append("恭喜【").append(info.getNickname()).append("】获胜").append(info.getNumber()).append("组    ");
        }
        notice.setText(text.toString());
        notice.setFocusable(true);
        notice.setFocusableInTouchMode(true);
        notice.requestFocus();
    }

    /**
     * 获取最新参与信息成功
     *
     * @param list 集合
     */
    @Override
    public void getNewestBuySuccess(List<NewestWinInfo> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        newestBuyList.clear();
        newestBuyList.addAll(list);
        if (canyuAdapter == null) {
            canyuAdapter = new IndianaCanyuAdapter(getContext(), newestBuyList);
            canyuListView.setAdapter(canyuAdapter);
        } else {
            canyuAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public IndianaPresenter initPresenter() {
        return new IndianaPresenter(this);
    }

    private View.OnClickListener retryListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            presenter.getIndianaData(false);
        }
    };

    @Override
    public void onStop() {
        super.onStop();
        if (goodsAdapter != null) {
            goodsAdapter.cancelAllTimers();
        }
    }

    @Override
    public void onDestroyView() {
        cancelListAutoScroolThread();
        cancelGetDataThread();
        OkGo.getInstance().cancelTag(ApiUtil.INDEX_TAG);
        super.onDestroyView();
        unbinder.unbind();
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

    private void refreshCurrentCycleCode(IndexInfo.LastSscResultBean cycleInfo) {
        try {
            cycleCode.setText("第" + cycleInfo.getCycle_code() + "期：");
            char[] cycleNumbers = cycleInfo.getResult().toCharArray();
            if (cycleNumbers.length > 4) {
                cycleNumber1.setText(String.valueOf(cycleNumbers[0]));
                cycleNumber2.setText(String.valueOf(cycleNumbers[1]));
                cycleNumber3.setText(String.valueOf(cycleNumbers[2]));
                cycleNumber4.setText(String.valueOf(cycleNumbers[3]));
                cycleNumber5.setText(String.valueOf(cycleNumbers[4]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
