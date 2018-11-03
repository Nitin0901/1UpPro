package app.app1uppro.modules.message;

import java.util.ArrayList;

import app.app1uppro.apibase.modelclass.FriendsListModel;
import app.app1uppro.apibase.modelclass.MessageListModel;
import app.app1uppro.baseui.MvpView;

public interface IMessagesListView extends MvpView {
    void messageListResponse(ArrayList<MessageListModel.DataBean> dataBeans);
    void noSuccessMsg(String msg);
}
