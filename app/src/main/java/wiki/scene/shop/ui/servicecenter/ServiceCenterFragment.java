package wiki.scene.shop.ui.servicecenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wiki.scene.shop.R;
import wiki.scene.shop.mvp.BaseMainMvpFragment;
import wiki.scene.shop.ui.servicecenter.presenter.ServiceCenterPresenter;
import wiki.scene.shop.ui.servicecenter.view.IServiceCenterView;

/**
 * 客服中心
 * Created by scene on 2017/11/6.
 */

public class ServiceCenterFragment extends BaseMainMvpFragment<IServiceCenterView, ServiceCenterPresenter> implements IServiceCenterView {
    public static ServiceCenterFragment newInstance() {
        Bundle args = new Bundle();
        ServiceCenterFragment fragment = new ServiceCenterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service_center, container, false);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }

    @Override
    public void showLoading(int resId) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public ServiceCenterPresenter initPresenter() {
        return new ServiceCenterPresenter(this);
    }
}
