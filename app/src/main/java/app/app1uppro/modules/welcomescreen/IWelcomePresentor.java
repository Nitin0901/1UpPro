package app.app1uppro.modules.welcomescreen;

import app.app1uppro.baseui.MvpPresenter;
import app.app1uppro.di.PerActivity;

@PerActivity
public interface IWelcomePresentor<V extends IWelcomeView> extends MvpPresenter<V> {

}
