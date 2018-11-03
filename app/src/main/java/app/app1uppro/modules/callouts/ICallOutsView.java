package app.app1uppro.modules.callouts;

import java.util.ArrayList;

import app.app1uppro.apibase.modelclass.CallOutsModel;
import app.app1uppro.apibase.modelclass.ChallengeListModel;
import app.app1uppro.baseui.MvpView;

public interface ICallOutsView extends MvpView {
    void callOutsListResponse(ArrayList<CallOutsModel.DataBean> calloutsmodel);
    void acceptRejectResponse(String msg);
    void noDataResponse(String msg);
}
