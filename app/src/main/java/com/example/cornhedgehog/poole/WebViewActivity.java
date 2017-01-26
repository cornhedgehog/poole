package com.example.cornhedgehog.poole;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.Objects;

class UrlFinderInBackground extends AsyncTask<Object, Void, Void> {

    private String finalUrl = "";
    private String previousUrl = "";

    @Override //0 - UrlFinder, 1 - url
    protected Void doInBackground(Object[] objects) {
        try {
            UrlFinder urlFinder = (UrlFinder) objects[0];
            finalUrl = urlFinder.getUrl((String) objects[1]);
            WebViewActivity.newUrl = finalUrl;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute() {
        previousUrl = finalUrl;
        finalUrl = WebViewActivity.newUrl;

    }
}

    public class WebViewActivity extends AppCompatActivity {

        public static String newUrl = "";
        public static String previousUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        final WebView wv = (WebView)findViewById(R.id.browser_webview);
        UrlFinder url = new UrlFinder(this.getApplicationContext());
        String url_str = getIntent().getExtras().getString("Url");
        try {
            Object[] objects = new Object[3];
            objects[0] = url;
            objects[1] = url_str;
            new UrlFinderInBackground().execute(objects);
        } catch (Exception e) {
            e.printStackTrace();
        }
        wv.getSettings().setJavaScriptEnabled(true);
        final Activity activity = this;
        wv.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
        });

        //cheaper than onPostExecute
        while (Objects.equals(newUrl, previousUrl)) { }
        wv.loadUrl(newUrl);
    }

        public void onNewSearchButtonClicked(View view) throws Exception {
            final WebView wv = (WebView)findViewById(R.id.browser_webview);
            UrlFinder url = new UrlFinder(this.getApplicationContext());
            String url_str =  wv.getUrl();
            try {
                Object[] objects = new Object[3];
                objects[0] = url;
                objects[1] = url_str;
                new UrlFinderInBackground().execute(objects);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //cheaper than onPostExecute
            while (Objects.equals(newUrl, previousUrl)) { }
            wv.loadUrl(newUrl);
        }
}
