package app.app1uppro.modules.videocategory;

import app.app1uppro.apibase.modelclass.LoginModel;
import app.app1uppro.apibase.modelclass.VideoCategoryModel;
import app.app1uppro.baseui.MvpView;

public interface IVideoView extends MvpView {
    void updateResponse(VideoCategoryModel videoCategoryModel);
}
