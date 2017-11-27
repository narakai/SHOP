package wiki.scene.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wiki.scene.loadmore.utils.SceneLogUtil;
import wiki.scene.shop.MainActivity;
import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.activity.mvpview.IRegister1View;
import wiki.scene.shop.activity.presenter.Register1Presenter;
import wiki.scene.shop.config.AppConfig;
import wiki.scene.shop.entity.PasswordInfo;
import wiki.scene.shop.entity.UserInfo;
import wiki.scene.shop.event.RegisterSuccessEvent;
import wiki.scene.shop.mvp.BaseMvpActivity;
import wiki.scene.shop.ui.mine.UserAgreementActivity;
import wiki.scene.shop.utils.SharedPreferencesUtil;
import wiki.scene.shop.utils.ToastUtils;
import wiki.scene.shop.utils.UpdatePageUtils;
import wiki.scene.shop.widgets.LoadingDialog;

/**
 * Case By:注册第一步
 * package:wiki.scene.shop.activity
 * Author：scene on 2017/6/27 14:19
 */

public class Register1Activity extends BaseMvpActivity<IRegister1View, Register1Presenter> implements IRegister1View {

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
    @BindView(R.id.agree)
    CheckBox agree;
    @BindView(R.id.password)
    EditText password;

    private Unbinder unbinder;

    private LoadingDialog loadingDialog;

    private int countDowntime = 60;
    private Timer timer;
    private TimerTask timerTask;

    private String unionid;
    private String nickName;
    private int sex;
    private String avaterPath;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);
        unbinder = ButterKnife.bind(this);
        initToolbar();
        UpdatePageUtils.updatePagePosition(AppConfig.POSITION_REGISTER, 0);
        if (getIntent() != null) {
            unionid = getIntent().getStringExtra("unionid");
            nickName = getIntent().getStringExtra("nickName");
            sex = getIntent().getIntExtra("sex", 0);
            avaterPath = getIntent().getStringExtra("avaterPath");
            type = getIntent().getIntExtra("type", 0);
        }
        initView();
    }

    @Override
    public Register1Presenter initPresenter() {
        return new Register1Presenter(this);
    }

    private void initToolbar() {
        toolbarTitle.setText("注册");
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initView() {
        loadingDialog = LoadingDialog.getInstance(this);
    }

    @OnClick(R.id.next_step)
    public void onClickNextStep() {
        if (type != 0) {
            presenter.registerByOthers(type,getPhoneNumber(),getCode(),unionid,nickName,avaterPath,sex);
        } else {
            presenter.setPassword();
        }
    }

    @OnClick(R.id.get_verification)
    public void onClickGetVerification() {
        presenter.getVerificationCode();
    }

    @Override
    public void showLoading(@StringRes int resId) {
        loadingDialog.showLoadingDialog(getString(resId));
    }

    @Override
    public void registerSuccess(UserInfo userInfo) {
        try {
            if (userInfo != null) {
                SharedPreferencesUtil.putString(this, ShopApplication.USER_INFO_KEY, new Gson().toJson(userInfo));
                PasswordInfo passwordInfo = new PasswordInfo();
                passwordInfo.setAccount(getPhoneNumber());
                passwordInfo.setPassword(getPassword());
                SharedPreferencesUtil.putString(Register1Activity.this, "password", new Gson().toJson(passwordInfo));
                ShopApplication.userInfo = userInfo;
                ShopApplication.hasLogin = true;
                EventBus.getDefault().post(new RegisterSuccessEvent(userInfo));
            }
            Intent intent = new Intent(Register1Activity.this, MainActivity.class);
            intent.putExtra("isRegister", true);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void hideLoading() {
        loadingDialog.cancelLoadingDialog();
    }

    @Override
    public void showFail(String message) {
        ToastUtils.getInstance(Register1Activity.this).showToast(message);
    }

    @Override
    public void showFail(@StringRes int resId) {
        ToastUtils.getInstance(Register1Activity.this).showToast(resId);
    }

    @Override
    public void showCountDownTimer() {
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
    }

    @Override
    public void showLoading(String str) {
        loadingDialog.showLoadingDialog(str);
    }


    @Override
    public void enterNextStep() {
        Intent intent = new Intent(Register1Activity.this, Register2Activity.class);
        intent.putExtra("phone", getPhoneNumber());
        intent.putExtra("code", getCode());
        if (!TextUtils.isEmpty(unionid)) {
            intent.putExtra("unionid", unionid);
        }
        if (!TextUtils.isEmpty(nickName)) {
            intent.putExtra("nickName", nickName);
        }
        if (sex != 0) {
            intent.putExtra("sex", sex);
        }
        if (!TextUtils.isEmpty(avaterPath)) {
            intent.putExtra("avaterPath", avaterPath);
        }
        if (type != 0) {
            intent.putExtra("type", type);
        }
        startActivity(intent);
    }

    @Override
    public String getCode() {
        return verification.getText().toString().trim();
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return password.getText().toString().trim();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRegisterSuccess(RegisterSuccessEvent event) {
        if (event != null) {
            onBackPressed();
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.agreement)
    public void onClickAgreement() {
        try {
            startActivity(new Intent(Register1Activity.this, UserAgreementActivity.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
