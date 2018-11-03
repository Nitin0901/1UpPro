package app.app1uppro.modules.uploadvideo;

import app.app1uppro.apibase.modelclass.SucessModel;
import app.app1uppro.apibase.modelclass.UploadVideoModel;
import app.app1uppro.apibase.modelclass.UserProfileModel;
import app.app1uppro.baseui.MvpView;

public interface IUpVideoView extends MvpView {

    void updateUI(String msg);
}
