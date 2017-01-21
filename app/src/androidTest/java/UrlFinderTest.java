import android.content.Context;

import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by cornhedgehog on 1/21/2017.
 */
public class UrlFinderTest {
    @Test
    public void getUrl() throws Exception {
        Context context = getInstrumentation().getContext();
        UrlFinder urlFinder = new UrlFinder(context);
        String res = urlFinder.getUrl("http://www.habrahabr.ru");
        assertNotEquals("", res);
    }

}