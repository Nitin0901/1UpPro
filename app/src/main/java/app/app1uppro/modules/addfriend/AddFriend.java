package app.app1uppro.modules.addfriend;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import app.app1uppro.R;
import app.app1uppro.baseui.BaseActivity;
import app.app1uppro.common.DataManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class AddFriend extends BaseActivity implements IAddFriendsView {

    @Inject
    AddFriendsPresenter<IAddFriendsView> presenter;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.toolbr_firstImage)
    ImageView toolbar_firstImage;
    @BindView(R.id.usernameedit)
    EditText usernameedit;
    @BindView(R.id.addfriendbuttonid)
    Button addfriendbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        presenter.onAttach(this);
        initialize();
    }//end onCreate

    private void initialize() {
        toolbar_title.setText(getString(R.string.add_friends));
        toolbar_firstImage.setImageResource(R.drawable.backwhiteicon);
        hideKeyboard();
    }//end initialize

    @OnClick({R.id.toolbr_firstImage, R.id.addfriendbuttonid})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbr_firstImage:
                hideKeyboard();
                finish();
                break;
            case R.id.addfriendbuttonid:
                addFriendMethod();
                break;
        }//end switch
    }//end onClick

    private void addFriendMethod() {
        if(isNetworkConnected()) {
            hideKeyboard();
            if (usernameedit.getText().length() == 0)
                Toasty.error(getApplicationContext(), "Please enter username or email", Toast.LENGTH_SHORT).show();
            else presenter.friendsAddMethod(usernameedit.getText().toString());
        }

    }//end add friend

    @Override
    protected void setUp() {
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void addFriendResponse(String msg) {
        Toasty.success(getApplicationContext(),msg, Toast.LENGTH_SHORT).show();
        finish();
    }
}//end main class
