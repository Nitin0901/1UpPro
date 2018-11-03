package app.app1uppro.di.component;


import app.app1uppro.baseui.BaseActivity;
import app.app1uppro.di.PerActivity;
import app.app1uppro.di.module.ActivityModule;
import app.app1uppro.modules.acceptvideochallenge.UploadAcceptVideo;
import app.app1uppro.modules.addfriend.AddFriend;
import app.app1uppro.modules.callouts.CallOuts;
import app.app1uppro.modules.changepassword.ChangePassActivity;
import app.app1uppro.modules.chatscreen.ChatActivity;
import app.app1uppro.modules.createchallenge.CreateChallenges;
import app.app1uppro.modules.following.FollowingFragment;
import app.app1uppro.modules.following.UserFollowCategory;
import app.app1uppro.modules.forgetpassword.ForgotPasswordFrag;
import app.app1uppro.modules.friendlist.FriendsListFragment;
import app.app1uppro.modules.friendprofile.FriendProfileStatus;
import app.app1uppro.modules.homefragment.BottomHomeFragment;
import app.app1uppro.modules.login.LoginFragment;
import app.app1uppro.modules.mainactivity.MainActivity;
import app.app1uppro.modules.message.MessageFragment;
import app.app1uppro.modules.pastvideos.PastVideoSelectCategory;
import app.app1uppro.modules.pastvideos.PastVideosFragment;
import app.app1uppro.modules.profilefragment.ProfileFragment;
import app.app1uppro.modules.sendmessage.SendMessage;
import app.app1uppro.modules.settings.SettingsFragment;
import app.app1uppro.modules.signup.SignUpFragment;
import app.app1uppro.modules.splash.SplashActivity;
import app.app1uppro.modules.uploadvideo.GoogleLoginUploadVideo;
import app.app1uppro.modules.uploadvideo.UploadChallengeVideo;
import app.app1uppro.modules.videocategory.VideoCategoryFrag;
import app.app1uppro.modules.videocategoryhome.VideoCategoryHome;
import app.app1uppro.modules.videosubcategory.VideoSubCatFrag;
import app.app1uppro.modules.videosubcategory.VideoSubCategory;
import app.app1uppro.modules.welcomescreen.WelcomeScreen;
import app.app1uppro.modules.zoomimage.ZoomImage;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SplashActivity splashActivity);
    void inject(WelcomeScreen welcomeScreen);
    void inject(LoginFragment loginFragment);
    void inject(SignUpFragment signUpFragment);
    void inject(ForgotPasswordFrag forgotPasswordFrag);
    void inject(MainActivity mainActivity);
    void inject(SettingsFragment settingsFragment);
    void inject(ProfileFragment profileFragment);
    void inject(ZoomImage zoomImage);
    void inject(ChangePassActivity changePassActivity);
    void inject(BaseActivity baseActivity);
    void inject(CreateChallenges createChallenges);
    void inject(VideoSubCategory videoSubCategory);
    void inject(VideoSubCatFrag videoSubCatFrag);
    void inject(VideoCategoryHome videoCategoryHome);
    void inject(VideoCategoryFrag videoCategoryFrag);
    void inject(GoogleLoginUploadVideo googleLoginUploadVideo);
    void inject(UploadChallengeVideo uploadChallengeVideo);
    void inject(BottomHomeFragment bottomHomeFragment);
    void inject(CallOuts callOuts);
    void inject(FollowingFragment followingFragment);
    void inject(UserFollowCategory userFollowCategory);
    void inject(PastVideoSelectCategory pastVideoSelectCategory);
    void inject(PastVideosFragment pastVideosFragment);
    void inject(UploadAcceptVideo uploadAcceptVideo);
    void inject(AddFriend addFriend);
    void inject(FriendsListFragment friendsListFragment);
    void inject(FriendProfileStatus friendProfileStatus);
    void inject(SendMessage sendMessage);
    void inject(MessageFragment messageFragment);
    void inject(ChatActivity chatActivity);
}
