package com.inteliment.navigationapp.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.inteliment.navigationapp.MainActivity;
import com.inteliment.navigationapp.Webservice;

import java.io.IOException;

// AsyncTask for initiating webservice call
public class CallWebService extends AsyncTask<String,Void,String> {
    Context mContext;
    ProgressDialog progress;



    public CallWebService(Context context) {
        mContext=context;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            return Webservice.invokeSampleWebService();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    protected void onPreExecute() {
        progress = new ProgressDialog(mContext);
        progress.setMessage("Loading....");
        progress.show();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {

        // handling the webservice response and pass it to the main context

        if(null!=progress)
        {
            progress.dismiss();
        }

        if(null!=s) {
            if (mContext instanceof MainActivity) {

                ((MainActivity) mContext).loadSpinner(s);
            }
        }else{
            Toast.makeText(mContext, "Error in loading", Toast.LENGTH_SHORT).show();
        }

        super.onPostExecute(s);
    }
}
