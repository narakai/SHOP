package wiki.scene.shop.ui.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.lzy.okgo.OkGo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wiki.scene.shop.R;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.mvp.BaseBackMvpFragment;
import wiki.scene.shop.ui.mine.mvpview.IAddWishGoodsView;
import wiki.scene.shop.ui.mine.presenter.AddWishGoodsPresenter;
import wiki.scene.shop.utils.ToastUtils;
import wiki.scene.shop.widgets.LoadingDialog;

/**
 * Case By:添加心愿商品
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
    Unbinder unbinder;

    private LoadingDialog loadingDialog;

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
    public void showLoading(@StringRes int resId) {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog.getInstance(getContext());
        }
        loadingDialog.showLoadingDialog(getString(resId));
    }

    @Override
    public void hideLoading() {
        if (loadingDialog != null) {
            loadingDialog.cancelLoadingDialog();
        }
    }

    @Override
    public AddWishGoodsPresenter initPresenter() {
        return new AddWishGoodsPresenter(this);
    }


    @Override
    public void showMessage(@StringRes int resId) {
        ToastUtils.getInstance(_mActivity).showToast(resId);
    }

    @Override
    public void showMessage(String message) {
        ToastUtils.getInstance(_mActivity).showToast(message);
    }

    @Override
    public void addGoodsSuccess() {
        content.setText("");
        showMessage(R.string.we_has_receiver_your_wish);
    }

    @OnClick(R.id.submit)
    public void onClickSubmit() {
        presenter.addWishGoods(content.getText().toString().trim());
    }

    @Override
    public void onDestroyView() {
        OkGo.getInstance().cancelTag(ApiUtil.ADD_WISH_GOODS_TAG);
        super.onDestroyView();
        unbinder.unbind();
    }
}
