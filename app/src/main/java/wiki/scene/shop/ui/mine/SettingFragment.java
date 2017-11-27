package wiki.scene.shop.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.suke.widget.SwitchButton;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.config.AppConfig;
import wiki.scene.shop.event.LoginOutEvent;
import wiki.scene.shop.mvp.BaseBackMvpFragment;
import wiki.scene.shop.ui.mine.mvpview.ISettingView;
import wiki.scene.shop.ui.mine.presenter.SettingPresenter;
import wiki.scene.shop.utils.GlideCacheUtil;
import wiki.scene.shop.utils.SharedPreferencesUtil;
import wiki.scene.shop.utils.UpdatePageUtils;
import wiki.scene.shop.widgets.LoadingDialog;

/**
 * Case By:设置
 * package:wiki.scene.shop.ui.mine
 * Author：scene on 2017/6/30 13:53
 */

public class SettingFragment extends BaseBackMvpFragment<ISettingView, SettingPresenter> implements ISettingView {
    @BindView(R.id.message_notice_status)
    SwitchButton messageNoticeStatus;
    @BindView(R.id.cache_size)
    TextView cacheSize;
    @BindView(R.id.add_wish_goods)
    TextView addWishGoods;
    @BindView(R.id.bug_feedback)
    TextView bugFeedback;
    @BindView(R.id.user_agreement)
    TextView userAgreement;
    @BindView(R.id.about_us)
    TextView aboutUs;
    Unbinder unbinder;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.exit_login)
    Button exitLogin;

    private LoadingDialog loadingDialog;

    public static SettingFragment newInstance() {
        Bundle args = new Bundle();
        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine_setting, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        toolbarTitle.setText(R.string.setting);
        initToolbarNav(toolbar);
        initView();
        UpdatePageUtils.updatePagePosition(AppConfig.POSITION_SETTING, 0);
    }

    private void initView() {
        cacheSize.setText(GlideCacheUtil.getInstance().getCacheSize(_mActivity));
        loadingDialog = LoadingDialog.getInstance(_mActivity);
        if (ShopApplication.hasLogin) {
            exitLogin.setVisibility(View.VISIBLE);
        } else {
            exitLogin.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.clear_cache)
    public void onClickClearCache() {
        showLoading(R.string.clear_caching);
//        GlideCacheUtil.getInstance().cleanCacheDisk(_mActivity);
//        GlideCacheUtil.getInstance().clearCacheDiskSelf(_mActivity);
        cacheSize.postDelayed(new Runnable() {
            @Override
            public void run() {
                _mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideLoading();
                    }
                });
            }
        }, 2000);
        cacheSize.setText("0M");
    }

    @OnClick(R.id.add_wish_goods)
    public void onClickAddWishGoods() {
        start(new AddWishGoodsFragment());
    }

    @OnClick(R.id.bug_feedback)
    public void onClickBugFeedback() {
        start(new BugFeedbackFragment());
    }

    @OnClick(R.id.exit_login)
    public void onClickExitLogin() {
        presenter.loginOut(_mActivity);
    }

    @OnClick(R.id.user_agreement)
    public void onClickUserAgreement() {
        try {
            startActivity(new Intent(_mActivity, UserAgreementActivity.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public SettingPresenter initPresenter() {
        return new SettingPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void loginOut() {
        ShopApplication.hasLogin = false;
        ShopApplication.userInfo = null;
        SharedPreferencesUtil.deleteByKey(getContext(), ShopApplication.USER_INFO_KEY);
        EventBus.getDefault().post(new LoginOutEvent());
        SharedPreferencesUtil.deleteByKey(getContext(),"password");
        _mActivity.onBackPressed();
    }
}
