package com.inteliment.navigationapp.genric;



import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class ConnectionLookUP {

    // inernet connection checking function

    public static boolean isInternetConnectionActive(Context context) {
        try {

            NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

            if (networkInfo == null || !networkInfo.isConnected()) {

                return false;

            } else {
                return true;
            }

        } catch (Exception e) {

            return false;
        }

    }
}




