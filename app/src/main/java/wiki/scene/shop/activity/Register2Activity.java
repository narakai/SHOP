package wiki.scene.shop.activity;

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
import wiki.scene.shop.activity.mvpview.IRegister2View;
import wiki.scene.shop.activity.presenter.Register2Presenter;
import wiki.scene.shop.entity.UserInfo;
import wiki.scene.shop.event.RegisterSuccessEvent;
import wiki.scene.shop.mvp.BaseMvpActivity;
import wiki.scene.shop.utils.ToastUtils;
import wiki.scene.shop.widgets.LoadingDialog;

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

    private LoadingDialog loadingDialog;

    private String phoneNumber;
    private String code;

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
        toolbarTitle.setText(getString(R.string.register));
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initView() {
        loadingDialog=LoadingDialog.getInstance(this);
        phoneNumber = getIntent().getStringExtra("phone");
        code = getIntent().getStringExtra("code");
    }

    @OnClick(R.id.complete)
    public void onClickComplete() {
        presenter.setPassword(phoneNumber,code);
    }


    @Override
    public void showLoading(@StringRes int resId) {
       loadingDialog.showLoadingDialog(getString(resId));
    }

    @Override
    public void hideLoading() {
      loadingDialog.cancelLoadingDialog();
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
    public void registerSuccess(UserInfo userInfo) {
        EventBus.getDefault().post(new RegisterSuccessEvent(userInfo));
        onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
