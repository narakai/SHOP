package wiki.scene.shop.ui.mine.presenter;

import wiki.scene.shop.mvp.BasePresenter;
import wiki.scene.shop.ui.mine.mvpview.IBugFeedbackView;

/**
 * Case By:问题反馈
 * package:wiki.scene.shop.ui.mine.presenter
 * Author：scene on 2017/6/30 15:20
 */

public class BugFeedBackPresenter extends BasePresenter<IBugFeedbackView> {
    private IBugFeedbackView feedbackView;

    public BugFeedBackPresenter(IBugFeedbackView feedbackView) {
        this.feedbackView = feedbackView;
    }
}
