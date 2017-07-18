package wiki.scene.shop.ui.indiana;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.ShareBoardConfig;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;
import com.youth.banner.Banner;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.iwgang.countdownview.CountdownView;
import wiki.scene.loadmore.PtrClassicFrameLayout;
import wiki.scene.loadmore.PtrDefaultHandler;
import wiki.scene.loadmore.PtrFrameLayout;
import wiki.scene.loadmore.StatusViewLayout;
import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.adapter.GoodsDetailJoinRecordAdapter;
import wiki.scene.shop.adapter.GoodsDetailTuhaoRankAdapter;
import wiki.scene.shop.adapter.GuessLikeAdapter;
import wiki.scene.shop.entity.ListGoodsInfo;
import wiki.scene.shop.event.AddGoods2CartEvent;
import wiki.scene.shop.mvp.BaseBackMvpFragment;
import wiki.scene.shop.ui.indiana.mvpview.IGoodsDetailView;
import wiki.scene.shop.ui.indiana.presenter.GoodsDetailPresenter;
import wiki.scene.shop.utils.ToastUtils;
import wiki.scene.shop.widgets.CustomListView;
import wiki.scene.shop.widgets.CustomeGridView;
import wiki.scene.shop.widgets.LoadingDialog;

/**
 * Case By:
 * package:wiki.scene.shop.ui.indiana
 * Author：scene on 2017/7/4 11:40
 */

public class GoodsDetailFragment extends BaseBackMvpFragment<IGoodsDetailView, GoodsDetailPresenter> implements IGoodsDetailView {
    private static final String ARG_CYCLE_ID = "cycle_id";
    @BindView(R.id.toolbar_how_to_play)
    TextView toolbarHowToPlay;
    @BindView(R.id.toolbar_share)
    ImageView toolbarShare;
    Unbinder unbinder;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.goods_status)
    TextView goodsStatus;
    @BindView(R.id.goods_name)
    TextView goodsName;
    @BindView(R.id.goods_name_subtitle)
    TextView goodsNameSubtitle;
    @BindView(R.id.goods_times)
    TextView goodsTimes;
    @BindView(R.id.pending_text)
    TextView pendingText;
    @BindView(R.id.pending_count_down)
    CountdownView pendingCountDown;
    @BindView(R.id.pending_reckon_detail)
    TextView pendingReckonDetail;
    @BindView(R.id.layout_goods_status_pending_view)
    RelativeLayout layoutGoodsStatusPendingView;
    @BindView(R.id.ongoing_progressbar)
    ProgressBar ongoingProgressbar;
    @BindView(R.id.total_need_person_times)
    TextView totalNeedPersonTimes;
    @BindView(R.id.layout_goods_status_ongoing_view)
    RelativeLayout layoutGoodsStatusOngoingView;
    @BindView(R.id.announced_user_avater)
    ImageView announcedUserAvater;
    @BindView(R.id.announced_winner_name)
    TextView announcedWinnerName;
    @BindView(R.id.announced_winner_ip)
    TextView announcedWinnerIp;
    @BindView(R.id.announced_winner_id)
    TextView announcedWinnerId;
    @BindView(R.id.winner_join_times)
    TextView winnerJoinTimes;
    @BindView(R.id.announced_time)
    TextView announcedTime;
    @BindView(R.id.announced_luck_code)
    TextView announcedLuckCode;
    @BindView(R.id.announced_reckon_detail)
    TextView announcedReckonDetail;
    @BindView(R.id.layout_goods_status_announced_view)
    LinearLayout layoutGoodsStatusAnnouncedView;
    @BindView(R.id.goin_times)
    TextView goinTimes;
    @BindView(R.id.see_all_luck_code)
    TextView seeAllLuckCode;
    @BindView(R.id.has_join)
    LinearLayout hasJoin;
    @BindView(R.id.no_join)
    TextView noJoin;
    @BindView(R.id.ptrLayout)
    PtrClassicFrameLayout ptrLayout;
    @BindView(R.id.status_layout)
    StatusViewLayout statusLayout;
    @BindView(R.id.layout1)
    LinearLayout layout1;
    @BindView(R.id.layout2)
    LinearLayout layout2;
    @BindView(R.id.layout3)
    ImageView layout3;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.image_text_detail)
    TextView imageTextDetail;
    @BindView(R.id.trend)
    TextView trend;
    @BindView(R.id.old_announced)
    TextView oldAnnounced;
    @BindView(R.id.share_order_share)
    TextView shareOrderShare;
    @BindView(R.id.list_rules)
    TextView listRules;
    @BindView(R.id.tuhao_rank_gridView)
    CustomeGridView tuhaoRankGridView;
    @BindView(R.id.join_record_listview)
    CustomListView joinRecordListview;
    @BindView(R.id.see_all_join_record)
    TextView seeAllJoinRecord;
    @BindView(R.id.guesslike_gridView)
    CustomeGridView guesslikeGridView;
    @BindView(R.id.collection)
    TextView collection;
    @BindView(R.id.join_car)
    TextView joinCar;
    @BindView(R.id.immediately_indiana)
    TextView immediatelyIndiana;

    //土豪榜adapter
    private List<String> tuhaoRankList = new ArrayList<>();
    private GoodsDetailTuhaoRankAdapter tuhaoRankAdapter;
    //参与记录
    private List<String> joinRecordList = new ArrayList<>();
    private GoodsDetailJoinRecordAdapter joinRecordAdapter;
    //猜你喜欢
    private List<ListGoodsInfo> guessLiskList = new ArrayList<>();
    private GuessLikeAdapter guessLikeAdapter;
    //期数id 相当于产品id
    private int cycleId;

    //加载框
    private LoadingDialog loadingDialog;

    public static GoodsDetailFragment newInstance(int cycle_id) {
        Bundle args = new Bundle();
        args.putInt(ARG_CYCLE_ID, cycle_id);
        GoodsDetailFragment fragment = new GoodsDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cycleId = getArguments().getInt(ARG_CYCLE_ID, 0);
        }
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
        initToolbarNav(toolbar);
        hideLoading();
        initView();
    }

    private void initView() {
        loadingDialog = LoadingDialog.getInstance(_mActivity);
        ptrLayout.setLastUpdateTimeRelateObject(this);
        ptrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

            }
        });

        for (int i = 0; i < 3; i++) {
            tuhaoRankList.add("土豪" + (i + 1));
            joinRecordList.add("参与记录" + (i + 1));
        }
        tuhaoRankAdapter = new GoodsDetailTuhaoRankAdapter(_mActivity, tuhaoRankList);
        tuhaoRankGridView.setAdapter(tuhaoRankAdapter);

        joinRecordAdapter = new GoodsDetailJoinRecordAdapter(_mActivity, joinRecordList);
        joinRecordListview.setAdapter(joinRecordAdapter);

        guessLikeAdapter = new GuessLikeAdapter(_mActivity, guessLiskList);
        guesslikeGridView.setAdapter(guessLikeAdapter);
    }

    @OnClick(R.id.old_announced)
    public void onClickOldAnnounced() {
        start(OldAnnouncedFragment.newInstance());
    }

    @OnClick(R.id.toolbar_share)
    public void onClickToolbarShare() {
        ShareBoardConfig config = new ShareBoardConfig();
        config.setIndicatorVisibility(false);
        config.setShareboardBackgroundColor(getResources().getColor(R.color.white));
        config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE);
        config.setTitleText(getString(R.string.share_to));
        config.setCancelButtonText(getString(R.string.cancel));
        config.setCancelButtonTextColor(getResources().getColor(R.color.url_color));
        new ShareAction(_mActivity)
                .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.SINA, SHARE_MEDIA.QQ)
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                        if (share_media == SHARE_MEDIA.SINA) {
                            UMImage image = new UMImage(_mActivity, ShopApplication.userInfo.getAvatar());
                            new ShareAction(_mActivity).withText("测试的标题<a href='http://www.baidu.com'>链接</a>").withMedia(image)
                                    .setPlatform(share_media)
                                    .setCallback(shareListener)
                                    .share();
                        } else {
                            UMWeb web = new UMWeb("http://www.baidu.com");
                            web.setTitle("来自分享面板标题");
                            web.setDescription("来自分享面板内容");
                            web.setThumb(new UMImage(_mActivity, "https://mobile.umeng.com/images/pic/home/social/img-1.png"));
                            new ShareAction(_mActivity).withMedia(web)
                                    .setPlatform(share_media)
                                    .setCallback(shareListener)
                                    .share();
                        }
                    }
                })
                .open(config);
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         *  分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         *  分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            ToastUtils.getInstance(_mActivity).showToast("分享成功");
        }

        /**
         *  分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            ToastUtils.getInstance(_mActivity).showToast("分享失败，请重试");
        }

        /**
         *  分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
        }
    };

    /**
     * Case By:加入购物车
     * Author: scene on 2017/7/13 14:39
     */
    @OnClick(R.id.join_car)
    public void onClickJoinCar() {
        presenter.addGoods2Car(cycleId);
    }

    @Override
    public GoodsDetailPresenter initPresenter() {
        return new GoodsDetailPresenter(this);
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showMessage(String message) {
        ToastUtils.getInstance(_mActivity).showToast(message);
    }

    @Override
    public void addCartSuccess() {
        ToastUtils.getInstance(_mActivity).showToast(R.string.goods_has_added_cart);
        EventBus.getDefault().post(new AddGoods2CartEvent());
    }

    @Override
    public void showProgressDialog(@StringRes int resId) {
        loadingDialog.showLoadingDialog(getString(resId));
    }

    @Override
    public void hideProgressDialog() {
        loadingDialog.cancelLoadingDialog();
    }
}
