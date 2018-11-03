package app.app1uppro.modules.friendprofile;

import app.app1uppro.baseui.MvpPresenter;
import app.app1uppro.di.PerActivity;
import app.app1uppro.modules.changepassword.IChangePassView;

@PerActivity
public interface IFriendProfilePresenter<V extends IFriendProfileView> extends MvpPresenter<V> {

    void friendProfileMethod(String profileID);
}
