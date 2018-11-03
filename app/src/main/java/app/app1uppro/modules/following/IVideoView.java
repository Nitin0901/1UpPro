package app.app1uppro.modules.following;

import app.app1uppro.apibase.modelclass.VideoCategoryModel;
import app.app1uppro.baseui.MvpView;

public interface IVideoView extends MvpView {
    void updateResponse(VideoCategoryModel videoCategoryModel);
}
