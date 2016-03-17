package com.inteliment.navigationapp;
import android.content.Context;
import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import org.json.JSONArray;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Webservice {
    static AQuery aQuery;



    public static String invokeWebService() throws IOException {

        OkHttpClient client = new OkHttpClient();
        Response response = null;

        String url = "http://express-it.optusnet.com.au/sample.json";

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            response = client.newCall(request).execute();
            return response.body().string();





    }


}
