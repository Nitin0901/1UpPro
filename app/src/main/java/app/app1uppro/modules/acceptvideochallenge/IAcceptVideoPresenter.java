package app.app1uppro.modules.acceptvideochallenge;

import java.util.HashMap;

import app.app1uppro.baseui.MvpPresenter;
import app.app1uppro.di.PerActivity;
import app.app1uppro.modules.uploadvideo.IUpVideoView;

@PerActivity
public interface IAcceptVideoPresenter<V extends IAcceptVideoView> extends MvpPresenter<V> {

    void acceptVideoMethod(HashMap<String, String> params);
}



