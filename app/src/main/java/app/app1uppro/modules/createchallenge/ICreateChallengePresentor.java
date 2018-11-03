package app.app1uppro.modules.createchallenge;

import java.util.HashMap;

import app.app1uppro.baseui.MvpPresenter;
import app.app1uppro.di.PerActivity;
import app.app1uppro.modules.login.ILoginView;

@PerActivity
public interface ICreateChallengePresentor<V extends ICreateChallengeView> extends MvpPresenter<V> {
    void onLoginClick(HashMap<String, Object> param);
}
