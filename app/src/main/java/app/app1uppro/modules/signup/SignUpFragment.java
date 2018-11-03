package app.app1uppro.modules.signup;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;

import javax.inject.Inject;

import app.app1uppro.R;
import app.app1uppro.baseui.BaseFragment;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.DialogFile;
import app.app1uppro.common.GlobalVariable;
import app.app1uppro.common.PermissionFile;
import app.app1uppro.common.Validate_Structure;
import app.app1uppro.di.component.ActivityComponent;
import app.app1uppro.modules.login.LoginFragment;
import app.app1uppro.modules.welcomescreen.WelcomeScreen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpFragment extends BaseFragment implements ISignupView {

    @Inject
    DialogFile dialogFile;
    @Inject
    PermissionFile permissionFile;
    @Inject
    DataManager dataManager;
    @Inject
    DataManager sessionManager;

    @Inject
    SignupPresenter<ISignupView> presenter;

    @BindView(R.id.signupback)
    ImageView signupback;
    @BindView(R.id.textView4)
    TextView registerText;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.firstname)
    EditText firstname;
    @BindView(R.id.lastname)
    EditText lastname;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.confirmpass)
    EditText confirmpass;
    @BindView(R.id.signupbtn)
    Button signupbtn;
    @BindView(R.id.signuplayout)
    ConstraintLayout signuplayout;
    private HashMap<String, String> signuphashMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ActivityComponent component = getActivityComponent();

        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            presenter.onAttach(this);
        }
        hideKeyboard();
        signuphashMap = new HashMap<>();
        WelcomeScreen.frontBackCount=0;
        return view;
    }//end onCreateView

    @OnClick({R.id.signupback, R.id.signupbtn, R.id.signuplayout})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.signupbtn:
                if (isNetworkConnected() && Validate_Structure.validateSignup(username,
                        firstname, lastname, email, password, confirmpass)) {
                    hideKeyboard();
                    signuphashMap.put("username", username.getText().toString());
                    signuphashMap.put("firstname", firstname.getText().toString());
                    signuphashMap.put("lastname", lastname.getText().toString());
                    signuphashMap.put("email", email.getText().toString());
                    signuphashMap.put("password", password.getText().toString());
//                    if (sessionManager.getSharedpref(GlobalVariable.Device_Token).isEmpty()) {
//                        sessionManager.storesharedpref(GlobalVariable.Device_Token, FirebaseInstanceId.getInstance().getToken());
//                    }
                   // signuphashMap.put("device_token", sessionManager.getSharedpref(GlobalVariable.Device_Token));
                    signuphashMap.put("device_token", "");
                    signuphashMap.put("device_type", "android");
                    presenter.onSignupClick(signuphashMap);
                }
                break;
            case R.id.signupback:
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.frameLayout, new LoginFragment()).commit();
                break;
            case R.id.signuplayout:
                hideKeyboard();
                break;
        }
    }//end onViewClicked

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDetach();
    }

    @Override
    protected void setUp(View view) { }
    @Override
    public void updateResponse(String msg) {
        popMsg();
    }
    void popMsg() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("You have successfully registered your account. Check your Email and Verify")
                .setCancelable(false)
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        getFragmentManager().beginTransaction().replace(R.id.frameLayout, new LoginFragment()).commit();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }//end pop msg

}//end class
