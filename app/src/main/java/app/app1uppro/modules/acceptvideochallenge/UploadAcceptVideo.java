package app.app1uppro.modules.acceptvideochallenge;

import android.Manifest;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.rubensousa.bottomsheetbuilder.BottomSheetBuilder;
import com.github.rubensousa.bottomsheetbuilder.BottomSheetMenuDialog;
import com.github.rubensousa.bottomsheetbuilder.adapter.BottomSheetItemClickListener;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.extensions.android.gms.auth.GooglePlayServicesAvailabilityIOException;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.googleapis.media.MediaHttpUploaderProgressListener;
import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.HttpBackOffIOExceptionHandler;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeScopes;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoSnippet;
import com.google.api.services.youtube.model.VideoStatus;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.inject.Inject;

import app.app1uppro.R;
import app.app1uppro.baseui.BaseActivity;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.DialogFile;
import app.app1uppro.common.GlobalVariable;
import app.app1uppro.common.PermissionFile;
import app.app1uppro.common.Validate_Structure;
import app.app1uppro.modules.mainactivity.MainActivity;
import app.app1uppro.modules.uploadvideo.IUpVideoView;
import app.app1uppro.modules.uploadvideo.UploadChallengeVideo;
import app.app1uppro.modules.uploadvideo.UploadVideoPresenter;
import app.app1uppro.util.ImageUtility;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class UploadAcceptVideo extends BaseActivity implements IAcceptVideoView {

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.toolbr_firstImage)
    ImageView toolbar_firstImage;
    @BindView(R.id.userImage_profile)
    CircleImageView userImage_profile;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.titleedittxt)
    EditText titleedittxt;
    @BindView(R.id.description)
    EditText description;
    @BindView(R.id.privacyStatus)
    TextView privacyStatus;
    @BindView(R.id.choosefile)
    TextView choosefile;
    @BindView(R.id.choosefilepath)
    TextView filePathText;
    @BindView(R.id.uploadbtn)
    TextView uploadbtn;
    @BindView(R.id.termslink)
    TextView termslink;

    @Inject
    UploadAcceptVideoPresenter<IAcceptVideoView> presenter;
    @Inject
    PermissionFile permissionFile;
    @Inject
    ImageUtility imageUtility;
    @Inject
    DialogFile dialogFile;
    @Inject
    DataManager sessionManager;

    private HashMap<String, String> uploadhashMap;
    ArrayList<String> categoryIds;
    private String videofilePath = "", videoEmbedID = "",
            filemanagerstring = "", video_file_format = "video/*";
    BottomSheetMenuDialog dialog;
    Uri selectedImageUri;
    GoogleAccountCredential mCredential;
    private static final String[] SCOPES = {YouTubeScopes.YOUTUBE_READONLY,
            YouTubeScopes.YOUTUBE_UPLOAD};
    String googleIdToken = "", TAG = "uploadvideo";
    static final int REQUEST_AUTHORIZATION = 1001;
    private static final String PREF_ACCOUNT_NAME = "accountName";
    private GoogleSignInClient mGoogleSignInClient;
    static final int REQUEST_GOOGLE_PLAY_SERVICES = 1002;
    static final int REQUEST_ACCOUNT_PICKER = 1000;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_challenge_video);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        presenter.onAttach(this);
        initialize();
    }//end onCreate

    private void initialize() {
        uploadhashMap = new HashMap<>();
        categoryIds = new ArrayList<>();
        toolbar_title.setText(getString(R.string.upload_video));
        toolbar_firstImage.setImageResource(R.drawable.backwhiteicon);
        hideKeyboard();
        privacyStatus.setText(getString(R.string.public_privacy));
//        if (getIntent().getStringExtra("googleIdToken") != null)
//            googleIdToken = getIntent().getStringExtra("googleIdToken");
        //set User Image and Name
        Glide.with(this).load(sessionManager.getSharedpref(GlobalVariable.UserImage)).into(userImage_profile);
        username.setText(sessionManager.getSharedpref(GlobalVariable.UserName));
        categoryIds = getIntent().getStringArrayListExtra("categoryids_list");
        description.setScroller(new Scroller(getApplicationContext()));
        description.setVerticalScrollBarEnabled(true);
        description.setMovementMethod(new ScrollingMovementMethod());
        mCredential = GoogleAccountCredential.usingOAuth2(
                getApplicationContext(), Arrays.asList(SCOPES))
                .setBackOff(new ExponentialBackOff());
        //configure Google Sign-In Request
        GoogleSignInOptions gso = new GoogleSignInOptions.
                Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(getString(R.string.auth_key))
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getApplicationContext(), gso);
        if (getIntent().getStringExtra(GlobalVariable.PREF_ACCOUNT_NAME) != null)
            mCredential.setSelectedAccountName(getIntent().getStringExtra(GlobalVariable.PREF_ACCOUNT_NAME));
    }//end initialize

    private void chooseAccount() {
        // String accountName = getPreferences(Context.MODE_PRIVATE).getString(PREF_ACCOUNT_NAME, null);
        String accountName = getIntent().getStringExtra(GlobalVariable.PREF_ACCOUNT_NAME);
        if (accountName != null) {
            mCredential.setSelectedAccountName(accountName);
            getResultsFromApi();
        } else {
            startActivityForResult(
                    mCredential.newChooseAccountIntent(),
                    REQUEST_ACCOUNT_PICKER);
//            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//            startActivityForResult(signInIntent, GlobalVariable.GOOGLE_SIGN_IN);
        }
    }

    @Override
    protected void setUp() {
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @SuppressLint("ClickableViewAccessibility")
    @OnClick({R.id.toolbr_firstImage, R.id.uploadbtn,
            R.id.description, R.id.choosefile, R.id.privacyStatus})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbr_firstImage:
                finish();
                break;
            case R.id.uploadbtn:
                if (Validate_Structure.validateVideoUpload(getApplicationContext(),
                        titleedittxt, videofilePath)) {
                    if (isNetworkConnected()) {
                        Permissions.check(UploadAcceptVideo.this,
                                new String[]{Manifest.permission.GET_ACCOUNTS, Manifest.permission.READ_EXTERNAL_STORAGE}, "Accounts and Storage permissions are required ",
                                new Permissions.Options().setSettingsDialogTitle("Warning!").setRationaleDialogTitle("Info"),
                                new PermissionHandler() {
                                    @Override
                                    public void onGranted() {
                                        getResultsFromApi();
                                    }
                                });
                    }

//                    if (mCredential.getSelectedAccountName() == null)
//                        chooseAccount();
                }
                break;
            case R.id.description:
                description.setFocusableInTouchMode(true);
                description.requestFocus();
                description.setOnTouchListener(new View.OnTouchListener() {
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        assert imm != null;
                        imm.showSoftInput(description, InputMethodManager.SHOW_FORCED);
                        switch (event.getAction() & MotionEvent.ACTION_MASK) {
                            case MotionEvent.ACTION_UP:
                                description.setFocusableInTouchMode(true);
                                description.requestFocus();
                                v.getParent().requestDisallowInterceptTouchEvent(false);
                                return true;
                        }
                        return false;
                    }
                });
                break;
            case R.id.choosefile:
                bottomVideoSelect();
                break;
            case R.id.privacyStatus:
                bottomPrivacyStatus();
                break;
        }//end switch
    }//end onClick


    private void getResultsFromApi() {
        if (!isGooglePlayServicesAvailable()) {
            acquireGooglePlayServices();
        } else if (mCredential.getSelectedAccountName() == null) {
            chooseAccount();
        } else if (!isDeviceOnline()) {
            Toasty.error(getApplicationContext(), "No network connection available.", Toast.LENGTH_SHORT).show();
        } else {
            new UploadVideoAsync(selectedImageUri).execute();
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
                UploadAcceptVideo.this,
                connectionStatusCode,
                REQUEST_GOOGLE_PLAY_SERVICES);
        dialog.show();
    }

    public void apiParameterStart() {
        uploadhashMap.put("user_id", sessionManager.getSharedpref(GlobalVariable.User_id));
        uploadhashMap.put("challengeID", getIntent().getStringExtra("challengeID"));
        uploadhashMap.put("videoEmbedID", videoEmbedID);
        uploadhashMap.put("videoName", titleedittxt.getText().toString());
        uploadhashMap.put("videoDescription", description.getText().toString());
        uploadhashMap.put("type",getIntent().getStringExtra("type"));
        presenter.acceptVideoMethod(uploadhashMap);
    }

    public void selectVideoFromGallery() {
        Intent intent;
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        else
            intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.INTERNAL_CONTENT_URI);
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, GlobalVariable.VIDEO_PICKER);
    }//end selectVideoFromGallery

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case GlobalVariable.VIDEO_PICKER:
                    selectedImageUri = data.getData();
                    assert selectedImageUri != null;
                    videofilePath = selectedImageUri.getPath();
                    filePathText.setText(videofilePath);
                    break;
                case GlobalVariable.REQUEST_VIDEO_CAPTURE:
                    selectedImageUri = data.getData();
                    videofilePath = getPath(selectedImageUri);
                    filePathText.setText(videofilePath);
                    break;
                case GlobalVariable.GOOGLE_SIGN_IN:
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                    handleSignInResult(task);
                    break;
                case REQUEST_AUTHORIZATION:
                    if (mCredential.getSelectedAccountName() == null)
                        chooseAccount();
                    break;
                case REQUEST_GOOGLE_PLAY_SERVICES:
                    if (resultCode != RESULT_OK) {
                        Toasty.error(getApplicationContext(), "This app requires Google Play Services. Please install " + "Google Play Services on your device and relaunch this app.", Toast.LENGTH_SHORT).show();
                    } else {
                        getResultsFromApi();
                    }
                    break;
                case REQUEST_ACCOUNT_PICKER:
                    if (data != null && data.getExtras() != null) {
                        String accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
                        if (accountName != null) {
                            mCredential.setSelectedAccountName(accountName);
                            SharedPreferences settings = getPreferences(Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = settings.edit();
                            editor.putString(GlobalVariable.PREF_ACCOUNT_NAME, accountName);
                            editor.apply();
                            getResultsFromApi();
                        }
                    }
                    break;
            }
        }//end Result ok
    }//end onActivityResult

    //Handle Signin Request
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount acct = completedTask.getResult(ApiException.class);
            hideLoading();
            if (acct != null) {
                new UploadVideoAsync(selectedImageUri).execute();
            }
        } catch (Exception e) {
        }
    }//end handleSignInResult

    private void selectVideoFromCamera() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, GlobalVariable.REQUEST_VIDEO_CAPTURE);
        }
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Video.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else return null;
    }//end getPath

    public void bottomPrivacyStatus() {
        dialog = new BottomSheetBuilder(this,
                R.style.AppTheme_BottomSheetDialog)
                .setMode(BottomSheetBuilder.MODE_LIST)
                .setMenu(R.menu.bottomprivacy)
                .expandOnStart(true)           // expand the dialog automatically:
                .setItemClickListener(new BottomSheetItemClickListener() {
                    @Override
                    public void onBottomSheetItemClick(MenuItem item) {
                        int id = item.getItemId();
                        switch (id) {
                            case R.id.publicid:
                                privacyStatus.setText(getString(R.string.public_privacy));
                                dialog.dismiss();
                                break;
                            case R.id.privateid:
                                privacyStatus.setText(getString(R.string.private_privacy));
                                dialog.dismiss();
                                break;
                        }
                    }
                })
                .createDialog();
        dialog.show();
    }//end bottom dialog

    public void bottomVideoSelect() {
        dialog = new BottomSheetBuilder(this,
                R.style.AppTheme_BottomSheetDialog)
                .setMode(BottomSheetBuilder.MODE_LIST)
                .setMenu(R.menu.bottom_video_select)
                .expandOnStart(true)           // expand the dialog automatically:
                .setItemClickListener(new BottomSheetItemClickListener() {
                    @Override
                    public void onBottomSheetItemClick(MenuItem item) {
                        int id = item.getItemId();
                        switch (id) {
                            case R.id.menu_camera:
                                if (permissionFile.checkLocStorgePermission(UploadAcceptVideo.this))
                                    selectVideoFromCamera();
                                dialog.dismiss();
                                break;
                            case R.id.menu_gallery:
                                selectVideoFromGallery();
                                dialog.dismiss();
                                break;
                        }
                    }
                })
                .createDialog();
        dialog.show();
    }//end bottom dialog

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == GlobalVariable.MULTI_LOC_STOR) {
            for (int i = 0, len = permissions.length; i < len; i++) {
                String permission = permissions[i];
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    boolean showRationale = false;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                        showRationale = shouldShowRequestPermissionRationale(permission);
                    }
                    if (!showRationale) {
                        permissionDeniedAlertBox(getString(R.string.denied_media_title), getString(R.string.denied_media_msg));
                    }
                }
            }
        }
    }


    public void permissionDeniedAlertBox(String title, String msg) {
        android.support.v7.app.AlertDialog.Builder builder;
        builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setMessage(msg)
                .setCancelable(true)
                .setTitle(title)
                .setPositiveButton(R.string.go_to_setting, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getApplicationContext().getPackageName(), null);
                        intent.setData(uri);
                        startActivity(intent);
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void updateUI(String msg) {
        Toasty.success(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        finish();
    }


    public class UploadVideoAsync extends AsyncTask<Void, Void, String> {
        Uri data;
        ProgressDialog progressDialog;

        UploadVideoAsync(Uri data) {
            this.data = data;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(UploadAcceptVideo.this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Uploading Video to youtube");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            return uploadYoutube(data);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            if (s != null) {
                videoEmbedID = s;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        apiParameterStart();
                    }
                });
//                Toasty.success(getApplicationContext(), "Video Uploaded on YouTube Channel ", Toast.LENGTH_SHORT).show();
            } else {
                Toasty.error(getApplicationContext(), "Uploading Failed ..!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String uploadYoutube(Uri data) {

        HttpTransport transport = AndroidHttp.newCompatibleTransport();
//        JsonFactory jsonFactory = new AndroidJsonFactory(); // GsonFactory
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

        HttpRequestInitializer initializer = new HttpRequestInitializer() {
            @Override
            public void initialize(HttpRequest request) throws IOException {
                mCredential.initialize(request);
                request.setLoggingEnabled(true);
                request.setIOExceptionHandler(
                        new HttpBackOffIOExceptionHandler(new ExponentialBackOff()));
            }
        };

        YouTube.Builder youtubeBuilder = new YouTube.Builder(transport, jsonFactory, initializer);
        youtubeBuilder.setApplicationName(getString(R.string.app_name));
//        youtubeBuilder.setYouTubeRequestInitializer(new YouTubeRequestInitializer(API_KEY));
        YouTube youtube = youtubeBuilder.build();

        String PRIVACY_STATUS = privacyStatus.getText().toString(); // or public,private
        String PARTS = "snippet,status,contentDetails";

        String videoId = null;
        try {
            Video videoObjectDefiningMetadata = new Video();
            videoObjectDefiningMetadata.setStatus(new VideoStatus().setPrivacyStatus(PRIVACY_STATUS));

            VideoSnippet snippet = new VideoSnippet();
            snippet.setTitle(titleedittxt.getText().toString());
            snippet.setDescription(description.getText().toString());
            snippet.setTags(Arrays.asList(new String[]{"TaG1,TaG2"}));
            videoObjectDefiningMetadata.setSnippet(snippet);

            YouTube.Videos.Insert videoInsert = youtube.videos().insert(
                    PARTS,
                    videoObjectDefiningMetadata,
                    getMediaContent(getFileFromUri(data, UploadAcceptVideo.this)))
                    .setOauthToken(token).setKey(getString(R.string.auth_key));

            MediaHttpUploader uploader = videoInsert.getMediaHttpUploader();
            uploader.setDirectUploadEnabled(false);

            MediaHttpUploaderProgressListener progressListener = new MediaHttpUploaderProgressListener() {
                public void progressChanged(MediaHttpUploader uploader) throws IOException {
                    Log.d(TAG, "progressChanged: " + uploader.getUploadState());
                    switch (uploader.getUploadState()) {
                        case INITIATION_STARTED:
                            break;
                        case INITIATION_COMPLETE:
                            break;
                        case MEDIA_IN_PROGRESS:
                            break;
                        case MEDIA_COMPLETE:
                        case NOT_STARTED:
                            Log.d(TAG, "progressChanged: upload_not_started");
                            break;
                    }
                }
            };
            uploader.setProgressListener(progressListener);

            Log.d(TAG, "Uploading..");
            Video returnedVideo = videoInsert.execute();
            Log.d(TAG, "Video upload completed");
            videoId = returnedVideo.getId();
            Log.d(TAG, String.format("videoId = [%s]", videoId));
        } catch (final GooglePlayServicesAvailabilityIOException availabilityException) {
            Log.e(TAG, "GooglePlayServicesAvailabilityIOException", availabilityException);
        } catch (UserRecoverableAuthIOException userRecoverableException) {
            startActivityForResult(userRecoverableException.getIntent(), REQUEST_AUTHORIZATION);
            Log.i(TAG, String.format("UserRecoverableAuthIOException: %s",
                    userRecoverableException.getMessage()));
        } catch (IOException e) {
            Log.e(TAG, "IOException", e);
        }

        return videoId;

    }//end upload you tube


    private AbstractInputStreamContent getMediaContent(File file) throws FileNotFoundException {
        InputStreamContent mediaContent = null;
        try {
            mediaContent = new InputStreamContent(
                    "video/*",
                    new BufferedInputStream(new FileInputStream(file)));
            mediaContent.setLength(file.length());
//            return mediaContent;
        } catch (Exception e) {
            Log.e("AbstractInputStream", e.toString());
        }
        return mediaContent;
    }


    private static File getFileFromUri(Uri uri, Activity activity) {
        try {
            String filePath = null;
            String[] proj = {MediaStore.Video.VideoColumns.DATA};
            Cursor cursor = activity.getContentResolver().
                    query(uri, proj, null, null, null);
            if (cursor.moveToFirst()) {
                int column_index = cursor.
                        getColumnIndexOrThrow(MediaStore.Video.VideoColumns.DATA);
                filePath = cursor.getString(column_index);
            }
            cursor.close();
            File file = new File(filePath);
            cursor.close();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}//end main class
