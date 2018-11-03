package app.app1uppro.modules.videocategoryhome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import app.app1uppro.R;
import app.app1uppro.baseui.BaseActivity;
import app.app1uppro.modules.login.LoginFragment;
import app.app1uppro.modules.videocategory.VideoCategoryFrag;
import app.app1uppro.modules.videosubcategory.VideoSubCatFrag;

public class VideoCategoryHome extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_category_home);
        getSupportFragmentManager().beginTransaction().replace(R.id.videoframeLayout,
                new VideoCategoryFrag()).commit();
    }//end onCreate

    @Override
    protected void setUp() { }

}//end main class
