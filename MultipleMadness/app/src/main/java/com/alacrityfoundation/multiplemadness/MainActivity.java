package com.alacrityfoundation.multiplemadness;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView wv = (WebView)findViewById(R.id.webView);
        wv.setWebViewClient(new CustomWebViewClient());
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl("http://phosphorus.github.io/app.html?id=47729710&turbo=false&full-screen=true");

        if (Build.VERSION.SDK_INT >= 19) {
            wv.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
    }

    private class CustomWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            Log.i("xxxxxxx", url);
            super.onLoadResource(view, url);
        }
    }
}
