package app.app1uppro.modules.login;

import android.widget.EditText;

import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import javax.inject.Inject;

import app.app1uppro.apibase.NetworkConstants;
import app.app1uppro.apibase.modelclass.LoginModel;
import app.app1uppro.baseui.BasePresenter;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.Validate_Structure;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter<V extends ILoginView> extends BasePresenter<V>
        implements ILoginPresentor<V> {

    @Inject
    public LoginPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }
  
    @Override
    public void onLoginClick(HashMap<String, String> param) {
            getMvpView().showLoading();
            getCompositeDisposable().add(apiService().login(param)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse,this::handleError));

    }

    private void handleResponse(LoginModel loginModel) {
        getMvpView().hideLoading();
        if(loginModel.getSuccess()== NetworkConstants.SUCCESS) {
            getMvpView().updateResponse(loginModel.getData());
        } else {
            getMvpView().onError(loginModel.getMessage());
        }
    }
}//end class