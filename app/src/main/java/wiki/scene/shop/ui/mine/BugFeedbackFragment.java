package wiki.scene.shop.ui.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wiki.scene.shop.R;
import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.mvp.BaseBackMvpFragment;
import wiki.scene.shop.ui.mine.mvpview.IBugFeedbackView;
import wiki.scene.shop.ui.mine.presenter.BugFeedBackPresenter;

/**
 * Case By:问题反馈
 * package:wiki.scene.shop.ui.mine
 * Author：scene on 2017/6/30 15:19
 */

public class BugFeedbackFragment extends BaseBackMvpFragment<IBugFeedbackView, BugFeedBackPresenter> implements IBugFeedbackView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.content)
    EditText content;
    @BindView(R.id.contact_number)
    EditText contactNumber;
    Unbinder unbinder;

    public static BugFeedbackFragment newInstance() {
        Bundle args = new Bundle();
        BugFeedbackFragment fragment = new BugFeedbackFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bug_feedback, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        toolbarTitle.setText(R.string.bug_feedback);
        initToolbarNav(toolbar);
    }

    @Override
    public void showLoading(@StringRes int resId) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public BugFeedBackPresenter initPresenter() {
        return new BugFeedBackPresenter(this);
    }

    @Override
    public void showMessage(@StringRes int resId) {
        ToastUtils.showShort(resId);
    }

    @Override
    public void showMessage(String message) {
        ToastUtils.showShort(message);
    }

    @Override
    public void bugFeedBackSuccess() {
        content.setText("");
        contactNumber.setText("");
        showMessage(R.string.please_edit_your_bug_feedback);
    }

    @OnClick(R.id.submit)
    public void onClickSubmit() {
        presenter.submitBugFeedBack(content.getText().toString().trim(), contactNumber.getText().toString().trim());
    }

    @Override
    public void onDestroyView() {
        OkGo.getInstance().cancelTag(ApiUtil.BUG_FEED_BACK_TAG);
        super.onDestroyView();
        unbinder.unbind();
    }
}
