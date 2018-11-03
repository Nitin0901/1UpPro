package app.app1uppro.modules.pastvideos;

import app.app1uppro.baseui.MvpPresenter;
import app.app1uppro.di.PerActivity;
import app.app1uppro.modules.homefragment.IBottomHomeView;

@PerActivity
public interface IPastVideoPresenter<V extends IPastVideoView> extends MvpPresenter<V> {

    void pastVideoList(String videoCatID);
    void pastVideosVotes(String videoID, String challengeID, int position, String userType);
}



