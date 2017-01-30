package martin.broappen.model;

import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static martin.broappen.util.Reader.read;


/**
 * Created by Martin on 2017-01-29.
 */

public class BridgeChecker {
    private URL url;

    public BridgeChecker(String key) throws MalformedURLException {
        String format = "Json";
        url = new URL("http://data.goteborg.se/BridgeService/v1.0/GetGABOpenedStatus/" +
                key + "?format=Json");
    }

    public boolean requestIsOpen() throws IOException, JSONException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        String response;
        boolean val = false;
        try {
            InputStream in = new BufferedInputStream(connection.getInputStream());
            response = read(in);
            JSONObject jsonObject = new JSONObject(response);
            val = jsonObject.getBoolean("Value");
        } finally {
            connection.disconnect();
        }

        return val;
    }
}
