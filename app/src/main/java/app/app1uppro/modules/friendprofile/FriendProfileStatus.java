package app.app1uppro.modules.friendprofile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.inject.Inject;

import app.app1uppro.R;
import app.app1uppro.apibase.modelclass.ChangePasswordModel;
import app.app1uppro.apibase.modelclass.FriendProfileStatusModel;
import app.app1uppro.apibase.modelclass.FriendsListModel;
import app.app1uppro.baseui.BaseActivity;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.GlobalVariable;
import app.app1uppro.common.Validate_Structure;
import app.app1uppro.di.component.ActivityComponent;
import app.app1uppro.modules.changepassword.ChangePasswordPresenter;
import app.app1uppro.modules.changepassword.IChangePassView;
import app.app1uppro.modules.friendlist.FriendsListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FriendProfileStatus extends BaseActivity implements IFriendProfileView {

    @Inject
    FriendProfilePresenter<IFriendProfileView> presenter;
    @Inject
    DataManager sessionManager;
    @BindView(R.id.changebackicon)
    ImageView changebackicon;
    @BindView(R.id.userImage_profile)
    ImageView userImage_profile;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.challengetaken)
    TextView challengetaken;
    @BindView(R.id.average_score)
    TextView average_score;
    @BindView(R.id.challenge_follow)
    TextView challenge_follow;
    @BindView(R.id.video_count)
    TextView video_count;
    @BindView(R.id.challenge_given)
    TextView challenge_given;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile_status);
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this));
            presenter.onAttach(this);
        }
        if (sessionManager.checkConnectionActivity() && getIntent().getStringExtra("profile_id") != null)
            presenter.friendProfileMethod(getIntent().getStringExtra("profile_id"));
    }//end onCreate

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }

    @OnClick({R.id.changebackicon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.changebackicon:
                hideKeyboard();
                finish();
                break;
        }//end switch
    }//onClick

    @Override
    protected void setUp() {
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void updateUi(FriendProfileStatusModel.DataBean dataBean) {
        if (dataBean.getProfile_image().isEmpty())
            Glide.with(this).load(R.drawable.user_dummy).into(userImage_profile);
        else
            Glide.with(this).load(dataBean.getProfile_image()).into(userImage_profile);

        username.setText(String.format("%s's Status",dataBean.getUsername()));
        challengetaken.setText(String.valueOf(dataBean.getOthers().getChallengeTaken()));
        average_score.setText(String.valueOf(new DecimalFormat("##.##").format(dataBean.getOthers().getAvgScore())));
        challenge_follow.setText(String.valueOf(dataBean.getOthers().getChallengeFollow()));
        video_count.setText(String.valueOf(dataBean.getOthers().getVideoCount()));
        challenge_given.setText(String.valueOf(dataBean.getOthers().getChallengeGiven()));
    }
}//end main class
