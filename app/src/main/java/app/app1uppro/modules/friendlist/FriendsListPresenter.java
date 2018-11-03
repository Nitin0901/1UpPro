package app.app1uppro.modules.friendlist;

import java.util.ArrayList;

import javax.inject.Inject;

import app.app1uppro.apibase.NetworkConstants;
import app.app1uppro.apibase.modelclass.FriendsListModel;
import app.app1uppro.apibase.modelclass.SucessModel;
import app.app1uppro.baseui.BasePresenter;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.GlobalVariable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class FriendsListPresenter<V extends IFriendsListView> extends BasePresenter<V>
        implements IFriendsListPresenter<V> {

    String type = "";
    ArrayList<FriendsListModel.DataBean> dataBeans = new ArrayList<>();

    @Inject
    public FriendsListPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void friendsListMethod(String type) {
        this.type = type;
        getMvpView().showLoading();
        if (getMvpView().isNetworkConnected()) {
            getCompositeDisposable().add(apiHeaderService().friends_list_method(
                    getDataManager().getSharedpref(GlobalVariable.User_id))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleListResponse, this::handleErrorMsg));
        }
    }

    @Override
    public void friendsRequestListMethod(String type) {
        this.type = type;
        getMvpView().showLoading();
        if (getMvpView().isNetworkConnected()) {
            getCompositeDisposable().add(apiHeaderService().friends_list_method(
                    getDataManager().getSharedpref(GlobalVariable.User_id))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleReqListResponse, this::handleErrorMsg));
        }
    }

    private void handleListResponse(FriendsListModel friendsListModel) {
        getMvpView().hideLoading();
        dataBeans.clear();
        if (friendsListModel.getSuccess() == NetworkConstants.SUCCESS) {
            for (int i = 0; i < friendsListModel.getData().size(); i++) {
                if (friendsListModel.getData().get(i).getStatus().equals(type) &&
                        friendsListModel.getData().get(i).getFriend_id().equals(getDataManager().getSharedpref(GlobalVariable.User_id)))
                    dataBeans.add(friendsListModel.getData().get(i));
            }
            getMvpView().FriendListResponse(dataBeans, type);
        } else {
            getMvpView().noSuccessMsg(friendsListModel.getMessage());
        }
    }

    private void handleReqListResponse(FriendsListModel friendsListModel) {
        getMvpView().hideLoading();
        dataBeans.clear();
        if (friendsListModel.getSuccess() == NetworkConstants.SUCCESS) {
            for (int i = 0; i < friendsListModel.getData().size(); i++) {
                if (friendsListModel.getData().get(i)
                        .getFriend_id().equals(getDataManager().getSharedpref(GlobalVariable.User_id)) &&
                        friendsListModel.getData().get(i).getStatus().equals(type))
                    dataBeans.add(friendsListModel.getData().get(i));
            }
            getMvpView().FriendListResponse(dataBeans, type);
        } else {
            getMvpView().noSuccessMsg(friendsListModel.getMessage());
        }
    }

    @Override
    public void fndReqAcceptReject(String id, String type) {
        getMvpView().showLoading();
        if (getMvpView().isNetworkConnected()) {
            getCompositeDisposable().add(apiHeaderService().friends_Accept_Reject(
                    getDataManager().getSharedpref(GlobalVariable.User_id), id, type)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleReqResponse, this::handleErrorMsg));
        }
    }

    private void handleReqResponse(SucessModel sucessModel) {
        getMvpView().hideLoading();
        dataBeans.clear();
        if (sucessModel.getSuccess() == NetworkConstants.SUCCESS) {
            getMvpView().fndReqResponse(sucessModel.getMessage());
        } else {
            getMvpView().noSuccessMsg(sucessModel.getMessage());
        }
    }

    private void handleErrorMsg(Throwable throwable) {
        getMvpView().hideLoading();
        getMvpView().showMessage(throwable.getMessage());
    }

}//end class
