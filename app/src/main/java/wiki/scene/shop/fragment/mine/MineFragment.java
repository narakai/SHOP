package wiki.scene.shop.fragment.mine;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yuyh.library.imgsel.ImageLoader;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;
import wiki.scene.loadmore.utils.SceneLogUtil;
import wiki.scene.shop.R;
import wiki.scene.shop.activity.LoginActivity;
import wiki.scene.shop.config.AppConfig;
import wiki.scene.shop.event.ChooseAvaterResultEvent;
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

    private ProgressDialog progressDialog;

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
        Glide.with(this).load("http://img1.imgtn.bdimg.com/it/u=3795770905,4042765228&fm=26&gp=0.jpg")
                .bitmapTransform(new CropCircleTransformation(_mActivity))
                .into(userAvater);
    }

    private void initView() {
        progressDialog = new ProgressDialog(_mActivity);
        progressDialog.setMessage("正在上传");
    }

    @OnClick(R.id.user_avater)
    public void onClickLogin() {

    }

    @OnClick(R.id.user_avater)
    public void onClickUserAvater() {
        chooseAvater(userAvater);
    }

    private ImageLoader loader = new ImageLoader() {
        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    };

    public void chooseAvater(View view) {
        ImgSelConfig config = new ImgSelConfig.Builder(_mActivity, loader)
                // 是否多选
                .multiSelect(false)
                .btnText("Confirm")
                // 确定按钮文字颜色
                .btnTextColor(Color.WHITE)
                // 使用沉浸式状态栏
                .statusBarColor(getResources().getColor(R.color.colorPrimary))
                // 返回图标ResId
                .backResId(R.drawable.ic_toolbar_back)
                .title("Images")
                .titleColor(Color.WHITE)
                .titleBgColor(getResources().getColor(R.color.colorPrimary))
                .allImagesText("全部图片")
                .needCrop(false)
                .cropSize(1, 1, 200, 200)
                // 第一个是否显示相机
                .needCamera(true)
                .build();

        ImgSelActivity.startActivity(_mActivity, config, AppConfig.CHOOSE_AVATER_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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
        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null) {
            progressDialog.cancel();
        }
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeUserAvater(ChooseAvaterResultEvent event) {
        SceneLogUtil.e("changeUserAvater");
        Glide.with(this).load("file://" + event.avaterPath)
                .bitmapTransform(new CropCircleTransformation(_mActivity))
                .into(userAvater);
        Luban.with(_mActivity).load(new File(event.avaterPath)).setCompressListener(new OnCompressListener() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(File file) {
                SceneLogUtil.e("压缩完成");
                hideLoading();
                Glide.with(_mActivity).load(file.getAbsolutePath())
                        .bitmapTransform(new CropCircleTransformation(_mActivity))
                        .into(userAvater);
            }

            @Override
            public void onError(Throwable e) {
                SceneLogUtil.e("压缩失败");
                hideLoading();
            }
        }).launch();
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }
}
