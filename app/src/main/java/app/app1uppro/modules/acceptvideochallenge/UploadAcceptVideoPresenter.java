package app.app1uppro.modules.acceptvideochallenge;

import java.util.HashMap;

import javax.inject.Inject;

import app.app1uppro.apibase.NetworkConstants;
import app.app1uppro.apibase.modelclass.SucessModel;
import app.app1uppro.baseui.BasePresenter;
import app.app1uppro.common.DataManager;
import app.app1uppro.modules.uploadvideo.IUpVideoPresenter;
import app.app1uppro.modules.uploadvideo.IUpVideoView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class UploadAcceptVideoPresenter<V extends IAcceptVideoView> extends BasePresenter<V>
        implements IAcceptVideoPresenter<V> {

    @Inject
    public UploadAcceptVideoPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void acceptVideoMethod(HashMap<String, String> params) {
        getMvpView().showLoading();
        if (getMvpView().isNetworkConnected()) {
            getCompositeDisposable().add(apiHeaderService().accept_Reject_Challenge(params)
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
