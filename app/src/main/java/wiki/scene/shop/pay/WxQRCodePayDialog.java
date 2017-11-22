package wiki.scene.shop.pay;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.sunfusheng.glideimageview.GlideImageLoader;

import java.io.File;
import java.util.List;

import wiki.scene.shop.R;


/**
 * Case By:显示二维码
 * package:
 * Author：scene on 2017/4/18 13:53
 */
public class WxQRCodePayDialog extends Dialog {
    public WxQRCodePayDialog(@NonNull Context context) {
        super(context);
    }

    public WxQRCodePayDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected WxQRCodePayDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {
        private Context context;
        private String url;
        ImageView imageView;

        public Builder(Context context, String url) {
            this.context = context;
            this.url = url;
        }

        public WxQRCodePayDialog create() {
            LayoutInflater inflater = LayoutInflater.from(context);
            final WxQRCodePayDialog dialog = new WxQRCodePayDialog(context, R.style.Dialog);

            View layout = inflater.inflate(R.layout.dialog_wx_qr_code_pay, null);

            dialog.addContentView(layout, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            imageView = (ImageView) layout.findViewById(R.id.image);
            layout.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialog != null) {
                        dialog.cancel();
                    }
                }
            });

            layout.findViewById(R.id.toWeChat).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if (isWeixinAvilible(context)) {
                            Intent intent = context.getPackageManager().getLaunchIntentForPackage("com.tencent.mm");
                            context.startActivity(intent);
                            if (dialog != null) {
                                dialog.cancel();
                            }
                        } else {
                            ToastUtils.showShort("请先安装微信");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        ToastUtils.showShort("请先安装微信");
                    }

                }
            });
            GlideImageLoader.create(imageView).loadImage(url, R.drawable.ic_default_image);
            DownLoadImageService service = new DownLoadImageService(context, url, new ImageDownLoadCallBack() {

                @Override
                public void onDownLoadSuccess(File file) {

                }

                @Override
                public void onDownLoadSuccess(Bitmap bitmap) {
                }

                @Override
                public void onDownLoadFailed() {

                }
            });
            //启动图片下载线程
            new Thread(service).start();
            dialog.setContentView(layout);
            dialog.setCanceledOnTouchOutside(false);
            return dialog;
        }

    }


    public static boolean isWeixinAvilible(Context context) {
        PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }

}