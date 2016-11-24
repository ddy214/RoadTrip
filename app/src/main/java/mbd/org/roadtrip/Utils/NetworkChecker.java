package mbd.org.roadtrip.Utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by derekyu on 11/23/16.
 */

public class NetworkChecker {

    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}