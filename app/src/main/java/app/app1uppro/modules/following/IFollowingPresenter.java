package app.app1uppro.modules.following;

import app.app1uppro.baseui.MvpPresenter;
import app.app1uppro.di.PerActivity;
import app.app1uppro.modules.callouts.ICallOutsView;

@PerActivity
public interface IFollowingPresenter<V extends IFollowingView> extends MvpPresenter<V> {
    void followedListing();
    void startFollowUnfollowingCategory(String videoCatID ,String follow_status);
}



