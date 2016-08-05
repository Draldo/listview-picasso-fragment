package com.example.admin.listviewpicassofragment;

import android.os.AsyncTask;
import android.util.Log;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by admin on 8/5/2016.
 */
public class getJson extends AsyncTask <String, Void, String> {

    private static final String TAG = getJson.class.getSimpleName() + "TAG_";
    private ListviewFragment mlistviewFragment;

    public getJson(ListviewFragment listviewFragment) {
        mlistviewFragment = listviewFragment;
    }

    @Override
    protected String doInBackground(String... strings) {
        String urlStr = "http://www.mocky.io/v2/57a01bec0f0000c10d0f650f";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(urlStr)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String str = response.body().string();
            return str;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    protected void onPostExecute(String s) {
        mlistviewFragment.loadJson(s);
        super.onPostExecute(s);
    }
}
