package wiki.scene.shop.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

public class CountDownUtil extends CountDownTimer {
    private TextView tv;//

    /**
     * @param millisInFuture    倒计时时间
     * @param countDownInterval 间隔
     * @param tv                控件
     */
    public CountDownUtil(long millisInFuture, long countDownInterval,
                         TextView tv) {
        super(millisInFuture, countDownInterval);
        this.tv = tv;

    }

    @Override
    public void onTick(long millisUntilFinished) {
        if (millisUntilFinished * 1000 <= NetTimeUtils.getWebsiteDatetime()) {
            tv.setText("等待开奖");//设置时间
        } else {
            tv.setText(DateFormatUtils.getHoursByNow(millisUntilFinished));//设置时间
        }
    }

    @Override
    public void onFinish() {

    }
}