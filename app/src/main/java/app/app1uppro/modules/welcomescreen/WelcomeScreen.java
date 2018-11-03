package app.app1uppro.modules.welcomescreen;

import android.os.Bundle;
import javax.inject.Inject;
import app.app1uppro.R;
import app.app1uppro.baseui.BaseActivity;
import app.app1uppro.modules.login.LoginFragment;
import butterknife.ButterKnife;

public class WelcomeScreen extends BaseActivity implements IWelcomeView {

    @Inject
    WelcomePresenter<IWelcomeView> presenter;
    public static int frontBackCount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        presenter.onAttach(WelcomeScreen.this);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new LoginFragment()).commit();
    }//end onCreate

    @Override
    protected void setUp() { }

    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (frontBackCount == 1) {
            finish();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new LoginFragment()).commit();
        }
    }//end onBackPressed
}//end class
