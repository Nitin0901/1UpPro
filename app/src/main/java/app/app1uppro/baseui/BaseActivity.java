package app.app1uppro.baseui;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import app.app1uppro.MyApplication;
import app.app1uppro.R;
import app.app1uppro.common.CommonUtils;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.GlobalVariable;
import app.app1uppro.di.component.ActivityComponent;
import app.app1uppro.di.component.DaggerActivityComponent;
import app.app1uppro.di.module.ActivityModule;
import app.app1uppro.modules.welcomescreen.WelcomeScreen;
import app.app1uppro.service.ConnectivityReceiver;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;

public abstract class BaseActivity extends AppCompatActivity implements MvpView,
        BaseFragment.BaseCallback {
    @Inject
    DataManager dataManager;

    private ProgressDialog mProgressDialog;
    private ActivityComponent mActivityComponent;
    private Unbinder mUnBinder;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((MyApplication) getApplication()).getComponent())
                .build();
        getActivityComponent().inject(this);

    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.initProgressDialog(this);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    private void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        snackbar.show();
    }

    @Override
    public void onError(String message) {
        if (message != null) { showSnackBar(message); }
        else { showSnackBar(getString(R.string.some_error)); }
    }

    @Override
    public void onError(@StringRes int resId) {
        onError(getString(resId));
    }

    @Override
    public void showMessage(String message) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.some_error), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showCustomMessage(String message) {
        Toasty.info(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(@StringRes int resId) {
        showMessage(getString(resId));
    }

    @Override
    public boolean isNetworkConnected() {
        return ConnectivityReceiver.isConnected();
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void openActivityOnTokenExpire() {
        String username= dataManager.getSharedpref(GlobalVariable.UserName);
        String userpass= dataManager.getSharedpref(GlobalVariable.UserPassword);
        String rememberme=dataManager.getSharedpref(GlobalVariable.REMEMBER_ME);
        dataManager.clearsharedpref();
        dataManager.storesharedpref(GlobalVariable.UserName,username);
        dataManager.storesharedpref(GlobalVariable.UserPassword,userpass);
        dataManager.storesharedpref(GlobalVariable.REMEMBER_ME,rememberme);
        Intent intent = new Intent(this, WelcomeScreen.class);
        startActivity(intent);
        finishAffinity();

    }
    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }
    @Override
    protected void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind(); }
        super.onDestroy();
    }
    protected abstract void setUp();
}
