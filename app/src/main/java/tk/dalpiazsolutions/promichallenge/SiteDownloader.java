package tk.dalpiazsolutions.promichallenge;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Christoph on 30.03.2018.
 */

public class SiteDownloader extends AsyncTask<String, Void, String> {

    private URL url;
    private HttpURLConnection connection;
    private StringBuilder result = new StringBuilder();
    private String line;

    @Override
    protected String doInBackground(String... urls) {

        try {
            url = new URL(urls[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            while ((line=bufferedReader.readLine()) != null)
            {
                Log.i("line", line);
                result.append(line);
            }

            return result.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
