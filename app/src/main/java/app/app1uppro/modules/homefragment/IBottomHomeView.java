package app.app1uppro.modules.homefragment;

import java.util.ArrayList;

import app.app1uppro.apibase.modelclass.ChallengeListModel;
import app.app1uppro.apibase.modelclass.UserProfileModel;
import app.app1uppro.baseui.MvpView;

public interface IBottomHomeView extends MvpView {
    void challengeListResponse(ChallengeListModel.DataBean challengeDataBean);
    void updateVotesResponse(int position,String message);
}
