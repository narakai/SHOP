package wiki.scene.shop.ui.indiana;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
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
import android.widget.TextView;

import butterknife.ButterKnife;
import wiki.scene.shop.R;
import wiki.scene.shop.config.AppConfig;
import wiki.scene.shop.utils.PriceUtil;

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
    private TextView numberLess;
    private TextView numberAdd;
    private TextView num1;
    private TextView num10;
    private TextView num30;
    private TextView num50;
    private TextView num100;
    private TextView cycleCode;
    private TextView countDownView;
    private TextView accountBalance;
    private TextView payPrice;
    private TextView toPay;
    //玩法
    private int playType = AppConfig.PLAY_TYPE_TWO;
    //下注类型
    private int buyType = AppConfig.BUY_TYPE_BIG;
    //购买的数量
    private int buyNumber = 1;
    //单价
    private int twoPrice = 0;
    private int fourPrice = 0;
    private int tenPrice = 0;
    //需要支付的总价
    private int totalPrice = 0;
    private int balance = 0;
    //监听器
    private OnClickPopWindowPayListener onClickPopWindowPayListener;

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
        numberLess = (TextView) view.findViewById(R.id.number_less);
        numberAdd = (TextView) view.findViewById(R.id.number_add);
        num1 = (TextView) view.findViewById(R.id.num_1);
        num10 = (TextView) view.findViewById(R.id.num_10);
        num30 = (TextView) view.findViewById(R.id.num_30);
        num50 = (TextView) view.findViewById(R.id.num_50);
        num100 = (TextView) view.findViewById(R.id.num_100);
        number.setSelection(number.getText().toString().length());

        cycleCode = (TextView) view.findViewById(R.id.cycle_code);
        countDownView = (TextView) view.findViewById(R.id.countdownView);
        accountBalance = (TextView) view.findViewById(R.id.account_balance);
        payPrice = (TextView) view.findViewById(R.id.pay_price);
        toPay = (TextView) view.findViewById(R.id.to_pay);
        setTotalPrice();
    }

    public void setOnClickPopWindowPayListener(OnClickPopWindowPayListener onClickPopWindowPayListener) {
        this.onClickPopWindowPayListener = onClickPopWindowPayListener;
    }

    private void setTotalPrice() {
        switch (playType) {
            case AppConfig.PLAY_TYPE_TWO:
                totalPrice = twoPrice * buyNumber;
                break;
            case AppConfig.PLAY_TYPE_FOUR:
                totalPrice = fourPrice * buyNumber;
                break;
            case AppConfig.PLAY_TYPE_TEN:
                totalPrice = tenPrice * buyNumber;
                break;
        }
        payPrice.setText(PriceUtil.getPrice(totalPrice));
        if (totalPrice > balance) {
            toPay.setText("余额不足，去充值");
        } else {
            toPay.setText("确定购买");
        }
    }

    private void setListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rd_shuangren:
                        playType = AppConfig.PLAY_TYPE_TWO;
                        rg_way1.setVisibility(View.VISIBLE);
                        rg_way2.setVisibility(View.GONE);
                        rg_way3.setVisibility(View.GONE);
                        setTotalPrice();
                        break;
                    case R.id.rd_siren:
                        playType = AppConfig.PLAY_TYPE_FOUR;
                        rg_way1.setVisibility(View.GONE);
                        rg_way2.setVisibility(View.VISIBLE);
                        rg_way3.setVisibility(View.GONE);
                        setTotalPrice();
                        break;
                    case R.id.rd_shiren:
                        playType = AppConfig.PLAY_TYPE_TEN;
                        rg_way1.setVisibility(View.GONE);
                        rg_way2.setVisibility(View.GONE);
                        rg_way3.setVisibility(View.VISIBLE);
                        setTotalPrice();
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

        numberAdd.setOnClickListener(this);
        numberLess.setOnClickListener(this);
        num1.setOnClickListener(this);
        num10.setOnClickListener(this);
        num30.setOnClickListener(this);
        num50.setOnClickListener(this);
        num100.setOnClickListener(this);

        toPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickPopWindowPayListener != null) {
                    if (totalPrice > balance) {
                        onClickPopWindowPayListener.onClickToRecharge();
                    } else {
                        onClickPopWindowPayListener.onClickToPay(playType, buyType, buyNumber);
                    }
                }
            }
        });

        number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String numberText = number.getText().toString().trim();
                try {
                    buyNumber = Integer.parseInt(numberText);
                    if (buyNumber < 1) {
                        buyNumber = 1;
                    } else if (buyNumber > AppConfig.MAX_BUY_NUMBER) {
                        buyNumber = AppConfig.MAX_BUY_NUMBER;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    buyNumber = 1;
                } finally {
                    String numberString = String.valueOf(buyNumber);
                    if (!numberText.equals(numberString)) {
                        number.setText(numberString);
                        number.setSelection(numberString.length());
                        setTotalPrice();
                    }
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
        switch (v.getId()) {
            case R.id.number_add:
                buyNumber += 1;
                if (buyNumber > AppConfig.MAX_BUY_NUMBER) {
                    buyNumber = AppConfig.MAX_BUY_NUMBER;
                }
                break;
            case R.id.number_less:
                buyNumber -= 1;
                if (buyNumber < 1) {
                    buyNumber = 1;
                }
                break;
            case R.id.num_1:
                buyNumber = 1;
                break;
            case R.id.num_10:
                buyNumber = 10;
                break;
            case R.id.num_30:
                buyNumber = 30;
                break;
            case R.id.num_50:
                buyNumber = 50;
                break;
            case R.id.num_100:
                buyNumber = 100;
                break;
        }
        String numberString = String.valueOf(buyNumber);
        number.setText(numberString);
        number.setSelection(numberString.length());
        setTotalPrice();
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

    /**
     * 获取当前选择的玩法
     *
     * @return 1 => '2人夺宝', 2 => '4人夺宝', 3 => '10人夺宝'
     */
    public int getPlayType() {
        return playType;
    }

    /**
     * 获取下注的方式
     *
     * @return 方式
     */
    public int getBuyType() {
        if (playType == AppConfig.PLAY_TYPE_TWO) {
            switch (rg_way1.getCheckedRadioButtonId()) {
                case R.id.way1_type_1:
                    buyType = AppConfig.BUY_TYPE_BIG;
                    break;
                case R.id.way1_type_2:
                    buyType = AppConfig.BUY_TYPE_SMALL;
                    break;
                case R.id.way1_type_3:
                    buyType = AppConfig.BUY_TYPE_SINGLE;
                    break;
                case R.id.way1_type_4:
                    buyType = AppConfig.BUY_TYPE_DOUBLE;
                    break;
            }
        } else if (playType == AppConfig.PLAY_TYPE_FOUR) {
            switch (rg_way2.getCheckedRadioButtonId()) {
                case R.id.way2_type_1:
                    buyType = AppConfig.BUY_TYPE_BIG_SINGLE;
                    break;
                case R.id.way2_type_2:
                    buyType = AppConfig.BUY_TYPE_BIG_DOUBLE;
                    break;
                case R.id.way2_type_3:
                    buyType = AppConfig.BUY_TYPE_SMALL_SINGLE;
                    break;
                case R.id.way2_type_4:
                    buyType = AppConfig.BUY_TYPE_SMALL_DOUBLE;
                    break;
            }
        } else if (playType == AppConfig.PLAY_TYPE_TEN) {
            switch (rg_way3_1.getCheckedRadioButtonId()) {
                case R.id.way3_type_1:
                    buyType = AppConfig.BUY_TYPE_NUM_1;
                    break;
                case R.id.way3_type_2:
                    buyType = AppConfig.BUY_TYPE_NUM_2;
                    break;
                case R.id.way3_type_3:
                    buyType = AppConfig.BUY_TYPE_NUM_3;
                    break;
                case R.id.way3_type_4:
                    buyType = AppConfig.BUY_TYPE_NUM_4;
                    break;
                case R.id.way3_type_5:
                    buyType = AppConfig.BUY_TYPE_NUM_5;
                    break;
            }
            switch (rg_way3_2.getCheckedRadioButtonId()) {
                case R.id.way3_type_6:
                    buyType = AppConfig.BUY_TYPE_NUM_6;
                    break;
                case R.id.way3_type_7:
                    buyType = AppConfig.BUY_TYPE_NUM_7;
                    break;
                case R.id.way3_type_8:
                    buyType = AppConfig.BUY_TYPE_NUM_8;
                    break;
                case R.id.way3_type_9:
                    buyType = AppConfig.BUY_TYPE_NUM_9;
                    break;
                case R.id.way3_type_10:
                    buyType = AppConfig.BUY_TYPE_NUM_0;
                    break;
            }
        }
        return buyType;
    }

    /**
     * 获取下注的数量
     *
     * @return 数量
     */
    public int getBuyNumber() {
        return buyNumber;
    }

    /**
     * 设置显示的期数
     *
     * @param cycleCodeStr 期数
     */
    public void setCycleCode(String cycleCodeStr) {
        cycleCode.setText(cycleCodeStr);
    }

    /**
     * 倒计时
     *
     * @param time 倒计时
     */
    public void setCountDownView(String time) {
        countDownView.setText(time);
    }

    /**
     * 显示余额
     *
     * @param balance 余额 单位分
     */
    public void setAccountBalance(int balance) {
        this.balance = balance;
        accountBalance.setText(PriceUtil.getPrice(balance));
        setTotalPrice();
    }

    /**
     * 双人单价
     *
     * @param twoPrice 单价，单位分
     */
    public void setTwoPrice(int twoPrice) {
        this.twoPrice = twoPrice;
        setTotalPrice();
    }

    /**
     * 4人单价
     *
     * @param fourPrice 单价，单位分
     */
    public void setFourPrice(int fourPrice) {
        this.fourPrice = fourPrice;
        setTotalPrice();
    }

    /**
     * 10人单价
     *
     * @param tenPrice 单价，单位分
     */
    public void setTenPrice(int tenPrice) {
        this.tenPrice = tenPrice;
        setTotalPrice();
    }

    public interface OnClickPopWindowPayListener {
        void onClickToPay(int playType, int buyType, int buyNumber);

        void onClickToRecharge();
    }
}
