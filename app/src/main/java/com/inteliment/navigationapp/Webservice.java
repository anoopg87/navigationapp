package com.inteliment.navigationapp;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
public class Webservice {

    // invokes the GET webservice using OkHttpClient and return the respnse
    public static String invokeSampleWebService() throws IOException {
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
