package app.app1uppro.modules.following;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.github.rubensousa.bottomsheetbuilder.BottomSheetMenuDialog;
import java.util.ArrayList;
import javax.inject.Inject;
import app.app1uppro.R;
import app.app1uppro.apibase.modelclass.VideoCategoryModel;
import app.app1uppro.baseui.BaseActivity;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.GlobalVariable;
import app.app1uppro.di.component.ActivityComponent;
import app.app1uppro.modules.videocategory.GetVideocategoryPos;
import app.app1uppro.modules.videocategory.IVideoView;
import app.app1uppro.modules.videocategory.VideocatgyAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserFollowCategory extends BaseActivity implements GetVideocategoryPos,
        IVideoView {

    @Inject
    DataManager dataManager;
    @Inject
    DataManager sessionManager;
    @BindView(R.id.toolbr_firstImage)
    ImageView toolbrFirstImage;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.videocatRecylerview)
    ShimmerRecyclerView videocatRecylerview;
    @BindView(R.id.no_dataLayout)
    ImageView no_dataLayout;

    FollowVideocatgyAdapter videocatgyAdapter;
    ArrayList<VideoCategoryModel.DataBean> allDataList;
    LinearLayoutManager videoLayoutManager;
    BottomSheetMenuDialog dialog;
    Intent returnIntent;

    @Inject
    FollowVideoPresenter<IVideoView> presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_follow_category);
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this));
            presenter.onAttach(this);
        }
        hideKeyboard();
        initialize();
    }

    @Override
    protected void setUp() { }

    private void initialize() {
        allDataList = new ArrayList<>();
        toolbrFirstImage.setImageResource(R.drawable.backwhiteicon);
        toolbarTitle.setText(getString(R.string.video_category));
        videoLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        videocatRecylerview.setLayoutManager(videoLayoutManager);
        videocatgyAdapter = new FollowVideocatgyAdapter(this, allDataList);
        videocatRecylerview.setAdapter(videocatgyAdapter);
        returnIntent = new Intent();

        if (isNetworkConnected())
            presenter.onLoginClick(dataManager.getSharedpref(GlobalVariable.User_id));
        else
            videocatRecylerview.hideShimmerAdapter();
    }//end initialize


    @Override
    public void getCategoryPosition(int pos) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to Follow this category ?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (sessionManager.checkConnectionActivity()) {
                            returnIntent.putExtra("category_name", allDataList.get(pos).getVideoCatName());
                            returnIntent.putExtra("category_id", allDataList.get(pos).getVideoCatID());
                            setResult(Activity.RESULT_OK, returnIntent);
                            finish();
                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setTitle("Follow Category")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    @OnClick(R.id.toolbr_firstImage)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbr_firstImage:
                finish();
                break;
        }//end switch
    }//end onClick

    @Override
    public void updateResponse(VideoCategoryModel videoCategoryModel) {
        if (videoCategoryModel.getData().size() > 0) {
            allDataList.addAll(videoCategoryModel.getData());
            videocatgyAdapter.notifyDataSetChanged();
        } else no_dataLayout.setVisibility(View.VISIBLE);
    }

}//end main class
