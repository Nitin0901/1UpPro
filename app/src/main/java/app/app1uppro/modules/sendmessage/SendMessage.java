package app.app1uppro.modules.sendmessage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.Toast;

import javax.inject.Inject;

import app.app1uppro.R;
import app.app1uppro.apibase.modelclass.ChangePasswordModel;
import app.app1uppro.baseui.BaseActivity;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.GlobalVariable;
import app.app1uppro.common.Validate_Structure;
import app.app1uppro.di.component.ActivityComponent;
import app.app1uppro.modules.welcomescreen.WelcomeScreen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class SendMessage extends BaseActivity implements ISendMessageView {

    @Inject
    SendMessagePresenter<ISendMessageView> presenter;
    @Inject
    DataManager sessionManager;

    @BindView(R.id.changebackicon)
    ImageView changebackicon;
    @BindView(R.id.currentpass)
    EditText userTo;
    @BindView(R.id.newpass)
    EditText subject;
    @BindView(R.id.confmpass)
    EditText message;
    @BindView(R.id.changepassbtn)
    Button sendmsgbtn;
    String userIdValue="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this));
            presenter.onAttach(this);
        }
        if (getIntent().getStringExtra("user_to_name") != null)
            userTo.setText(getIntent().getStringExtra("user_to_name"));
        if(getIntent().getStringExtra("user_to_id")!=null )
            userIdValue=getIntent().getStringExtra("user_to_id");
        message.setScroller(new Scroller(getApplicationContext()));
        message.setVerticalScrollBarEnabled(true);
        message.setMovementMethod(new ScrollingMovementMethod());
    }//end onCreate

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }

    @Override
    protected void setUp() {
    }

    @Override
    public void updateUi(String msg) {
        Toasty.success(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        finish();
    }

    @SuppressLint("ClickableViewAccessibility")
    @OnClick({R.id.changebackicon, R.id.changepassbtn, R.id.confmpass})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.changebackicon:
                hideKeyboard();
                finish();
                break;
            case R.id.changepassbtn:
                if (isNetworkConnected() && Validate_Structure.validateMessage(userIdValue,
                        userTo, subject, message))
                    presenter.sendMessageMethod(userIdValue, subject.getText().toString(),
                            message.getText().toString());
                break;
            case R.id.confmpass:
                message.setFocusableInTouchMode(true);
                message.requestFocus();
                message.setOnTouchListener(new View.OnTouchListener() {
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        assert imm != null;
                        imm.showSoftInput(message, InputMethodManager.SHOW_FORCED);
                        switch (event.getAction() & MotionEvent.ACTION_MASK) {
                            case MotionEvent.ACTION_UP:
                                message.setFocusableInTouchMode(true);
                                message.requestFocus();
                                v.getParent().requestDisallowInterceptTouchEvent(false);
                                return true;
                        }
                        return false;
                    }
                });
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}//end main class
