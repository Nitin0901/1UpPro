package app.app1uppro.modules.mainactivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import app.app1uppro.R;
import app.app1uppro.baseui.BaseActivity;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.GlobalVariable;
import app.app1uppro.di.component.ActivityComponent;
import app.app1uppro.modules.addfriend.AddFriend;
import app.app1uppro.modules.playweek.PlayWeekFragment;
import app.app1uppro.modules.aboutus.AboutUsFragment;
import app.app1uppro.modules.following.FollowingFragment;
import app.app1uppro.modules.friendlist.FriendsListFragment;
import app.app1uppro.modules.homefragment.HomeFragment;
import app.app1uppro.modules.message.MessageFragment;
import app.app1uppro.modules.pastvideos.PastVideosFragment;
import app.app1uppro.modules.profilefragment.ProfileFragment;
import app.app1uppro.modules.settings.SettingsFragment;
import app.app1uppro.modules.termscondition.TermsFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener, UpdateNavigationInterface, HomeTitleInterface {

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.toolbr_firstImage)
    ImageView toolbar_firstImage;
    @BindView(R.id.toolbr_secondImage)
    ImageView toolbr_secondImage;
    CircleImageView userimage;
    TextView username;
    int closeStatus = 0;
    boolean doubleBackToExitPressedOnce = false;
    @BindView(R.id.maincontainerid)
    FrameLayout frameLayout;
    @Inject
    DataManager sessionManager;
    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this));
        }
        unbinder = ButterKnife.bind(this);
        hideKeyboard();
        //Toolbar and drawer part
        toolbar_part();
        //Navigation Part
        navigation_part();
    }//end onCreate

    private void navigation_part() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerview = navigationView.getHeaderView(0);
        username = headerview.findViewById(R.id.usernameid);
        userimage = headerview.findViewById(R.id.userimageid);
        username.setOnClickListener(this);
        userimage.setOnClickListener(this);
        navigationheader();
        toolbar_title.setText(getString(R.string.home));
        getSupportFragmentManager().beginTransaction().replace(R.id.maincontainerid, new HomeFragment(this)).commit();
    }//end navigation part

    private void navigationheader() {
        if (!sessionManager.getSharedpref(GlobalVariable.UserImage).isEmpty())
            Glide.with(this).load(sessionManager.getSharedpref(GlobalVariable.UserImage))
                    .into(userimage);
        else
            Glide.with(this).load(R.drawable.user_dummy).into(userimage);

        if (!sessionManager.getSharedpref(GlobalVariable.UserName).isEmpty()) {
            username.setVisibility(View.VISIBLE);
            username.setText(sessionManager.getSharedpref(GlobalVariable.UserName));
        } else username.setVisibility(View.INVISIBLE);
    }//end navigation header

    private void toolbar_part() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.navigationicon);

        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

    }//end toolbar part

    @Override
    protected void setUp() { }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (closeStatus == 1) {
            closeStatus = 0;
            frameLayout.removeAllViews();
            toolbar_title.setText(getString(R.string.home));
            getSupportFragmentManager().beginTransaction().replace(R.id.maincontainerid,
                    new HomeFragment(this)).commit();
        } else if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
        } else {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }//end onBackPressed

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        hideKeyboard();
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_home:
                closeStatus = 0;
                toolbar_title.setText(getString(R.string.home));
                toolbr_secondImage.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().replace(R.id.maincontainerid, new HomeFragment(this)).commit();
                break;
            case R.id.nav_profile:
                closeStatus = 1;
                toolbar_title.setText(getString(R.string.profile));
                toolbr_secondImage.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().replace(R.id.maincontainerid, new ProfileFragment(this)).commit();
                break;
            case R.id.nav_pastvideos:
                closeStatus = 1;
                toolbar_title.setText(getString(R.string.past_videos));
                toolbr_secondImage.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().replace(R.id.maincontainerid, new PastVideosFragment()).commit();
                break;
            case R.id.nav_following:
                closeStatus = 1;
                toolbar_title.setText(getString(R.string.following));
                toolbr_secondImage.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().replace(R.id.maincontainerid, new FollowingFragment()).commit();
                break;
            case R.id.nav_friend:
                closeStatus = 1;
                toolbar_title.setText(getString(R.string.friend));
                toolbr_secondImage.setVisibility(View.VISIBLE);
                toolbr_secondImage.setImageResource(R.drawable.addicon);
                toolbr_secondImage.setOnClickListener(this);
                getSupportFragmentManager().beginTransaction().replace(R.id.maincontainerid, new FriendsListFragment()).commit();
                break;
            case R.id.nav_message:
                closeStatus = 1;
                toolbar_title.setText(getString(R.string.message));
                toolbr_secondImage.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().replace(R.id.maincontainerid, new MessageFragment()).commit();
                break;
            case R.id.nav_aboutus:
                closeStatus = 1;
                toolbar_title.setText(getString(R.string.about_us));
                toolbr_secondImage.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().replace(R.id.maincontainerid, new AboutUsFragment()).commit();
                break;
            case R.id.nav_terms:
                closeStatus = 1;
                toolbar_title.setText(getString(R.string.help_terms_conditions));
                toolbr_secondImage.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().replace(R.id.maincontainerid, new TermsFragment()).commit();
                break;
            case R.id.nav_playweek:
                closeStatus = 1;
                toolbar_title.setText(getString(R.string.play_of_the_week));
                toolbr_secondImage.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().replace(R.id.maincontainerid, new PlayWeekFragment()).commit();
                break;
            case R.id.nav_setting:
                closeStatus = 1;
                toolbar_title.setText(getString(R.string.settings));
                toolbr_secondImage.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().replace(R.id.maincontainerid, new SettingsFragment()).commit();
                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.usernameid:
                closeStatus = 1;
                toolbar_title.setText(getString(R.string.profile));
                getSupportFragmentManager().beginTransaction().replace(R.id.maincontainerid, new ProfileFragment(this)).commit();
                break;
            case R.id.userimageid:
                closeStatus = 1;
                toolbar_title.setText(getString(R.string.profile));
                getSupportFragmentManager().beginTransaction().replace(R.id.maincontainerid, new ProfileFragment(this)).commit();
                break;
            case R.id.toolbr_secondImage:
                Intent addfriendintent=new Intent(getApplicationContext(), AddFriend.class);
                startActivity(addfriendintent);
                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void updateNavigation() {
        navigationheader();
    }

    @Override
    public void titleHomeName(String name) {
        toolbar_title.setText(name);
    }
}//end main class
