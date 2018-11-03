package app.app1uppro.modules.chatscreen;

import app.app1uppro.baseui.MvpPresenter;
import app.app1uppro.di.PerActivity;
import app.app1uppro.modules.videocategory.IVideoView;

@PerActivity
public interface IChatPresentor<V extends IChatView> extends MvpPresenter<V> {
    void sendReplyMethod(String message_id,String user_to, String message);
    void getMessageDetails(String message_id);
    void chaneMessageReadStatus(String message_id);
}
