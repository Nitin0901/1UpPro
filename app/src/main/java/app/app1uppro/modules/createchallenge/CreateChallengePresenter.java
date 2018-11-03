package app.app1uppro.modules.createchallenge;

import java.util.HashMap;

import javax.inject.Inject;

import app.app1uppro.apibase.NetworkConstants;
import app.app1uppro.apibase.modelclass.CreateChallengeModel;
import app.app1uppro.apibase.modelclass.LoginModel;
import app.app1uppro.baseui.BasePresenter;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.GlobalVariable;
import app.app1uppro.modules.login.ILoginPresentor;
import app.app1uppro.modules.login.ILoginView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class CreateChallengePresenter<V extends ICreateChallengeView> extends BasePresenter<V>
        implements ICreateChallengePresentor<V> {

    @Inject
    public CreateChallengePresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }
  
    @Override
    public void onLoginClick(HashMap<String, Object> param) {
            getMvpView().showLoading();
        getCompositeDisposable().add(apiHeaderService().createChallenge(param)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse,this::handleError));

    }

    private void handleResponse(CreateChallengeModel createChallengeModel) {
        getMvpView().hideLoading();
        if(createChallengeModel.getSuccess()== NetworkConstants.SUCCESS) {
            getMvpView().updateResponse(createChallengeModel.getMessage(),createChallengeModel.getData());
        } else {
            getMvpView().onError(createChallengeModel.getMessage());
        }
    }
}//end class