package app.app1uppro.modules.friendlist;

import app.app1uppro.baseui.MvpPresenter;
import app.app1uppro.di.PerActivity;

@PerActivity
public interface IFriendsListPresenter<V extends IFriendsListView> extends MvpPresenter<V> {

    void friendsListMethod(String type);
    void friendsRequestListMethod(String type);
    void fndReqAcceptReject(String id,String type);
}



