package app.app1uppro.modules.following;

import javax.inject.Inject;

import app.app1uppro.apibase.NetworkConstants;
import app.app1uppro.apibase.modelclass.CallOutsModel;
import app.app1uppro.apibase.modelclass.FollowedCategoryModel;
import app.app1uppro.apibase.modelclass.SucessModel;
import app.app1uppro.apibase.modelclass.VideoCategoryModel;
import app.app1uppro.baseui.BasePresenter;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.GlobalVariable;
import app.app1uppro.modules.callouts.ICallOutsPresenter;
import app.app1uppro.modules.callouts.ICallOutsView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class FollowingPresenter<V extends IFollowingView> extends BasePresenter<V>
        implements IFollowingPresenter<V> {

    @Inject
    public FollowingPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void followedListing() {
        getMvpView().showLoading();
        if (getMvpView().isNetworkConnected()) {
            getCompositeDisposable().add(apiHeaderService().
                    getCategoryFollowed_List(getDataManager().
                            getSharedpref(GlobalVariable.User_id))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleFollowedResponse, this::handleError));
        }
    }

    @Override
    public void startFollowUnfollowingCategory(String videoCatID, String follow_status) {
        getMvpView().showLoading();
        if (getMvpView().isNetworkConnected()) {
            getCompositeDisposable().add(apiHeaderService().startFollowingUnfollowing(getDataManager().
                            getSharedpref(GlobalVariable.User_id),videoCatID,follow_status)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        }
    }


    private void handleFollowedResponse(FollowedCategoryModel followedCategoryModel) {
        getMvpView().hideLoading();
        if (followedCategoryModel.getSuccess() == NetworkConstants.SUCCESS) {
            getMvpView().followedListResponse(followedCategoryModel.getData());
        } else {
            handleApiError(followedCategoryModel.getSuccess(), followedCategoryModel.getMessage());
        }
    }

    private void handleResponse(SucessModel sucessModel) {
        getMvpView().hideLoading();
        if (sucessModel.getSuccess() == NetworkConstants.SUCCESS) {
            getMvpView().startfollowResponse(sucessModel.getMessage());
        } else {
            handleApiError(sucessModel.getSuccess(), sucessModel.getMessage());
        }
    }

}//end class
