package app.app1uppro.modules.uploadvideo;

import java.util.HashMap;

import app.app1uppro.baseui.MvpPresenter;
import app.app1uppro.di.PerActivity;
import app.app1uppro.modules.profilefragment.IProfileView;

@PerActivity
public interface IUpVideoPresenter<V extends IUpVideoView> extends MvpPresenter<V> {

    void uploadVideoMethod(HashMap<String,String> params);

}



