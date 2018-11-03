package app.app1uppro.modules.forgetpassword;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import javax.inject.Inject;

import app.app1uppro.R;
import app.app1uppro.baseui.BaseFragment;
import app.app1uppro.di.component.ActivityComponent;
import app.app1uppro.modules.login.LoginFragment;
import app.app1uppro.modules.welcomescreen.WelcomeScreen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgotPasswordFrag extends BaseFragment implements IForgotPasswordView {

    @Inject
    ForgotPasswordPresentor<IForgotPasswordView> presenter;
    @BindView(R.id.forgetback)
    ImageView forgetback;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.resetbtn)
    Button resetbtn;
    @BindView(R.id.forgetlayout)
    ConstraintLayout forgetlayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forgot_password, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            presenter.onAttach(this);
        }
        hideKeyboard();
        WelcomeScreen.frontBackCount=0;
        return view;
    }//end onCreateView

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDetach();
    }

    @Override
    protected void setUp(View view) { }

    @Override
    public void updateResponse(String message) {
        popMsg(message);
    }

    @OnClick({R.id.forgetback, R.id.resetbtn, R.id.forgetlayout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.forgetlayout:
                hideKeyboard();
                break;
            case R.id.forgetback:
                getFragmentManager().beginTransaction().replace(R.id.frameLayout, new LoginFragment()).commit();
                break;
            case R.id.resetbtn:
                hideKeyboard();
                presenter.onForgotPassword(username, email);
                break;
        }
    }

    void popMsg(String message) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        getFragmentManager().beginTransaction().replace(R.id.frameLayout, new LoginFragment()).commit();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}//end class
