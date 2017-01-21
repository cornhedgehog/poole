import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by cornhedgehog on 1/16/2017.
 */

public class UrlFinder {
    Context m_context;
    UrlFinder() {}
    UrlFinder (Context context) {
        this.m_context = context;
    }

    //получить список url на странице
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    String getUrl(String urlString) throws Exception {
        URL url = new URL(urlString);
        String returnedValue = urlString;

        if (isConnectedToInternet()) {
            Document doc = Jsoup.connect(urlString).get();
            Elements tt = doc.select("a[href]");

            int randomDepthStep = ThreadLocalRandom.current().nextInt(0, 10);
            while (!tt.isEmpty() && randomDepthStep != 0) {
                int randomLink = ThreadLocalRandom.current().nextInt(0, tt.size());
                returnedValue = tt.get(randomLink).attr("abs:href");

                //возможно, будет пипец
                Document nextDoc = Jsoup.connect(goInDepth(tt)).get();
                tt = nextDoc.select("a[href]");
                randomDepthStep--;
            }

            if (!tt.isEmpty()) {
                int randomLink = ThreadLocalRandom.current().nextInt(0, tt.size());
                returnedValue = tt.get(randomLink).attr("abs:href");
            }
        }
        return returnedValue;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    String goInDepth(Elements elements) {
        int randomLink = ThreadLocalRandom.current().nextInt(0, elements.size());
        return elements.get(randomLink).attr("abs:href");
    }

    public boolean isConnectedToInternet() {
        final ConnectivityManager conMgr = (ConnectivityManager) m_context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
        return (activeNetwork != null && activeNetwork.isConnected());
    }
}