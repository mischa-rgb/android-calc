package com.recall.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * The whole app is one screen: the calculator page bundled in assets/www,
 * shown full screen. Everything runs offline; memories are saved on the phone.
 */
public class MainActivity extends Activity {

    private static final String START_URL = "file:///android_asset/www/index.html";
    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        web = new WebView(this);
        web.setBackgroundColor(getResources().getColor(R.color.page, getTheme()));

        WebSettings settings = web.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);   // where memories are kept
        settings.setAllowContentAccess(false);
        settings.setTextZoom(100);             // keep the keypad layout stable

        // Stay on the bundled page; never navigate anywhere else.
        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return !request.getUrl().toString().startsWith("file:///android_asset/");
            }
        });

        setContentView(web);
        web.loadUrl(START_URL);
    }

    /** Back closes the memories sheet or form first; then leaves the app. */
    @Override
    @SuppressWarnings("deprecation")
    public void onBackPressed() {
        web.evaluateJavascript(
            "(window.recallBack && window.recallBack()) ? 'handled' : 'exit'",
            value -> {
                if (value == null || !value.contains("handled")) finish();
            });
    }

    @Override
    protected void onDestroy() {
        if (web != null) web.destroy();
        super.onDestroy();
    }
}
