package app.app1uppro.modules.profilefragment;

import app.app1uppro.apibase.modelclass.UserProfileModel;
import app.app1uppro.baseui.MvpView;

public interface IProfileView extends MvpView {

    void getProfileImage(String image);
    void updateUI(UserProfileModel.DataBean dataBean);
    void updatedUIByUser(UserProfileModel.DataBean dataBean);
}
