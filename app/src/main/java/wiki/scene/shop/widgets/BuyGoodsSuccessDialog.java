package wiki.scene.shop.widgets;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import wiki.scene.loadmore.utils.PtrLocalDisplay;
import wiki.scene.shop.R;

/**
 * 商品购买成功
 * Created by scene on 2017/11/21.
 */

public class BuyGoodsSuccessDialog extends Dialog {
    public BuyGoodsSuccessDialog(@NonNull Context context) {
        super(context);
    }

    public BuyGoodsSuccessDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BuyGoodsSuccessDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void show() {
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (PtrLocalDisplay.SCREEN_WIDTH_PIXELS * 0.95f); // 宽度
        dialogWindow.setAttributes(lp);
        super.show();
    }

    public static class Builder {
        private Context context;
        private TextView goonBuy;
        private TextView seeOrder;

        private OnClickBuyGoodsSuccessDialogListener onClickBuyGoodsSuccessDialogListener;

        public Builder(Context context) {
            this.context = context;
        }

        public void setOnClickBuyGoodsSuccessDialogListener(OnClickBuyGoodsSuccessDialogListener onClickBuyGoodsSuccessDialogListener) {
            this.onClickBuyGoodsSuccessDialogListener = onClickBuyGoodsSuccessDialogListener;
        }

        public BuyGoodsSuccessDialog create() {
            LayoutInflater inflater = LayoutInflater.from(context);
            BuyGoodsSuccessDialog dialog = new BuyGoodsSuccessDialog(context, R.style.alert_dialog);
            View view = inflater.inflate(R.layout.dialog_buy_goods_success, null);
            dialog.addContentView(view, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            goonBuy = (TextView) view.findViewById(R.id.goon_buy);
            seeOrder = (TextView) view.findViewById(R.id.see_order);
            goonBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onClickBuyGoodsSuccessDialogListener != null) {
                        onClickBuyGoodsSuccessDialogListener.onClickGoonBuy();
                    }
                }
            });
            seeOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onClickBuyGoodsSuccessDialogListener != null) {
                        onClickBuyGoodsSuccessDialogListener.onClickSeeOrder();
                    }
                }
            });
            dialog.setContentView(view);
            dialog.setCanceledOnTouchOutside(false);
            return dialog;
        }
    }


    public interface OnClickBuyGoodsSuccessDialogListener {
        void onClickGoonBuy();

        void onClickSeeOrder();
    }
}
