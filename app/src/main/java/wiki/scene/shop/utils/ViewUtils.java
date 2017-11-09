package wiki.scene.shop.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * 处理View的工具类
 * Created by scene on 2017/3/14.
 */

public class ViewUtils {

    public static void setViewHeightByLinearLayout(View view, int height) {
        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        linearParams.height = height;// 控件的高强制设成20
        view.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
    }

    public static void setViewHeightByRelativeLayout(View view, int height) {
        RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        linearParams.height = height;// 控件的高强制设成20
        view.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
    }

    public static void setViewHeightByViewGroup(View view, int height) {
        ViewGroup.LayoutParams linearParams = view.getLayoutParams();
        linearParams.height = height;// 控件的高强制设成20
        view.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
    }

    public static void setDialogViewWidth(View view, int width) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = width;
        view.setLayoutParams(params);
    }

    public static int[] unDisplayViewSize(View view) {
        int size[] = new int[2];
        int width = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        view.measure(width, height);
        size[0] = view.getMeasuredWidth();
        size[1] = view.getMeasuredHeight();
        return size;
    }

    public static int getViewWidth(View view) {
        return unDisplayViewSize(view)[0];
    }

    public static int getViewHeight(View view) {
        return unDisplayViewSize(view)[1];
    }
}
