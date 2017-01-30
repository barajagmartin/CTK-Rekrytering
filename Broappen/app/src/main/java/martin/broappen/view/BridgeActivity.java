package martin.broappen.view;

import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import martin.broappen.R;
import martin.broappen.model.BridgeChecker;

import static martin.broappen.util.Reader.read;

public class BridgeActivity extends AppCompatActivity {
    private BridgeChecker bridgeChecker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bridge);
        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("secret_key");
            String key = read(inputStream);
            bridgeChecker = new BridgeChecker(key);
        } catch (IOException e) {
            Toast.makeText(this.getBaseContext(), R.string.secretKeyMissing, Toast.LENGTH_LONG).show();
        }
    }

    public void updateBridge(View view) {
        new UpdateBridgeTask().execute();
    }

    private class UpdateBridgeTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected void onPreExecute () {
            findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
        }

        @Override
        protected Boolean doInBackground(Void params[]) {
            try {
                return bridgeChecker.requestIsOpen();
            } catch (IOException | JSONException e) {
                return null;//e.printStackTrace();
            }
        }

        @Override
        protected void onPostExecute(Boolean isOpen) {
            if(isOpen == null) { // something went wrong
                Toast.makeText(getBaseContext(), R.string.error, Toast.LENGTH_LONG).show();
            } else {
                ImageView image = (ImageView) findViewById(R.id.imageView);
                if (isOpen) {
                    image.setImageResource(R.drawable.open);
                } else {
                    image.setImageResource(R.drawable.closed);
                }
            }
            findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
        }
    }
}
