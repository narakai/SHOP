package wiki.scene.shop.widgets;

import android.app.Dialog;
import android.content.Context;

import com.zhl.cbdialog.CBDialogBuilder;

/**
 * Case By:加载对话框
 * package:wiki.scene.shop.widgets
 * Author：scene on 2017/7/17 10:34
 */

public class LoadingDialog {
    private Context context;
    private Dialog dialog;
    private CBDialogBuilder dialogBuilder;

    private static LoadingDialog instance;

    private LoadingDialog(Context context) {
        this.context = context;
    }

    public static LoadingDialog getInstance(Context context) {    //对获取实例的方法进行同步
        if (instance == null) {
            synchronized (LoadingDialog.class) {
                if (instance == null)
                    instance = new LoadingDialog(context);
            }
        }
        return instance;
    }

    public void showLoadingDialog(String message) {
        if (dialogBuilder == null) {
            dialogBuilder = new CBDialogBuilder(context, CBDialogBuilder.DIALOG_STYLE_PROGRESS, 0.5f)
                    .showCancelButton(true)
                    .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM);
        }
        dialogBuilder.setMessage(message);
        if (dialog == null) {
            dialog = dialogBuilder.create();
        }
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    public void cancelLoadingDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.cancel();
            dialog.dismiss();
        }
    }
}