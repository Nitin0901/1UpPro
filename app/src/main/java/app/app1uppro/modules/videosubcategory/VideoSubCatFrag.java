package app.app1uppro.modules.videosubcategory;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import app.app1uppro.apibase.modelclass.VideoSubCatModel;
import app.app1uppro.baseui.BaseFragment;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.GlobalVariable;
import app.app1uppro.di.component.ActivityComponent;
import app.app1uppro.modules.videocategory.GetVideocategoryPos;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoSubCatFrag extends BaseFragment implements GetVideocategoryPos,
        ISubVideoView {
    @Inject
    DataManager dataManager;

    @BindView(R.id.toolbr_firstImage)
    ImageView toolbrFirstImage;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.videosubcatRecylerview)
    ShimmerRecyclerView videosubcatRecylerview;
    VideoSubcatgyAdapter videosubcatgyAdapter;
    ArrayList<VideoSubCatModel.DataBean> allDataList;
    LinearLayoutManager videosubLayoutManager;
    BottomSheetMenuDialog dialog;
    Intent returnIntent;
    String categoryId = "", categoryName = "";
    ArrayList<String> categoryNamesList=new ArrayList<>();
    ArrayList<String> categoryIdsList=new ArrayList<>();

    @Inject
    VideoSubPresenter<ISubVideoView> presenter;
    public VideoSubCatFrag() { }

    @SuppressLint("ValidFragment")
    public VideoSubCatFrag(String categoryName, String categoryId,ArrayList<String> categoryNamesList,ArrayList<String> categoryIdsList) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryNamesList=categoryNamesList;
        this.categoryIdsList=categoryIdsList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_sub_cat, container, false);
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

    @Override
    protected void setUp(View view) {
    }

    private void initialize() {
        allDataList = new ArrayList<>();
        // Bundle bundle = this.getArguments();
//        if (bundle != null) {
//            categoryName = bundle.getString("category_name");
//            categoryId = bundle.getString("category_id");
//            toolbarTitle.setText(categoryName);
//        }
        toolbarTitle.setText(categoryName);
        toolbrFirstImage.setImageResource(R.drawable.backwhiteicon);
        videosubLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        videosubcatRecylerview.setLayoutManager(videosubLayoutManager);
        videosubcatgyAdapter = new VideoSubcatgyAdapter(this, allDataList);
        videosubcatRecylerview.setAdapter(videosubcatgyAdapter);
        returnIntent = new Intent();

        if (isNetworkConnected() && categoryId!=null)
            presenter.onLoginClick(dataManager.getSharedpref(GlobalVariable.User_id), categoryId);
        else
            videosubcatRecylerview.hideShimmerAdapter();
    }//end initialize

    @Override
    public void getCategoryPosition(int pos) {
        if (allDataList.get(pos).getCatChild() > 0) {
            bottomChallengeLength(pos);
        } else {
            doneFilterValue(allDataList,allDataList.get(pos).getVideoCatName(),allDataList.get(pos).getVideoCatID());
            // categoryNamesList.add(allDataList.get(pos).getVideoCatName());
          //  categoryIdsList.add(allDataList.get(pos).getVideoCatID());
            returnIntent.putStringArrayListExtra("category_name", categoryNamesList);
            returnIntent.putStringArrayListExtra("category_id",categoryIdsList);
            getActivity().setResult(Activity.RESULT_OK, returnIntent);
            getActivity().finish();
        }
    }

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
                                doneFilterValue(allDataList,allDataList.get(pos).getVideoCatName(),allDataList.get(pos).getVideoCatID());
//                                categoryNamesList.add(allDataList.get(pos).getVideoCatName());
//                                categoryIdsList.add(allDataList.get(pos).getVideoCatID());
                                returnIntent.putStringArrayListExtra("category_name", categoryNamesList);
                                returnIntent.putStringArrayListExtra("category_id",categoryIdsList);
                                getActivity().setResult(Activity.RESULT_OK, returnIntent);
                                getActivity().finish();
                                break;
                            case R.id.nextid:
                                filtervalue(allDataList,allDataList.get(pos).getVideoCatName(),allDataList.get(pos).getVideoCatID());
                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.videoframeLayout,
                                        new VideoSubCatFrag(allDataList.get(pos).getVideoCatName(),allDataList.get(pos).getVideoCatID(),categoryNamesList,categoryIdsList)).addToBackStack(null).commit();

//                                Intent subIntent = new Intent(getActivity(), VideoSubCategory.class);
//                                subIntent.putExtra("categoryName", allDataList.get(pos).getVideoCatName());
//                                subIntent.putExtra("categoryId", allDataList.get(pos).getVideoCatID());
//                                startActivity(subIntent);
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

    private void filtervalue(ArrayList<VideoSubCatModel.DataBean> allDataList, String videoCatName, String videoCatID) {
        for (int i = 0; i <allDataList.size() ; i++) {
            for (int j = 0; j <categoryNamesList.size() ; j++) {
                if (allDataList.get(i).getVideoCatName().equals(categoryNamesList.get(j))) {
                    categoryNamesList.remove(categoryNamesList.indexOf(categoryNamesList.get(j)));
                }
            }
            for (int j = 0; j <categoryIdsList.size() ; j++) {
                if(allDataList.get(i).getVideoCatName().equals(categoryIdsList.get(j))) {
                     categoryIdsList.remove(categoryIdsList.indexOf(categoryIdsList.get(j)));
                }
            }
        }
        categoryNamesList.add(videoCatName);
        categoryIdsList.add(videoCatID);
    }

    private void doneFilterValue(ArrayList<VideoSubCatModel.DataBean> allDataList, String videoCatName, String videoCatID) {
        for (int i = 0; i <allDataList.size() ; i++) {
            for (int j = 0; j <categoryNamesList.size() ; j++) {
                if (allDataList.get(i).getVideoCatName().equals(categoryNamesList.get(j))) {
                    categoryNamesList.remove(categoryNamesList.indexOf(categoryNamesList.get(j)));
                }
            }
            for (int j = 0; j <categoryIdsList.size() ; j++) {
                if(allDataList.get(i).getVideoCatName().equals(categoryIdsList.get(j))) {
                    categoryIdsList.remove(categoryIdsList.indexOf(categoryIdsList.get(j)));
                }
            }
        }
        categoryNamesList.add(videoCatName);
        categoryIdsList.add(videoCatID);
    }

    @OnClick({R.id.toolbr_firstImage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbr_firstImage:
                getActivity().onBackPressed();
                break;
        }//end switch
    }//end onViewClicked


    @Override
    public void updateResponse(VideoSubCatModel videoSubCatModel) {
        allDataList.addAll(videoSubCatModel.getData());
        videosubcatgyAdapter.notifyDataSetChanged();
    }
}//end main class
