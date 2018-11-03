package app.app1uppro.modules.changepassword;

import javax.inject.Inject;

import app.app1uppro.apibase.NetworkConstants;
import app.app1uppro.apibase.modelclass.ChangePasswordModel;
import app.app1uppro.baseui.BasePresenter;
import app.app1uppro.common.DataManager;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ChangePasswordPresenter<V extends IChangePassView> extends BasePresenter<V>
        implements IChangePassPresenter<V> {


    @Inject
    public ChangePasswordPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void onChangePassword(String userId,String oldPassword, String newPassword) {
        getMvpView().showLoading();
        getCompositeDisposable().add(apiService().changePassword(userId,oldPassword,newPassword)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse,this::handleError));
    }

    private void handleResponse(ChangePasswordModel passwordModel) {
        getMvpView().hideLoading();
        if(passwordModel.getSuccess()== NetworkConstants.SUCCESS) {
            getMvpView().showMessage(passwordModel.getMessage());
            getMvpView().updateUi(passwordModel.getData());
        }
        else handleApiError(passwordModel.getSuccess(),passwordModel.getMessage());
    }

}//end main class
