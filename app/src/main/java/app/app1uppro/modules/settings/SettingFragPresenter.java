package app.app1uppro.modules.settings;

import javax.inject.Inject;

import app.app1uppro.apibase.NetworkConstants;
import app.app1uppro.apibase.modelclass.SucessModel;
import app.app1uppro.baseui.BasePresenter;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.GlobalVariable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SettingFragPresenter<V extends ISettings> extends BasePresenter<V>
        implements ISettingsPresenter<V> {

    @Inject
    public SettingFragPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void logout(String user_id) {
        getMvpView().showLoading();
        getCompositeDisposable().add(apiService().logout(user_id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::getLogoutResponse, this::handleError));
    }

    private void getLogoutResponse(SucessModel model) {
        getMvpView().hideLoading();
        if (model.getSuccess() == NetworkConstants.SUCCESS) {
            getMvpView().updateUI(model.getMessage());
        } else {
            handleApiError(model.getSuccess(), model.getMessage());
        }
    }

}//end main class
