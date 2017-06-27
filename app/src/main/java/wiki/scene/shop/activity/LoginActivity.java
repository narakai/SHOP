package wiki.scene.shop.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wiki.scene.shop.R;
import wiki.scene.shop.activity.presenter.LoginPresenter;
import wiki.scene.shop.activity.view.ILoginView;
import wiki.scene.shop.mvp.BaseMvpActivity;
import wiki.scene.shop.utils.ToastUtils;

/**
 * Case By:登录
 * package:wiki.scene.shop.activity
 * Author：scene on 2017/6/27 10:48
 */

public class LoginActivity extends BaseMvpActivity<ILoginView, LoginPresenter> implements ILoginView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    private Unbinder unbinder;
    @BindView(R.id.phone_number)
    TextView phoneNumber;
    @BindView(R.id.password)
    TextView password;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        unbinder = ButterKnife.bind(this);
        initToolbar();
        initView();
    }

    @Override
    public LoginPresenter initPresenter() {
        return new LoginPresenter(this);
    }

    private void initToolbar() {
        toolbarTitle.setText(R.string.login);
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initView() {
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage(getString(R.string.is_logining));
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
    public String getPhoneNumber() {
        return phoneNumber.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return password.getText().toString().trim();
    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public void showFailInfo(String failInfo) {
        ToastUtils.getInstance(LoginActivity.this).showToast(failInfo);
    }

    @Override
    public void showFailInfo(@IdRes int resId) {
        ToastUtils.getInstance(LoginActivity.this).showToast(resId);
    }

    @Override
    public void enterRegisterActivity() {
        Intent intent = new Intent(LoginActivity.this, Register1Activity.class);
        startActivity(intent);
    }

    @Override
    public void enterLosePasswordActivity() {

    }

    @OnClick(R.id.login)
    public void onClickLogin() {
        presenter.login();
    }

    @OnClick(R.id.register)
    public void onClickRegister() {
        presenter.enterRegisterActivity();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
