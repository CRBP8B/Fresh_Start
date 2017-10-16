package edu.umkc.ch043097.freshstart;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class JSONService {

    private static final String TAG = JSONService.class.getName();

    public static JSONObject fetchJSONObject(String uri) throws JSONException {
        // Fetch JSON string at uri and convert it to a JSON object
        Log.i(TAG, "Testing fetchJSONObject");
        return new JSONObject(fetchJSONString(uri));
    }

    public static String fetchJSONString(String uri) {

        StringBuilder builder = new StringBuilder();
        try {
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(5000 /* milliseconds */);
            conn.setConnectTimeout(7000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            int response = conn.getResponseCode();

            if (response == 200) {
                InputStream content = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
            } else {
                Log.e(TAG, "Failed to download file");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
