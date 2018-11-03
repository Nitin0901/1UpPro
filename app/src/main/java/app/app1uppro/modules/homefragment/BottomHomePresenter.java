package app.app1uppro.modules.homefragment;

import javax.inject.Inject;

import app.app1uppro.apibase.NetworkConstants;
import app.app1uppro.apibase.modelclass.ChallengeListModel;
import app.app1uppro.apibase.modelclass.SucessModel;
import app.app1uppro.baseui.BasePresenter;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.GlobalVariable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class BottomHomePresenter<V extends IBottomHomeView> extends BasePresenter<V>
        implements IBottomHomePresenter<V> {

    int position;

    @Inject
    public BottomHomePresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void challengeList() {
        getMvpView().showLoading();
        if(getMvpView().isNetworkConnected()) {
            getCompositeDisposable().add(apiHeaderService().getChallenge_List(getDataManager().getSharedpref(GlobalVariable.User_id))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse,this::handleError));
        }
    }

    @Override
    public void challengeHomeVotes(String videoID, String challengeID, int position) {

        this.position=position;
        getMvpView().showLoading();
        if(getMvpView().isNetworkConnected()) {
            getCompositeDisposable().add(apiHeaderService().
                    getHomeVotes(getDataManager().getSharedpref(GlobalVariable.User_id),
                            videoID,challengeID)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleVoteResponse,this::handleError));
        }
    }


    private void handleVoteResponse(SucessModel sucessModel) {
        if(sucessModel.getSuccess()== NetworkConstants.SUCCESS) {
            getMvpView().hideLoading();
            getMvpView().updateVotesResponse(position,sucessModel.getMessage());
        } else {
            getMvpView().hideLoading();
            handleApiError(sucessModel.getSuccess(), sucessModel.getMessage());
        }
    }

    private void handleResponse(ChallengeListModel challengeListModel) {
        if(challengeListModel.getSuccess()== NetworkConstants.SUCCESS) {
            getMvpView().hideLoading();
            getMvpView().challengeListResponse(challengeListModel.getData());
        } else {
            getMvpView().hideLoading();
            handleApiError(challengeListModel.getSuccess(), challengeListModel.getMessage());
        }
    }

}//end class
