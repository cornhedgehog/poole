package com.example.cornhedgehog.poole;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.Random;

/**
 * Created by cornhedgehog on 1/16/2017.
 */

public class UrlFinder {
    Context m_context;
    UrlFinder() {}
    public UrlFinder(Context context) {
        this.m_context = context;
    }

    //get Url for further browsing
    public String getUrl(String url) throws Exception {
        if (isConnectedToInternet()) {
            Connection c = Jsoup.connect(url);
            Document doc = c.get();
            Elements tt = doc.select("a[href]");

            //TODO optimize
            Random random = new Random(System.nanoTime()*System.currentTimeMillis());
            int rand = random.nextInt(10);
            for (int i = 0; i < rand; i++) {
                int randLimited = random.nextInt(tt.size() - 1);
                url = tt.get(randLimited).attr("abs:href");
                if (tt.isEmpty()) break;
            }
            if (!tt.isEmpty()) {
                int randomLink =  random.nextInt(tt.size() - 1);
                url = tt.get(randomLink).attr("abs:href");
            }
        }
        return url;
    }

    public boolean isConnectedToInternet() {
        final ConnectivityManager conMgr = (ConnectivityManager) m_context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
        return (activeNetwork != null && activeNetwork.isConnected());
    }
}