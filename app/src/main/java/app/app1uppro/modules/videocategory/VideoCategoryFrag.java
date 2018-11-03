package app.app1uppro.modules.videocategory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.github.rubensousa.bottomsheetbuilder.BottomSheetBuilder;
import com.github.rubensousa.bottomsheetbuilder.BottomSheetMenuDialog;
import com.github.rubensousa.bottomsheetbuilder.adapter.BottomSheetItemClickListener;

import java.util.ArrayList;

import javax.inject.Inject;

import app.app1uppro.R;
import app.app1uppro.apibase.modelclass.VideoCategoryModel;
import app.app1uppro.baseui.BaseFragment;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.GlobalVariable;
import app.app1uppro.di.component.ActivityComponent;
import app.app1uppro.modules.videosubcategory.VideoSubCatFrag;
import app.app1uppro.modules.videosubcategory.VideoSubCategory;
import app.app1uppro.modules.welcomescreen.WelcomeScreen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoCategoryFrag extends BaseFragment implements GetVideocategoryPos,
        IVideoView {

    @Inject
    DataManager dataManager;

    @BindView(R.id.toolbr_firstImage)
    ImageView toolbrFirstImage;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.videocatRecylerview)
    ShimmerRecyclerView videocatRecylerview;
    @BindView(R.id.no_dataLayout)
    ImageView no_dataLayout;

    VideocatgyAdapter videocatgyAdapter;
    ArrayList<VideoCategoryModel.DataBean> allDataList;
    LinearLayoutManager videoLayoutManager;
    BottomSheetMenuDialog dialog;
    Intent returnIntent;
    @Inject
    VideoPresenter<IVideoView> presenter;
    ArrayList<String> categoryNamesList = new ArrayList<>();
    ArrayList<String> categoryIdsList = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_category, container, false);
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            presenter.onAttach(this);
        }
        hideKeyboard();
        initialize();
        return view;
    }//end onCreateView

    private void initialize() {
        allDataList = new ArrayList<>();
        toolbrFirstImage.setImageResource(R.drawable.backwhiteicon);
        toolbarTitle.setText(getString(R.string.video_category));
        videoLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        videocatRecylerview.setLayoutManager(videoLayoutManager);
        videocatgyAdapter = new VideocatgyAdapter(this, allDataList);
        videocatRecylerview.setAdapter(videocatgyAdapter);
        returnIntent = new Intent();
        if (isNetworkConnected())
            presenter.onLoginClick(dataManager.getSharedpref(GlobalVariable.User_id));
        else
            videocatRecylerview.hideShimmerAdapter();
    }//end initialize

    @Override
    public void getCategoryPosition(int pos) {
        if (allDataList.get(pos).getCatChild() > 0) {
            bottomChallengeLength(pos);
        } else {
            categoryIdsList.clear();
            categoryNamesList.clear();
            categoryNamesList.add(allDataList.get(pos).getVideoCatName());
            categoryIdsList.add(allDataList.get(pos).getVideoCatID());
            returnIntent.putStringArrayListExtra("category_name", categoryNamesList);
            returnIntent.putStringArrayListExtra("category_id", categoryIdsList);
            getActivity().setResult(Activity.RESULT_OK, returnIntent);
            getActivity().finish();
        }
    }

    @Override
    protected void setUp(View view) { }

    @OnClick(R.id.toolbr_firstImage)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbr_firstImage:
                getActivity().finish();
                break;
        }//end switch
    }//end onClick

    public void bottomChallengeLength(int pos) {
        dialog = new BottomSheetBuilder(getActivity(), R.style.AppTheme_BottomSheetDialog)
                .setMode(BottomSheetBuilder.MODE_LIST)
                .setMenu(R.menu.categoryoptions)
                .expandOnStart(true)           // expand the dialog automatically:
                .setItemClickListener(new BottomSheetItemClickListener() {
                    @Override
                    public void onBottomSheetItemClick(MenuItem item) {
                        int id = item.getItemId();
                        switch (id) {
                            case R.id.doneid:
                                categoryIdsList.clear();
                                categoryNamesList.clear();
                                categoryNamesList.add(allDataList.get(pos).getVideoCatName());
                                categoryIdsList.add(allDataList.get(pos).getVideoCatID());
                                returnIntent.putStringArrayListExtra("category_name", categoryNamesList);
                                returnIntent.putStringArrayListExtra("category_id", categoryIdsList);
                                getActivity().setResult(Activity.RESULT_OK, returnIntent);
                                getActivity().finish();
                                break;
                            case R.id.nextid:
                                categoryIdsList.clear();
                                categoryNamesList.clear();
                                categoryNamesList.add(allDataList.get(pos).getVideoCatName());
                                categoryIdsList.add(allDataList.get(pos).getVideoCatID());
                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.videoframeLayout,
                                        new VideoSubCatFrag(allDataList.get(pos).getVideoCatName(), allDataList.get(pos).getVideoCatID(), categoryNamesList, categoryIdsList)).addToBackStack(null).commit();

//                                Intent subIntent = new Intent(getApplicationContext(), VideoSubCategory.class);
//                                subIntent.putExtra("categoryName", allDataList.get(pos).getVideoCatName());
//                                subIntent.putExtra("categoryId", allDataList.get(pos).getVideoCatID());
//                                startActivity(subIntent)
                                dialog.dismiss();
                                break;
                            case R.id.cancelid:
                                dialog.dismiss();
                                break;
                        }
                    }
                })
                .createDialog();
        dialog.show();
    }//end bottom dialog

    @Override
    public void updateResponse(VideoCategoryModel videoCategoryModel) {
        if (videoCategoryModel.getData().size() > 0) {
            allDataList.addAll(videoCategoryModel.getData());
            videocatgyAdapter.notifyDataSetChanged();
        } else no_dataLayout.setVisibility(View.VISIBLE);
    }

}//end main class
