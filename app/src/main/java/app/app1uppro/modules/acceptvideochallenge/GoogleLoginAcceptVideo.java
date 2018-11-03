package app.app1uppro.modules.acceptvideochallenge;

import android.Manifest;
import android.accounts.AccountManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.youtube.YouTubeScopes;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

import java.util.Arrays;

import app.app1uppro.R;
import app.app1uppro.baseui.BaseActivity;
import app.app1uppro.common.GlobalVariable;
import app.app1uppro.modules.mainactivity.MainActivity;
import app.app1uppro.modules.uploadvideo.UploadChallengeVideo;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class GoogleLoginAcceptVideo extends BaseActivity {

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.toolbr_firstImage)
    ImageView toolbar_firstImage;
    @BindView(R.id.btn_google)
    ImageView btn_google;
    private GoogleSignInClient mGoogleSignInClient;
    GoogleAccountCredential mCredential;
    private static final String[] SCOPES = {YouTubeScopes.YOUTUBE_READONLY,
            YouTubeScopes.YOUTUBE_UPLOAD};
    static final int REQUEST_GOOGLE_PLAY_SERVICES = 1002;
    static final int REQUEST_ACCOUNT_PICKER = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_login_upload_video);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        initialize();
    }//end onCreate

    private void initialize() {
        toolbar_title.setText(getString(R.string.upload_video));
        toolbar_firstImage.setImageResource(R.drawable.backwhiteicon);
        hideKeyboard();
        //configure Google Sign-In Request
        GoogleSignInOptions gso = new GoogleSignInOptions.
                Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(getString(R.string.auth_key))
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getApplicationContext(), gso);
        mCredential = GoogleAccountCredential.usingOAuth2(getApplicationContext(),
                Arrays.asList(SCOPES)).setBackOff(new ExponentialBackOff());
    }//end initialize

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_GOOGLE_PLAY_SERVICES:
                if (resultCode != RESULT_OK) {
                    Toasty.error(getApplicationContext(), "This app requires Google Play Services. Please install " + "Google Play Services on your device and relaunch this app.", Toast.LENGTH_SHORT).show();
                } else getResultsFromApi();
                break;
//            case GlobalVariable.GOOGLE_SIGN_IN:
//                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//                handleSignInResult(task);
//                break;
            case REQUEST_ACCOUNT_PICKER:
                if (data != null && data.getExtras() != null) {
                    String accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
                    if (accountName != null) {
                        mCredential.setSelectedAccountName(accountName);
                        Intent videoIntent = new Intent(getApplicationContext(), UploadAcceptVideo.class);
                        videoIntent.putExtra("challengeID", getIntent().getStringExtra("challengeID"));
                        videoIntent.putExtra("type", getIntent().getStringExtra("type"));
                        videoIntent.putExtra(GlobalVariable.PREF_ACCOUNT_NAME,accountName);
                        startActivity(videoIntent);
                        finish();

//                        SharedPreferences settings = getPreferences(Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor = settings.edit();
//                        editor.putString(PREF_ACCOUNT_NAME, accountName);
//                        editor.apply();
//                        getResultsFromApi();
                    }
                }
                break;
        }
    }//end onActivityResult
    //Handle Signin Request
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount acct = completedTask.getResult(ApiException.class);
            hideLoading();
            if (acct != null) {
                Log.d("googleToken", "handleSignInResult: " + acct.getIdToken());
                Intent videoIntent = new Intent(getApplicationContext(), UploadAcceptVideo.class);
                videoIntent.putExtra("ChallengeID", getIntent().getStringExtra("ChallengeID"));
                videoIntent.putExtra("type", getIntent().getStringExtra("type"));
                videoIntent.putExtra("googleIdToken", acct.getIdToken());
                startActivity(videoIntent);
                finish();
            }
        } catch (Exception e) {
        }
    }//end handleSignInResult

    @Override
    protected void setUp() { }

    @Override
    public void onBackPressed() {
        finish();
    }

    @OnClick({R.id.toolbr_firstImage, R.id.btn_google})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbr_firstImage:
                finish();
                break;
            case R.id.btn_google:
                google_login();
                break;
        }//end switch
    }//end onClick

    private void google_login() {
        if (isNetworkConnected()) {
            Permissions.check(GoogleLoginAcceptVideo.this,
                    new String[]{Manifest.permission.GET_ACCOUNTS, Manifest.permission.READ_EXTERNAL_STORAGE}, "Accounts and Storage permissions are required ",
                    new Permissions.Options().
                            setSettingsDialogTitle("Warning!").setRationaleDialogTitle("Info"),
                    new PermissionHandler() {
                        @Override
                        public void onGranted() {
                            getResultsFromApi();
                        }
                    });
        }
    }//end google login


    private void getResultsFromApi() {
        if (!isGooglePlayServicesAvailable()) {
            acquireGooglePlayServices();
        } else if (mCredential.getSelectedAccountName() == null) {
            chooseAccount();
        } else if (!isDeviceOnline()) {
            Toasty.error(getApplicationContext(), "No network connection available.", Toast.LENGTH_SHORT).show();
        }
    }//end getResults from APi

    private void chooseAccount() {
        String accountName = getPreferences(Context.MODE_PRIVATE).getString(GlobalVariable.PREF_ACCOUNT_NAME, null);
        if (accountName != null) {
            mCredential.setSelectedAccountName(accountName);
//            getResultsFromApi();
        } else {
            startActivityForResult(
                    mCredential.newChooseAccountIntent(),
                    REQUEST_ACCOUNT_PICKER);
//            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//            startActivityForResult(signInIntent, GlobalVariable.GOOGLE_SIGN_IN);
        }
    }

    private boolean isDeviceOnline() {
        ConnectivityManager connMgr =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    private boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability apiAvailability =
                GoogleApiAvailability.getInstance();
        final int connectionStatusCode =
                apiAvailability.isGooglePlayServicesAvailable(this);
        return connectionStatusCode == ConnectionResult.SUCCESS;
    }

    private void acquireGooglePlayServices() {
        GoogleApiAvailability apiAvailability =
                GoogleApiAvailability.getInstance();
        final int connectionStatusCode =
                apiAvailability.isGooglePlayServicesAvailable(this);
        if (apiAvailability.isUserResolvableError(connectionStatusCode)) {
            showGooglePlayServicesAvailabilityErrorDialog(connectionStatusCode);
        }
    }

    void showGooglePlayServicesAvailabilityErrorDialog(
            final int connectionStatusCode) {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        Dialog dialog = apiAvailability.getErrorDialog(
                GoogleLoginAcceptVideo.this,
                connectionStatusCode,
                REQUEST_GOOGLE_PLAY_SERVICES);
        dialog.show();
    }

}//end main class
