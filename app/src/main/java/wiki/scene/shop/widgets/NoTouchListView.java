package wiki.scene.shop.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * 禁用了滚动的listView
 * Created by scene on 2017/11/9.
 */

public class NoTouchListView extends ListView {
    private int mPosition;

    public NoTouchListView(Context context) {
        super(context);
    }

    public NoTouchListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoTouchListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("==============>", "111111111111111111");
        final int actionMasked = ev.getActionMasked() & MotionEvent.ACTION_MASK;

        Log.e("==============>", "22222222222222");
        if (actionMasked == MotionEvent.ACTION_DOWN) {
            Log.e("==============>", "33333333333333");
            // 记录手指按下时的位置
            mPosition = pointToPosition((int) ev.getX(), (int) ev.getY());
            return super.dispatchTouchEvent(ev);
        }

        Log.e("==============>", "444444444444444444444");
        if (actionMasked == MotionEvent.ACTION_MOVE) {
            // 最关键的地方，忽略MOVE 事件
            // ListView onTouch获取不到MOVE事件所以不会发生滚动处理
            return true;
        }

        Log.e("==============>", "555555555555555");
        // 手指抬起时
        if (actionMasked == MotionEvent.ACTION_UP
                || actionMasked == MotionEvent.ACTION_CANCEL) {

            Log.e("==============>", "6666666666666666");
            // 手指按下与抬起都在同一个视图内，交给父控件处理，这是一个点击事件
            if (pointToPosition((int) ev.getX(), (int) ev.getY()) == mPosition) {

                Log.e("==============>", "77777777777777777777");
                super.dispatchTouchEvent(ev);
            } else {

                Log.e("==============>", "88888888888888888888888");
                // 如果手指已经移出按下时的Item，说明是滚动行为，清理Item pressed状态
                setPressed(false);
                invalidate();
                return true;
            }
        }

        Log.e("==============>","9999999999999999999");
        return super.dispatchTouchEvent(ev);
    }
}
