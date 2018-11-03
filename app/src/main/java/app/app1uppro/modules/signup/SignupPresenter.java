package app.app1uppro.modules.signup;

import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;

import javax.inject.Inject;

import app.app1uppro.apibase.NetworkConstants;
import app.app1uppro.apibase.modelclass.SucessModel;
import app.app1uppro.baseui.BasePresenter;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.GlobalVariable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SignupPresenter<V extends ISignupView> extends BasePresenter<V>
        implements ISignupPresentor<V> {

    @Inject
    SignupPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void onSignupClick(HashMap<String, String> param) {
        getMvpView().showLoading();
        getCompositeDisposable().add(apiService().signUp(param)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(SucessModel sucessModel) {
        getMvpView().hideLoading();
        if (sucessModel.getSuccess() == NetworkConstants.SUCCESS) {
            getMvpView().updateResponse(sucessModel.getMessage());
        } else {
            getMvpView().onError(sucessModel.getMessage());
        }
    }
}
