package app.app1uppro.baseui;

import javax.inject.Inject;

import app.app1uppro.apibase.ApiClient;
import app.app1uppro.apibase.ApiInterface;
import app.app1uppro.apibase.NetworkConstants;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.GlobalVariable;
import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private static final String TAG = "BasePresenter";

    private final DataManager mDataManager;

    private final CompositeDisposable mCompositeDisposable;

    private ApiInterface apiService;

    private ApiInterface apiHeaderService;

    private ApiInterface googleApiService;

    private V mMvpView;

    @Inject
    public BasePresenter(DataManager dataManager,
                         CompositeDisposable compositeDisposable) {
        this.mDataManager = dataManager;
        this.mCompositeDisposable = compositeDisposable;

        apiService = ApiClient.getClient().create(ApiInterface.class);

        googleApiService=ApiClient.getGoogleClient().create(ApiInterface.class);

        apiHeaderService = new ApiClient().getClientHeader(dataManager.getSharedpref(GlobalVariable.AUTH_TOKEN)).create(ApiInterface.class);
    }

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void onDetach() {
        getMvpView().hideLoading();
        mCompositeDisposable.clear();
        mMvpView = null;
        apiService=null;
        apiHeaderService=null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public V getMvpView() {
        return mMvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public DataManager getDataManager() {
        return mDataManager;
    }



    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public ApiInterface apiService()
    {
        return apiService;
    }


    public ApiInterface apiHeaderService()
    {
        return apiHeaderService;
    }

    public ApiInterface apiGoogleService()
    {
        return googleApiService;
    }



    @Override
    public void handleApiError(int errorType,String message) {

        switch (errorType)
        {
            case NetworkConstants.AUTHFAILED:
                getMvpView().onError(message);
                getMvpView().openActivityOnTokenExpire();
                break;
             default:
                 getMvpView().onError(message);
                 break;
        }

    }

    @Override
    public void setUserAsLoggedOut() { }

    public void handleError(Throwable throwable) {
        getMvpView().hideLoading();
        getCompositeDisposable().clear();
        getMvpView().onError(throwable.getLocalizedMessage());
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
