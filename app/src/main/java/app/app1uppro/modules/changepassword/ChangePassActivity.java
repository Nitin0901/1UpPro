package app.app1uppro.modules.changepassword;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import javax.inject.Inject;

import app.app1uppro.R;
import app.app1uppro.apibase.modelclass.ChangePasswordModel;
import app.app1uppro.baseui.BaseActivity;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.GlobalVariable;
import app.app1uppro.common.Validate_Structure;
import app.app1uppro.di.component.ActivityComponent;
import app.app1uppro.modules.welcomescreen.WelcomeScreen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePassActivity extends BaseActivity implements IChangePassView {

    @Inject
    ChangePasswordPresenter<IChangePassView> presenter;
    @Inject
    DataManager sessionManager;

    @BindView(R.id.changebackicon)
    ImageView changebackicon;
    @BindView(R.id.currentpass)
    EditText currentpassword;
    @BindView(R.id.newpass)
    EditText newpassword;
    @BindView(R.id.confmpass)
    EditText confmpassword;
    @BindView(R.id.changepassbtn)
    Button changepassbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this));
            presenter.onAttach(this);
        }
    }//end onCreate

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }

    @Override
    public void updateUi(ChangePasswordModel.DataBean dataBean) {
        sessionManager.clearsharedpref();
        Intent intent = new Intent(this, WelcomeScreen.class);
        startActivity(intent);
        finishAffinity();
    }

    @Override
    protected void setUp() { }

    @OnClick({R.id.changebackicon, R.id.changepassbtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.changebackicon:
                hideKeyboard();
                finish();
                break;
            case R.id.changepassbtn:
                if (isNetworkConnected() && Validate_Structure.validatePassword(currentpassword,newpassword, confmpassword))
                    presenter.onChangePassword(sessionManager.getSharedpref(GlobalVariable.User_id)
                            , currentpassword.getText().toString(),
                            newpassword.getText().toString());
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}//end main class
