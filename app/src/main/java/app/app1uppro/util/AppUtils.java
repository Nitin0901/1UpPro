package app.app1uppro.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.app1uppro.R;
import app.app1uppro.di.ApplicationContext;
import es.dmoral.toasty.Toasty;

@Singleton
public class AppUtils {

    private Context mContext;

    @Inject
    public AppUtils(@ApplicationContext Context context) {
        mContext = context;

        intializeToasty();
        intializeAppFont();
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void intializeToasty()
    {
        Toasty.Config.getInstance()
                .setErrorColor(ContextCompat.getColor(mContext, R.color.toastError)) // optional
                .setSuccessColor(ContextCompat.getColor(mContext, R.color.light_grey))
                .setInfoColor(ContextCompat.getColor(mContext, R.color.app_color))
                .setTextColor(Color.WHITE)
                .setTextSize(18)
                .apply(); // required
    }

    private void intializeAppFont()
    {
        //calligraphy for fonts
//        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
//                .setDefaultFontPath("fonts/avenir_book.otf")
//                .setFontAttrId(R.attr.fontPath)
//                .build());

        //TypefaceUtil.overrideFont(getApplicationContext(), "serif", "assets/font/Hanken-Book.ttf"); // font from assets: "assets/fonts/Roboto-Regular.ttf
    }
}
