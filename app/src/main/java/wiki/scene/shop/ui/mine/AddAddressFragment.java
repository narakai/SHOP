package wiki.scene.shop.ui.mine;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wiki.scene.shop.R;
import wiki.scene.shop.mvp.BaseBackMvpFragment;
import wiki.scene.shop.ui.mine.mvpview.IAddAddressView;
import wiki.scene.shop.ui.mine.presenter.AddAddressPresenter;

/**
 * Case By:添加或者修改收货地址
 * package:wiki.scene.shop.ui.mine
 * Author：scene on 2017/6/30 11:14
 */

public class AddAddressFragment extends BaseBackMvpFragment<IAddAddressView, AddAddressPresenter> implements IAddAddressView {

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

    Unbinder unbinder;

    private ProgressDialog progressDialog;

    public static AddAddressFragment newInstance() {
        Bundle args = new Bundle();
        AddAddressFragment fragment = new AddAddressFragment();
        fragment.setArguments(args);
        return fragment;
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

        toolbarTitle.setText(R.string.add_address);
        toolbarText.setText(R.string.save);
        initToolbarNav(toolbar);

        progressDialog = new ProgressDialog(_mActivity);
        progressDialog.setMessage("加载中...");
    }

    @OnClick(R.id.toolbar_text)
    public void onClickSave() {
        exit();
        _mActivity.onBackPressed();
    }

    @Override
    public void showLoading() {
        if (progressDialog != null && !progressDialog.isShowing())
            progressDialog.show();
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null)
            progressDialog.cancel();
    }

    @Override
    public AddAddressPresenter initPresenter() {
        return new AddAddressPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void exit() {
        Bundle bundle = new Bundle();
        bundle.putString("aa", "bb");
        setFragmentResult(RESULT_OK, bundle);
    }

}
