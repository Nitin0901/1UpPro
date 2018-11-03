package app.app1uppro.modules.callouts;

import app.app1uppro.baseui.MvpPresenter;
import app.app1uppro.di.PerActivity;
import app.app1uppro.modules.homefragment.IBottomHomeView;

@PerActivity
public interface ICallOutsPresenter<V extends ICallOutsView> extends MvpPresenter<V> {

    void callOutsList();
    void acceptReject(String challengeID,String videoEmbedID,
                      String videoName,String videoDescription,String type);
}



