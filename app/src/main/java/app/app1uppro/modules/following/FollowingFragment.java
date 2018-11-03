package app.app1uppro.modules.following;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.inject.Inject;

import app.app1uppro.R;
import app.app1uppro.apibase.modelclass.CallOutsModel;
import app.app1uppro.apibase.modelclass.FollowedCategoryModel;
import app.app1uppro.apibase.modelclass.VideoCategoryModel;
import app.app1uppro.baseui.BaseFragment;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.GlobalVariable;
import app.app1uppro.modules.callouts.CallOutsAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FollowingFragment extends BaseFragment
        implements FollowedPosition, IFollowingView {

    @Inject
    FollowingPresenter<IFollowingView> presenter;
    @Inject
    DataManager sessionManager;
    @BindView(R.id.followcategory)
    TextView followcategory;
    @BindView(R.id.followinglisting)
    RecyclerView followinglisting;
    @BindView(R.id.imfollowing)
    TextView imfollowingtext;
    LinearLayoutManager linearLayoutManager;
    FollowingAdapter followingAdapter;
    ArrayList<String> categoryNames;
    ArrayList<FollowedCategoryModel.DataBean> followedList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_following, container, false);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this, view));
        presenter.onAttach(this);
        initialize();
        return view;
    }//end onCreateView

    private void initialize() {
        categoryNames = new ArrayList<>();
        followedList = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        followinglisting.setLayoutManager(linearLayoutManager);
        followingAdapter = new FollowingAdapter(followedList, this);
        followinglisting.setAdapter(followingAdapter);
        if (sessionManager.checkConnectionActivity())
            presenter.followedListing();
    }//end initialize

    @Override
    protected void setUp(View view) { }


    @Override
    public void followedListResponse(ArrayList<FollowedCategoryModel.DataBean> followed) {
        if (followed.size() > 0) {
            followedList.clear();
            imfollowingtext.setVisibility(View.VISIBLE);
            followinglisting.setVisibility(View.VISIBLE);
            followedList.addAll(followed);
            followingAdapter.notifyDataSetChanged();
        } else {
            imfollowingtext.setVisibility(View.VISIBLE);
            followinglisting.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void startfollowResponse(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        if (sessionManager.checkConnectionActivity())
            presenter.followedListing();
    }

    @OnClick(R.id.followcategory)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.followcategory:
                Intent intent = new Intent(getActivity(), UserFollowCategory.class);
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
                    presenter.startFollowUnfollowingCategory(data.getStringExtra("category_id"), "1");
                }
            }
        }
    }//end onActivityResult

    @Override
    public void getPosition(int position) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Are you sure you want to UnFollow this category ?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (sessionManager.checkConnectionActivity()) {
                            presenter.startFollowUnfollowingCategory(followedList.get(position).getCatID(), "0");
                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setTitle("UnFollow Category")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}//end main clas
