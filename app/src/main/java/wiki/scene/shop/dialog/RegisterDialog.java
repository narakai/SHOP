package wiki.scene.shop.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;

import wiki.scene.loadmore.utils.PtrLocalDisplay;
import wiki.scene.shop.R;
import wiki.scene.shop.utils.ViewUtils;


public class RegisterDialog extends Dialog {

    public RegisterDialog(Context context) {
        super(context);
    }

    public RegisterDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    public void show() {
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (PtrLocalDisplay.SCREEN_WIDTH_PIXELS * 0.9f); // 宽度
        dialogWindow.setAttributes(lp);
        super.show();
    }

    public static class Builder {
        private Context context;
        private RegisterDialogConfirmListener listener;

        public void setListener(RegisterDialogConfirmListener listener) {
            this.listener = listener;
        }

        public Builder(Context context) {
            this.context = context;
        }

        public RegisterDialog create() {
            LayoutInflater inflater = LayoutInflater.from(context);
            final RegisterDialog dialog = new RegisterDialog(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.dialog_register, null);
            dialog.addContentView(layout, new LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            ViewUtils.setViewHeightByViewGroup(layout, (int) (PtrLocalDisplay.SCREEN_WIDTH_PIXELS * 0.9f));
            layout.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        if (listener != null) {
                            listener.onClickConfirm();
                        }
                        dialog.cancel();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            dialog.setContentView(layout);
            dialog.setCanceledOnTouchOutside(false);
            return dialog;
        }
    }

    public interface RegisterDialogConfirmListener {
        void onClickConfirm();
    }
}