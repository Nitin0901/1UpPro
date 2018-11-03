package app.app1uppro.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import app.app1uppro.di.ActivityContext;
import app.app1uppro.di.PerActivity;
import app.app1uppro.modules.forgetpassword.ForgotPasswordPresentor;
import app.app1uppro.modules.forgetpassword.IForgotPasswordPresentor;
import app.app1uppro.modules.forgetpassword.IForgotPasswordView;
import app.app1uppro.modules.login.ILoginPresentor;
import app.app1uppro.modules.login.ILoginView;
import app.app1uppro.modules.login.LoginPresenter;
import app.app1uppro.modules.signup.ISignupPresentor;
import app.app1uppro.modules.signup.ISignupView;
import app.app1uppro.modules.signup.SignupPresenter;
import app.app1uppro.modules.welcomescreen.IWelcomePresentor;
import app.app1uppro.modules.welcomescreen.IWelcomeView;
import app.app1uppro.modules.welcomescreen.WelcomePresenter;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ActivityModule {

    private Activity mActivity;
    public ActivityModule(Activity activity) {
        mActivity = activity;
    }
    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }
    @Provides
    Activity provideActivity() {
        return mActivity;
    }
    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @PerActivity
    ILoginPresentor<ILoginView> provideLoginPresenter(LoginPresenter<ILoginView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    ISignupPresentor<ISignupView> provideSignUpPresenter(SignupPresenter<ISignupView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    IWelcomePresentor<IWelcomeView> provideWelcomePresenter(WelcomePresenter<IWelcomeView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    IForgotPasswordPresentor<IForgotPasswordView> provideForgotPassPresenter(
            ForgotPasswordPresentor<IForgotPasswordView> presenter) {
        return presenter;
    }

//
//
//    @Provides
//    @PerActivity
//    ISocialDataPresentor<ISocialDataView> provideSocialPresenter(
//            SocialDataPresentor<ISocialDataView> presenter) {
//        return presenter;
//    }
//
//

//
//
//    @Provides
//    @PerActivity
//    IMyDetailPresenter<IMyDetailView> provideMyDetailPassPresenter(
//            ProfilePresenter<IMyDetailView> presenter) {
//        return presenter;
//    }
//
//
//    @Provides
//    @PerActivity
//    IChangePassPresenter<IChangePassView> provideChangePassPresenter(
//            IChangePassPresenter<IChangePassView> presenter) {
//        return presenter;
//    }
//
//
//    @Provides
//    @PerActivity
//    ISettingsPresenter<ISettings> provideMyAccountPresenter(
//            ISettingsPresenter<ISettings> presenter) {
//        return presenter;
//    }
//
//    @Provides
//    @PerActivity
//    IAllCategoryPresenter<IAllCateoryView> provideAllCayegoryPresenter(
//            IAllCategoryPresenter<IAllCateoryView> presenter) {
//        return presenter;
//    }
//
//
//    @Provides
//    @PerActivity
//    IMaleCategoryPresenter<IMaleCategoryView> getPresenter(
//            IMaleCategoryPresenter<IMaleCategoryView> presenter) {
//        return presenter;
//    }
//
//
//    @Provides
//    @PerActivity
//    IFemaleCategoryPresenter<IFemaleCategoryView> categoryPresenter(
//            IFemaleCategoryPresenter<IFemaleCategoryView> presenter) {
//        return presenter;
//    }
//
//
//    @Provides
//    @PerActivity
//    IProductListPresenter<IProductListView> productPresenter(
//            IProductListPresenter<IProductListView> presenter) {
//        return presenter;
//    }
//
//
//    @Provides
//    @PerActivity
//    IProductDetailPresenter<IProductDetailView> productDetailPresenter(
//            IProductDetailPresenter<IProductDetailView> presenter) {
//        return presenter;
//    }
//
//
//    @Provides
//    @PerActivity
//    IFavouritePresenter<IFavouriteView> favPresenter(
//            IFavouritePresenter<IFavouriteView> presenter) {
//        return presenter;
//    }
//
//
//    @Provides
//    @PerActivity
//    IFilterPresenter<IFilterView> filterPresenter(
//            IFilterPresenter<IFilterView> presenter) {
//        return presenter;
//    }
//
//
//    @Provides
//    @PerActivity
//    ICartPresenter<ICartView> cartPresenter(
//            ICartPresenter<ICartView> presenter) {
//        return presenter;
//    }
//
//
//    @Provides
//    @PerActivity
//    IHomePresenter<IHomeView> homePresenter(
//            IHomePresenter<IHomeView> presenter) {
//        return presenter;
//    }
//
//    @Provides
//    @PerActivity
//    IAddressBookPresenter<IAddressBookView> addressBookPresenter(
//            IAddressBookPresenter<IAddressBookView> presenter) {
//        return presenter;
//    }
//
//
//    @Provides
//    @PerActivity
//    IAddressPresenter<IAddressView> addressPresenter(
//            IAddressPresenter<IAddressView> presenter) {
//        return presenter;
//    }
//
//
//    @Provides
//    @PerActivity
//    ICheckoutPresenter<ICheckoutView> checkoutPresenter(
//            ICheckoutPresenter<ICheckoutView> presenter) {
//        return presenter;
//    }
//
//
//    @Provides
//    @PerActivity
//    IChangeAddressPresenter<IChangeAddressView> changeAddressPresenter(
//            IChangeAddressPresenter<IChangeAddressView> presenter) {
//        return presenter;
//    }
//
//    @Provides
//    @PerActivity
//    IOrderListPresenter<IOrderListView> orderListPresenter(
//            IOrderListPresenter<IOrderListView> presenter) {
//        return presenter;
//    }
//
//    @Provides
//    @PerActivity
//    IOrderPresenter<IOrderView> orderDetailPresenter(
//            IOrderPresenter<IOrderView> presenter) {
//        return presenter;
//    }
//
//    @Provides
//    @PerActivity
//    IAdminEnquiryPresenter<IAdminEnquiryView> adminEnquiryPresenter(
//            IAdminEnquiryPresenter<IAdminEnquiryView> presenter) {
//        return presenter;
//    }
//
//    @Provides
//    @PerActivity
//    IMessageScreenPresenter<IMesasgeScreenView> messageScreenPresenter(
//            IMessageScreenPresenter<IMesasgeScreenView> presenter) {
//        return presenter;
//    }
//
//
//    @Provides
//    @PerActivity
//    ICategoryBrandPresenter<ICategoryBrandView> catBrandPresenter(
//            ICategoryBrandPresenter<ICategoryBrandView> presenter) {
//        return presenter;
//    }
//
//
//    @Provides
//    @PerActivity
//    ISearchPresenter<ISearchView> searchProdPresenter(
//            ISearchPresenter<ISearchView> presenter) {
//        return presenter;
//    }
//
//
//    @Provides
//    @PerActivity
//    IUserMessagePresenter<IUserMessageView> userMessagePresenter(
//            IUserMessagePresenter<IUserMessageView> presenter) {
//        return presenter;
//    }
//
//
//
//    @Provides
//    @PerActivity
//    IChatPresenter<IChatView> chatPresenter(
//            IChatPresenter<IChatView> presenter) {
//        return presenter;
//    }
//
//
//
//    @Provides
//    @PerActivity
//    INotificationPresenter<INotificationView> notificationPresenter(
//            INotificationPresenter<INotificationView> presenter) {
//        return presenter;
//    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }

}
