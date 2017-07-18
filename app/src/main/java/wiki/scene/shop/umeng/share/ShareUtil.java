package wiki.scene.shop.umeng.share;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;
import com.zhl.cbdialog.CBDialogBuilder;

import wiki.scene.shop.R;
import wiki.scene.shop.utils.ToastUtils;

/**
 * Case By:自定义分享面板
 * package:wiki.scene.shop.umeng.share
 * Author：scene on 2017/7/17 11:38
 */

public class ShareUtil implements View.OnClickListener {
    private static ShareUtil instance;
    private Activity activity;
    private Dialog dialog;
    private CBDialogBuilder dialogBuilder;

    private UMWeb umWeb;

    private ShareUtil(Activity activity) {
        this.activity = activity;
    }

    public static ShareUtil getInstance(Activity activity) {    //对获取实例的方法进行同步
        if (instance == null) {
            synchronized (ShareUtil.class) {
                if (instance == null)
                    instance = new ShareUtil(activity);
            }
        }
        return instance;
    }

    public void showShareBoard(UMWeb umWeb) {
        this.umWeb = umWeb;
        if (dialogBuilder == null) {
            View shareView = LayoutInflater.from(activity).inflate(R.layout.layout_share_board, null);
            shareView.findViewById(R.id.share_wechat).setOnClickListener(this);
            shareView.findViewById(R.id.share_qq).setOnClickListener(this);
            shareView.findViewById(R.id.share_weibo).setOnClickListener(this);
            dialogBuilder = new CBDialogBuilder(activity, CBDialogBuilder.DIALOG_STYLE_NORMAL, 1.0f)
                    .showIcon(false)
                    .setTouchOutSideCancelable(true)
                    .showCancelButton(false)
                    .setDialoglocation(CBDialogBuilder.DIALOG_LOCATION_BOTTOM)
                    .setTitle("选择要分享到的平台")
                    .setView(shareView)
                    .setConfirmButtonText("取消分享")
                    .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM);
        }

        if (dialog == null) {
            dialog = dialogBuilder.create();
        }
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    private void hideShareBoard() {
        if (dialog != null) {
            dialog.cancel();
        }
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         *  分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         *  分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            ToastUtils.getInstance(activity).showToast("分享成功");
        }

        /**
         *  分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            ToastUtils.getInstance(activity).showToast("分享失败，请重试");
        }

        /**
         *  分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
        }
    };

    @Override
    public void onClick(View v) {
        ShareAction shareAction = new ShareAction(activity);
        switch (v.getId()) {
            case R.id.share_wechat:
                if (UMShareAPI.get(activity).isInstall(activity, SHARE_MEDIA.WEIXIN)) {
                    shareAction.setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                            .withMedia(umWeb)//分享内容
                            .setCallback(shareListener)//回调监听器
                            .share();
                } else {
                    ToastUtils.getInstance(activity).showToast(R.string.please_install_wechat);
                }

                break;
            case R.id.share_qq:
                if (UMShareAPI.get(activity).isInstall(activity, SHARE_MEDIA.QQ)) {
                    shareAction.setPlatform(SHARE_MEDIA.QQ)//传入平台
                            .withMedia(umWeb)//分享内容
                            .setCallback(shareListener)//回调监听器
                            .share();
                } else {
                    ToastUtils.getInstance(activity).showToast(R.string.please_install_qq);
                }
                break;
            case R.id.share_weibo:
                if (UMShareAPI.get(activity).isInstall(activity, SHARE_MEDIA.SINA)) {
                    shareAction.setPlatform(SHARE_MEDIA.SINA)//传入平台
                            .withText("")
                            .withMedia(umWeb)//分享内容
                            .setCallback(shareListener)//回调监听器
                            .share();
                } else {
                    ToastUtils.getInstance(activity).showToast(R.string.please_install_weibo);
                }
                break;

        }
    }
}
