package wiki.scene.shop.ui.mine.mvpview;

import android.support.annotation.StringRes;

import java.io.File;
import java.util.List;

import wiki.scene.shop.mvp.BaseView;

/**
 * 晒单
 * Created by scene on 17-8-14.
 */

public interface IShareOrderView extends BaseView {
    void showProgressDialog(String msg);

    void showProgressDialog(@StringRes int resId);

    void hideProgressDialog();

    void showMessage(@StringRes int resId);

    void showMessage(String msg);

    void shareSuccess();

    void compressSuccess(List<File> fileList);
}
