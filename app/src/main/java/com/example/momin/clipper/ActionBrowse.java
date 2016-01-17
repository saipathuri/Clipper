package com.example.momin.clipper;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ActionBrowse extends Activity {

    WebView webview;

    public void onCreate(Bundle testHard) {

        super.onCreate(testHard);
        String url = getIntent().getStringExtra("URL");

        webview = new WebView(this);
        WebSettings websettings = webview.getSettings();
        websettings.setJavaScriptCanOpenWindowsAutomatically(true);
        websettings.setJavaScriptEnabled(true);
        websettings.setBuiltInZoomControls(true);
        websettings.setPluginState(WebSettings.PluginState.ON);

        webview.loadUrl(url);

        setContentView(webview);
        WebViewClient theWebClient = new WebViewClient();
        webview.setWebViewClient(theWebClient);

    }

    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
            webview.goBack();
            return true;
        }
        else return super.onKeyDown(keyCode, event);
    }



}
