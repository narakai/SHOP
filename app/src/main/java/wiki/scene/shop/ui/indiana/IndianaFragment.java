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
import wiki.scene.shop.entity.IndexInfo;
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

public class IndianaFragment extends BaseMainMvpFragment<IIndianaView, IndianaPresenter> implements IIndianaView, View.OnClickListener {

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

    private List<IndexInfo.ProductsBean> goodsList = new ArrayList<>();
    private IndianaGoodsAdapter goodsAdapter;

    private ScheduledFuture scheduledFuture;
    private ThreadPoolUtils threadPoolUtils;

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

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("用户：" + (i + 1));
        }
        IndianaCanyuAdapter adapter = new IndianaCanyuAdapter(getContext(), list);
        canyuListView.setAdapter(adapter);
        huojiangListView.setAdapter(adapter);
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
    }


    @Override
    public void onClick(View view) {

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
    }

    @Override
    public void showMessage(String msg) {

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
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
        if (threadPoolUtils != null && !threadPoolUtils.isShutDown()) {
            threadPoolUtils.shutDownNow();
        }
        OkGo.getInstance().cancelTag(ApiUtil.INDEX_TAG);
        super.onDestroyView();
        unbinder.unbind();
    }
}
