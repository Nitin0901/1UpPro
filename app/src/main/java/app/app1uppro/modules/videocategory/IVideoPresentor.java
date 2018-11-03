package app.app1uppro.modules.videocategory;

import java.util.HashMap;

import app.app1uppro.baseui.MvpPresenter;
import app.app1uppro.di.PerActivity;
import app.app1uppro.modules.login.ILoginView;

@PerActivity
public interface IVideoPresentor<V extends IVideoView> extends MvpPresenter<V> {
    void onLoginClick(String userId);
}
