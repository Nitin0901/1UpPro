package app.app1uppro.modules.pastvideos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import javax.inject.Inject;
import app.app1uppro.R;
import app.app1uppro.apibase.modelclass.PastVideosModel;
import app.app1uppro.baseui.BaseFragment;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.GlobalVariable;
import app.app1uppro.modules.youtubeactivity.YoutubePlayerActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class PastVideosFragment extends BaseFragment implements
        IPastVideoView, GetPastVideoPosition, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    PastVideosPresenter<IPastVideoView> presenter;
    @Inject
    DataManager sessionManager;
    @BindView(R.id.followcategory)
    TextView followcategory;
    @BindView(R.id.pastvideoRecylerview)
    RecyclerView pastvideoRecylerview;
    @BindView(R.id.no_dataLayout)
    ImageView noDataLayout;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    LinearLayoutManager linearLayoutManager;
    PastVideoAdapter pastVideoAdapter;
    ArrayList<PastVideosModel.DataBean> pastVideosList;
    String category_id = "";
    int position=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_past_videos, container, false);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this, view));
        presenter.onAttach(this);
        initialize();
        return view;
    }//end onCreate

    private void initialize() {
        pastVideosList = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        pastvideoRecylerview.setLayoutManager(linearLayoutManager);
        pastVideoAdapter = new PastVideoAdapter(pastVideosList, this);
        pastvideoRecylerview.setAdapter(pastVideoAdapter);
        swipeRefreshLayout.setOnRefreshListener(this);
        if (sessionManager.checkConnectionActivity())
            presenter.pastVideoList("");
    }//end initialize

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }

    @OnClick(R.id.followcategory)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.followcategory:
                Intent intent = new Intent(getActivity(), PastVideoSelectCategory.class);
                startActivityForResult(intent, GlobalVariable.Video_Category);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Select Category Part
        if (resultCode == Activity.RESULT_OK && requestCode == GlobalVariable.Video_Category) {
            if (data.getExtras() != null) {
                if (data.getStringExtra("category_id") != null) {
                    if (sessionManager.checkConnectionActivity()) {
                        followcategory.setText(data.getStringExtra("category_name"));
                        category_id = data.getStringExtra("category_id");
                        presenter.pastVideoList(data.getStringExtra("category_id"));
                    }
                }
            }
        }
    }//end onActivityResult

    @Override
    protected void setUp(View view) {
    }

    @Override
    public void pastVideoListResponse(ArrayList<PastVideosModel.DataBean> challengeDataBean) {
        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
        if (challengeDataBean.size() == 0) {
            noDataLayout.setVisibility(View.VISIBLE);
            pastvideoRecylerview.setVisibility(View.GONE);
            pastVideoAdapter.notifyDataSetChanged();
        } else {
            pastVideosList.clear();
            pastVideosList.addAll(challengeDataBean);
            pastvideoRecylerview.setVisibility(View.VISIBLE);
            noDataLayout.setVisibility(View.GONE);
            pastVideoAdapter.notifyDataSetChanged();
            pastvideoRecylerview.scrollToPosition(position);
        }
    }

    @Override
    public void upDownVotesResponse(int position, String message) {
        Toasty.success(getActivity(), message, Toast.LENGTH_SHORT).show();
        if (sessionManager.checkConnectionActivity()) {
            if (category_id.isEmpty())
                presenter.pastVideoList("");
            else
                presenter.pastVideoList(category_id);

            pastvideoRecylerview.scrollToPosition(position);
        }
    }

    @Override
    public void noDataSuccessResponse(String msg) {
        swipeRefreshLayout.setRefreshing(false);
        noDataLayout.setVisibility(View.VISIBLE);
        pastvideoRecylerview.setVisibility(View.GONE);
        pastVideoAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        if (sessionManager.checkConnectionActivity()) {
            followcategory.setText(getString(R.string.select_category));
            presenter.pastVideoList("");
        }
        else {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void getLeftVideoPosition(int position) {
        this.position=position;
        presenter.pastVideosVotes(pastVideosList.get(position).getVideoID(),
                pastVideosList.get(position).getChallengeID(), position, "up");
    }

    @Override
    public void getRightVideoPosition(int position) {
        this.position=position;
        presenter.pastVideosVotes(pastVideosList.get(position).getVideoID(),
                pastVideosList.get(position).getChallengeID(), position, "down");
    }

    @Override
    public void getVideoStart(int id) {
        startActivity(new Intent(getActivity(), YoutubePlayerActivity.class)
                .putExtra("video_id", pastVideosList.get(id).getVideoEmbedID()));

    }

}//end main class
