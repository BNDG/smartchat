package com.hjq.demo.chat.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;

import com.hjq.demo.R;

public class WebViewActivity extends Activity {
    private WebView mContentWv;
    public static final String RESULT = "result_data";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        initViews();
    }

    private void initViews() {
        mContentWv = findViewById(R.id.wv_content);

        mContentWv.setWebChromeClient(new WebChromeClient());
        mContentWv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });

        Intent intent = getIntent();
        String url = intent.getStringExtra(RESULT);
        mContentWv.loadUrl(url);
    }

}
