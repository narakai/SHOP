package wiki.scene.shop.ui.mine;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yuyh.library.imgsel.ImageLoader;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;
import wiki.scene.loadmore.utils.SceneLogUtil;
import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.config.AppConfig;
import wiki.scene.shop.event.ChooseAvaterResultEvent;
import wiki.scene.shop.mvp.BaseMvpActivity;
import wiki.scene.shop.ui.mine.mvpview.IMineInfoView;
import wiki.scene.shop.ui.mine.presenter.MineInfoPresenter;
import wiki.scene.shop.utils.ToastUtils;

/**
 * Case By:个人资料
 * package:wiki.scene.shop.fragment.mine
 * Author：scene on 2017/6/29 17:44
 */

public class MineInfoActivity extends BaseMvpActivity<IMineInfoView, MineInfoPresenter> implements IMineInfoView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.user_avater)
    ImageView userAvater;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.sex_male)
    RadioButton sexMale;
    @BindView(R.id.sex_female)
    RadioButton sexFemale;
    @BindView(R.id.sex_radiogroup)
    RadioGroup sexRadiogroup;
    @BindView(R.id.save)
    Button save;
    @BindView(R.id.phone_number)
    TextView phoneNumber;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_info);
        ButterKnife.bind(this);
        initToolbar();
        initView();
    }

    private void initToolbar() {
        toolbarTitle.setText(R.string.person_data);
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initView() {
        Glide.with(this).load(ShopApplication.userInfo.getAvatar()).error(R.drawable.ic_default_avater).into(userAvater);
        username.setText(ShopApplication.userInfo.getNickname());
        phoneNumber.setText(ShopApplication.userInfo.getMobile());
        sexRadiogroup.check(ShopApplication.userInfo.getSex() == 1 ? R.id.sex_male : R.id.sex_female);
    }

    private ImageLoader loader = new ImageLoader() {
        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    };

    public void chooseAvater() {
        ImgSelConfig config = new ImgSelConfig.Builder(MineInfoActivity.this, loader)
                // 是否多选
                .multiSelect(false)
                .btnText(getString(R.string.confirm))
                // 确定按钮文字颜色
                .btnTextColor(Color.WHITE)
                // 使用沉浸式状态栏
                .statusBarColor(getResources().getColor(R.color.colorPrimary))
                // 返回图标ResId
                .backResId(R.drawable.ic_toolbar_back)
                .title(getString(R.string.image))
                .titleColor(Color.WHITE)
                .titleBgColor(getResources().getColor(R.color.colorPrimary))
                .allImagesText(getString(R.string.all_images))
                .needCrop(false)
                .cropSize(1, 1, 200, 200)
                // 第一个是否显示相机
                .needCamera(true)
                .build();

        ImgSelActivity.startActivity(MineInfoActivity.this, config, AppConfig.CHOOSE_AVATER_REQUEST_CODE);
    }

    @OnClick(R.id.user_avater)
    public void onClickUserAvater() {
        chooseAvater();
    }

    @OnClick(R.id.save)
    public void onClickSave(){
        presenter.updateInfo();
    }

    @Override
    public void showLoading() {

    }


    @Override
    public void hideLoading() {
        if (progressDialog != null) {
            progressDialog.cancel();
        }
    }

    @Override
    public MineInfoPresenter initPresenter() {
        return new MineInfoPresenter(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (resultCode == RESULT_OK && requestCode == AppConfig.CHOOSE_AVATER_REQUEST_CODE) {
                try {
                    List<String> pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
                    if (pathList != null && pathList.size() > 0) {

                        EventBus.getDefault().post(new ChooseAvaterResultEvent(pathList.get(0)));
                        changeUserAvater(pathList.get(0));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    SceneLogUtil.e("选择头像异常了");
                }
            }
        }
    }

    public void changeUserAvater(String filePath) {
        SceneLogUtil.e("changeUserAvater");
        Glide.with(this).load("file://" + filePath)
                .bitmapTransform(new CropCircleTransformation(MineInfoActivity.this))
                .into(userAvater);
        Luban.with(MineInfoActivity.this).load(new File(filePath)).setCompressListener(new OnCompressListener() {
            @Override
            public void onStart() {
                showLoading();
            }

            @Override
            public void onSuccess(File file) {
                SceneLogUtil.e("压缩完成");
                hideLoading();
                Glide.with(MineInfoActivity.this).load(file.getAbsolutePath())
                        .bitmapTransform(new CropCircleTransformation(MineInfoActivity.this))
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
    public void showLoading(String msg) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(MineInfoActivity.this);
        }
        progressDialog.setMessage(msg);
        progressDialog.show();
    }

    @Override
    public void showLoading(@StringRes int resId) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(MineInfoActivity.this);
        }
        progressDialog.setMessage(getString(resId));
        progressDialog.show();
    }

    @Override
    public String getNickName() {
        return username.getText().toString().trim();
    }

    @Override
    public int getSex() {
        return sexMale.isChecked() ? 1 : 2;
    }

    @Override
    public void showSuccess() {

    }

    @Override
    public void showFail(String str) {
        ToastUtils.getInstance(MineInfoActivity.this).showToast(str);
    }
}
