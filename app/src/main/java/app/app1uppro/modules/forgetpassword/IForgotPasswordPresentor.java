package app.app1uppro.modules.forgetpassword;

import android.widget.EditText;
import app.app1uppro.baseui.MvpPresenter;
import app.app1uppro.di.PerActivity;

@PerActivity
public interface IForgotPasswordPresentor<V extends IForgotPasswordView> extends MvpPresenter<V> {
    void onForgotPassword(EditText username,EditText email);
}
