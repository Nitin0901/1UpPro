package app.app1uppro.modules.addfriend;

import app.app1uppro.baseui.MvpPresenter;
import app.app1uppro.di.PerActivity;

@PerActivity
public interface IAddFriendsPresenter<V extends IAddFriendsView> extends MvpPresenter<V> {

    void friendsAddMethod(String username);
}



