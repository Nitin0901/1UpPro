package app.app1uppro.modules.chatscreen;

import java.util.ArrayList;

import app.app1uppro.apibase.modelclass.MessageDetailsModel;
import app.app1uppro.apibase.modelclass.MessageListModel;
import app.app1uppro.apibase.modelclass.SucessModel;
import app.app1uppro.apibase.modelclass.VideoCategoryModel;
import app.app1uppro.baseui.MvpView;

public interface IChatView extends MvpView {
    void updateReplyResponse(String msg);
    void updateDetailsResponse(ArrayList<MessageDetailsModel.DataBean> list);
    void updateStatusChangeResponse();
}
