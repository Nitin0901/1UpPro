package app.app1uppro.modules.message;

import app.app1uppro.baseui.MvpPresenter;
import app.app1uppro.di.PerActivity;
import app.app1uppro.modules.friendlist.IFriendsListView;

@PerActivity
public interface IMessagesListPresenter<V extends IMessagesListView> extends MvpPresenter<V> {

    void messageListMethod();
}



