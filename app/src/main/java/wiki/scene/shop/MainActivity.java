package wiki.scene.shop;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.UMShareAPI;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;
import com.yuyh.library.imgsel.ImgSelActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import wiki.scene.loadmore.utils.SceneLogUtil;
import wiki.scene.shop.config.AppConfig;
import wiki.scene.shop.entity.CurrentCycleInfo;
import wiki.scene.shop.event.ChooseAvaterResultEvent;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.base.LzyResponse;
import wiki.scene.shop.http.callback.JsonCallback;
import wiki.scene.shop.utils.ThreadPoolUtils;

/**
 * Case By: 主界面
 * package:
 * Author：scene on 2017/6/26 14:03
 */
public class MainActivity extends SupportActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findFragment(MainFragment.class) == null) {
            loadRootFragment(R.id.fl_container, MainFragment.newInstance());
        }
        getCurrentCycle();
    }

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    /**
     * Case By:申请权限
     * Author: scene on 2017/6/26 15:43
     */
    public void applyPermission() {
        AndPermission.with(MainActivity.this)
                .requestCode(100)
                .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {

                    }
                })
                .callback(new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message msg) {
                        return false;
                    }
                })
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (resultCode == RESULT_OK && requestCode == AppConfig.CHOOSE_AVATER_REQUEST_CODE) {
                try {
                    List<String> pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
                    if (pathList != null && pathList.size() > 0) {
                        EventBus.getDefault().post(new ChooseAvaterResultEvent(pathList.get(0)));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    SceneLogUtil.e("选择头像异常了");
                }
            }
        }
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
        if (poolUtils != null && !poolUtils.isShutDown()) {
            poolUtils.shutDownNow();
        }
        OkGo.getInstance().cancelTag(ApiUtil.CURRENT_CYCLE_TAG);
        super.onDestroy();
        UMShareAPI.get(this).release();
    }

    private ThreadPoolUtils poolUtils;
    private ScheduledFuture scheduledFuture;

    private void getCurrentCycle() {
        long time = ShopApplication.currentCycleInfo.getOpen_time() * 1000 - System.currentTimeMillis();
        long delay = time > 0 ? time : 1;
        poolUtils = new ThreadPoolUtils(ThreadPoolUtils.SingleThread, 1);
        scheduledFuture = poolUtils.schedule(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getCurrentCycleData();
                    }
                });
            }
        }, delay, TimeUnit.SECONDS);
    }

    private void getCurrentCycleData() {
        OkGo.<LzyResponse<CurrentCycleInfo>>get(ApiUtil.API_PRE + ApiUtil.CURRENT_CYCLE)
                .tag(ApiUtil.CURRENT_CYCLE_TAG)
                .execute(new JsonCallback<LzyResponse<CurrentCycleInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<CurrentCycleInfo>> response) {
                        ShopApplication.currentCycleInfo = response.body().data;
                        getCurrentCycle();
                    }

                    @Override
                    public void onError(Response<LzyResponse<CurrentCycleInfo>> response) {
                        super.onError(response);
                        finish();
                    }
                });
    }
}
