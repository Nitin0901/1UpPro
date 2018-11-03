package app.app1uppro.modules.signup;

import java.util.HashMap;
import app.app1uppro.baseui.MvpPresenter;
import app.app1uppro.di.PerActivity;

@PerActivity
public interface ISignupPresentor<V extends ISignupView> extends MvpPresenter<V> {
    void onSignupClick(HashMap<String, String> param);
}
