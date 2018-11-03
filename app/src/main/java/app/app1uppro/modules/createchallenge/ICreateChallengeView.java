package app.app1uppro.modules.createchallenge;

import app.app1uppro.apibase.modelclass.CreateChallengeModel;
import app.app1uppro.apibase.modelclass.LoginModel;
import app.app1uppro.baseui.MvpView;

public interface ICreateChallengeView extends MvpView {

    void updateResponse(String msg,CreateChallengeModel.DataBean dataBean);
}
