package wiki.scene.shop.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wiki.scene.shop.R;
import wiki.scene.shop.activity.presenter.Register1Presenter;
import wiki.scene.shop.activity.mvpview.IRegister1View;
import wiki.scene.shop.event.RegisterSuccessEvent;
import wiki.scene.shop.mvp.BaseMvpActivity;

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

    private Unbinder unbinder;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);
        unbinder = ButterKnife.bind(this);
        initToolbar();
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
        progressDialog = new ProgressDialog(Register1Activity.this);
        progressDialog.setMessage(getString(R.string.is_get_verification));
    }

    @OnClick(R.id.next_step)
    public void onClickNextStep() {
        presenter.enterNextStep();
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
    public void enterNextStep() {
        Intent intent = new Intent(Register1Activity.this, Register2Activity.class);
        startActivity(intent);
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
        super.onDestroy();
        unbinder.unbind();
    }
}
