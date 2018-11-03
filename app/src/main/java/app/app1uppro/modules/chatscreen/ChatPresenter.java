package app.app1uppro.modules.chatscreen;

import javax.inject.Inject;

import app.app1uppro.apibase.NetworkConstants;
import app.app1uppro.apibase.modelclass.MessageDetailsModel;
import app.app1uppro.apibase.modelclass.MessageListModel;
import app.app1uppro.apibase.modelclass.SucessModel;
import app.app1uppro.apibase.modelclass.VideoCategoryModel;
import app.app1uppro.baseui.BasePresenter;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.GlobalVariable;
import app.app1uppro.modules.videocategory.IVideoPresentor;
import app.app1uppro.modules.videocategory.IVideoView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

class ChatPresenter<V extends IChatView> extends BasePresenter<V>
        implements IChatPresentor<V> {

    @Inject
    public ChatPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void chaneMessageReadStatus(String message_id) {
        getMvpView().showLoading();
        getCompositeDisposable().add(apiHeaderService().updateMessageReadStatus(getDataManager().getSharedpref(GlobalVariable.User_id),
                message_id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleMsgStatusResponse,this::handleError));
    }
    private void handleMsgStatusResponse(SucessModel sucessModel) {
        getMvpView().hideLoading();
        if(sucessModel.getSuccess()== NetworkConstants.SUCCESS) {
            getMvpView().updateStatusChangeResponse();
        } else {
            getMvpView().onError(sucessModel.getMessage());
        }
    }

    @Override
    public void getMessageDetails(String message_id) {
        getMvpView().showLoading();
        getCompositeDisposable().add(apiHeaderService().getMessageDetail(getDataManager().getSharedpref(GlobalVariable.User_id),
                message_id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleDetailResponse,this::handleError));
    }
    private void handleDetailResponse(MessageDetailsModel messageListModel) {
        getMvpView().hideLoading();
        if(messageListModel.getSuccess()== NetworkConstants.SUCCESS) {
            getMvpView().updateDetailsResponse(messageListModel.getData());
        } else {
            getMvpView().onError(messageListModel.getMessage());
        }
    }

    @Override
    public void sendReplyMethod(String message_id,String user_to, String message) {
        getMvpView().showLoading();
        getCompositeDisposable().add(apiHeaderService().sendReplyMessage(getDataManager().getSharedpref(GlobalVariable.User_id),
                message_id,user_to,message )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse,this::handleError));
    }
    private void handleResponse(SucessModel sucessModel) {
        getMvpView().hideLoading();
        if(sucessModel.getSuccess()== NetworkConstants.SUCCESS) {
            getMvpView().updateReplyResponse(sucessModel.getMessage());
        } else {
            getMvpView().onError(sucessModel.getMessage());
        }
    }

}//end class
