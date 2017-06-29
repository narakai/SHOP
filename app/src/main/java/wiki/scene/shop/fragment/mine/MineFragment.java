package wiki.scene.shop.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import wiki.scene.shop.R;
import wiki.scene.shop.activity.LoginActivity;
import wiki.scene.shop.fragment.mine.mvpview.IMineView;
import wiki.scene.shop.fragment.mine.presenter.MinePresenter;
import wiki.scene.shop.mvp.BaseMainMvpFragment;

/**
 * Case By:我的
 * package:wiki.scene.shop.fragment.indiana
 * Author：scene on 2017/6/26 14:13
 */
public class MineFragment extends BaseMainMvpFragment<IMineView, MinePresenter> implements IMineView {

    @BindView(R.id.user_avater)
    ImageView userAvater;
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
        // http://img1.imgtn.bdimg.com/it/u=3795770905,4042765228&fm=26&gp=0.jpg
        Glide.with(this).load("http://img1.imgtn.bdimg.com/it/u=3795770905,4042765228&fm=26&gp=0.jpg")
                .bitmapTransform(new CropCircleTransformation(_mActivity))
                .into(userAvater);
    }

    @OnClick(R.id.user_avater)
    public void onClickLogin() {

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
        presenter.recharge();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

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

    }

    /**
     * 中奖记录
     */
    @Override
    public void enterWinRecord() {

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

    }

    /**
     * 我的红包
     */
    @Override
    public void enterMineRed() {

    }

    /**
     * 我的收货地址
     */
    @Override
    public void enterReceiverAddress() {

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

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
