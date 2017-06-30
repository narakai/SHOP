package wiki.scene.shop.ui.indiana;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wiki.scene.loadmore.PtrClassicFrameLayout;
import wiki.scene.loadmore.PtrDefaultHandler;
import wiki.scene.loadmore.PtrFrameLayout;
import wiki.scene.loadmore.StatusViewLayout;
import wiki.scene.loadmore.utils.PtrLocalDisplay;
import wiki.scene.shop.R;
import wiki.scene.shop.adapter.IndianaAdapter;
import wiki.scene.shop.ui.indiana.mvpview.IIndianaView;
import wiki.scene.shop.ui.indiana.presenter.IndianaPresenter;
import wiki.scene.shop.itemDecoration.GridSpacingItemDecoration;
import wiki.scene.shop.mvp.BaseMainMvpFragment;
import wiki.scene.shop.widgets.verticalrollingtextview.VerticalRollingTextView;

/**
 * Case By:夺宝
 * package:wiki.scene.shop.fragment.indiana
 * Author：scene on 2017/6/26 14:13
 */
public class IndianaFragment extends BaseMainMvpFragment<IIndianaView, IndianaPresenter> implements IIndianaView {
    private final static int TITLE_BAR_popular = 0;
    private final static int TITLE_BAR_NEWEST = 1;
    private final static int TITLE_BAR_FASTEST = 2;
    private final static int TITLE_BAR_PRICE = 3;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.indiana_price_10)
    TextView indianaPrice10;
    @BindView(R.id.indiana_fast)
    TextView indianaFast;
    @BindView(R.id.indiana_red88)
    TextView indianaRed88;
    @BindView(R.id.invite_money)
    TextView inviteMoney;
    @BindView(R.id.notice_textView)
    VerticalRollingTextView noticeTextView;
    @BindView(R.id.newest_title_image_text)
    TextView newestTitleImageText;
    @BindView(R.id.more_newest)
    TextView moreNewest;
    @BindView(R.id.text_popular1)
    TextView textPopular1;
    @BindView(R.id.line_popular1)
    View linePopular1;
    @BindView(R.id.layout_popular1)
    RelativeLayout layoutPopular1;
    @BindView(R.id.text_newest1)
    TextView textNewest1;
    @BindView(R.id.line_newest1)
    View lineNewest1;
    @BindView(R.id.layout_newest1)
    RelativeLayout layoutNewest1;
    @BindView(R.id.text_fastest1)
    TextView textFastest1;
    @BindView(R.id.line_fastest1)
    View lineFastest1;
    @BindView(R.id.layout_fastest1)
    RelativeLayout layoutFastest1;
    @BindView(R.id.text_price1)
    TextView textPrice1;
    @BindView(R.id.line_price1)
    View linePrice1;
    @BindView(R.id.layout_price1)
    RelativeLayout layoutPrice1;
    @BindView(R.id.title_bar1)
    LinearLayout titleBar1;
    @BindView(R.id.text_popular)
    TextView textPopular;
    @BindView(R.id.line_popular)
    View linePopular;
    @BindView(R.id.layout_popular)
    RelativeLayout layoutPopular;
    @BindView(R.id.text_newest)
    TextView textNewest;
    @BindView(R.id.line_newest)
    View lineNewest;
    @BindView(R.id.layout_newest)
    RelativeLayout layoutNewest;
    @BindView(R.id.text_fastest)
    TextView textFastest;
    @BindView(R.id.line_fastest)
    View lineFastest;
    @BindView(R.id.layout_fastest)
    RelativeLayout layoutFastest;
    @BindView(R.id.text_price)
    TextView textPrice;
    @BindView(R.id.line_price)
    View linePrice;
    @BindView(R.id.layout_price)
    RelativeLayout layoutPrice;
    @BindView(R.id.title_bar)
    LinearLayout titleBar;
    @BindView(R.id.ptrLayout)
    PtrClassicFrameLayout ptrLayout;
    @BindView(R.id.status_layout)
    StatusViewLayout statusLayout;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.appbarlayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    //appbar滑动的距离
    private int verticalOffset = 0;
    //adapter
    private List<String> list = new ArrayList<>();
    private GridLayoutManager layoutManager;
    private IndianaAdapter adapter;

    public static IndianaFragment newInstance() {
        IndianaFragment fragment = new IndianaFragment();
        Bundle args = new Bundle();
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
        hideLoading();
        initView();
        initRecyclerView();
    }

    private void initView() {
        ptrLayout.setLastUpdateTimeRelateObject(this);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
                verticalOffset = offset;
            }
        });
        ptrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return verticalOffset == 0 && PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

            }

        });

    }

    private void initRecyclerView() {
        for (int i = 0; i < 30; i++) {
            list.add("数据：" + (i + 1));
        }
        adapter = new IndianaAdapter(_mActivity, list);
        layoutManager = new GridLayoutManager(_mActivity, 2);
        recyclerView.setLayoutManager(layoutManager);
        int space=PtrLocalDisplay.dp2px(1);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2,space,false));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public IndianaPresenter initPresenter() {
        return new IndianaPresenter(this);
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
        statusLayout.showContent();
    }

    @OnClick({R.id.layout_popular, R.id.layout_newest, R.id.layout_fastest, R.id.layout_price})
    public void onClickTitleBarItem(View view) {
        switch (view.getId()) {
            case R.id.layout_popular:
                presenter.setChoosedTitleBar(TITLE_BAR_popular);
                break;
            case R.id.layout_newest:
                presenter.setChoosedTitleBar(TITLE_BAR_NEWEST);
                break;
            case R.id.layout_fastest:
                presenter.setChoosedTitleBar(TITLE_BAR_FASTEST);
                break;
            case R.id.layout_price:
                presenter.setChoosedTitleBar(TITLE_BAR_PRICE);
                break;
        }
    }


    @Override
    public void setTitlebarChoosed(int choosedPosition) {
        textPopular.setTextColor(getResources().getColor(R.color.text_color_title));
        linePopular.setBackgroundColor(getResources().getColor(R.color.transparent));
        textNewest.setTextColor(getResources().getColor(R.color.text_color_title));
        lineNewest.setBackgroundColor(getResources().getColor(R.color.transparent));
        textFastest.setTextColor(getResources().getColor(R.color.text_color_title));
        lineFastest.setBackgroundColor(getResources().getColor(R.color.transparent));
        textPrice.setTextColor(getResources().getColor(R.color.text_color_title));
        linePrice.setBackgroundColor(getResources().getColor(R.color.transparent));
        switch (choosedPosition) {
            case TITLE_BAR_popular:
                textPopular.setTextColor(getResources().getColor(R.color.colorPrimary));
                linePopular.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            case TITLE_BAR_NEWEST:
                textNewest.setTextColor(getResources().getColor(R.color.colorPrimary));
                lineNewest.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            case TITLE_BAR_FASTEST:
                textFastest.setTextColor(getResources().getColor(R.color.colorPrimary));
                lineFastest.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            case TITLE_BAR_PRICE:
                textPrice.setTextColor(getResources().getColor(R.color.colorPrimary));
                linePrice.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}
