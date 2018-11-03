package app.app1uppro.modules.settings;


import app.app1uppro.baseui.MvpPresenter;
import app.app1uppro.di.PerActivity;

@PerActivity
public interface ISettingsPresenter<V extends ISettings> extends MvpPresenter<V> {

    void logout(String user_id);
}

