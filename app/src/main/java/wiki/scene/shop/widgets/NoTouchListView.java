package wiki.scene.shop.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * 禁用了滚动的listView
 * Created by scene on 2017/11/9.
 */

public class NoTouchListView extends ListView {
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
        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            return true; // 禁止滑动
        }
        return super.dispatchTouchEvent(ev);
    }
}
