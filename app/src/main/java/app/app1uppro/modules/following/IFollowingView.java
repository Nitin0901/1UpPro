package app.app1uppro.modules.following;

import java.util.ArrayList;

import app.app1uppro.apibase.modelclass.CallOutsModel;
import app.app1uppro.apibase.modelclass.FollowedCategoryModel;
import app.app1uppro.apibase.modelclass.VideoCategoryModel;
import app.app1uppro.baseui.MvpView;

public interface IFollowingView extends MvpView {
    void followedListResponse(ArrayList<FollowedCategoryModel.DataBean> followed);
    void startfollowResponse(String msg);
}
