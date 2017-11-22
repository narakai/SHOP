package wiki.scene.shop.ui.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunfusheng.glideimageview.GlideImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wiki.scene.loadmore.StatusViewLayout;
import wiki.scene.shop.R;
import wiki.scene.shop.mvp.BaseBackMvpFragment;
import wiki.scene.shop.ui.mine.mvpview.IOrderPkDetailView;
import wiki.scene.shop.ui.mine.presenter.OrderPkDetailPresenter;

/**
 * pk详情
 * Created by scene on 2017/11/22.
 */

public class OrderPkDetailFragment extends BaseBackMvpFragment<IOrderPkDetailView, OrderPkDetailPresenter> implements IOrderPkDetailView {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.cycle_code)
    TextView cycleCode;
    @BindView(R.id.result)
    TextView result;
    @BindView(R.id.mine_buy_type)
    TextView mineBuyType;
    @BindView(R.id.mine_avatar)
    GlideImageView mineAvatar;
    @BindView(R.id.mine_name)
    TextView mineName;
    @BindView(R.id.other_buy_type)
    TextView otherBuyType;
    @BindView(R.id.other_avatar)
    GlideImageView otherAvatar;
    @BindView(R.id.other_name)
    TextView otherName;
    @BindView(R.id.status_layout)
    StatusViewLayout statusLayout;
    Unbinder unbinder;
    private int orderId;

    public static OrderPkDetailFragment newInstance(int orderId) {
        Bundle args = new Bundle();
        args.putInt("id", orderId);
        OrderPkDetailFragment fragment = new OrderPkDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            orderId = getArguments().getInt("id");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_pk_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        toolbarTitle.setText("PK详情");
        initToolbarNav(toolbar);
        initView();
    }

    private void initView() {

    }

    @Override
    public void showLoading(int resId) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public OrderPkDetailPresenter initPresenter() {
        return new OrderPkDetailPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
