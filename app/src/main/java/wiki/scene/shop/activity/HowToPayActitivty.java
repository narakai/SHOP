package wiki.scene.shop.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;
import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.config.AppConfig;
import wiki.scene.shop.utils.UpdatePageUtils;
import wiki.scene.shop.widgets.ProgressWebView;

/**
 * 玩法
 * Created by scene on 2017/11/28.
 */

public class HowToPayActitivty extends SwipeBackActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.webView)
    ProgressWebView webView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);
        ButterKnife.bind(this);
        initToolbar();
        UpdatePageUtils.updatePagePosition(AppConfig.POSITION_USER_AGREEMENT, 0);
        try {
            webView.loadUrl(ShopApplication.configInfo.getPlay_method());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initToolbar() {
        toolbarTitle.setText("玩法说明");
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
