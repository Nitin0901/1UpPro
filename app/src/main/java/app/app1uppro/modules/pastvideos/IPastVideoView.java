package app.app1uppro.modules.pastvideos;

import java.util.ArrayList;

import app.app1uppro.apibase.modelclass.ChallengeListModel;
import app.app1uppro.apibase.modelclass.PastVideosModel;
import app.app1uppro.baseui.MvpView;

public interface IPastVideoView extends MvpView {
    void pastVideoListResponse(ArrayList<PastVideosModel.DataBean> challengeDataBean);
    void upDownVotesResponse(int position, String message);
    void noDataSuccessResponse(String msg);
}
