package app.app1uppro.modules.login;

import android.widget.EditText;

import java.util.HashMap;

import app.app1uppro.baseui.MvpPresenter;
import app.app1uppro.di.PerActivity;

@PerActivity
public interface ILoginPresentor<V extends ILoginView> extends MvpPresenter<V> {
    void onLoginClick(HashMap<String, String> param);
}
