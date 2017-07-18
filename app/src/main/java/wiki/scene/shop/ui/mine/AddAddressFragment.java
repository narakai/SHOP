package wiki.scene.shop.ui.mine;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.suke.widget.SwitchButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wiki.scene.shop.R;
import wiki.scene.shop.entity.AddressInfo;
import wiki.scene.shop.mvp.BaseBackMvpFragment;
import wiki.scene.shop.ui.mine.mvpview.IAddAddressView;
import wiki.scene.shop.ui.mine.presenter.AddAddressPresenter;
import wiki.scene.shop.utils.ToastUtils;

/**
 * Case By:添加或者修改收货地址
 * package:wiki.scene.shop.ui.mine
 * Author：scene on 2017/6/30 11:14
 */

public class AddAddressFragment extends BaseBackMvpFragment<IAddAddressView, AddAddressPresenter> implements IAddAddressView {
    private static final String ARG_TYPE = "type";
    private static final String ARG_ADDRESS_INFO = "address_info";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.receiver_name)
    EditText receiverName;
    @BindView(R.id.receiver_phone)
    EditText receiverPhone;
    @BindView(R.id.receiver_address)
    EditText receiverAddress;
    @BindView(R.id.default_address)
    SwitchButton defaultAddress;

    Unbinder unbinder;
    @BindView(R.id.delete)
    Button delete;

    private boolean isAdd = false;
    private AddressInfo addressInfo;

    private ProgressDialog progressDialog;

    public static AddAddressFragment newInstance(boolean isAdd, AddressInfo info) {
        Bundle args = new Bundle();
        args.putBoolean(ARG_TYPE, isAdd);
        args.putSerializable(ARG_ADDRESS_INFO, info);
        AddAddressFragment fragment = new AddAddressFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            isAdd = args.getBoolean(ARG_TYPE);
            addressInfo = (AddressInfo) args.getSerializable(ARG_ADDRESS_INFO);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine_add_address, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        if (isAdd) {
            toolbarTitle.setText(R.string.add_address);
            delete.setVisibility(View.GONE);
        } else {
            toolbarTitle.setText(R.string.edit_address);
            delete.setVisibility(View.VISIBLE);
            if (addressInfo != null) {
                receiverName.setText(addressInfo.getName());
                receiverPhone.setText(addressInfo.getMobile());
                receiverAddress.setText(addressInfo.getAddress());
                defaultAddress.setChecked(addressInfo.getIs_default() != 0);
            }
        }
        toolbarText.setText(R.string.save);
        initToolbarNav(toolbar);

        progressDialog = new ProgressDialog(_mActivity);
    }

    @OnClick(R.id.toolbar_text)
    public void onClickSave() {
        if (isAdd) {
            presenter.addAddress();
        } else {
            if (addressInfo != null)
                presenter.updateAddress(addressInfo.getId());
        }
    }

    @OnClick(R.id.delete)
    public void onClickDelete() {
        if (!isAdd&&addressInfo!=null) {
            presenter.deleteAddress(addressInfo.getId());
        }
    }

    @Override
    public void showLoading(@StringRes int resId) {
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public AddAddressPresenter initPresenter() {
        return new AddAddressPresenter(this);
    }

    @Override
    public void onDestroyView() {
        OkGo.getInstance().cancelAll();
        super.onDestroyView();
        unbinder.unbind();
    }

    private void exit() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("isNeedRefresh", true);
        setFragmentResult(RESULT_OK, bundle);
        _mActivity.onBackPressed();
    }

    @Override
    public void showProgressDialog(@StringRes int message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(_mActivity);
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.setMessage(getString(message));
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog.cancel();
        }
    }

    @Override
    public String getReceiverName() {
        return receiverName.getText().toString().trim();
    }

    @Override
    public String getReceiverPhone() {
        return receiverPhone.getText().toString().trim();
    }

    @Override
    public String getReceiverAddress() {
        return receiverAddress.getText().toString().trim();
    }

    @Override
    public int getIsDefault() {
        return defaultAddress.isChecked() ? 1 : 0;
    }

    @Override
    public void showFail(String message) {
        ToastUtils.getInstance(_mActivity).showToast(message);
    }

    @Override
    public void showFail(@StringRes int message) {
        ToastUtils.getInstance(_mActivity).showToast(message);
    }

    @Override
    public void addSuccess() {
        hideProgressDialog();
        ToastUtils.getInstance(_mActivity).showToast(R.string.add_success);
        exit();
    }

    @Override
    public void updateSuccess() {
        hideProgressDialog();
        ToastUtils.getInstance(_mActivity).showToast(R.string.update_success);
        exit();
    }

    @Override
    public void deleteSuccess() {
        hideProgressDialog();
        ToastUtils.getInstance(_mActivity).showToast(R.string.delete_success);
        exit();
    }
}
