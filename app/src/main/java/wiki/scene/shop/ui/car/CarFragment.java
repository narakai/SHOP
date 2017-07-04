package wiki.scene.shop.ui.car;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wiki.scene.shop.R;
import wiki.scene.shop.adapter.CarGoodsAdapter;
import wiki.scene.shop.adapter.GuessLikeAdapter;
import wiki.scene.shop.event.StartBrotherEvent;
import wiki.scene.shop.ui.car.mvpview.ICarView;
import wiki.scene.shop.ui.car.presenter.CarPresenter;
import wiki.scene.shop.mvp.BaseMainMvpFragment;
import wiki.scene.shop.widgets.CustomListView;
import wiki.scene.shop.widgets.CustomeGridView;

/**
 * Case By:购物车
 * package:wiki.scene.shop.fragment.indiana
 * Author：scene on 2017/6/26 14:13
 */
public class CarFragment extends BaseMainMvpFragment<ICarView, CarPresenter> implements ICarView {

    @BindView(R.id.layout_unlogin)
    LinearLayout layoutUnlogin;
    @BindView(R.id.goods_listview)
    CustomListView goodsListview;
    @BindView(R.id.like_gridView)
    CustomeGridView likeGridView;
    @BindView(R.id.total_price)
    TextView totalPrice;
    //猜你喜欢adapter
    private List<String> guessLikeList = new ArrayList<>();
    private GuessLikeAdapter guessLikeAdapter;
    //购物车商品
    private List<String> goodsList = new ArrayList<>();
    private CarGoodsAdapter goodsAdapter;

    public static CarFragment newInstance() {
        CarFragment fragment = new CarFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
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
        initView();
    }

    private void initView() {
        for (int i = 0; i < 10; i++) {
            guessLikeList.add("猜你喜欢商品" + (i + 1));
            if (goodsList.size() < 4)
                goodsList.add("购物车商品" + (i + 1));
        }
        guessLikeAdapter = new GuessLikeAdapter(_mActivity, guessLikeList);
        likeGridView.setAdapter(guessLikeAdapter);

        goodsAdapter = new CarGoodsAdapter(_mActivity, goodsList);
        goodsListview.setAdapter(goodsAdapter);
        layoutUnlogin.setVisibility(View.GONE);
    }

    /**
     * 立即夺宝
     */
    @OnClick(R.id.immediately_indiana)
    public void onClickImmediatelyIndiana() {
        EventBus.getDefault().post(new StartBrotherEvent(PayOrderFragment.newInstance()));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public CarPresenter initPresenter() {
        return new CarPresenter(this);
    }
}
