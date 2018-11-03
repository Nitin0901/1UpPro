package app.app1uppro.modules.message;

import java.util.ArrayList;

import javax.inject.Inject;

import app.app1uppro.apibase.NetworkConstants;
import app.app1uppro.apibase.modelclass.FriendsListModel;
import app.app1uppro.apibase.modelclass.MessageListModel;
import app.app1uppro.apibase.modelclass.SucessModel;
import app.app1uppro.baseui.BasePresenter;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.GlobalVariable;
import app.app1uppro.modules.friendlist.IFriendsListPresenter;
import app.app1uppro.modules.friendlist.IFriendsListView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MessagesListPresenter<V extends IMessagesListView> extends BasePresenter<V>
        implements IMessagesListPresenter<V> {

    @Inject
    public MessagesListPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void messageListMethod() {
        getMvpView().showLoading();
        if (getMvpView().isNetworkConnected()) {
            getCompositeDisposable().add(apiHeaderService().get_Messages_list(
                    getDataManager().getSharedpref(GlobalVariable.User_id))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleListResponse, this::handleErrorMsg));
        }
    }

    private void handleListResponse(MessageListModel messageListModel) {
        getMvpView().hideLoading();
        if (messageListModel.getSuccess() == NetworkConstants.SUCCESS) {
            getMvpView().messageListResponse(messageListModel.getData());
        } else {
            getMvpView().noSuccessMsg(messageListModel.getMessage());
        }
    }

    private void handleErrorMsg(Throwable throwable) {
        getMvpView().hideLoading();
        getMvpView().showMessage(throwable.getMessage());
    }

}//end class
