package app.app1uppro.modules.sendmessage;

import app.app1uppro.baseui.MvpPresenter;
import app.app1uppro.di.PerActivity;
import app.app1uppro.modules.changepassword.IChangePassView;

@PerActivity
public interface ISendMessagePresenter<V extends ISendMessageView> extends MvpPresenter<V> {

    void sendMessageMethod(String user_to, String subject, String message);
}
