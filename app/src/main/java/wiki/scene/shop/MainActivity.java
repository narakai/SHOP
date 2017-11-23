package wiki.scene.shop;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.UMShareAPI;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.zhl.cbdialog.CBDialogBuilder;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import wiki.scene.loadmore.utils.SceneLogUtil;
import wiki.scene.shop.config.AppConfig;
import wiki.scene.shop.dialog.DownLoadDialog;
import wiki.scene.shop.dialog.RegisterDialog;
import wiki.scene.shop.entity.CheckPayResultInfo;
import wiki.scene.shop.entity.CurrentCycleInfo;
import wiki.scene.shop.entity.UpdateVersionInfo;
import wiki.scene.shop.event.ChooseAvaterResultEvent;
import wiki.scene.shop.event.RefreshMineEvent;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.base.LzyResponse;
import wiki.scene.shop.http.callback.JsonCallback;
import wiki.scene.shop.utils.PriceUtil;
import wiki.scene.shop.utils.ThreadPoolUtils;
import wiki.scene.shop.widgets.LoadingDialog;

/**
 * Case By: 主界面
 * package:
 * Author：scene on 2017/6/26 14:03
 */
public class MainActivity extends SupportActivity {
    private LoadingDialog loadingDialog;

    private ThreadPoolUtils updateStayPoolUtils;
    private ScheduledFuture updateStayFuture;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findFragment(MainFragment.class) == null) {
            loadRootFragment(R.id.fl_container, MainFragment.newInstance());
        }
        getCurrentCycle();
        getUpdateVersion(false);
        updateStay();
        if (getIntent() != null) {
            Intent intent = getIntent();
            boolean isRegister = intent.getBooleanExtra("isRegister", false);
            if (isRegister) {
                showRegisterDialog();
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            boolean isRegister = intent.getBooleanExtra("isRegister", false);
            if (isRegister) {
                showRegisterDialog();
            }
        }
    }

    private void showRegisterDialog() {
        try {
            RegisterDialog.Builder builder = new RegisterDialog.Builder(MainActivity.this);
            final RegisterDialog registerDialog = builder.create();
            builder.setListener(new RegisterDialog.RegisterDialogConfirmListener() {
                @Override
                public void onClickConfirm() {
                    try {
                        registerDialog.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            registerDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        try {
            if (ShopApplication.isNeedCheckOrder && ShopApplication.orderIdInt != 0) {
                checkPayResult();
            } else {
                ShopApplication.isNeedCheckOrder = false;
                ShopApplication.orderIdInt = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        try {
            if (scheduledFuture != null) {
                scheduledFuture.cancel(true);
            }
            if (poolUtils != null && !poolUtils.isShutDown()) {
                poolUtils.shutDownNow();
            }
            if (updateStayFuture != null) {
                updateStayFuture.cancel(true);
            }
            if (updateStayPoolUtils != null && !updateStayPoolUtils.isShutDown()) {
                updateStayPoolUtils.shutDownNow();
            }
            OkGo.getInstance().cancelTag(ApiUtil.CURRENT_CYCLE_TAG);
            OkGo.getInstance().cancelTag(ApiUtil.CHECK_PAY_RESULT_TAG);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
        UMShareAPI.get(this).release();
    }

    private ThreadPoolUtils poolUtils;
    private ScheduledFuture scheduledFuture;

    private void getCurrentCycle() {
        long time = ShopApplication.currentCycleInfo.getOpen_time() * 1000 - System.currentTimeMillis();
        long delay = time > 0 ? time : 1;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
        if (poolUtils != null) {
            poolUtils.shutDownNow();
        }
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

    public void getCurrentCycleData() {
        OkGo.<LzyResponse<CurrentCycleInfo>>get(ApiUtil.API_PRE + ApiUtil.CURRENT_CYCLE)
                .tag(ApiUtil.CURRENT_CYCLE_TAG)
                .execute(new JsonCallback<LzyResponse<CurrentCycleInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<CurrentCycleInfo>> response) {
                        ShopApplication.currentCycleInfo = response.body().data;
                        getCurrentCycle();
                        //EventBus.getDefault().post(new GetCurrentCycleSuccessEvent(response.body().data));
                    }

                    @Override
                    public void onError(Response<LzyResponse<CurrentCycleInfo>> response) {
                        super.onError(response);
                        finish();
                    }
                });
    }

    public void checkPayResult() {
        showLoading();
        HttpParams params = new HttpParams();
        params.put("order_id", ShopApplication.orderIdInt);
        OkGo.<LzyResponse<CheckPayResultInfo>>get(ApiUtil.API_PRE + ApiUtil.CHECK_PAY_RESULT)
                .tag(ApiUtil.CHECK_PAY_RESULT_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<CheckPayResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<CheckPayResultInfo>> response) {
                        try {
                            EventBus.getDefault().post(new RefreshMineEvent());
                            if (response == null || response.body() == null || response.body().data == null || !response.body().data.isStatus()) {
                                //充值失败，
                                showMessageDialog("充值失败，请重试！如您已成功付款，请检查余额或联系客服");
                            } else {
                                showMessageDialog("恭喜您成功充值" + PriceUtil.getPrice(response.body().data.getCost()) + "夺宝币");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            showMessageDialog("充值失败，请重试！如您已成功付款，请检查余额或联系客服");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<CheckPayResultInfo>> response) {
                        super.onError(response);
                        try {
                            EventBus.getDefault().post(new RefreshMineEvent());
                            ToastUtils.showShort(response.getException().getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                            showMessageDialog("充值失败，请重试！如您已成功付款，请检查余额或联系客服");
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        try {
                            hideLoading();
                            ShopApplication.isNeedCheckOrder = false;
                            ShopApplication.orderIdInt = 0;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void showMessageDialog(String message) {
        try {
            CBDialogBuilder builder = new CBDialogBuilder(MainActivity.this);
            TextView titleView = builder.getView(R.id.dialog_title);
            titleView.setSingleLine(false);
            builder.setTouchOutSideCancelable(false)
                    .showCancelButton(false)
                    .setTitle(message)
                    .setMessage("")
                    .setCustomIcon(0)
                    .setConfirmButtonText("确定")
                    .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                    .setButtonClickListener(true, new CBDialogBuilder.onDialogbtnClickListener() {
                        @Override
                        public void onDialogbtnClick(Context context, Dialog dialog, int whichBtn) {
                            switch (whichBtn) {
                                case BUTTON_CONFIRM:
                                    dialog.cancel();
                                    break;
                                default:
                                    break;
                            }
                        }
                    })
                    .create().show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void showLoading() {
        try {
            if (loadingDialog == null) {
                loadingDialog = LoadingDialog.getInstance(MainActivity.this);
            }
            loadingDialog.showLoadingDialog("正在获取支付结果...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hideLoading() {
        try {
            if (loadingDialog != null) {
                loadingDialog.cancelLoadingDialog();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //下载
    private DownLoadDialog downLoadDialog;

    public void getUpdateVersion(final boolean isMine) {
        OkGo.<LzyResponse<UpdateVersionInfo>>get(ApiUtil.API_PRE + ApiUtil.UPDATE_VERSION)
                .tag(ApiUtil.UPDATE_VERSION_TAG)
                .execute(new JsonCallback<LzyResponse<UpdateVersionInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<UpdateVersionInfo>> response) {
                        try {
                            UpdateVersionInfo updateInfo = response.body().data;
                            int versionCode = AppUtils.getAppVersionCode();
                            if (versionCode != 0 && updateInfo.getVersion() > versionCode) {
                                showUpdateDialog(MainActivity.this, updateInfo.getUrl());
                            } else {
                                if (isMine) {
                                    showMessageDialog("当前已经是最新版本");
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void showUpdateDialog(Context context, final String apkUrl) {
        try {
            CBDialogBuilder builder = new CBDialogBuilder(context);
            TextView titleView = builder.getView(R.id.dialog_title);
            titleView.setSingleLine(false);
            builder.setTouchOutSideCancelable(false)
                    .showCancelButton(false)
                    .setTitle("检测到应用有新版本，立即更新")
                    .setMessage("")
                    .setConfirmButtonText("确定")
                    .setCancelButtonText("取消")
                    .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                    .setButtonClickListener(true, new CBDialogBuilder.onDialogbtnClickListener() {
                        @Override
                        public void onDialogbtnClick(Context context, Dialog dialog, int whichBtn) {
                            switch (whichBtn) {
                                case BUTTON_CONFIRM:
                                    showUploadDialog(apkUrl);
                                    break;
                                case BUTTON_CANCEL:
                                    //退出APP
                                    android.os.Process.killProcess(android.os.Process.myPid());
                                    System.exit(1);
                                    break;
                                default:
                                    break;
                            }
                        }
                    })
                    .create().show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showUploadDialog(final String url) {
        DownLoadDialog.Builder downLoadDialogBuilder = new DownLoadDialog.Builder(MainActivity.this);
        downLoadDialog = downLoadDialogBuilder.create();
        downLoadDialog.show();
        downLoadFile(url);
//        downLoadDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialog) {
//                android.os.Process.killProcess(android.os.Process.myPid());
//                System.exit(1);
//            }
//        });
    }

    private void downLoadFile(String url) {
        OkGo.<File>get(url).tag("DOWNLOAD_APK").execute(new FileCallback() {
            @Override
            public void onSuccess(Response<File> response) {
                try {
                    AppUtils.installApp(response.body().getAbsolutePath(), "wiki.scene.shop.provider");
                    if (downLoadDialog != null) {
                        downLoadDialog.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtils.showShort("更新失败，请重试");
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                }
            }

            @Override
            public void onError(Response<File> response) {
                super.onError(response);
                try {
                    if (downLoadDialog != null) {
                        downLoadDialog.dismiss();
                    }
                    ToastUtils.showShort("更新失败，请重试");
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtils.showShort("更新失败，请重试");
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                }

            }
        });
    }


    private void updateStay() {
        updateStayPoolUtils = new ThreadPoolUtils(ThreadPoolUtils.SingleThread, 1);
        updateStayFuture = updateStayPoolUtils.scheduleWithFixedRate(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        OkGo.<LzyResponse<String>>get(ApiUtil.API_PRE + ApiUtil.UPDATE_STAY)
                                .tag(ApiUtil.UPDATE_STAY_TAG)
                                .execute(new JsonCallback<LzyResponse<String>>() {
                                    @Override
                                    public void onSuccess(Response<LzyResponse<String>> response) {

                                    }
                                });
                    }
                });
            }
        }, 1, 30, TimeUnit.SECONDS);


    }

}
