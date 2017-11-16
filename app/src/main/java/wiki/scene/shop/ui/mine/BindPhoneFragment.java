package wiki.scene.shop.ui.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wiki.scene.shop.R;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.mvp.BaseBackMvpFragment;
import wiki.scene.shop.ui.mine.mvpview.IBindPhoneView;
import wiki.scene.shop.ui.mine.presenter.BindPhonePresenter;
import wiki.scene.shop.utils.ThreadPoolUtils;
import wiki.scene.shop.widgets.LoadingDialog;

/**
 * 绑定手机
 * Created by scene on 2017/11/15.
 */

public class BindPhoneFragment extends BaseBackMvpFragment<IBindPhoneView, BindPhonePresenter> implements IBindPhoneView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.phone_number)
    EditText phoneNumber;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.verification)
    EditText verification;
    @BindView(R.id.get_verification)
    TextView getVerification;
    @BindView(R.id.next_step)
    Button nextStep;
    Unbinder unbinder;

    private ThreadPoolUtils threadPoolUtils;
    private ScheduledFuture scheduledFuture;
    private int time = 60;

    private LoadingDialog loadingDialog;

    public static BindPhoneFragment newInstance() {
        Bundle args = new Bundle();
        BindPhoneFragment fragment = new BindPhoneFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bind_phone, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        toolbarTitle.setText("绑定手机");
        initToolbarNav(toolbar);
        loadingDialog = LoadingDialog.getInstance(getContext());
    }


    @OnClick(R.id.get_verification)
    public void onClickGetSMS() {
        presenter.getSMS();
    }

    @Override
    public void showLoading(int resId) {
        try {
            loadingDialog.showLoadingDialog(getString(resId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hideLoading() {
        try {
            loadingDialog.cancelLoadingDialog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public BindPhonePresenter initPresenter() {
        return new BindPhonePresenter(this);
    }


    @Override
    public String getPhoneNumber() {
        try {
            return phoneNumber.getText().toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public void showMessage(String message) {
        ToastUtils.showShort(message);
    }

    @Override
    public void getSMSSuccess() {
        ToastUtils.showShort("验证码已发送，请注意查收");

    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }

    @Override
    public String getCode() {
        return verification.getText().toString();
    }

    @Override
    public void resetPhoneNumberSuccess() {
        presenter.resetPhoneMumber();
    }

    private void showCountDownTimer() {
        try {
            getVerification.setClickable(false);
            getVerification.setText(String.format(getString(R.string.retry_xx), 60));
            getVerification.setBackgroundResource(R.drawable.btn_retry);
            if (scheduledFuture != null) {
                scheduledFuture.cancel(true);
            }
            if (threadPoolUtils != null) {
                threadPoolUtils.shutDownNow();
            }
            time = 60;
            threadPoolUtils = new ThreadPoolUtils(ThreadPoolUtils.SingleThread, 1);
            scheduledFuture = threadPoolUtils.scheduleWithFixedRate(new Runnable() {
                @Override
                public void run() {
                    _mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            time -= 1;
                            if (time > 0) {
                                getVerification.setText(String.format(getString(R.string.retry_xx), time));
                            } else {
                                getVerification.setClickable(true);
                                getVerification.setText(R.string.get_verification);
                                getVerification.setBackgroundResource(R.drawable.bg_theme_round);
                                cancelDownTimer();
                            }
                        }
                    });
                }
            }, 1, 1, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void cancelDownTimer() {
        try {
            if (scheduledFuture != null) {
                scheduledFuture.cancel(true);
            }
            if (threadPoolUtils != null) {
                threadPoolUtils.shutDownNow();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        cancelDownTimer();
    }

    @Override
    public void onDestroyView() {
        OkGo.getInstance().cancelTag(ApiUtil.RESET_PHONE_SMS_TAG);
        super.onDestroyView();
        unbinder.unbind();
    }
}
