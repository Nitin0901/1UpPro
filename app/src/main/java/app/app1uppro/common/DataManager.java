package app.app1uppro.common;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.github.rubensousa.bottomsheetbuilder.BottomSheetBuilder;
import com.github.rubensousa.bottomsheetbuilder.BottomSheetMenuDialog;
import com.github.rubensousa.bottomsheetbuilder.adapter.BottomSheetItemClickListener;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.app1uppro.R;
import app.app1uppro.di.ApplicationContext;
import app.app1uppro.intrface.ImagePickerStatus;
import app.app1uppro.service.ConnectivityReceiver;
import es.dmoral.toasty.Toasty;


@Singleton
public class DataManager {
    private Context mContext;
    private SessionManager mSharedPrefsHelper;
    @Inject
    public DataManager(@ApplicationContext Context context, SessionManager sharedPrefsHelper) {
        mContext = context;
        mSharedPrefsHelper = sharedPrefsHelper;
    }

    public void storesharedpref(String acesskey, String accessValue) {
        mSharedPrefsHelper.setValue(acesskey, accessValue);
    }

    public String getSharedpref(String accessKey) {
        return mSharedPrefsHelper.getvalue(accessKey);
    }

    public void clearsharedpref() {
        mSharedPrefsHelper.logout();
    }

    public void saveLoginSahredPref(String username, String user_id) {
        mSharedPrefsHelper.setLoginParams(username, user_id);
    }

    public void hideKeyboard(Context context, View view) {
        final InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void selectImage(Context mContext, final ImagePickerStatus imagePickerStatus, final String screenType) {
        BottomSheetMenuDialog dialog = new BottomSheetBuilder(mContext, R.style.AppTheme_BottomSheetDialog)
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


    public boolean checkConnectionActivity() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        String message = "No Internet Connection";
        if (!isConnected) {
            Toasty.error(mContext, message, Toast.LENGTH_SHORT).show();
        }
        return isConnected;
    }
}
