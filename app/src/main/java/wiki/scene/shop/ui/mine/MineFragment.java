package wiki.scene.shop.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.sunfusheng.glideimageview.GlideImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wiki.scene.loadmore.PtrClassicFrameLayout;
import wiki.scene.loadmore.PtrDefaultHandler;
import wiki.scene.loadmore.PtrFrameLayout;
import wiki.scene.loadmore.StatusViewLayout;
import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.activity.LoginActivity;
import wiki.scene.shop.config.AppConfig;
import wiki.scene.shop.entity.MineInfo;
import wiki.scene.shop.event.ChooseAvaterResultEvent;
import wiki.scene.shop.event.LoginOutEvent;
import wiki.scene.shop.event.RefreshMineEvent;
import wiki.scene.shop.event.RegisterSuccessEvent;
import wiki.scene.shop.event.StartBrotherEvent;
import wiki.scene.shop.event.TabSelectedEvent;
import wiki.scene.shop.mvp.BaseMainMvpFragment;
import wiki.scene.shop.ui.mine.mvpview.IMineView;
import wiki.scene.shop.ui.mine.presenter.MinePresenter;
import wiki.scene.shop.utils.PriceUtil;
import wiki.scene.shop.utils.SharedPreferencesUtil;
import wiki.scene.shop.utils.UpdatePageUtils;
import wiki.scene.shop.widgets.LoadingDialog;

/**
 * Case By:我的
 * package:wiki.scene.shop.fragment.indiana
 * Author：scene on 2017/6/26 14:13
 */
public class MineFragment extends BaseMainMvpFragment<IMineView, MinePresenter> implements IMineView {


    @BindView(R.id.user_avater)
    GlideImageView userAvater;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.user_id)
    TextView userId;
    @BindView(R.id.win_time)
    TextView winTime;
    @BindView(R.id.commission)
    TextView commission;
    @BindView(R.id.coin)
    TextView coin;
    @BindView(R.id.layout1)
    LinearLayout layout1;
    @BindView(R.id.recharge)
    TextView recharge;
    @BindView(R.id.exchange)
    TextView exchange;
    @BindView(R.id.draw_cash)
    TextView drawCash;
    @BindView(R.id.make_money)
    TextView makeMoney;
    @BindView(R.id.indiana_record)
    TextView indianaRecord;
    @BindView(R.id.exchange_record)
    TextView exchangeRecord;
    @BindView(R.id.draw_cash_record)
    TextView drawCashRecord;
    @BindView(R.id.mine_phone)
    TextView minePhone;
    @BindView(R.id.mine_card)
    TextView mineCard;
    @BindView(R.id.today_join)
    TextView todayJoin;
    @BindView(R.id.today_commission)
    TextView todayCommission;
    @BindView(R.id.today_win)
    TextView todayWin;

    Unbinder unbinder;
    @BindView(R.id.ptrLayout)
    PtrClassicFrameLayout ptrLayout;
    @BindView(R.id.status_layout)
    StatusViewLayout statusLayout;

    private LoadingDialog loadingDialog;

    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        EventBus.getDefault().register(this);
        UpdatePageUtils.updatePagePosition(AppConfig.POSITION_MINE, 0);
        initView();
        if (ShopApplication.hasLogin) {
            presenter.getMineInfo(true);
        } else {
            showContentPage();
        }
    }

    private void initView() {
        ptrLayout.setLastUpdateTimeRelateObject(this);
        ptrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                if (ShopApplication.hasLogin) {
                    presenter.getMineInfo(false);
                } else {
                    ptrLayout.refreshComplete();
                }
            }
        });
        loadingDialog = LoadingDialog.getInstance(_mActivity);
        if (ShopApplication.hasLogin) {
            hasLogin();
        } else {
            hasNoLogin();
        }
    }

    @OnClick(R.id.user_avater)
    public void onClickUserAvater() {
        if (ShopApplication.hasLogin) {
            startActivity(new Intent(_mActivity, MineInfoActivity.class));
        } else {
            enterLogin();
        }
    }

    /**
     * 充值
     */
    @OnClick(R.id.recharge)
    public void onClickRecharge() {
        presenter.recharge();
    }

    /**
     * 夺宝记录
     */
    @OnClick(R.id.indiana_record)
    public void onClickIndianaRecord() {
        presenter.clickIndianaRecord();
    }

    @OnClick(R.id.setting)
    public void onClickSetting() {
        presenter.clickSetting();
    }

    @OnClick(R.id.mine_card)
    public void onClickMineBankCard() {
        presenter.clickMineBankCard();
    }

    @OnClick(R.id.draw_cash_record)
    public void onClickDrawCashRecord() {
        presenter.clickDrawCashRecord();
    }

    @OnClick(R.id.exchange_record)
    public void onClickExchangeRecord() {
        presenter.clickExchangeRecord();
    }

    @OnClick(R.id.mine_phone)
    public void onClickMinePhone() {
        presenter.clickMinePhone();
    }

    @OnClick(R.id.exchange)
    public void onClickExchange() {
        presenter.clickExchange();
    }

    @OnClick(R.id.draw_cash)
    public void onClickDrawCash() {
        presenter.clickDrawCash();
    }

    @Override
    public void showLoading(@StringRes int resId) {
        loadingDialog.showLoadingDialog(getString(resId));
    }

    @Override
    public void hideLoading() {
        loadingDialog.cancelLoadingDialog();
    }

    @Override
    public MinePresenter initPresenter() {
        return new MinePresenter(this);
    }


    /**
     * 登录
     */
    @Override
    public void enterLogin() {
        startActivity(new Intent(_mActivity, LoginActivity.class));
    }

    /**
     * 夺宝记录
     */
    @Override
    public void enterIndianaRecord() {
        if (ShopApplication.hasLogin && ShopApplication.userInfo != null) {
            EventBus.getDefault().post(new StartBrotherEvent(IndianaRecordFragment.newInstance()));
        } else {
            startActivity(new Intent(_mActivity, LoginActivity.class));
        }
    }

    /**
     * 中奖记录
     */
    @Override
    public void enterWinRecord() {
        EventBus.getDefault().post(new StartBrotherEvent(WinRecordFragment.newInstance()));
    }

    /**
     * 夺宝攻略
     */
    @Override
    public void enterIndianaRaiders() {

    }

    /**
     * 晒单
     */
    @Override
    public void enterMineShare() {
        EventBus.getDefault().post(new StartBrotherEvent(MyShareOrderFragment.newInstance()));
    }

    /**
     * 我的红包
     */
    @Override
    public void enterMineRed() {
        EventBus.getDefault().post(new StartBrotherEvent(MineRedFragment.newInstance(true, null)));
    }

    /**
     * 我的收货地址
     */
    @Override
    public void enterReceiverAddress() {
        EventBus.getDefault().post(new StartBrotherEvent(MineReceiverAddressFragment.newInstance()));
    }

    /**
     * 服务中心
     */
    @Override
    public void enterServiceCenter() {

    }

    /**
     * 分享app
     */
    @Override
    public void enterShareApp() {

    }

    /**
     * 设置
     */
    @Override
    public void enterSetting() {
        EventBus.getDefault().post(new StartBrotherEvent(SettingFragment.newInstance()));
    }


    @Override
    public void enterRecharge() {
        EventBus.getDefault().post(new StartBrotherEvent(RechargeFragment.newInstance()));
    }

    @Override
    public void enterMyCollect() {
        EventBus.getDefault().post(new StartBrotherEvent(MineCollectFragment.newInstance()));
    }

    /**
     * 我的银行卡
     */
    @Override
    public void enterMineBankCard() {
        EventBus.getDefault().post(new StartBrotherEvent(BankListFragment.newInstance()));
    }

    @Override
    public void enterDrawCashRecord() {
        EventBus.getDefault().post(new StartBrotherEvent(CashRecordFragment.newInstance()));
    }

    @Override
    public void enterExchangeRecord() {
        EventBus.getDefault().post(new StartBrotherEvent(ExchangeRecordFragment.newInstance()));
    }

    @Override
    public void enterBindPhone() {
        EventBus.getDefault().post(new StartBrotherEvent(BindPhoneFragment.newInstance()));
    }

    @Override
    public void showLoadingPage() {
        try {
            statusLayout.showLoading();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showContentPage() {
        try {
            statusLayout.showContent();
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
    public void refreshComplite() {
        try {
            ptrLayout.refreshComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showMessage(String message) {
        ToastUtils.showShort(message);
    }

    @Override
    public void bindMineInfo(MineInfo mineInfo) {
        try {
            ShopApplication.userInfo.setMoney(mineInfo.getMoney());
            SharedPreferencesUtil.putString(_mActivity, ShopApplication.USER_INFO_KEY, new Gson().toJson(ShopApplication.userInfo));
            winTime.setText(mineInfo.getWin_times() + "次");
            commission.setText("0");
            coin.setText(PriceUtil.getPrice(mineInfo.getMoney()));

            todayJoin.setText("今日参与：" + mineInfo.getToday_buy() + "次");
            todayCommission.setText("今日佣金：0");
            todayWin.setText("今日获胜：" + mineInfo.getToday_win() + "次");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void enterExchange() {
        try {
            EventBus.getDefault().post(new StartBrotherEvent(ExchangeFragment.newInstance()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void enterDrawCash() {
        try {
            EventBus.getDefault().post(new StartBrotherEvent(CashFragment.newInstance()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener retryListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            presenter.getMineInfo(true);
        }
    };

    @Override
    public void hasLogin() {
        username.setText(ShopApplication.userInfo.getNickname().isEmpty() ? ShopApplication.userInfo.getMobile() : ShopApplication.userInfo.getNickname());
        userAvater.loadCircleImage(ShopApplication.userInfo.getAvatar(), R.drawable.ic_default_avater);
        userId.setText("用户ID:" + ShopApplication.userInfo.getUser_id());
        ptrLayout.autoRefresh();
    }

    @Override
    public void hasNoLogin() {
        username.setText(R.string.please_login);
        userAvater.loadLocalCircleImage(R.drawable.ic_default_avater, R.drawable.ic_default_avater);
        userId.setText("用户ID:0");
        winTime.setText(PriceUtil.getPrice(0));
        coin.setText(PriceUtil.getPrice(0));
        commission.setText("0");
        todayCommission.setText("今日佣金：0");
        todayJoin.setText("今日参与：0");
        todayWin.setText("今日获胜：0");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeUserAvater(ChooseAvaterResultEvent event) {
        userAvater.loadCircleImage("file://" + event.avaterPath, R.drawable.ic_default_avater);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRegisterSuccess(RegisterSuccessEvent event) {
        if (event != null) {
            hasLogin();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginOut(LoginOutEvent event) {
        hasNoLogin();
    }

    @Subscribe
    public void reshowMinePage(TabSelectedEvent event) {
        try {
            ptrLayout.autoRefresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void refreshMine(RefreshMineEvent event) {
        try {
            ptrLayout.autoRefresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
        unbinder.unbind();
    }
}
