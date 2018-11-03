package app.app1uppro.modules.sendmessage;

import app.app1uppro.apibase.modelclass.ChangePasswordModel;
import app.app1uppro.baseui.MvpView;

public interface ISendMessageView extends MvpView {

    void updateUi(String msg);
}
