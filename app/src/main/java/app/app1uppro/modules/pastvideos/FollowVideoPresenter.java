package app.app1uppro.modules.pastvideos;

import javax.inject.Inject;

import app.app1uppro.apibase.NetworkConstants;
import app.app1uppro.apibase.modelclass.VideoCategoryModel;
import app.app1uppro.baseui.BasePresenter;
import app.app1uppro.common.DataManager;
import app.app1uppro.modules.videocategory.IVideoPresentor;
import app.app1uppro.modules.videocategory.IVideoView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

class FollowVideoPresenter<V extends IVideoView> extends BasePresenter<V>
        implements IVideoPresentor<V> {

    @Inject
    public FollowVideoPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void onLoginClick(String userid) {
        getMvpView().showLoading();
        getCompositeDisposable().add(apiHeaderService().getVideoCategory(userid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse,this::handleError));

    }

    private void handleResponse(VideoCategoryModel videoCategoryModel) {
        getMvpView().hideLoading();
        if(videoCategoryModel.getSuccess()== NetworkConstants.SUCCESS) {
            getMvpView().updateResponse(videoCategoryModel);
        } else {
            getMvpView().onError(videoCategoryModel.getMessage());
        }
    }

}//end class
