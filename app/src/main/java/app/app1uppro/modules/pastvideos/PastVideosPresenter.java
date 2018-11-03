package app.app1uppro.modules.pastvideos;

import javax.inject.Inject;

import app.app1uppro.apibase.NetworkConstants;
import app.app1uppro.apibase.modelclass.ChallengeListModel;
import app.app1uppro.apibase.modelclass.PastVideosModel;
import app.app1uppro.apibase.modelclass.SucessModel;
import app.app1uppro.baseui.BasePresenter;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.GlobalVariable;
import app.app1uppro.modules.homefragment.IBottomHomePresenter;
import app.app1uppro.modules.homefragment.IBottomHomeView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class PastVideosPresenter<V extends IPastVideoView> extends BasePresenter<V>
        implements IPastVideoPresenter<V> {

    int position;

    @Inject
    public PastVideosPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void pastVideoList(String videoCatID) {
        getMvpView().showLoading();
        if(getMvpView().isNetworkConnected()) {
            getCompositeDisposable().add(apiHeaderService().
                    getPastVideos(getDataManager().getSharedpref(GlobalVariable.User_id),
                    videoCatID)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse,this::handleErrorMsg));
        }
    }

    private void handleErrorMsg(Throwable throwable) {
        getMvpView().hideLoading();
        getMvpView().showMessage(throwable.getMessage());
    }

    private void handleResponse(PastVideosModel pastVideosModel) {
        if(pastVideosModel.getSuccess()== NetworkConstants.SUCCESS) {
            getMvpView().hideLoading();
            getMvpView().pastVideoListResponse(pastVideosModel.getData());
        } else {
            getMvpView().hideLoading();
            getMvpView().noDataSuccessResponse(pastVideosModel.getMessage());
        }
    }

    @Override
    public void pastVideosVotes(String videoID, String challengeID,
                                int position, String userType) {
        this.position=position;
        getMvpView().showLoading();
        if(getMvpView().isNetworkConnected()) {
            getCompositeDisposable().add(apiHeaderService().
                    getPastVideosVotes(getDataManager().getSharedpref(GlobalVariable.User_id),
                            videoID,challengeID,userType)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleVoteResponse,this::handleError));
        }
    }

    private void handleVoteResponse(SucessModel sucessModel) {
        if(sucessModel.getSuccess()== NetworkConstants.SUCCESS) {
            getMvpView().hideLoading();
            getMvpView().upDownVotesResponse(position,sucessModel.getMessage());
        } else {
            getMvpView().hideLoading();
            handleApiError(sucessModel.getSuccess(), sucessModel.getMessage());
        }
    }

}//end class
