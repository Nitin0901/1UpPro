package app.app1uppro.common;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.app1uppro.di.ApplicationContext;
import app.app1uppro.service.ConnectivityReceiver;
import es.dmoral.toasty.Toasty;

/**
 * Created by Ajit on 29-12-2017.
 */

@Singleton
public class CheckInternet {

    private Context mContext;

    @Inject
    public CheckInternet(@ApplicationContext Context context) {
        this.mContext=context;
    }

    public boolean checkConnectionActivity() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        String message = "No Internet Connection";
        if (!isConnected) {
            Toasty.error(mContext, message, Toast.LENGTH_SHORT).show();
        }
        return isConnected;
    }

}
