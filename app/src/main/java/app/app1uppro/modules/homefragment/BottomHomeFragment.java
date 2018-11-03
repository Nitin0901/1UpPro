package app.app1uppro.modules.homefragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.inject.Inject;

import app.app1uppro.R;
import app.app1uppro.apibase.modelclass.ChallengeListModel;
import app.app1uppro.baseui.BaseFragment;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.DialogFile;
import app.app1uppro.common.PermissionFile;
import app.app1uppro.modules.acceptvideochallenge.GoogleLoginAcceptVideo;
import app.app1uppro.modules.uploadvideo.GoogleLoginUploadVideo;
import app.app1uppro.modules.youtubeactivity.YoutubePlayerActivity;
import app.app1uppro.util.ImageUtility;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;

public class BottomHomeFragment extends BaseFragment
        implements IBottomHomeView, GetVideoPosition {

    @Inject
    BottomHomePresenter<IBottomHomeView> presenter;
    @Inject
    DataManager sessionManager;

    @BindView(R.id.videolisting)
    RecyclerView videolisting;
    LinearLayoutManager linearLayoutManager;
    BottomHomeAdapter bottomHomeAdapter;
    Unbinder unbinder;
    @BindView(R.id.no_dataLayout)
    ImageView noDataLayout;
    ArrayList<ChallengeListModel.DataBean.ChallengeDataBean> challengeArrayList;
    int rec_position = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_home, container, false);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this, view));
        presenter.onAttach(this);
        initialize();
        return view;
    }//end onCreateView

    private void initialize() {
        challengeArrayList = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        videolisting.setLayoutManager(linearLayoutManager);
        bottomHomeAdapter = new BottomHomeAdapter(challengeArrayList, this);
        videolisting.setAdapter(bottomHomeAdapter);
        if (sessionManager.checkConnectionActivity())
            presenter.challengeList();
    }//end initialize

    @Override
    protected void setUp(View view) {
    }

    @Override
    public void onResume() {
        super.onResume();
        if (sessionManager.checkConnectionActivity()) {
            presenter.challengeList();
            videolisting.scrollToPosition(rec_position);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }

    @Override
    public void challengeListResponse(ChallengeListModel.DataBean challengeDataBean) {
        challengeArrayList.clear();
        challengeArrayList.addAll(challengeDataBean.getOpen_challenge_data());
        challengeArrayList.addAll(challengeDataBean.getChallenge_data());
        if (challengeArrayList.size() == 0) {
            noDataLayout.setVisibility(View.VISIBLE);
            videolisting.setVisibility(View.GONE);
        } else {
            videolisting.setVisibility(View.VISIBLE);
            noDataLayout.setVisibility(View.GONE);
            bottomHomeAdapter.notifyDataSetChanged();
        }
    }//end challengeListResponse

    @Override
    public void updateVotesResponse(int position, String message) {
        Toasty.success(getActivity(), message, Toast.LENGTH_SHORT).show();
        if (sessionManager.checkConnectionActivity()) {
            presenter.challengeList();
            videolisting.scrollToPosition(position);
        }
    }

    @Override
    public void getLeftVideoPosition(int position) {
        if (sessionManager.checkConnectionActivity())
            presenter.challengeHomeVotes(challengeArrayList.get(position).getChallengeCreatorVideoID(),
                    challengeArrayList.get(position).getChallengeID(), position);
    }

    @Override
    public void getRightVideoPosition(int position) {
        if (sessionManager.checkConnectionActivity())
            presenter.challengeHomeVotes(challengeArrayList.get(position).getChallengerVideoID(),
                    challengeArrayList.get(position).getChallengeID(), position);
    }

    @Override
    public void getAcceptPosition(int position) {
        rec_position=position;
        Intent videointent = new Intent(getActivity(), GoogleLoginAcceptVideo.class);
        videointent.putExtra("challengeID", challengeArrayList.get(position).getChallengeID());
        videointent.putExtra("type", "accept");
        startActivity(videointent);
    }

    @Override
    public void getLeftVideoStart(int position) {
        rec_position=position;
        if (challengeArrayList.get(position).getChallengerID().equals("0")) {
            startActivity(new Intent(getActivity(), YoutubePlayerActivity.class)
                    .putExtra("video_id", challengeArrayList.get(position).getVideoEmbedID()));
        } else {
            startActivity(new Intent(getActivity(), YoutubePlayerActivity.class)
                    .putExtra("video_id", challengeArrayList.get(position).getCreatorvideoEmbedID()));
        }

    }

    @Override
    public void getRightVideoStart(int position) {
        rec_position=position;
        startActivity(new Intent(getActivity(), YoutubePlayerActivity.class)
                .putExtra("video_id", challengeArrayList.get(position).getVideoEmbedID()));
    }

}//end main class
