package app.app1uppro.modules.friendlist;

import java.util.ArrayList;

import app.app1uppro.apibase.modelclass.FriendsListModel;
import app.app1uppro.baseui.MvpView;

public interface IFriendsListView extends MvpView {
    void FriendListResponse(ArrayList<FriendsListModel.DataBean>  dataBeans,String type);
    void noSuccessMsg(String msg);
    void fndReqResponse(String msg);
}
