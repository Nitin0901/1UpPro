package app.app1uppro.modules.uploadvideo;

import java.io.File;
import java.util.HashMap;

import javax.inject.Inject;

import app.app1uppro.apibase.NetworkConstants;
import app.app1uppro.apibase.modelclass.SucessModel;
import app.app1uppro.apibase.modelclass.UploadVideoModel;
import app.app1uppro.apibase.modelclass.UserProfileModel;
import app.app1uppro.baseui.BasePresenter;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.GlobalVariable;
import app.app1uppro.modules.profilefragment.IProfilePresenter;
import app.app1uppro.modules.profilefragment.IProfileView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class UploadVideoPresenter<V extends IUpVideoView> extends BasePresenter<V>
        implements IUpVideoPresenter<V> {

    @Inject
    public UploadVideoPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void uploadVideoMethod(HashMap<String, String> params) {
        getMvpView().showLoading();
        if (getMvpView().isNetworkConnected()) {
            getCompositeDisposable().add(apiHeaderService().add_Challenge_Video(params)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleUploadResponse, this::handleError));
        }
    }

    private void handleUploadResponse(SucessModel sucessModel) {
        getMvpView().hideLoading();
        if (sucessModel.getSuccess() == NetworkConstants.SUCCESS) {
            getMvpView().updateUI(sucessModel.getMessage());
        } else {
            handleApiError(sucessModel.getSuccess(),sucessModel.getMessage());
        }
    }

}
