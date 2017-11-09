package wiki.scene.shop.ui.indiana;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wiki.scene.loadmore.PtrClassicFrameLayout;
import wiki.scene.loadmore.StatusViewLayout;
import wiki.scene.shop.R;
import wiki.scene.shop.entity.IndianaIndexInfo;
import wiki.scene.shop.entity.NewWaitInfo;
import wiki.scene.shop.entity.SliderInfo;
import wiki.scene.shop.entity.WinningNoticeInfo;
import wiki.scene.shop.mvp.BaseMainMvpFragment;
import wiki.scene.shop.ui.indiana.mvpview.IIndianaView;
import wiki.scene.shop.ui.indiana.presenter.IndianaPresenter;
import wiki.scene.shop.utils.ViewUtils;
import wiki.scene.shop.widgets.CustomeGridView;
import wiki.scene.shop.widgets.NoTouchListView;

/**
 * Case By:
 * package:wiki.scene.shop.fragment.indiana
 * Author：scene on 2017/6/28 17:36
 */

public class IndianaFragment extends BaseMainMvpFragment<IIndianaView, IndianaPresenter> implements IIndianaView, View.OnClickListener {

    @BindView(R.id.gridView)
    CustomeGridView gridView;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.rd_canyu)
    RadioButton rdCanyu;
    @BindView(R.id.rd_huojiang)
    RadioButton rdHuojiang;
    @BindView(R.id.canyuListView)
    NoTouchListView canyuListView;
    @BindView(R.id.huojiangListView)
    NoTouchListView huojiangListView;
    @BindView(R.id.ptrLayout)
    PtrClassicFrameLayout ptrLayout;
    @BindView(R.id.status_layout)
    StatusViewLayout statusLayout;
    Unbinder unbinder;

    public static IndianaFragment newInstance() {
        Bundle args = new Bundle();
        IndianaFragment fragment = new IndianaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_indiana, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        statusLayout.showContent();
        initView();
    }

    private void initView() {
        View canyuItemview = LayoutInflater.from(getContext()).inflate(R.layout.fragment_indiana_canyu_item, null);
        ViewUtils.setViewHeightByViewGroup(canyuListView, ViewUtils.getViewHeight(canyuItemview) * 4);
        ViewUtils.setViewHeightByViewGroup(huojiangListView, ViewUtils.getViewHeight(canyuItemview) * 4);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == rdCanyu.getId()) {
                    canyuListView.setVisibility(View.VISIBLE);
                    huojiangListView.setVisibility(View.GONE);
                } else if (checkedId == rdHuojiang.getId()) {
                    canyuListView.setVisibility(View.GONE);
                    huojiangListView.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    @Override
    public void onClick(View view) {

    }

    @Override
    public void showLoading(int resId) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setTitlebarChoosed(int choosedPosition, int oldChoosedPosition) {

    }

    @Override
    public void getDataSuccess(IndianaIndexInfo indexInfo, boolean isRefresh) {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void showFailPage() {

    }

    @Override
    public void bindBannerData(List<SliderInfo> bannerList) {

    }

    @Override
    public void bindWinnerNotice(List<WinningNoticeInfo> noticeInfoList) {

    }

    @Override
    public void bindNewWaiting(List<NewWaitInfo> newWaitInfoList) {

    }

    @Override
    public IndianaPresenter initPresenter() {
        return new IndianaPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
