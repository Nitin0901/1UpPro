package app.app1uppro.modules.profilefragment;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.util.HashMap;

import javax.inject.Inject;

import app.app1uppro.R;
import app.app1uppro.apibase.modelclass.UserProfileModel;
import app.app1uppro.baseui.BaseFragment;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.DialogFile;
import app.app1uppro.common.GlobalVariable;
import app.app1uppro.common.PermissionFile;
import app.app1uppro.common.Validate_Structure;
import app.app1uppro.intrface.ImagePickerStatus;
import app.app1uppro.modules.mainactivity.UpdateNavigationInterface;
import app.app1uppro.modules.zoomimage.ZoomImage;
import app.app1uppro.util.ImageUtility;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends BaseFragment implements IProfileView, ImagePickerStatus {

    private static final int PIC_CROP = 787;
    @Inject
    ProfilePresenter<IProfileView> presenter;
    @Inject
    PermissionFile permissionFile;
    @Inject
    ImageUtility imageUtility;
    @Inject
    DialogFile dialogFile;
    @Inject
    DataManager sessionManager;

    @BindView(R.id.userImage_profile)
    CircleImageView userImage_profile;
    @BindView(R.id.ediit_profileImage)
    CircleImageView ediit_profileImage;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.firstname)
    EditText firstname;
    @BindView(R.id.lastname)
    EditText lastname;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.updatebtn)
    Button updatebtn;
    private File destination;
    Uri outputFileUri;
    String profileImage = "";
    UpdateNavigationInterface updateNavigationInterface;

    public ProfileFragment(){

    }

    @SuppressLint("ValidFragment")
    public ProfileFragment(UpdateNavigationInterface updateNavigationInterface){
        this.updateNavigationInterface=updateNavigationInterface;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this, view));
        presenter.onAttach(ProfileFragment.this);
        presenter.onUserDeatil();
        return view;
    }//end onCreateView

    @Override
    protected void setUp(View view) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }

    @OnClick({R.id.updatebtn, R.id.userImage_profile, R.id.ediit_profileImage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.updatebtn:
                if (Validate_Structure.validateProfile(username, firstname, lastname)) {
                    presenter.onUpdateProfile(username.getText().toString(),
                            firstname.getText().toString(), lastname.getText().toString());
                }
                break;
            case R.id.ediit_profileImage:
                if (permissionFile.checkLocStorgePermission(getActivity())) {
                    sessionManager.selectImage(getActivity(), ProfileFragment.this, getString(R.string.profile_fragment));
                }
                break;
            case R.id.userImage_profile:
                if (profileImage.isEmpty())
                    Toasty.info(getActivity(), "No Profile Image", Toast.LENGTH_SHORT).show();
                else {
                    Intent imageintent = new Intent(getActivity(), ZoomImage.class);
                    imageintent.putExtra("zoomimage", profileImage);
                    imageintent.putExtra("zoomimagetitle", "Profile Image");
                    startActivity(imageintent);
                }
                break;
        }//end switch
    }//end click

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //userImageEdit.setOnClickListener(this);
        if (resultCode == RESULT_OK) {
            if (requestCode == GlobalVariable.GalleryPicker) {
                performCrop(data.getData());
            } else if (requestCode == GlobalVariable.CameraPicker) {
                performCrop(outputFileUri);
            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (sessionManager.checkConnectionActivity()) {
                    outputFileUri = result.getUri();
                    profileImage = outputFileUri.getPath();
                    presenter.onupdateImage(profileImage);
                }
            }
        }
    }

    private void performCrop(Uri Imageuri) {
        try {
            CropImage.activity(Imageuri).setAspectRatio(1, 1).start(getContext(), ProfileFragment.this);
        } catch (ActivityNotFoundException anfe) {
            Toasty.error(getActivity(), "This device doesn't support the crop action!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getProfileImage(String image) {
        if (image.isEmpty()) {
            Glide.with(this).load(R.drawable.user_dummy).into(userImage_profile);
        } else {
            Glide.with(this).load(image).into(userImage_profile);
            profileImage=image;
            sessionManager.storesharedpref(GlobalVariable.UserImage,image);
            updateNavigationInterface.updateNavigation();
            Toasty.success(getActivity(),getString(R.string.profile_image_updated_done),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
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
        builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
        builder.setMessage(msg)
                .setCancelable(true)
                .setTitle(title)
                .setPositiveButton(R.string.go_to_setting, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
                        intent.setData(uri);
                        startActivity(intent);
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void updateUI(UserProfileModel.DataBean dataBean) {
        if (dataBean.getProfile_image().isEmpty()) {
            Glide.with(this).load(R.drawable.user_dummy).into(userImage_profile);
        } else {
            Glide.with(this).load(dataBean.getProfile_image()).into(userImage_profile);
            profileImage=dataBean.getProfile_image();
        }
        username.setText(dataBean.getUsername());
        firstname.setText(dataBean.getFirstname());
        lastname.setText(dataBean.getLastname());
        email.setClickable(false);
        email.setEnabled(false);
        email.setText(dataBean.getEmail());
        sessionManager.storesharedpref(GlobalVariable.UserName,dataBean.getUsername());
        updateNavigationInterface.updateNavigation();
    }//end updateUI

    @Override
    public void updatedUIByUser(UserProfileModel.DataBean dataBean) {
        if (dataBean.getProfile_image().isEmpty()) {
            Glide.with(this).load(R.drawable.user_dummy).into(userImage_profile);
        } else {
            Glide.with(this).load(dataBean.getProfile_image()).into(userImage_profile);
            profileImage=dataBean.getProfile_image();
        }
        username.setText(dataBean.getUsername());
        firstname.setText(dataBean.getFirstname());
        lastname.setText(dataBean.getLastname());
        email.setClickable(false);
        email.setEnabled(false);
        email.setText(dataBean.getEmail());
        sessionManager.storesharedpref(GlobalVariable.UserName,dataBean.getUsername());
        updateNavigationInterface.updateNavigation();
        Toasty.success(getActivity(),getString(R.string.profile_updated_done),Toast.LENGTH_SHORT).show();

    }

    @Override
    public void imageStatus(int status, String type) {
        if (status == GlobalVariable.CameraPicker) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            final File root = permissionFile.getFile();
            root.mkdirs();
            String filename = permissionFile.getUniqueImageFilename();
            destination = new File(root, filename);
         //   outputFileUri = Uri.fromFile(destination);
            outputFileUri = FileProvider.getUriForFile(getActivity(), getActivity().getPackageName() + ".provider", destination);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            startActivityForResult(intent, GlobalVariable.CameraPicker);
        } else {
            Intent pickIntent = new Intent(Intent.ACTION_PICK);
            pickIntent.setType("image/*");
            startActivityForResult(pickIntent, GlobalVariable.GalleryPicker);
        }
    }//end image status


}//end main class
