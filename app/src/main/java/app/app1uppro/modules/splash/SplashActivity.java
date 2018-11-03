package app.app1uppro.modules.splash;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import javax.inject.Inject;

import app.app1uppro.R;
import app.app1uppro.baseui.BaseActivity;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.GlobalVariable;
import app.app1uppro.modules.mainactivity.MainActivity;
import app.app1uppro.modules.welcomescreen.WelcomeScreen;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity {

    int SPLASH_TIME_OUT = 3000;
    @Inject
    DataManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = null;
                if (sessionManager.getSharedpref(GlobalVariable.User_id).equals("")) {
                    i = new Intent(SplashActivity.this, WelcomeScreen.class);
                } else {
                    i = new Intent(SplashActivity.this, MainActivity.class);
                }
                startActivity(i);
                finish();
            }//end run
        }, SPLASH_TIME_OUT); //end handler
    }//end onCreate

    @Override
    protected void setUp() {

    }


}//end class
