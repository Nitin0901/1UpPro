package app.app1uppro.modules.login;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;

import javax.inject.Inject;

import app.app1uppro.R;
import app.app1uppro.apibase.modelclass.LoginModel;
import app.app1uppro.baseui.BaseFragment;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.DialogFile;
import app.app1uppro.common.GlobalVariable;
import app.app1uppro.common.PermissionFile;
import app.app1uppro.common.Validate_Structure;
import app.app1uppro.di.component.ActivityComponent;
import app.app1uppro.modules.forgetpassword.ForgotPasswordFrag;
import app.app1uppro.modules.mainactivity.MainActivity;
import app.app1uppro.modules.signup.SignUpFragment;
import app.app1uppro.modules.welcomescreen.WelcomeScreen;
import app.app1uppro.util.GPSTracker;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class LoginFragment extends BaseFragment implements ILoginView,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, CompoundButton.OnCheckedChangeListener {

    @Inject
    DialogFile dialogFile;
    @Inject
    PermissionFile permissionFile;
    @Inject
    DataManager dataManager;
    @Inject
    DataManager sessionManager;

    @Inject
    LoginPresenter<ILoginView> presenter;

    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.loginbtn)
    Button loginbtn;
    @BindView(R.id.checkBox)
    CheckBox checkBox;
    @BindView(R.id.createaccount)
    TextView createaccount;
    @BindView(R.id.forgetpass)
    TextView forgetpass;
    @BindView(R.id.loginlayout)
    ConstraintLayout loginlayout;
    private HashMap<String, String> loginhashMap;
    GPSTracker gpsTracker;
    double userlatitude = 0, userlongitude = 0;
    LocationManager locationManager;
    Location location = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            presenter.onAttach(this);
        }
        loginhashMap = new HashMap<>();
        EnableGPSAutoMatically();
        hideKeyboard();
        if (dataManager.getSharedpref(GlobalVariable.REMEMBER_ME).equals("1")) {
            username.setText(dataManager.getSharedpref(GlobalVariable.UserName));
            password.setText(dataManager.getSharedpref(GlobalVariable.UserPassword));
            checkBox.setChecked(true);
        } else {
            dataManager.clearsharedpref();
        }
        checkBox.setOnCheckedChangeListener(this);
        WelcomeScreen.frontBackCount = 1;
        return view;
    }//end onCreate

    public void getLocation() {
        if (permissionFile.checklocationPermissions(getActivity())) {
            gpsTracker = new GPSTracker(getActivity());
            if (gpsTracker.canGetLocation()) {
                userlatitude = gpsTracker.getlatitude();
                userlongitude = gpsTracker.getLongtitude();
            }
        }
    }//end getLocation

    @OnClick({R.id.loginbtn, R.id.checkBox, R.id.createaccount, R.id.forgetpass, R.id.loginlayout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.loginbtn:
                if (isNetworkConnected() && Validate_Structure.validateLogin(username, password)) {
                    hideKeyboard();
                    loginhashMap.put("username", username.getText().toString());
                    loginhashMap.put("password", password.getText().toString());
                    loginhashMap.put("latitude", String.valueOf(userlatitude));
                    loginhashMap.put("longitude", String.valueOf(userlongitude));
//                    if (sessionManager.getSharedpref(GlobalVariable.Device_Token).isEmpty()) {
//                        sessionManager.storesharedpref(GlobalVariable.Device_Token, FirebaseInstanceId.getInstance().getToken());
//                    }
//                    loginhashMap.put("device_token", sessionManager.getSharedpref(GlobalVariable.Device_Token));
                    loginhashMap.put("device_token", "");
                    loginhashMap.put("device_type", "android");
                    presenter.onLoginClick(loginhashMap);
                }
                break;
            case R.id.checkBox:

                break;
            case R.id.createaccount:
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.frameLayout, new SignUpFragment()).commit();
                break;
            case R.id.forgetpass:
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.frameLayout, new ForgotPasswordFrag()).commit();
                break;
            case R.id.loginlayout:
                hideKeyboard();
                break;
        }//end switch
    }//end onViewClicked

    private void EnableGPSAutoMatically() {
        GoogleApiClient googleApiClient = null;
        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(getActivity())
                    .addApi(LocationServices.API).addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this).build();
            googleApiClient.connect();
            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval(30 * 1000);
            locationRequest.setFastestInterval(5 * 1000);
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                    .addLocationRequest(locationRequest);
            builder.setAlwaysShow(true); // this is the key ingredient
            PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi
                    .checkLocationSettings(googleApiClient, builder.build());
            result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
                @Override
                public void onResult(LocationSettingsResult result) {
                    final Status status = result.getStatus();
                    final LocationSettingsStates state = result
                            .getLocationSettingsStates();
                    switch (status.getStatusCode()) {
                        case LocationSettingsStatusCodes.SUCCESS:
                            getLocation();
                            break;
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            try {
                                status.startResolutionForResult(getActivity(), 1000);
                            } catch (IntentSender.SendIntentException e) {
                                // Ignore the error.
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            Toasty.error(getActivity(), "Setting change not allowed", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
        }
    }


    @Override
    protected void setUp(View view) {
    }

    @Override
    public void updateResponse(LoginModel.DataBean dataBean) {
        dataManager.storesharedpref(GlobalVariable.AUTH_TOKEN, dataBean.getAuthentication_token());
        dataManager.storesharedpref(GlobalVariable.User_id, dataBean.getID());
        dataManager.storesharedpref(GlobalVariable.UserName, dataBean.getUsername());
        dataManager.storesharedpref(GlobalVariable.UserImage, dataBean.getProfile_image());
        dataManager.storesharedpref(GlobalVariable.UserPassword, password.getText().toString());

        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onDestroyView() {
        presenter.onDetach();
        super.onDestroyView();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.checkBox:
                if (isChecked) {
                    dataManager.storesharedpref(GlobalVariable.REMEMBER_ME, "1");
                    checkBox.setChecked(true);
                } else {
                    dataManager.clearsharedpref();
                    checkBox.setChecked(false);
                }
                break;
        }
    }

}//end class
