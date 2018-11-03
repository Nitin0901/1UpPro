package app.app1uppro.modules.profilefragment;

import app.app1uppro.baseui.MvpPresenter;
import app.app1uppro.di.PerActivity;

@PerActivity
public interface IProfilePresenter<V extends IProfileView> extends MvpPresenter<V> {

    void onUserDeatil();
    void onupdateImage(String image);
    void onUpdateProfile(String user_name, String first_name, String last_name);

}



