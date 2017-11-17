package wiki.scene.shop.base;

import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;
import wiki.scene.shop.R;
import wiki.scene.shop.utils.ToastUtils;

/**
 * 懒加载
 * Created by YoKeyword on 16/6/5.
 */
public abstract class BaseMainFragment extends SupportFragment {
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;
    public Unbinder unbinder;

    /**
     * 处理回退事件
     *
     * @return
     */
    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            ToastUtils.getInstance(_mActivity).showToast(getString(R.string.press_again_exit));
        }
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

}
