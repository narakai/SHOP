package wiki.scene.shop.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.TimeUtils;
import android.widget.Chronometer;

/**
 * Created by scene on 2017/11/8.
 */

public class CountDownTimerView extends Chronometer {
    public CountDownTimerView(Context context) {
        super(context);
    }

    public CountDownTimerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CountDownTimerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CountDownTimerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void init(long time) {
    }

}
