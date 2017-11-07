package wiki.scene.shop.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sunfusheng.glideimageview.GlideImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
import wiki.scene.shop.utils.PriceUtil;
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
    @BindView(R.id.image_level)
    TextView imageLevel;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.level)
    TextView level;
    @BindView(R.id.score)
    TextView score;
    @BindView(R.id.coin_number)
    TextView coinNumber;

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

    @OnClick(R.id.win_record)
    public void onClickWinRecord() {
        presenter.clickWinRecord();
    }

    @OnClick(R.id.mine_collect)
    public void onClickMineCollect() {
        presenter.clickMyCollect();
    }

    @OnClick(R.id.mine_share)
    public void onClickMyShareOrder() {
        presenter.clickMineShare();
    }

    @OnClick(R.id.receiver_address)
    public void onClickReceiverAddress() {
        presenter.clickMineReceiverAddress();
    }

    @OnClick(R.id.mine_red)
    public void onClickMineRed() {
        presenter.clickMineRed();
    }

    @OnClick(R.id.setting)
    public void onClickSetting() {
        presenter.clickSetting();
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
            EventBus.getDefault().post(new StartBrotherEvent(IndianaRecordTypeFragment.newInstance()));
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
    public void hasNoLogin() {
        level.setVisibility(View.GONE);
        score.setVisibility(View.GONE);
        username.setText(R.string.please_login);
        userAvater.loadLocalCircleImage(R.drawable.ic_default_avater, R.drawable.ic_default_avater);
        imageLevel.setText(String.valueOf(1));
        coinNumber.setText(String.format(getString(R.string.coin_number), String.valueOf(0)));
    }

    @Override
    public void enterRecharge() {
        EventBus.getDefault().post(new StartBrotherEvent(RechargeFragment.newInstance()));
    }

    @Override
    public void enterMyCollect() {
        EventBus.getDefault().post(new StartBrotherEvent(MineCollectFragment.newInstance()));
    }

    @Override
    public void hasLogin() {
        level.setVisibility(View.VISIBLE);
        score.setVisibility(View.VISIBLE);
        level.setText(String.format(getString(R.string.mine_level), ShopApplication.userInfo.getLevel()));
        score.setText(String.format(getString(R.string.mine_score), ShopApplication.userInfo.getScore()));
        imageLevel.setText(String.valueOf(ShopApplication.userInfo.getLevel()));
        username.setText(ShopApplication.userInfo.getNickname().isEmpty() ? ShopApplication.userInfo.getMobile() : ShopApplication.userInfo.getNickname());
        userAvater.loadCircleImage(ShopApplication.userInfo.getAvatar(), R.drawable.ic_default_avater);
        coinNumber.setText(String.format(getString(R.string.coin_number), PriceUtil.getPrice(ShopApplication.userInfo.getMoney())));
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
    }
}
