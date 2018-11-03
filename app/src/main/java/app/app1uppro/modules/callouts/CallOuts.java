package app.app1uppro.modules.callouts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import app.app1uppro.apibase.modelclass.CallOutsModel;
import app.app1uppro.baseui.BaseFragment;
import app.app1uppro.common.DataManager;
import app.app1uppro.modules.acceptvideochallenge.GoogleLoginAcceptVideo;
import app.app1uppro.modules.youtubeactivity.YoutubePlayerActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;

public class CallOuts extends BaseFragment implements ICallOutsView, GetCallOutsPosition {

    @Inject
    CallOutsPresenter<ICallOutsView> presenter;
    @Inject
    DataManager sessionManager;
    @BindView(R.id.calloutslisting)
    RecyclerView calloutslisting;
    @BindView(R.id.calledouttext)
    TextView calledouttext;

    LinearLayoutManager linearLayoutManager;
    CallOutsAdapter callOutsAdapter;
    @BindView(R.id.no_dataLayout)
    ImageView noDataLayout;
    ArrayList<CallOutsModel.DataBean> callOutsList;
    private int rec_position = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_call_outs, container, false);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this, view));
        presenter.onAttach(this);
        initialize();
        return view;
    }//end onCreate

    private void initialize() {
        callOutsList = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        calloutslisting.setLayoutManager(linearLayoutManager);
        callOutsAdapter = new CallOutsAdapter(callOutsList, this);
        calloutslisting.setAdapter(callOutsAdapter);
        if (sessionManager.checkConnectionActivity())
            presenter.callOutsList();
    }//end initialize

    @Override
    protected void setUp(View view) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }

    @Override
    public void callOutsListResponse(ArrayList<CallOutsModel.DataBean> calloutsmodel) {
        callOutsList.clear();
        callOutsList.addAll(calloutsmodel);
        if (callOutsList.size() == 0) {
            noDataLayout.setVisibility(View.VISIBLE);
            calloutslisting.setVisibility(View.GONE);
            calledouttext.setVisibility(View.GONE);
        } else {
            calloutslisting.setVisibility(View.VISIBLE);
            calledouttext.setVisibility(View.VISIBLE);
            noDataLayout.setVisibility(View.GONE);
            callOutsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void acceptRejectResponse(String msg) {
        Toasty.success(getActivity(), msg, Toast.LENGTH_SHORT).show();
        if (sessionManager.checkConnectionActivity()) {
            presenter.callOutsList();
            calloutslisting.scrollToPosition(rec_position);
        }
    }

    @Override
    public void noDataResponse(String msg) {
        noDataLayout.setVisibility(View.VISIBLE);
        calloutslisting.setVisibility(View.GONE);
        calledouttext.setVisibility(View.GONE);
    }

    @Override
    public void getPosition(int position, String type) {
        rec_position = position;
        //accept 1 and reject 0
        if (type.equals("1")) {
            Intent videointent = new Intent(getActivity(), GoogleLoginAcceptVideo.class);
            videointent.putExtra("challengeID", callOutsList.get(position).getChallengeID());
            videointent.putExtra("type", "accept");
            startActivity(videointent);
        } else {
            presenter.acceptReject(callOutsList.get(position).getChallengeID(),
                    callOutsList.get(position).getVideoEmbedID(),
                    callOutsList.get(position).getVideoName(),
                    callOutsList.get(position).getVideoDescription(), "reject");
        }
    }

    @Override
    public void getVideoStart(int position) {
        startActivity(new Intent(getActivity(), YoutubePlayerActivity.class)
                .putExtra("video_id", callOutsList.get(position).getVideoEmbedID()));

    }

    @Override
    public void onResume() {
        super.onResume();
        if (sessionManager.checkConnectionActivity()) {
            presenter.callOutsList();
            calloutslisting.scrollToPosition(rec_position);
        }
    }
}//end main class
