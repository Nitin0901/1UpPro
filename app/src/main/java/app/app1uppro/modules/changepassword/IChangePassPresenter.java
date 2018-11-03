package app.app1uppro.modules.changepassword;

import app.app1uppro.baseui.MvpPresenter;
import app.app1uppro.di.PerActivity;

@PerActivity
public interface IChangePassPresenter<V extends IChangePassView> extends MvpPresenter<V> {

    void onChangePassword(String userId, String oldPassword, String newPassword);
}
