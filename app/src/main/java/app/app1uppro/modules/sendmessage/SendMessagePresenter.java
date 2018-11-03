package app.app1uppro.modules.sendmessage;

import javax.inject.Inject;

import app.app1uppro.apibase.NetworkConstants;
import app.app1uppro.apibase.modelclass.ChangePasswordModel;
import app.app1uppro.apibase.modelclass.SucessModel;
import app.app1uppro.baseui.BasePresenter;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.GlobalVariable;
import app.app1uppro.modules.changepassword.IChangePassPresenter;
import app.app1uppro.modules.changepassword.IChangePassView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SendMessagePresenter<V extends ISendMessageView> extends BasePresenter<V>
        implements ISendMessagePresenter<V> {


    @Inject
    public SendMessagePresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void sendMessageMethod(String user_to, String subject, String message) {
        getMvpView().showLoading();
        getCompositeDisposable().add(apiService().sendMessage(
                getDataManager().getSharedpref(GlobalVariable.User_id),
                user_to,subject,message)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse,this::handleError));
    }

    private void handleResponse(SucessModel sucessModel) {
        getMvpView().hideLoading();
        if(sucessModel.getSuccess()== NetworkConstants.SUCCESS) {
            getMvpView().updateUi(sucessModel.getMessage());
        }
        else handleApiError(sucessModel.getSuccess(),sucessModel.getMessage());
    }

}//end main class
