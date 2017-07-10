package wiki.scene.shop.ui.mine;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lzy.okgo.OkGo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wiki.scene.loadmore.PtrClassicFrameLayout;
import wiki.scene.loadmore.PtrDefaultHandler;
import wiki.scene.loadmore.PtrFrameLayout;
import wiki.scene.loadmore.StatusViewLayout;
import wiki.scene.loadmore.utils.PtrLocalDisplay;
import wiki.scene.shop.R;
import wiki.scene.shop.adapter.MineReceiverAddressAdapter;
import wiki.scene.shop.config.AppConfig;
import wiki.scene.shop.entity.AddressInfo;
import wiki.scene.shop.itemDecoration.SpacesItemDecoration;
import wiki.scene.shop.mvp.BaseBackMvpFragment;
import wiki.scene.shop.ui.mine.mvpview.IMineReceiverAddressView;
import wiki.scene.shop.ui.mine.presenter.MineReceiverAddressPresenter;
import wiki.scene.shop.utils.ToastUtils;

/**
 * Case By:我的收货地址列表
 * package:wiki.scene.shop.ui.mine
 * Author：scene on 2017/6/30 09:59
 */

public class MineReceiverAddressFragment extends BaseBackMvpFragment<IMineReceiverAddressView, MineReceiverAddressPresenter> implements IMineReceiverAddressView, MineReceiverAddressAdapter.OnReceiverAddressListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptrLayout)
    PtrClassicFrameLayout ptrLayout;
    @BindView(R.id.status_layout)
    StatusViewLayout statusLayout;

    Unbinder unbinder;

    //adapter
    private List<AddressInfo> list = new ArrayList<>();
    private MineReceiverAddressAdapter adapter;

    private ProgressDialog progressDialog;

    public static MineReceiverAddressFragment newInstance() {
        Bundle args = new Bundle();
        MineReceiverAddressFragment fragment = new MineReceiverAddressFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine_receiver_address, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        toolbarTitle.setText(R.string.mine_receiver_address);
        initToolbarNav(toolbar);
        initView();
        presenter.getAddressList(false);
    }

    private void initView() {
        ptrLayout.setLastUpdateTimeRelateObject(this);
        ptrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.getAddressList(true);
            }
        });
        adapter = new MineReceiverAddressAdapter(_mActivity, list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(PtrLocalDisplay.dp2px(13.33f)));
        adapter.setReceiverAddressListener(this);
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.add_address)
    public void onClickAddAddress() {
        startForResult(AddAddressFragment.newInstance(true, null), AppConfig.ADD_ADDRESS_REQUEST_CODE);
    }

    @Override
    public void showLoading() {
        statusLayout.showLoading();
    }

    @Override
    public void hideLoading() {
        statusLayout.showContent();
        ptrLayout.refreshComplete();
    }

    @Override
    public MineReceiverAddressPresenter initPresenter() {
        return new MineReceiverAddressPresenter(this);
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (data != null) {
            if (resultCode == RESULT_OK && requestCode == AppConfig.ADD_ADDRESS_REQUEST_CODE) {
                ptrLayout.autoRefresh();
            }
        }
    }

    @Override
    public void getAddressSuccess(List<AddressInfo> data, boolean isRefresh) {
        if (isRefresh) {
            this.list.clear();
        }
        list.addAll(data);
        adapter.notifyDataSetChanged();
        ptrLayout.refreshComplete();
        statusLayout.showContent();
    }

    @Override
    public void refreshFail() {
        ptrLayout.refreshComplete();
    }

    @Override
    public void loadFail() {
        statusLayout.showFailed(retryListener);
    }

    @Override
    public void deleteFail() {
        ToastUtils.getInstance(_mActivity).showToast("删除失败");
    }

    @Override
    public void deleteSuccess(int position) {
        ToastUtils.getInstance(_mActivity).showToast("删除成功");
        list.remove(position);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showProgressDialog(@StringRes int resId) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(_mActivity);
        }
        progressDialog.setMessage(getString(resId));
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog.cancel();
        }
    }

    @Override
    public void setDefaultFail() {
        ToastUtils.getInstance(_mActivity).showToast("默认地址设置失败");
    }

    @Override
    public void setDefaultSuccess(int position) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setIs_default(i == position ? 1 : 0);
        }
        adapter.notifyDataSetChanged();
    }

    private View.OnClickListener retryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.getAddressList(false);
        }
    };

    @Override
    public void onDestroyView() {
        OkGo.getInstance().cancelAll();
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onItemClickSetDefaultAddress(int position) {
        if(list.get(position).getIs_default()!=1){
            presenter.setDefaultAddress(list.get(position).getId(),position);
        }
    }

    @Override
    public void onItemClickEdit(int position) {
        startForResult(AddAddressFragment.newInstance(false, list.get(position)), AppConfig.ADD_ADDRESS_REQUEST_CODE);
    }

    @Override
    public void onItemClickDelete(int position) {
        presenter.deleteAddress(list.get(position).getId(), position);
    }
}
