package app.app1uppro.modules.videosubcategory;

import app.app1uppro.baseui.MvpPresenter;
import app.app1uppro.di.PerActivity;
import app.app1uppro.modules.videocategory.IVideoView;

@PerActivity
public interface ISubVideoPresentor<V extends ISubVideoView> extends MvpPresenter<V> {
    void onLoginClick(String userId,String videoCatID);
}
