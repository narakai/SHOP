package wiki.scene.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.lzy.okgo.OkGo;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wiki.scene.loadmore.utils.SceneLogUtil;
import wiki.scene.shop.R;
import wiki.scene.shop.activity.mvpview.IFindPasswordView;
import wiki.scene.shop.activity.presenter.FindPasswordPresenter;
import wiki.scene.shop.config.AppConfig;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.mvp.BaseMvpActivity;
import wiki.scene.shop.utils.ToastUtils;
import wiki.scene.shop.utils.UpdatePageUtils;
import wiki.scene.shop.widgets.LoadingDialog;

/**
 * 找回密码
 * Created by scene on 17-8-18.
 */

public class FindPasswordActivity extends BaseMvpActivity<IFindPasswordView, FindPasswordPresenter> implements IFindPasswordView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.phone_number)
    EditText phoneNumber;
    @BindView(R.id.verification)
    EditText verification;
    @BindView(R.id.get_verification)
    TextView getVerification;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.repassword)
    EditText repassword;
    @BindView(R.id.complete)
    Button complete;
    Unbinder unbinder;

    private LoadingDialog loadingDialog;
    //倒计时
    private int countDowntime = 60;
    private Timer timer;
    private TimerTask timerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);
        unbinder = ButterKnife.bind(this);
        initToolbar();
        initView();
        UpdatePageUtils.updatePagePosition(AppConfig.POSITION_FIND_PASSWORD, 0);
    }

    private void initToolbar() {
        try {
            toolbar.setNavigationIcon(R.drawable.ic_toolbar_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
            Intent intent = getIntent();
            if (intent != null) {
                String title = intent.getStringExtra("title");
                if (StringUtils.isEmpty(title)) {
                    toolbarTitle.setText("找回密码");
                } else {
                    toolbarTitle.setText("修改密码");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initView() {
        loadingDialog = LoadingDialog.getInstance(this);
    }


    @OnClick(R.id.get_verification)
    public void onClickGetCode() {
        presenter.getFindPasswordCode(phoneNumber.getText().toString().trim());
    }

    @OnClick(R.id.complete)
    public void onClickComplete() {
        presenter.resetPassword(phoneNumber.getText().toString().trim(), verification.getText().toString().trim(),
                password.getText().toString().trim(), repassword.getText().toString().trim());
    }

    @Override
    public void showLoading(@StringRes int resId) {
        try {
            loadingDialog.showLoadingDialog(getString(resId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hideLoading() {
        try {
            if (loadingDialog != null) {
                loadingDialog.cancelLoadingDialog();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public FindPasswordPresenter initPresenter() {
        return new FindPasswordPresenter(this);
    }

    @Override
    protected void onDestroy() {
        OkGo.getInstance().cancelTag(ApiUtil.FIND_PASSWORD_CODE_TAG);
        OkGo.getInstance().cancelTag(ApiUtil.FIND_PASSWORD_RESET_TAG);
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void showMessage(String message) {
        ToastUtils.getInstance(this).showToast(message);
    }

    @Override
    public void showMessage(@StringRes int resId) {
        ToastUtils.getInstance(this).showToast(resId);
    }

    @Override
    public void showCountDownTimer() {
        try {
            getVerification.setClickable(false);
            getVerification.setText(String.format(getString(R.string.retry_xx), 60));
            getVerification.setBackgroundResource(R.drawable.btn_retry);
            timer = new Timer();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if (countDowntime > 1) {
                                    countDowntime -= 1;
                                    getVerification.setText(String.format(getString(R.string.retry_xx), countDowntime));
                                } else {
                                    getVerification.setClickable(true);
                                    getVerification.setText(R.string.get_verification);
                                    getVerification.setBackgroundResource(R.drawable.bg_theme_round);
                                    if (timer != null) {
                                        timer.cancel();
                                        timer = null;
                                    }
                                    if (timerTask != null) {
                                        timerTask.cancel();
                                        timerTask = null;
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                SceneLogUtil.e("抛出View空异常");
                            }
                        }
                    });
                }
            };
            timer.schedule(timerTask, 1000, 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void resetPasswordSuccess() {
        onBackPressedSupport();
    }
}
