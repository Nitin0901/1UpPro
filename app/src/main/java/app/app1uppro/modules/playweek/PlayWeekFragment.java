package app.app1uppro.modules.playweek;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.app1uppro.R;
import app.app1uppro.baseui.BaseFragment;

public class PlayWeekFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_play_week, container, false);
    }//end onCreate

    @Override
    protected void setUp(View view) { }

}//end main class
