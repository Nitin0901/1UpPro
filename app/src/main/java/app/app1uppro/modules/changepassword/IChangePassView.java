package app.app1uppro.modules.changepassword;

import app.app1uppro.apibase.modelclass.ChangePasswordModel;
import app.app1uppro.baseui.MvpView;

public interface IChangePassView extends MvpView {

    void updateUi(ChangePasswordModel.DataBean dataBean);
}
