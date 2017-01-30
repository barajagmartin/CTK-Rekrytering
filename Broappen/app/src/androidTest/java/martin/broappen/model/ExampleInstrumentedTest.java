package martin.broappen.model;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.MalformedURLException;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {

        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("martin.broappen", appContext.getPackageName());
    }

    @Test(expected = MalformedURLException.class)
    public void testCreateBridgeChecker() throws Exception {
        BridgeChecker bc = new BridgeChecker();
    }

    @Test(expected = IOException.class)
    public void testRequestIsOpen1() throws Exception {
        BridgeChecker bc = new BridgeChecker();
        assertFalse(bc.requestIsOpen());
    }

    @Test(expected = JSONException.class)
    public void testRequestIsOpen2() throws Exception {
        BridgeChecker bc = new BridgeChecker();
        assertTrue(bc.requestIsOpen());
    }
}
