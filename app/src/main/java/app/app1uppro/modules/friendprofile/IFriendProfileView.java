package app.app1uppro.modules.friendprofile;

import app.app1uppro.apibase.modelclass.ChangePasswordModel;
import app.app1uppro.apibase.modelclass.FriendProfileStatusModel;
import app.app1uppro.baseui.MvpView;

public interface IFriendProfileView extends MvpView {

    void updateUi(FriendProfileStatusModel.DataBean dataBean);
}
