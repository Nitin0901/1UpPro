package app.app1uppro.modules.settings;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import javax.inject.Inject;

import app.app1uppro.R;
import app.app1uppro.baseui.BaseFragment;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.GlobalVariable;
import app.app1uppro.di.component.ActivityComponent;
import app.app1uppro.modules.changepassword.ChangePassActivity;
import app.app1uppro.modules.welcomescreen.WelcomeScreen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class SettingsFragment extends BaseFragment implements ISettings{

    @Inject
    DataManager dataManager;

    @Inject
    SettingFragPresenter<ISettings> presenter;

    @BindView(R.id.changepassword)
    TextView changepassword;
    @BindView(R.id.logout)
    TextView logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            presenter.onAttach(this);
        }
        return view;
    }//end onCreateView

    @OnClick({R.id.changepassword, R.id.logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.changepassword:
                Intent pass=new Intent(getActivity(), ChangePassActivity.class);
                startActivity(pass);
                break;
            case  R.id.logout:
                logoutMethod();
                break;
        }//end switch
    }

    @Override
    public void onDestroyView() {
        presenter.onDetach();
        super.onDestroyView();
    }

    private void logoutMethod() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Are you sure you want to Logout ?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (isNetworkConnected()) {
                            presenter.logout(dataManager.getSharedpref(GlobalVariable.User_id));
                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) { }
                })
                .setTitle("Logout")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }//end logout

    @Override
    protected void setUp(View view) {    }


    @Override
    public void updateUI(String msg) {
        Toasty.success(getActivity(),msg, Toast.LENGTH_SHORT).show();
//        //user id
//        SharedPreferences.Editor userid = getActivity().getSharedPreferences(GlobalVariable.User_id, Context.MODE_PRIVATE).edit().clear();
//        userid.apply();
//
//        //user auth
//        SharedPreferences.Editor userauth = getActivity().getSharedPreferences(GlobalVariable.AUTH_TOKEN, Context.MODE_PRIVATE).edit().clear();
//        userauth.apply();
//
//        //user image
//        SharedPreferences.Editor userimage = getActivity().getSharedPreferences(GlobalVariable.UserImage, Context.MODE_PRIVATE).edit().clear();
//        userimage.apply();
       // googleSignOut();
        String username=dataManager.getSharedpref(GlobalVariable.UserName);
        String userpass=dataManager.getSharedpref(GlobalVariable.UserPassword);
        String rememberme=dataManager.getSharedpref(GlobalVariable.REMEMBER_ME);
        dataManager.clearsharedpref();
        dataManager.storesharedpref(GlobalVariable.UserName,username);
        dataManager.storesharedpref(GlobalVariable.UserPassword,userpass);
        dataManager.storesharedpref(GlobalVariable.REMEMBER_ME,rememberme);
        Intent intent = new Intent(getActivity(), WelcomeScreen.class);
        startActivity(intent);
        getActivity().finishAffinity();

    }

    private void googleSignOut() {
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();
            GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
            mGoogleSignInClient.signOut()
                    .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) { }
                    });
    }//end google signout
}//end main class
