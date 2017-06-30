package wiki.scene.shop.ui.mine;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suke.widget.SwitchButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wiki.scene.shop.R;
import wiki.scene.shop.mvp.BaseBackMvpFragment;
import wiki.scene.shop.ui.mine.mvpview.ISettingView;
import wiki.scene.shop.ui.mine.presenter.SettingPresenter;
import wiki.scene.shop.utils.GlideCacheUtil;

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

    private ProgressDialog progressDialog;

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
    }

    void initView() {
        cacheSize.setText(GlideCacheUtil.getInstance().getCacheSize(_mActivity));
        progressDialog = new ProgressDialog(_mActivity);
        progressDialog.setMessage("正在清理");
    }

    @OnClick(R.id.clear_cache)
    public void onClickClearCache() {
        showLoading();
        GlideCacheUtil.getInstance().cleanCacheDisk(_mActivity);
        GlideCacheUtil.getInstance().clearCacheDiskSelf(_mActivity);
        hideLoading();
        cacheSize.setText(GlideCacheUtil.getInstance().getCacheSize(_mActivity));
    }

    @OnClick(R.id.add_wish_goods)
    public void onClickAddWishGoods() {
        start(new AddWishGoodsFragment());
    }
    @OnClick(R.id.bug_feedback)
    public void onClickBugFeedback() {
        start(new BugFeedbackFragment());
    }


    @Override
    public void showLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null)
            progressDialog.cancel();
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
}
