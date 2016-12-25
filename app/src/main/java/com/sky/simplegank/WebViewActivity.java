package com.sky.simplegank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.load_progress)
    ProgressBar mProgressBar;
    @BindView(R.id.web_view)
    WebView mWebView;
    @BindView(R.id.tv_title)
    TextSwitcher mSwitcher;

    private String mUrl;
    private String mTitle;
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_URL = "url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);

        getData();

        initToolBar();

        initWebView();

        initTextSwitcher();

        if (!TextUtils.isEmpty(mUrl))
            mWebView.loadUrl(mUrl);
    }

    private void getData() {
        mTitle = getIntent().getStringExtra(EXTRA_TITLE);
        mUrl = getIntent().getStringExtra(EXTRA_URL);
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        mSwitcher.setText(title);
    }

    /**
     * 对ToolBar进行相关设置
     */
    private void initToolBar() {
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    /**
     * 对WebView进行一些初始化设置
     */
    private void initWebView() {
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setAppCacheEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.setWebViewClient(new WebClient());
        mWebView.setWebChromeClient(new ChromeClient());
    }

    /**
     * 对TextSwitcher进行设置，跑马灯形式显示标题
     */
    private void initTextSwitcher() {
        mSwitcher.setFactory(mFactory);
        mSwitcher.setInAnimation(WebViewActivity.this, android.R.anim.fade_in);
        mSwitcher.setOutAnimation(WebViewActivity.this, android.R.anim.fade_out);
        if (mTitle != null) {
            setTitle(mTitle);
        }
    }

    private ViewSwitcher.ViewFactory mFactory = new ViewSwitcher.ViewFactory() {
        @Override
        public View makeView() {
            final TextView textView = new TextView(WebViewActivity.this);
            textView.setTextAppearance(WebViewActivity.this, R.style.WebTitle);
            textView.setSingleLine();
            textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            textView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    textView.setSelected(true);
                }
            }, 1378);
            return textView;
        }
    };

    public static Intent newIntent(Context packageContext, String title, String url) {
        Intent urlIntent = new Intent(packageContext, WebViewActivity.class);
        urlIntent.putExtra(EXTRA_URL, url);
        urlIntent.putExtra(EXTRA_TITLE, title);
        return urlIntent;
    }

    private class ChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            mProgressBar.setProgress(newProgress);
            if (newProgress == 100) {
                mProgressBar.setVisibility(View.GONE);
            } else {
                mProgressBar.setVisibility(View.VISIBLE);
            }
        }
    }

    private class WebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (!TextUtils.isEmpty(url)) {
                view.loadUrl(url);
            }
            return true;
        }
    }

    /**
     * 释放WebView
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebView.destroy();
    }
}
