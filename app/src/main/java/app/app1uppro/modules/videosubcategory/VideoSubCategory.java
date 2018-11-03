package app.app1uppro.modules.videosubcategory;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
import android.view.View;
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
import app.app1uppro.apibase.modelclass.VideoSubCatModel;
import app.app1uppro.baseui.BaseActivity;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.GlobalVariable;
import app.app1uppro.di.component.ActivityComponent;
import app.app1uppro.modules.videocategory.GetVideocategoryPos;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoSubCategory extends BaseActivity implements GetVideocategoryPos,
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
    String categoryId="";

    @Inject
    VideoSubPresenter<ISubVideoView> presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_sub_category);
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this));
            presenter.onAttach(this);
        }
        hideKeyboard();
        initialize();
    }//end onCreate

    private void initialize() {
        allDataList = new ArrayList<>();
        toolbrFirstImage.setImageResource(R.drawable.backwhiteicon);
        if (getIntent().getStringExtra("categoryName") != null)
            toolbarTitle.setText(getIntent().getStringExtra("categoryName"));
        if(getIntent().getStringExtra("categoryId")!=null)
            categoryId=getIntent().getStringExtra("categoryId");

        videosubLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        videosubcatRecylerview.setLayoutManager(videosubLayoutManager);
        videosubcatgyAdapter = new VideoSubcatgyAdapter(VideoSubCategory.this, allDataList);
        videosubcatRecylerview.setAdapter(videosubcatgyAdapter);
        returnIntent = new Intent();

        if (isNetworkConnected())
            presenter.onLoginClick(dataManager.getSharedpref(GlobalVariable.User_id),categoryId);
        else
            videosubcatRecylerview.hideShimmerAdapter();
    }//end initialize

    @Override
    protected void setUp() {
    }

    @Override
    public void getCategoryPosition(int pos) {
        if(allDataList.get(pos).getCatChild()>0){
            bottomChallengeLength(pos);
        }else {
            returnIntent.putExtra("category_name", allDataList.get(pos).getVideoCatName());
            returnIntent.putExtra("category_id", allDataList.get(pos).getVideoCatID());
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
    }

    public void bottomChallengeLength(int pos) {
        dialog = new BottomSheetBuilder(this,
                R.style.AppTheme_BottomSheetDialog)
                .setMode(BottomSheetBuilder.MODE_LIST)
                .setMenu(R.menu.categoryoptions)
                .expandOnStart(true)           // expand the dialog automatically:
                .setItemClickListener(new BottomSheetItemClickListener() {
                    @Override
                    public void onBottomSheetItemClick(MenuItem item) {
                        int id = item.getItemId();
                        switch (id) {
                            case R.id.doneid:
                                returnIntent.putExtra("category_name", allDataList.get(pos).getVideoCatName());
                                returnIntent.putExtra("category_id", allDataList.get(pos).getVideoCatID());
                                setResult(Activity.RESULT_OK, returnIntent);
                                finish();
                                break;
                            case R.id.nextid:
                                Intent subIntent=new Intent(getApplicationContext(), VideoSubCategory.class);
                                subIntent.putExtra("categoryName",allDataList.get(pos).getVideoCatName());
                                subIntent.putExtra("categoryId",allDataList.get(pos).getVideoCatID());
                                startActivity(subIntent);
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
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @OnClick({R.id.toolbr_firstImage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbr_firstImage:
                finish();
                break;
        }//end switch
    }//end onViewClicked


    @Override
    public void updateResponse(VideoSubCatModel videoSubCatModel) {
        allDataList.addAll(videoSubCatModel.getData());
        videosubcatgyAdapter.notifyDataSetChanged();
    }
}//end main class
