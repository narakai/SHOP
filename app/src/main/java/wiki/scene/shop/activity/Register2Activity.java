package wiki.scene.shop.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wiki.scene.shop.R;
import wiki.scene.shop.activity.presenter.Register2Presenter;
import wiki.scene.shop.activity.view.IRegister2View;
import wiki.scene.shop.event.RegisterSuccessEvent;
import wiki.scene.shop.mvp.BaseMvpActivity;
import wiki.scene.shop.utils.ToastUtils;

/**
 * Case By:注册第二步
 * package:wiki.scene.shop.activity
 * Author：scene on 2017/6/27 15:37
 */

public class Register2Activity extends BaseMvpActivity<IRegister2View, Register2Presenter> implements IRegister2View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.repassword)
    EditText repassword;

    private Unbinder unbinder;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        unbinder = ButterKnife.bind(this);
        initToolbar();
        initView();
    }

    @Override
    public Register2Presenter initPresenter() {
        return new Register2Presenter(this);
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
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.is_registing));
    }

    @OnClick(R.id.complete)
    public void onClickComplete() {
        presenter.setPassword();
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
    public String getPassword() {
        return password.getText().toString().trim();
    }

    @Override
    public String getRePassword() {
        return repassword.getText().toString().trim();
    }

    @Override
    public void showFail(@StringRes int resId) {
        ToastUtils.getInstance(Register2Activity.this).showToast(resId);
    }

    @Override
    public void showFail(String msg) {
        ToastUtils.getInstance(Register2Activity.this).showToast(msg);
    }

    @Override
    public void registerSuccess() {
        EventBus.getDefault().post(new RegisterSuccessEvent());
        onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
