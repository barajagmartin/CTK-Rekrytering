package martin.broappen.util;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by Martin on 2017-01-30.
 */

public class Reader {
    @NonNull
    public static String read(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        Scanner s = new Scanner(inputStream);
        while(s.hasNext()) {
            sb.append(s.next());
        }
        return sb.toString();
    }
}
