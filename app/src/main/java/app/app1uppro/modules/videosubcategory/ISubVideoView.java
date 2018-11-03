package app.app1uppro.modules.videosubcategory;

import app.app1uppro.apibase.modelclass.VideoCategoryModel;
import app.app1uppro.apibase.modelclass.VideoSubCatModel;
import app.app1uppro.baseui.MvpView;

public interface ISubVideoView extends MvpView {
    void updateResponse(VideoSubCatModel videoSubCatModel);
}
