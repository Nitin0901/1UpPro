package app.app1uppro.modules.forgetpassword;

import android.widget.EditText;

import javax.inject.Inject;

import app.app1uppro.apibase.NetworkConstants;
import app.app1uppro.apibase.modelclass.SucessModel;
import app.app1uppro.baseui.BasePresenter;
import app.app1uppro.common.DataManager;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ForgotPasswordPresentor<V extends IForgotPasswordView> extends BasePresenter<V>
        implements IForgotPasswordPresentor<V> {

    @Inject
    public ForgotPasswordPresentor(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    private static Boolean validateEmail(EditText username,EditText email) {
        if(username.length()==0) {
            username.setError("please enter username");
            username.requestFocus();
            return false;
        }else if(email.length()==0) {
            email.setError("please enter email");
            email.requestFocus();
            return false;
        } else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            email.setError("please enter valid email");
            email.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onForgotPassword(EditText username,EditText email) {
        if (getMvpView().isNetworkConnected()&&validateEmail(username,email)) {
            getMvpView().showLoading();
            getCompositeDisposable().add(apiService().forgotPassword(username.getText().toString(),
                    email.getText().toString())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse,this::handleError));
        }
    }

    private void handleResponse(SucessModel sucessModel) {
        getMvpView().hideLoading();
        if(sucessModel.getSuccess()== NetworkConstants.SUCCESS) {
            getMvpView().updateResponse(sucessModel.getMessage());
        } else {
            getMvpView().onError(sucessModel.getMessage());
        }
    }
}
