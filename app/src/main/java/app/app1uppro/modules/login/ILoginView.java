package app.app1uppro.modules.login;

import app.app1uppro.apibase.modelclass.LoginModel;
import app.app1uppro.baseui.MvpView;

public interface ILoginView extends MvpView {

    void updateResponse(LoginModel.DataBean dataBean);
}
