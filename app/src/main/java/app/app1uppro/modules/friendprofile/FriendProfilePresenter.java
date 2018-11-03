package app.app1uppro.modules.friendprofile;

import javax.inject.Inject;

import app.app1uppro.apibase.NetworkConstants;
import app.app1uppro.apibase.modelclass.ChangePasswordModel;
import app.app1uppro.apibase.modelclass.FriendProfileStatusModel;
import app.app1uppro.apibase.modelclass.SucessModel;
import app.app1uppro.baseui.BasePresenter;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.GlobalVariable;
import app.app1uppro.modules.changepassword.IChangePassPresenter;
import app.app1uppro.modules.changepassword.IChangePassView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class FriendProfilePresenter<V extends IFriendProfileView> extends BasePresenter<V>
        implements IFriendProfilePresenter<V> {


    @Inject
    public FriendProfilePresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void friendProfileMethod(String profileID) {
        getMvpView().showLoading();
        getCompositeDisposable().add(apiService().view_Profile(getDataManager().getSharedpref(GlobalVariable.User_id)
                ,profileID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse,this::handleError));
    }

    private void handleResponse(FriendProfileStatusModel friendProfileStatusModel) {
        getMvpView().hideLoading();
        if(friendProfileStatusModel.getSuccess()== NetworkConstants.SUCCESS) {
            getMvpView().updateUi(friendProfileStatusModel.getData());
        }
        else handleApiError(friendProfileStatusModel.getSuccess(),friendProfileStatusModel.getMessage());
    }

}//end main class
