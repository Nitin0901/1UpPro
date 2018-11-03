package app.app1uppro.modules.profilefragment;

import java.io.File;

import javax.inject.Inject;

import app.app1uppro.apibase.NetworkConstants;
import app.app1uppro.apibase.modelclass.UserProfileModel;
import app.app1uppro.apibase.modelclass.SucessModel;
import app.app1uppro.baseui.BasePresenter;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.GlobalVariable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ProfilePresenter<V extends IProfileView> extends BasePresenter<V>
        implements IProfilePresenter<V> {

    @Inject
    public ProfilePresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void onUserDeatil() {
        getMvpView().showLoading();
        if(getMvpView().isNetworkConnected()) {
            getCompositeDisposable().add(apiHeaderService().getProfile(getDataManager().getSharedpref(GlobalVariable.User_id))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse,this::handleError));
        }
    }

    @Override
    public void onupdateImage(String  profileImage) {
        getMvpView().showLoading();
        RequestBody imageToUpload = RequestBody.create(MediaType.parse("image/*"), new File(profileImage));
        RequestBody userId = RequestBody.create(MediaType.parse("text/plain"), getDataManager().getSharedpref(GlobalVariable.User_id));
        getCompositeDisposable().add(apiHeaderService().updateProfImage(userId,imageToUpload)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleProfileImage,this::handleError));

    }

    @Override
    public void onUpdateProfile(String user_name,String first_name,String last_name) {
        getMvpView().showLoading();
        if(getMvpView().isNetworkConnected()){
            getCompositeDisposable().add(apiHeaderService().updateProfile(getDataManager().getSharedpref(GlobalVariable.User_id),
                    user_name,first_name,last_name)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleUpdateResponse,this::handleError));
        }
    }

    private void handleUpdateResponse(UserProfileModel userProfileModel) {
        getMvpView().hideLoading();
        if(userProfileModel.getSuccess()== NetworkConstants.SUCCESS) {
            getMvpView().updatedUIByUser(userProfileModel.getData());
        } else {
            handleApiError(userProfileModel.getSuccess(), userProfileModel.getMessage());
        }
    }

    private void handleProfileImage(UserProfileModel userProfileModel) {
        getMvpView().hideLoading();
        if(userProfileModel.getSuccess()== NetworkConstants.SUCCESS) {
            getMvpView().getProfileImage(userProfileModel.getData().getProfile_image());
        } else {
            handleApiError(userProfileModel.getSuccess(),userProfileModel.getMessage());
        }
    }

    private void handleResponse(UserProfileModel userProfileModel) {
        getMvpView().hideLoading();
        if(userProfileModel.getSuccess()== NetworkConstants.SUCCESS) {
            getMvpView().updateUI(userProfileModel.getData());
        } else {
            handleApiError(userProfileModel.getSuccess(), userProfileModel.getMessage());
        }
    }


}
