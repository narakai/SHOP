package wiki.scene.shop.ui.mine.presenter;

import android.text.TextUtils;

import com.lzy.okgo.model.HttpParams;

import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.http.listener.HttpResultListener;
import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.model.BugFeedBackModel;
import wiki.scene.shop.ui.mine.mvpview.IBugFeedbackView;

/**
 * Case By:问题反馈
 * package:wiki.scene.shop.ui.mine.presenter
 * Author：scene on 2017/6/30 15:20
 */

public class BugFeedBackPresenter extends BasePresenter<IBugFeedbackView> {
    private IBugFeedbackView feedbackView;
    private BugFeedBackModel model;

    public BugFeedBackPresenter(IBugFeedbackView feedbackView) {
        this.mView = feedbackView;
        model = new BugFeedBackModel();
    }

    public void submitBugFeedBack(String goodsName, String contact) {
        try {
            if (TextUtils.isEmpty(goodsName)) {
                mView.showMessage(R.string.please_edit_your_need_feedback_questions);
                return;
            }
            if (TextUtils.isEmpty(contact)) {
                mView.showMessage(R.string.please_edit_contact_info);
                return;
            }
            if (ShopApplication.hasLogin && ShopApplication.userInfo != null) {
                mView.showLoading(R.string.loading);
                HttpParams params = new HttpParams();
                params.put("user_id", ShopApplication.userInfo.getUser_id());
                params.put("content", goodsName);
                params.put("contact", contact);
                model.bugFeedBack(params, new HttpResultListener<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            mView.bugFeedBackSuccess();
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
                            mView.hideLoading();
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
}
