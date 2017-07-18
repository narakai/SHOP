package wiki.scene.shop.mvp;

import android.support.annotation.StringRes;

/**
 * Case By: View基类
 * package:
 * Author：scene on 2017/6/27 10:54
 */
public interface BaseView {
    void showLoading(@StringRes int resId);

    void hideLoading();
}
