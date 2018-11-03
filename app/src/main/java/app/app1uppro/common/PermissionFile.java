package app.app1uppro.common;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import app.app1uppro.di.ApplicationContext;


@Singleton
public class PermissionFile {

    private Context mContext;

    @Inject
    public PermissionFile(@ApplicationContext Context context) {
        mContext = context;
    }

    public boolean checkLocStorgePermission(Context ctx) {
        int permissionCAMERA = ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.CAMERA);


        int readStorage = ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.READ_EXTERNAL_STORAGE);

        int writeStorage = ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);


        List<String> listPermissionsNeeded = new ArrayList<>();
        if (readStorage != PackageManager.PERMISSION_GRANTED && writeStorage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (permissionCAMERA != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (Build.VERSION.SDK_INT >= 23) {
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions((Activity) ctx,

                        listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                        GlobalVariable.MULTI_LOC_STOR);
                return false;
            }
        }

        return true;
    }


    public boolean checklocationPermissions(Context ctx) {
        int permissionCAMERA = ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.ACCESS_COARSE_LOCATION);

        List<String> listPermissionsNeeded = new ArrayList<>();

        if (permissionCAMERA != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (Build.VERSION.SDK_INT >= 23) {
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions((Activity) ctx,
                        listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), GlobalVariable.REQUEST_CODE_LOCATION);
                return false;
            }
        }
        return true;
    }


    public boolean checkCallPermissions(Context ctx) {


        if (Build.VERSION.SDK_INT >= 23) {
            if (ctx.checkSelfPermission(Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG", "Permission is granted");
                return true;
            } else {

                Log.v("TAG", "Permission is revoked");
                ActivityCompat.requestPermissions((FragmentActivity) ctx, new String[]{Manifest.permission.CALL_PHONE}, GlobalVariable.REQUEST_CALL);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG", "Permission is granted");
            return true;
        }


    }

    public File getFile() {
        return new File(Environment.getExternalStorageDirectory() + File.separator + "SampleApp"
                + File.separator);
    }

    public  String getUniqueImageFilename() {
        return "img_" + System.currentTimeMillis() + ".png";
    }
}
