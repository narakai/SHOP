package wiki.scene.shop.ui.indiana;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wiki.scene.loadmore.PtrClassicFrameLayout;
import wiki.scene.loadmore.PtrDefaultHandler;
import wiki.scene.loadmore.PtrFrameLayout;
import wiki.scene.loadmore.StatusViewLayout;
import wiki.scene.shop.R;
import wiki.scene.shop.entity.CreateOrderInfo;
import wiki.scene.shop.entity.GoodsDetailInfo;
import wiki.scene.shop.entity.ListGoodsInfo;
import wiki.scene.shop.mvp.BaseBackMvpFragment;
import wiki.scene.shop.ui.indiana.mvpview.IGoodsDetailView;
import wiki.scene.shop.ui.indiana.presenter.GoodsDetailPresenter;
import wiki.scene.shop.widgets.CustomListView;
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
    CustomListView buyListView;
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
    private int goodsId;

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
                ptrLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        _mActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ptrLayout.refreshComplete();
                            }
                        });
                    }
                }, 2000);
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

    @Override
    public void showLoading(int resId) {
        statusLayout.showContent();
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessage(int resId) {

    }

    @Override
    public void addCartSuccess() {

    }

    @Override
    public void showProgressDialog(int resId) {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void refreshComplete() {

    }

    @Override
    public void bindGoodsInfo(GoodsDetailInfo.GoodsDetailInfoData goodsDetailInfo) {

    }

    @Override
    public void showFailPage() {

    }

    @Override
    public void bindJoinRecord(List<GoodsDetailInfo.LogInfo> logInfo) {

    }

    @Override
    public void bindTuhaoRank(List<GoodsDetailInfo.BuyersInfo> buyersInfo) {

    }

    @Override
    public void bindGuessLike(List<ListGoodsInfo> listGoodsInfoList) {

    }

    @Override
    public void createOrderSuccess(CreateOrderInfo info) {

    }

    @Override
    public void hasCollected() {

    }

    @Override
    public void noCollected() {

    }

    @Override
    public void showCollectionStatus(boolean collectionStatus) {

    }
}
