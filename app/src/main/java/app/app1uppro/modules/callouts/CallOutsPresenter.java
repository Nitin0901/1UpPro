package app.app1uppro.modules.callouts;

import javax.inject.Inject;

import app.app1uppro.apibase.NetworkConstants;
import app.app1uppro.apibase.modelclass.CallOutsModel;
import app.app1uppro.apibase.modelclass.ChallengeListModel;
import app.app1uppro.apibase.modelclass.SucessModel;
import app.app1uppro.baseui.BasePresenter;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.GlobalVariable;
import app.app1uppro.modules.homefragment.IBottomHomePresenter;
import app.app1uppro.modules.homefragment.IBottomHomeView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CallOutsPresenter<V extends ICallOutsView> extends BasePresenter<V>
        implements ICallOutsPresenter<V> {

    @Inject
    public CallOutsPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void callOutsList() {
        getMvpView().showLoading();
        if (getMvpView().isNetworkConnected()) {
            getCompositeDisposable().add(apiHeaderService().getCallOuts_List(getDataManager().getSharedpref(GlobalVariable.User_id))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        }
    }

    @Override
    public void acceptReject(String challengeID, String videoEmbedID,
                             String videoName, String videoDescription, String type) {
        getMvpView().showLoading();
        if (getMvpView().isNetworkConnected()) {
            getCompositeDisposable().add(apiHeaderService().getCallOuts_AcceptReject(
                    getDataManager().getSharedpref(GlobalVariable.User_id),
                    challengeID, videoEmbedID, videoName, videoDescription, type)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::acceptRejectResponse, this::handleError));
        }
    }

    private void acceptRejectResponse(SucessModel sucessModel) {
        getMvpView().hideLoading();
        if (sucessModel.getSuccess() == NetworkConstants.SUCCESS)
            getMvpView().acceptRejectResponse(sucessModel.getMessage());
        else
            handleApiError(sucessModel.getSuccess(), sucessModel.getMessage());

    }

    private void handleResponse(CallOutsModel callOutsModel) {
        getMvpView().hideLoading();
        if (callOutsModel.getSuccess() == NetworkConstants.SUCCESS)
            getMvpView().callOutsListResponse(callOutsModel.getData());
        else
            getMvpView().noDataResponse(callOutsModel.getMessage());

    }

}//end class
