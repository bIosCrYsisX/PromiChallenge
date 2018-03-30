package tk.dalpiazsolutions.promichallenge;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Christoph on 30.03.2018.
 */

public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

    private URL imageURL;
    private HttpURLConnection imageURLConnection;
    private InputStream imageStream;
    private Bitmap bitmap;

    @Override
    protected Bitmap doInBackground(String... urls) {

        try
        {
            imageURL = new URL(urls[0]);
            imageURLConnection = (HttpURLConnection) imageURL.openConnection();
            imageURLConnection.connect();
            imageStream = imageURLConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(imageStream);
            return bitmap;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
