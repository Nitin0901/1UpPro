package app.app1uppro.modules.videosubcategory;

import javax.inject.Inject;

import app.app1uppro.apibase.NetworkConstants;
import app.app1uppro.apibase.modelclass.VideoCategoryModel;
import app.app1uppro.apibase.modelclass.VideoSubCatModel;
import app.app1uppro.baseui.BasePresenter;
import app.app1uppro.common.DataManager;
import app.app1uppro.modules.videocategory.IVideoPresentor;
import app.app1uppro.modules.videocategory.IVideoView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

class VideoSubPresenter<V extends ISubVideoView> extends BasePresenter<V>
        implements ISubVideoPresentor<V> {

    @Inject
    public VideoSubPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void onLoginClick(String userid,String categoryId) {
        getMvpView().showLoading();
        getCompositeDisposable().add(apiHeaderService().getSubVideoCategory(userid,categoryId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse,this::handleError));

    }

    private void handleResponse(VideoSubCatModel videoCategoryModel) {
        getMvpView().hideLoading();
        if(videoCategoryModel.getSuccess()== NetworkConstants.SUCCESS) {
            getMvpView().updateResponse(videoCategoryModel);
        } else {
            getMvpView().onError(videoCategoryModel.getMessage());
        }
    }

}//end class
