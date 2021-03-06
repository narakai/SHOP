package wiki.scene.shop.pay;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.blankj.utilcode.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;
import wiki.scene.shop.R;

/**
 * Case By:
 * package:com.hfaufhreu.hjfeuio
 * Author：scene on 2017/4/21 17:37
 */

public class WechatPayActivity extends SupportActivity {
    @BindView(R.id.webView)
    WebView mWebView;
    private Unbinder unbinder;
    private boolean isNeedFinish = false;

    public static final String WECHAT_PAY_URL = "wechat_pay_url";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_pay);
        unbinder = ButterKnife.bind(this);
        String weChatPayUrl = getIntent().getStringExtra(WECHAT_PAY_URL);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 如下方案可在非微信内部WebView的H5页面中调出微信支付
                if (url.startsWith("weixin:")) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        mWebView.getSettings().setJavaScriptEnabled(true);

        if (weChatPayUrl.startsWith("weixin:")) {
            try {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(weChatPayUrl));
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
                ToastUtils.showShort("请先安装或打开微信");
            }
        } else {
            mWebView.loadUrl(weChatPayUrl);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isNeedFinish) {
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        isNeedFinish = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebView.stopLoading();
        mWebView.removeAllViews();
        mWebView.destroy();
        mWebView = null;
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
