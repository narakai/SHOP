package wiki.scene.shop.mvp;

import android.os.Bundle;

import wiki.scene.shop.base.BaseBackFragment;
import wiki.scene.shop.base.BaseMainFragment;

/**
 * Case By:mvp模式带返回的fragment
 * package:wiki.scene.shop.mvp
 * Author：scene on 2017/6/27 16:46
 */

public abstract class BaseBackMvpFragment<V, T extends BasePresenter<V>> extends BaseBackFragment {
    public T presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attach((V) this);
    }

    @Override
    public void onDestroyView() {
        hideSoftInput();
        presenter.dettach();
        super.onDestroyView();
    }

    // 实例化presenter
    public abstract T initPresenter();
}
