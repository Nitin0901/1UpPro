package app.app1uppro.modules.homefragment;

import app.app1uppro.baseui.MvpPresenter;
import app.app1uppro.di.PerActivity;
import app.app1uppro.modules.profilefragment.IProfileView;

@PerActivity
public interface IBottomHomePresenter<V extends IBottomHomeView> extends MvpPresenter<V> {

    void challengeList();
    void challengeHomeVotes(String videoID,String challengeID,int position);
}



