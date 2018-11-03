package app.app1uppro.modules.zoomimage;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import app.app1uppro.R;
import app.app1uppro.baseui.BaseActivity;
import app.app1uppro.di.component.ActivityComponent;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZoomImage extends BaseActivity {
    String zoomImageStr="";
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbr_firstImage)
    ImageView toolbrFirstImage;

    @BindView(R.id.myZoomageView)
    PhotoView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_zoom_image);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        toolbrFirstImage.setImageResource(R.drawable.backwhiteicon);
        photoView.setZoomable(true);
        photoView.getMaximumScale();
        zoomImageStr=getIntent().getStringExtra("zoomimage");
        toolbarTitle.setText(getIntent().getStringExtra("zoomimagetitle"));
        if (zoomImageStr.equals(""))
            Glide.with(ZoomImage.this).load(R.drawable.user_dummy).into(photoView);
        else
            Glide.with(ZoomImage.this).load(zoomImageStr).into(photoView);
    }//end onCreate

    @Override
    protected void setUp() { }

    @OnClick(R.id.toolbr_firstImage)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.toolbr_firstImage:
                finish();
                break;
        }
    }//end onClick

}//end main class