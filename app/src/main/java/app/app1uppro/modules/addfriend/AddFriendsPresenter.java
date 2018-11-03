package app.app1uppro.modules.addfriend;

import javax.inject.Inject;

import app.app1uppro.apibase.NetworkConstants;
import app.app1uppro.apibase.modelclass.SucessModel;
import app.app1uppro.baseui.BasePresenter;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.GlobalVariable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class AddFriendsPresenter<V extends IAddFriendsView> extends BasePresenter<V>
        implements IAddFriendsPresenter<V> {

    @Inject
    public AddFriendsPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void friendsAddMethod(String username) {
        getMvpView().showLoading();
        if (getMvpView().isNetworkConnected()) {
            getCompositeDisposable().add(apiHeaderService().add_Friend_method(
                    getDataManager().getSharedpref(GlobalVariable.User_id),username)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        }
    }

    private void handleResponse(SucessModel sucessModel) {
        getMvpView().hideLoading();
        if (sucessModel.getSuccess() == NetworkConstants.SUCCESS) {
            getMvpView().addFriendResponse(sucessModel.getMessage());
        } else {
            handleApiError(sucessModel.getSuccess(), sucessModel.getMessage());
        }
    }

}//end class
