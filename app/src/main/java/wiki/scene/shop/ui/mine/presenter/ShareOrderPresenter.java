package wiki.scene.shop.ui.mine.presenter;

import android.content.Context;

import com.lzy.okgo.model.HttpParams;
import com.ta.utdid2.android.utils.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.shaohui.advancedluban.Luban;
import me.shaohui.advancedluban.OnMultiCompressListener;
import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.http.listener.HttpResultListener;
import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.model.ShareOrderModel;
import wiki.scene.shop.ui.mine.mvpview.IShareOrderView;

/**
 * 发布晒单
 * Created by scene on 17-8-14.
 */

public class ShareOrderPresenter extends BasePresenter<IShareOrderView> {
    private IShareOrderView shareOrderView;
    private ShareOrderModel model;

    public ShareOrderPresenter(IShareOrderView shareOrderView) {
        this.mView = shareOrderView;
        model = new ShareOrderModel();
    }

    public void shareOrder(String content, String orderId, String cycleId, List<File> images) {
        try {
            if (StringUtils.isEmpty(content)) {
                mView.showMessage(R.string.please_edit_content);
                return;
            }
            if (ShopApplication.hasLogin && ShopApplication.userInfo != null) {
                HttpParams params = new HttpParams();
                params.put("user_id", ShopApplication.userInfo.getUser_id());
                params.put("content", content);
                params.put("order_id", orderId);
                params.put("cycle_id", cycleId);
                if (images != null && images.size() != 0) {
                    params.putFileParams("images[]", images);
                }
                model.shareOrder(params, new HttpResultListener<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            mView.shareSuccess();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(String message) {
                        try {
                            mView.showMessage(message);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFinish() {
                        try {
                            mView.hideProgressDialog();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            } else {
                mView.showMessage(R.string.you_has_no_login_please_login);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void compressImages(Context context, List<String> images) {
        try {
            List<File> files = new ArrayList<>();
            for (String path : images) {
                if (!path.equals("add")) {
                    files.add(new File(path));
                }
            }
            Luban.compress(context, files).launch(new OnMultiCompressListener() {
                @Override
                public void onStart() {

                }

                @Override
                public void onSuccess(List<File> fileList) {
                    try {
                        mView.hideProgressDialog();
                        mView.compressSuccess(fileList);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(Throwable e) {
                    try {
                        mView.hideProgressDialog();
                        mView.showMessage(R.string.share_order_fail);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
