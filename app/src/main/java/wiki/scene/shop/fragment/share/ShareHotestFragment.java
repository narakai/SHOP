package wiki.scene.shop.fragment.share;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wiki.scene.shop.R;
import wiki.scene.shop.fragment.share.mvpview.IShareHotestView;
import wiki.scene.shop.fragment.share.presenter.ShareHotestPrsenter;
import wiki.scene.shop.mvp.BaseMvpFragment;

/**
 * Case By:最热
 * package:wiki.scene.shop.fragment.share
 * Author：scene on 2017/6/29 11:57
 */

public class ShareHotestFragment extends BaseMvpFragment<IShareHotestView, ShareHotestPrsenter> implements IShareHotestView {

    public static ShareHotestFragment newInstance() {
        Bundle args = new Bundle();
        ShareHotestFragment fragment = new ShareHotestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_share_hotest, container, false);
        return view;
    }

    @Override
    public ShareHotestPrsenter initPresenter() {
        return new ShareHotestPrsenter(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


}
