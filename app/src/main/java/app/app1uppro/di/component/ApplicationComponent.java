package app.app1uppro.di.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import app.app1uppro.MyApplication;
import app.app1uppro.common.CheckInternet;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.DialogFile;
import app.app1uppro.common.PermissionFile;
import app.app1uppro.common.SessionManager;
import app.app1uppro.di.ApplicationContext;
import app.app1uppro.di.module.ApplicationModule;
import app.app1uppro.util.AppUtils;
import app.app1uppro.util.ImageUtility;
import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MyApplication demoApplication);

    @ApplicationContext
    Context getContext();

    Application getApplication();
    DataManager getDataManager();
    SessionManager getPreferenceHelper();
    PermissionFile getPermissionHelper();
    AppUtils getAppUtil();
    ImageUtility imageUtils();
    CheckInternet checkInternet();
    DialogFile dialogFile();
}
