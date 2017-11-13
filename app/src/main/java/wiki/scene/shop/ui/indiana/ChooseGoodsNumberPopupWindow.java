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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.ButterKnife;
import wiki.scene.shop.R;

/**
 * 商品详情选择数量的popupwindow
 * Created by scene on 17-7-31.
 */

public class ChooseGoodsNumberPopupWindow extends PopupWindow implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private Context context;
    private EditText number;
    private RadioGroup radioGroup, rg_way1, rg_way2, rg_way3_1, rg_way3_2;
    private LinearLayout rg_way3;
    private RadioButton way3_type_1;
    private RadioButton way3_type_2;
    private RadioButton way3_type_3;
    private RadioButton way3_type_4;
    private RadioButton way3_type_5;
    private RadioButton way3_type_6;
    private RadioButton way3_type_7;
    private RadioButton way3_type_8;
    private RadioButton way3_type_9;
    private RadioButton way3_type_10;

    private OnClickImmediatelyIndianaListener onClickImmediatelyIndianaListener;


    private int choosedWayType = 1;


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
        number = (EditText) view.findViewById(R.id.number);
        radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        rg_way1 = (RadioGroup) view.findViewById(R.id.rg_way1);
        rg_way2 = (RadioGroup) view.findViewById(R.id.rg_way2);
        rg_way3 = (LinearLayout) view.findViewById(R.id.rg_way3);
        rg_way3_1 = (RadioGroup) view.findViewById(R.id.rg_way3_1);
        rg_way3_2 = (RadioGroup) view.findViewById(R.id.rg_way3_2);
        way3_type_1 = (RadioButton) view.findViewById(R.id.way3_type_1);
        way3_type_2 = (RadioButton) view.findViewById(R.id.way3_type_2);
        way3_type_3 = (RadioButton) view.findViewById(R.id.way3_type_3);
        way3_type_4 = (RadioButton) view.findViewById(R.id.way3_type_4);
        way3_type_5 = (RadioButton) view.findViewById(R.id.way3_type_5);
        way3_type_6 = (RadioButton) view.findViewById(R.id.way3_type_6);
        way3_type_7 = (RadioButton) view.findViewById(R.id.way3_type_7);
        way3_type_8 = (RadioButton) view.findViewById(R.id.way3_type_8);
        way3_type_9 = (RadioButton) view.findViewById(R.id.way3_type_9);
        way3_type_10 = (RadioButton) view.findViewById(R.id.way3_type_10);
    }

    private void setListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rd_shuangren:
                        choosedWayType = 1;
                        rg_way1.setVisibility(View.VISIBLE);
                        rg_way2.setVisibility(View.GONE);
                        rg_way3.setVisibility(View.GONE);
                        break;
                    case R.id.rd_siren:
                        choosedWayType = 2;
                        rg_way1.setVisibility(View.GONE);
                        rg_way2.setVisibility(View.VISIBLE);
                        rg_way3.setVisibility(View.GONE);
                        break;
                    case R.id.rd_shiren:
                        choosedWayType = 3;
                        rg_way1.setVisibility(View.GONE);
                        rg_way2.setVisibility(View.GONE);
                        rg_way3.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
        way3_type_1.setOnCheckedChangeListener(this);
        way3_type_2.setOnCheckedChangeListener(this);
        way3_type_3.setOnCheckedChangeListener(this);
        way3_type_4.setOnCheckedChangeListener(this);
        way3_type_5.setOnCheckedChangeListener(this);
        way3_type_6.setOnCheckedChangeListener(this);
        way3_type_7.setOnCheckedChangeListener(this);
        way3_type_8.setOnCheckedChangeListener(this);
        way3_type_9.setOnCheckedChangeListener(this);
        way3_type_10.setOnCheckedChangeListener(this);
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
            hideSoftInput();
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

    }


    private void hideSoftInput() {
        if (number != null && number.getContext() != null) {
            InputMethodManager imm = (InputMethodManager) number.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(number.getWindowToken(), 0);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.way3_type_1:
                rg_way3_2.clearCheck();
                break;
            case R.id.way3_type_2:
                rg_way3_2.clearCheck();
                break;
            case R.id.way3_type_3:
                rg_way3_2.clearCheck();
                break;
            case R.id.way3_type_4:
                rg_way3_2.clearCheck();
                break;
            case R.id.way3_type_5:
                rg_way3_2.clearCheck();
                break;
            case R.id.way3_type_6:
                rg_way3_1.clearCheck();
                break;
            case R.id.way3_type_7:
                rg_way3_1.clearCheck();
                break;
            case R.id.way3_type_8:
                rg_way3_1.clearCheck();
                break;
            case R.id.way3_type_9:
                rg_way3_1.clearCheck();
                break;
            case R.id.way3_type_10:
                rg_way3_1.clearCheck();
                break;
        }
    }

    public interface OnClickImmediatelyIndianaListener {
        void onClickImmediatelyIndiana(int number);
    }
}
