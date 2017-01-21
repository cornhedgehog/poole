/**
 * Created by cornhedgehog on 1/21/2017.
 */

import org.junit.Test;
import static org.junit.Assert.assertNotEquals;

public class UrlFinderTests extends UrlFinder {
    @Test
    public void getUrlTest() throws Exception {
        UrlFinder urlFinder = new UrlFinder();
        String res = urlFinder.getUrl("www.habrahabr.ru");
        assertNotEquals("", res);
    }
}