package app.app1uppro.modules.homefragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import app.app1uppro.R;
import app.app1uppro.baseui.BaseFragment;
import app.app1uppro.modules.callouts.CallOuts;
import app.app1uppro.modules.createchallenge.CreateChallenges;
import app.app1uppro.modules.mainactivity.HomeTitleInterface;

public class HomeFragment extends BaseFragment {
    BottomNavigationViewEx bottomNavigationViewEx;
    HomeTitleInterface homeTitleInterface;

    public HomeFragment() { }

    @SuppressLint("ValidFragment")
    public HomeFragment(HomeTitleInterface homeTitleInterface) {
        this.homeTitleInterface = homeTitleInterface;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        homeTitleInterface.titleHomeName(getString(R.string.home));
        assert getFragmentManager() != null;
        getFragmentManager().beginTransaction().replace(R.id.frameLayouthome, new BottomHomeFragment()).commit();
        bottomNavigationViewEx = view.findViewById(R.id.navigation);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(true);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationViewEx);
        bottomNavigationViewEx.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        return view;
    }//end onCreateView

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.homebottom:
                homeTitleInterface.titleHomeName(getString(R.string.home));
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.frameLayouthome,
                        new BottomHomeFragment()).commit();
                break;
            case R.id.creatbottom:
                homeTitleInterface.titleHomeName(getString(R.string.create_challenge));
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.frameLayouthome,
                        new CreateChallenges()).commit();
                break;
            case R.id.calloutsbottom:
                homeTitleInterface.titleHomeName(getString(R.string.callouts));
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.frameLayouthome,
                        new CallOuts()).commit();
                break;
        }

        return true;
    };

    @Override
    protected void setUp(View view) { }

}//end main class
