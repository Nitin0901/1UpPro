package app.app1uppro.common;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.view.MenuItem;

import com.github.rubensousa.bottomsheetbuilder.BottomSheetBuilder;
import com.github.rubensousa.bottomsheetbuilder.BottomSheetMenuDialog;
import com.github.rubensousa.bottomsheetbuilder.adapter.BottomSheetItemClickListener;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.app1uppro.R;
import app.app1uppro.di.ApplicationContext;
import app.app1uppro.intrface.DialogInterfce;
import app.app1uppro.intrface.ImagePickerStatus;

@Singleton
public class DialogFile {

    private Context mContext;

    @Inject
    public DialogFile(@ApplicationContext Context context) {
        mContext = context;
    }

    public void showLocationAlert(Context context) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        // Setting Dialog Title
        alertDialog.setTitle("GPS is settings");

        // Setting Dialog Message
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");

        // On pressing Settings button
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                context.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));


            }
        });

        // on pressing cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        // Showing Alert Message
        alertDialog.show();

    }

    public void selectImage(final ImagePickerStatus imagePickerStatus, Context context, final String screenType) {
        BottomSheetMenuDialog dialog = new BottomSheetBuilder(context, R.style.AppTheme_BottomSheetDialog)
                .setMode(BottomSheetBuilder.MODE_LIST)
                .setMenu(R.menu.menu_bottom_simple_sheet)
                .expandOnStart(true)           // expand the dialog automatically:
                .setIconTintColorResource(R.color.colorPrimary)   // tint the menu icons:
                .setItemClickListener(new BottomSheetItemClickListener() {
                    @Override
                    public void onBottomSheetItemClick(MenuItem item) {

                        int id = item.getItemId();
                        switch (id) {
                            case R.id.menu_camera:
                                imagePickerStatus.imageStatus(GlobalVariable.CameraPicker, screenType);
                                break;
                            case R.id.menu_gallery:
                                imagePickerStatus.imageStatus(GlobalVariable.GalleryPicker, screenType);
                                break;
                            default:
                                imagePickerStatus.imageStatus(GlobalVariable.CANCELREQUEST, screenType);
                                break;
                        }
                    }
                })
                .createDialog();

        dialog.show();
    }




    public void acceptRejectDialog(Context context, String message, String type, DialogInterfce dialogInterface, String id) {
        android.support.v7.app.AlertDialog.Builder builder;
        builder = new android.support.v7.app.AlertDialog.Builder(context);
        builder.setMessage(message)
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialogInterface.accept(type,id);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialogInterface.reject(type);
                    }
                })
                .setTitle("Alert Message")
                .show();
    }
}
