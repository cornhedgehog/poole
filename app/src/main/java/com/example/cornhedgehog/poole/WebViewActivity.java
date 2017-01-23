package com.example.cornhedgehog.poole;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.example.cornhedgehog.poole.*;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_webview);

        final WebView wv = (WebView)findViewById(R.id.browser_webview);
        wv.getSettings().setJavaScriptEnabled(true);

        final Activity activity = this;

        wv.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
        });

        //com.example.cornhedgehog.poole.UrlFinder urlFinder = new com.example.cornhedgehog.poole.UrlFinder(this.getApplicationContext());
        //String finalUrl =

        wv.loadUrl("http://" + getIntent().getExtras().getString("Url"));
        //wv.loadUrl("www.google.com");
        //wv.measure(100, 100);
        //wv.getSettings().setUseWideViewPort(true);
        //wv.getSettings().setLoadWithOverviewMode(true);
    }
}
