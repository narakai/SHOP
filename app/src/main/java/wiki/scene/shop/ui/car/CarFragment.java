package wiki.scene.shop.ui.car;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wiki.scene.loadmore.PtrClassicFrameLayout;
import wiki.scene.loadmore.PtrDefaultHandler;
import wiki.scene.loadmore.PtrFrameLayout;
import wiki.scene.loadmore.StatusViewLayout;
import wiki.scene.loadmore.utils.SceneLogUtil;
import wiki.scene.shop.MainFragment;
import wiki.scene.shop.R;
import wiki.scene.shop.adapter.CarGoodsAdapter;
import wiki.scene.shop.adapter.GuessLikeAdapter;
import wiki.scene.shop.entity.CartInfo;
import wiki.scene.shop.entity.ListGoodsInfo;
import wiki.scene.shop.event.AddGoods2CartEvent;
import wiki.scene.shop.event.StartBrotherEvent;
import wiki.scene.shop.event.TabSelectedEvent;
import wiki.scene.shop.mvp.BaseMainMvpFragment;
import wiki.scene.shop.ui.car.mvpview.ICarView;
import wiki.scene.shop.ui.car.presenter.CarPresenter;
import wiki.scene.shop.ui.indiana.GoodsDetailFragment;
import wiki.scene.shop.utils.ToastUtils;
import wiki.scene.shop.widgets.CustomListView;
import wiki.scene.shop.widgets.CustomeGridView;
import wiki.scene.shop.widgets.LoadingDialog;

/**
 * Case By:购物车
 * package:wiki.scene.shop.fragment.indiana
 * Author：scene on 2017/6/26 14:13
 */
public class CarFragment extends BaseMainMvpFragment<ICarView, CarPresenter> implements ICarView, CarGoodsAdapter.OnCarItemClickListener {

    @BindView(R.id.layout_unlogin)
    LinearLayout layoutUnlogin;
    @BindView(R.id.goods_listview)
    CustomListView goodsListview;
    @BindView(R.id.like_gridView)
    CustomeGridView likeGridView;
    @BindView(R.id.total_price)
    TextView tvTotalPrice;
    @BindView(R.id.immediately_indiana)
    TextView immediatelyIndiana;
    @BindView(R.id.layout_bottom)
    LinearLayout layoutBottom;
    @BindView(R.id.ptrLayout)
    PtrClassicFrameLayout ptrLayout;
    @BindView(R.id.status_layout)
    StatusViewLayout statusLayout;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    //猜你喜欢adapter
    private List<ListGoodsInfo> guessLikeList = new ArrayList<>();
    private GuessLikeAdapter guessLikeAdapter;
    //购物车商品
    private List<CartInfo> goodsList = new ArrayList<>();
    private CarGoodsAdapter goodsAdapter;
    //加载框
    private LoadingDialog loadingDialog;

    public static CarFragment newInstance() {
        CarFragment fragment = new CarFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        EventBus.getDefault().register(this);
        initView();
        presenter.showTotalPrice(goodsList);
    }

    private void initView() {
        loadingDialog = LoadingDialog.getInstance(_mActivity);

        guessLikeAdapter = new GuessLikeAdapter(_mActivity, guessLikeList);
        likeGridView.setAdapter(guessLikeAdapter);

        goodsAdapter = new CarGoodsAdapter(_mActivity, goodsList);
        goodsListview.setAdapter(goodsAdapter);
        layoutUnlogin.setVisibility(View.GONE);

        ptrLayout.setLastUpdateTimeRelateObject(this);
        ptrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return super.checkCanDoRefresh(frame, content, header) && scrollView.getScrollY() == 0;
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.getCarList(false);
            }
        });

        likeGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EventBus.getDefault().post(new StartBrotherEvent(GoodsDetailFragment.newInstance(guessLikeList.get(position).getId())));
            }
        });

        goodsAdapter.setOnCarItemClickListener(this);

        presenter.getCarList(true);
    }

    /**
     * 立即夺宝
     */
    @OnClick(R.id.immediately_indiana)
    public void onClickImmediatelyIndiana() {
        EventBus.getDefault().post(new StartBrotherEvent(PayOrderFragment.newInstance()));
    }

    /**
     * Case By:跳转到首页
     * Author: scene on 2017/7/14 10:11
     */
    @OnClick(R.id.toIndexPage)
    public void onClickToIndexPage() {
        EventBus.getDefault().post(new TabSelectedEvent(MainFragment.FIRST));
    }

    @Override
    public void showLoading() {
        statusLayout.showLoading();
    }

    @Override
    public void hideLoading() {
        statusLayout.showContent();
    }

    @Override
    public CarPresenter initPresenter() {
        return new CarPresenter(this);
    }


    @Override
    public void refreshComplete() {
        ptrLayout.refreshComplete();
    }

    @Override
    public void loadDataFail() {
        statusLayout.showFailed(retryListener);
    }

    @Override
    public void showMessage(String message) {
        ToastUtils.getInstance(_mActivity).showToast(message);
    }

    @Override
    public void bindCartData(List<CartInfo> list) {
        layoutUnlogin.setVisibility(View.GONE);
        goodsListview.setVisibility(View.VISIBLE);
        layoutBottom.setVisibility(View.VISIBLE);

        goodsList.clear();
        goodsList.addAll(list);
        goodsAdapter.notifyDataSetChanged();
        presenter.showTotalPrice(goodsList);
    }

    @Override
    public void bindGuessLikeData(List<ListGoodsInfo> list) {
        guessLikeList.clear();
        guessLikeList.addAll(list);
        guessLikeAdapter.notifyDataSetChanged();
    }

    @Override
    public void showTotalPrice(int totalPrice) {
        tvTotalPrice.setText(String.format(getString(R.string.price_number), totalPrice));
    }

    @Override
    public void onDeleteSuccess(int position) {
        goodsList.remove(position);
        goodsAdapter.notifyDataSetChanged();
        presenter.showTotalPrice(goodsList);
        if (goodsList.size() == 0) {
            showEmptyCart();
        }
    }

    @Override
    public void showProgress(@StringRes int resId) {
        loadingDialog.showLoadingDialog(getString(resId));
    }

    @Override
    public void hideProgress() {
        loadingDialog.cancelLoadingDialog();
    }

    @Override
    public void loadDataSuccess() {
        ptrLayout.refreshComplete();
        statusLayout.showContent();
    }

    @Override
    public void showEmptyCart() {
        layoutUnlogin.setVisibility(View.VISIBLE);
        goodsListview.setVisibility(View.GONE);
        layoutBottom.setVisibility(View.GONE);
    }

    View.OnClickListener retryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.getCarList(true);
        }
    };

    @Subscribe
    public void onNeedRefresh(AddGoods2CartEvent event) {
        try {
            presenter.getCarList(false);
        } catch (Exception e) {
            SceneLogUtil.e("界面未加载");
        }
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    @Override
    public void onItemClickStatus(int position) {
        goodsList.get(position).setChecked(!goodsList.get(position).isChecked());
        presenter.showTotalPrice(goodsList);
        goodsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClickAdd(int position) {
        int number = goodsList.get(position).getNumber();
        int shengyuNumber = goodsList.get(position).getNeed_source() - goodsList.get(position).getCurrent_source();
        if (number < shengyuNumber) {
            goodsList.get(position).setNumber(number + 1);
        }
        presenter.showTotalPrice(goodsList);
        goodsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClickLess(int position) {
        int number = goodsList.get(position).getNumber();
        if (number > 1) {
            goodsList.get(position).setNumber(number - 1);
        }
        presenter.showTotalPrice(goodsList);
        goodsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClickGoodsImage(int position) {
        EventBus.getDefault().post(new StartBrotherEvent(GoodsDetailFragment.newInstance(goodsList.get(position).getProduct_id())));
    }

    @Override
    public void onItemClickDelete(int position) {
        presenter.deleteCartGoods(goodsList.get(position).getId(), position);
    }
}
