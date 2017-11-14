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

import com.google.gson.Gson;
import com.sunfusheng.glideimageview.GlideImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.activity.LoginActivity;
import wiki.scene.shop.event.ChooseAvaterResultEvent;
import wiki.scene.shop.event.LoginOutEvent;
import wiki.scene.shop.event.RegisterSuccessEvent;
import wiki.scene.shop.event.StartBrotherEvent;
import wiki.scene.shop.mvp.BaseMainMvpFragment;
import wiki.scene.shop.ui.mine.mvpview.IMineView;
import wiki.scene.shop.ui.mine.presenter.MinePresenter;
import wiki.scene.shop.utils.SharedPreferencesUtil;
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
        initView();
    }

    private void initView() {
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
        if (ShopApplication.hasLogin) {
            startActivity(new Intent(_mActivity, MineInfoActivity.class));
        } else {
            enterLogin();
        }
    }

    @Override
    public void hasNoLogin() {
        username.setText(R.string.please_login);
        userAvater.loadLocalCircleImage(R.drawable.ic_default_avater, R.drawable.ic_default_avater);
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
    public void hasLogin() {
        username.setText(ShopApplication.userInfo.getNickname().isEmpty() ? ShopApplication.userInfo.getMobile() : ShopApplication.userInfo.getNickname());
        userAvater.loadCircleImage(ShopApplication.userInfo.getAvatar(), R.drawable.ic_default_avater);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeUserAvater(ChooseAvaterResultEvent event) {
        userAvater.loadCircleImage("file://" + event.avaterPath, R.drawable.ic_default_avater);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRegisterSuccess(RegisterSuccessEvent event) {
        if (event != null) {
            SharedPreferencesUtil.putString(_mActivity, ShopApplication.USER_INFO_KEY, new Gson().toJson(event.getUserInfo()));
            ShopApplication.userInfo = event.getUserInfo();
            ShopApplication.hasLogin = true;
            hasLogin();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginOut(LoginOutEvent event) {
        hasNoLogin();
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
        unbinder.unbind();
    }
}
