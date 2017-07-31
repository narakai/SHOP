package wiki.scene.shop.ui.indiana;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import butterknife.ButterKnife;
import wiki.scene.shop.R;

/**
 * 商品详情选择数量的popupwindow
 * Created by scene on 17-7-31.
 */

public class ChooseGoodsNumberPopupWindow extends PopupWindow implements View.OnClickListener {
    private Context context;

    private ImageView btnClose;
    private TextView btnNumber1;
    private TextView btnNumber5;
    private TextView btnNumber10;
    private TextView btnNumber20;
    private TextView btnNumber50;
    private TextView btnNumber100;
    private TextView btnNumberRest;
    private EditText editNumberOthers;
    private TextView immediately_indiana;


    public ChooseGoodsNumberPopupWindow(Context context) {
        super(context);
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mView = inflater.inflate(R.layout.pop_choose_goods_number, null);
        this.setContentView(mView);
        ButterKnife.bind(mView);
        init();
        initView(mView);
        setListener();
    }

    private void init() {
        //设置PopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置PopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置PopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.pop_animation);
        //设置SelectPicPopupWindow弹出窗体的背景
        ColorDrawable dw = new ColorDrawable(0x00000000);
        this.setBackgroundDrawable(dw);
    }

    private void setListener() {
        btnClose.setOnClickListener(this);
    }

    private void initView(View view) {
        btnClose = (ImageView) view.findViewById(R.id.btn_close);
    }

    private void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
        lp.alpha = bgAlpha;
        ((Activity) context).getWindow().setAttributes(lp);
    }

    @Override
    public void dismiss() {
        if (this.isShowing()) {
            setBackgroundAlpha(1.0f);
            super.dismiss();
        }
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        setBackgroundAlpha(0.5f);
        super.showAtLocation(parent, gravity, x, y);
    }

    public void show(View view) {
        showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_close:
                dismiss();
                break;
        }
    }
}
