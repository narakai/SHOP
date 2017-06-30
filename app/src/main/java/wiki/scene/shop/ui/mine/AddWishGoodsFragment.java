package wiki.scene.shop.ui.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wiki.scene.shop.R;
import wiki.scene.shop.mvp.BaseBackMvpFragment;
import wiki.scene.shop.ui.mine.mvpview.IAddWishGoodsView;
import wiki.scene.shop.ui.mine.presenter.AddWishGoodsPresenter;

/**
 * Case By:
 * package:wiki.scene.shop.ui.mine
 * Author：scene on 2017/6/30 15:19
 */

public class AddWishGoodsFragment extends BaseBackMvpFragment<IAddWishGoodsView, AddWishGoodsPresenter> implements IAddWishGoodsView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.content)
    EditText content;
    @BindView(R.id.submit)
    Button submit;
    Unbinder unbinder;

    public static AddWishGoodsFragment newInstance() {
        Bundle args = new Bundle();
        AddWishGoodsFragment fragment = new AddWishGoodsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_wish_goods, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        toolbarTitle.setText(R.string.add_wish_goods);
        initToolbarNav(toolbar);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public AddWishGoodsPresenter initPresenter() {
        return new AddWishGoodsPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
