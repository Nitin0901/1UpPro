package app.app1uppro.modules.welcomescreen;

import javax.inject.Inject;

import app.app1uppro.baseui.BasePresenter;
import app.app1uppro.common.DataManager;
import io.reactivex.disposables.CompositeDisposable;

public class WelcomePresenter<V extends IWelcomeView> extends BasePresenter<V> implements IWelcomePresentor<V> {

    @Inject
    public WelcomePresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }
}
