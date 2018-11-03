package app.app1uppro;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.codewaves.youtubethumbnailview.ThumbnailLoader;
import com.google.firebase.FirebaseApp;

import app.app1uppro.di.component.ApplicationComponent;
import app.app1uppro.di.component.DaggerApplicationComponent;
import app.app1uppro.di.module.ApplicationModule;
import app.app1uppro.service.ConnectivityReceiver;


public class MyApplication extends MultiDexApplication {

    private static MyApplication mInstance;
    protected ApplicationComponent applicationComponent;

    public static MyApplication get(Context context) {
        return (MyApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Fabric.with(this, new Crashlytics());

        mInstance = this;
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
        FirebaseApp.initializeApp(this);
        MultiDex.install(this);
        ThumbnailLoader.initialize(this);

//        if (BuildConfig.DEBUG)
//            Timber.plant(new Timber.DebugTree());
//        else
//            Timber.plant(new NotLoggingTree());

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }

    public static MyApplication get() {
        return mInstance;
    }


}