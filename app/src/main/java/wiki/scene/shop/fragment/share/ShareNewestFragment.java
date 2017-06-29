package wiki.scene.shop.fragment.share;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wiki.scene.shop.R;
import wiki.scene.shop.fragment.share.mvpview.IShareNewestView;
import wiki.scene.shop.fragment.share.presenter.ShareNewestPrsenter;
import wiki.scene.shop.mvp.BaseMvpFragment;

/**
 * Case By:最新
 * package:wiki.scene.shop.fragment.share
 * Author：scene on 2017/6/29 11:57
 */

public class ShareNewestFragment extends BaseMvpFragment<IShareNewestView, ShareNewestPrsenter> implements IShareNewestView {

    public static ShareNewestFragment newInstance() {
        Bundle args = new Bundle();
        ShareNewestFragment fragment = new ShareNewestFragment();
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
        View view = inflater.inflate(R.layout.fragment_share_newest, container, false);
        return view;
    }

    @Override
    public ShareNewestPrsenter initPresenter() {
        return new ShareNewestPrsenter(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
