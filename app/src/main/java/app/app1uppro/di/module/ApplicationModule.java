package app.app1uppro.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import app.app1uppro.common.GlobalVariable;
import app.app1uppro.di.ApplicationContext;
import dagger.Module;
import dagger.Provides;


@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application app) {
        mApplication = app;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    SharedPreferences provideSharedPrefs() {
        return mApplication.getSharedPreferences(GlobalVariable.SharedPrefenceName, Context.MODE_PRIVATE);
    }
}
