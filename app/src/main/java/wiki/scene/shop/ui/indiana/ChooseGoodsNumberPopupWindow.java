package wiki.scene.shop.ui.indiana;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import butterknife.ButterKnife;
import wiki.scene.shop.R;
import wiki.scene.shop.utils.ToastUtils;

/**
 * 商品详情选择数量的popupwindow
 * Created by scene on 17-7-31.
 */

public class ChooseGoodsNumberPopupWindow extends PopupWindow implements View.OnClickListener {
    private Context context;

    private TextView text;
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

    private OnClickImmediatelyIndianaListener onClickImmediatelyIndianaListener;

    private int currentChoosedPosition = 0;

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

    public void setOnClickImmediatelyIndianaListener(OnClickImmediatelyIndianaListener onClickImmediatelyIndianaListener) {
        this.onClickImmediatelyIndianaListener = onClickImmediatelyIndianaListener;
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

    private void initView(View view) {
        text = (TextView) view.findViewById(R.id.text);
        btnClose = (ImageView) view.findViewById(R.id.btn_close);
        btnNumber1 = (TextView) view.findViewById(R.id.btn_number_1);
        btnNumber5 = (TextView) view.findViewById(R.id.btn_number_5);
        btnNumber10 = (TextView) view.findViewById(R.id.btn_number_10);
        btnNumber20 = (TextView) view.findViewById(R.id.btn_number_20);
        btnNumber50 = (TextView) view.findViewById(R.id.btn_number_50);
        btnNumber100 = (TextView) view.findViewById(R.id.btn_number_100);
        btnNumberRest = (TextView) view.findViewById(R.id.btn_number_rest);
        editNumberOthers = (EditText) view.findViewById(R.id.edit_number_others);
        immediately_indiana = (TextView) view.findViewById(R.id.immediately_indiana);

        text.setFocusable(true);
        text.setFocusableInTouchMode(true);
        text.requestFocus();
    }

    private void setListener() {
        btnClose.setOnClickListener(this);
        btnNumber1.setOnClickListener(this);
        btnNumber5.setOnClickListener(this);
        btnNumber10.setOnClickListener(this);
        btnNumber20.setOnClickListener(this);
        btnNumber50.setOnClickListener(this);
        btnNumber100.setOnClickListener(this);
        btnNumberRest.setOnClickListener(this);
        immediately_indiana.setOnClickListener(this);
        editNumberOthers.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (v == editNumberOthers && hasFocus) {
                    editNumberOthers.setBackgroundResource(R.drawable.bg_number_text_can_use);
                    choosedNumber(7);
                } else {
                    editNumberOthers.setBackgroundResource(R.drawable.bg_number_text);
                }
            }
        });
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
            case R.id.btn_number_1:
                choosedNumber(0);
                break;
            case R.id.btn_number_5:
                choosedNumber(1);
                break;
            case R.id.btn_number_10:
                choosedNumber(2);
                break;
            case R.id.btn_number_20:
                choosedNumber(3);
                break;
            case R.id.btn_number_50:
                choosedNumber(4);
                break;
            case R.id.btn_number_100:
                choosedNumber(5);
                break;
            case R.id.btn_number_rest:
                choosedNumber(6);
                break;
            case R.id.immediately_indiana:
                if (onClickImmediatelyIndianaListener != null) {
                    int number = 0;
                    switch (currentChoosedPosition) {
                        case 0:
                            number=1;
                            break;
                        case 1:
                            number=5;
                            break;
                        case 2:
                            number=10;
                            break;
                        case 3:
                            number=20;
                            break;
                        case 4:
                            number=50;
                            break;
                        case 5:number=100;
                            break;
                        case 6:
                            number=-1;
                            break;
                        case 7:
                            try{
                                number=Integer.valueOf(editNumberOthers.getText().toString().trim());
                            }catch (Exception e){
                                ToastUtils.getInstance(context).showToast(R.string.input_right_number);
                                return;
                            }
                            break;
                    }
                    onClickImmediatelyIndianaListener.onClickImmediatelyIndiana(number);
                }
                break;
        }
    }


    private void choosedNumber(int position) {
        if (position != 7) {
            text.setFocusable(true);
            text.setFocusableInTouchMode(true);
            text.requestFocus();
            hideSoftInput();
        }
        switch (currentChoosedPosition) {
            case 0:
                btnNumber1.setTextColor(context.getResources().getColor(R.color.text_color_title));
                btnNumber1.setBackgroundResource(R.drawable.bg_number_text);
                break;
            case 1:
                btnNumber5.setTextColor(context.getResources().getColor(R.color.text_color_title));
                btnNumber5.setBackgroundResource(R.drawable.bg_number_text);
                break;
            case 2:
                btnNumber10.setTextColor(context.getResources().getColor(R.color.text_color_title));
                btnNumber10.setBackgroundResource(R.drawable.bg_number_text);
                break;
            case 3:
                btnNumber20.setTextColor(context.getResources().getColor(R.color.text_color_title));
                btnNumber20.setBackgroundResource(R.drawable.bg_number_text);
                break;
            case 4:
                btnNumber50.setTextColor(context.getResources().getColor(R.color.text_color_title));
                btnNumber50.setBackgroundResource(R.drawable.bg_number_text);
                break;
            case 5:
                btnNumber100.setTextColor(context.getResources().getColor(R.color.text_color_title));
                btnNumber100.setBackgroundResource(R.drawable.bg_number_text);
                break;
            case 6:
                btnNumberRest.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                btnNumberRest.setBackgroundResource(R.drawable.bg_number_text);
                break;
        }
        switch (position) {
            case 0:
                btnNumber1.setTextColor(context.getResources().getColor(R.color.white));
                btnNumber1.setBackgroundResource(R.drawable.bg_number_text_choosed);
                break;
            case 1:
                btnNumber5.setTextColor(context.getResources().getColor(R.color.white));
                btnNumber5.setBackgroundResource(R.drawable.bg_number_text_choosed);
                break;
            case 2:
                btnNumber10.setTextColor(context.getResources().getColor(R.color.white));
                btnNumber10.setBackgroundResource(R.drawable.bg_number_text_choosed);
                break;
            case 3:
                btnNumber20.setTextColor(context.getResources().getColor(R.color.white));
                btnNumber20.setBackgroundResource(R.drawable.bg_number_text_choosed);
                break;
            case 4:
                btnNumber50.setTextColor(context.getResources().getColor(R.color.white));
                btnNumber50.setBackgroundResource(R.drawable.bg_number_text_choosed);
                break;
            case 5:
                btnNumber100.setTextColor(context.getResources().getColor(R.color.white));
                btnNumber100.setBackgroundResource(R.drawable.bg_number_text_choosed);
                break;
            case 6:
                btnNumberRest.setTextColor(context.getResources().getColor(R.color.white));
                btnNumberRest.setBackgroundResource(R.drawable.bg_number_text_choosed);
                break;
        }
        currentChoosedPosition = position;
    }

    private void hideSoftInput() {
        if (editNumberOthers != null && editNumberOthers.getContext() != null) {
            InputMethodManager imm = (InputMethodManager) editNumberOthers.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editNumberOthers.getWindowToken(), 0);
        }
    }

    public interface OnClickImmediatelyIndianaListener {
        void onClickImmediatelyIndiana(int number);
    }
}
