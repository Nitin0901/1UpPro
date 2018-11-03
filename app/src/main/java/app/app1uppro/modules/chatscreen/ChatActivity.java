package app.app1uppro.modules.chatscreen;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import app.app1uppro.R;
import app.app1uppro.apibase.modelclass.MessageDetailsModel;
import app.app1uppro.apibase.modelclass.MessageListModel;
import app.app1uppro.apibase.modelclass.SucessModel;
import app.app1uppro.apibase.modelclass.VideoCategoryModel;
import app.app1uppro.baseui.BaseActivity;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.GlobalVariable;
import app.app1uppro.common.Validate_Structure;
import app.app1uppro.di.component.ActivityComponent;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class ChatActivity extends BaseActivity implements IChatView {

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.toolbr_firstImage)
    ImageView toolbar_firstImage;
    @BindView(R.id.messageRecyclerView)
    RecyclerView messageRecyclerView;
    @BindView(R.id.editTextMessage)
    EditText editTextMessage;
    @BindView(R.id.sendMessage)
    TextView sendMessage;
    @BindView(R.id.sendlayoutid)
    LinearLayout sendlayout;
    @BindView(R.id.subjectid)
    TextView subject;
    @Inject
    DataManager dataManager;
    @Inject
    DataManager sessionManager;
    ChatAdapter chatAdapter;
    ArrayList<MessageDetailsModel.DataBean.ReplyBean> messageDetailList;
    ArrayList<SucessModel> chatMessages;
    LinearLayoutManager chatLayoutManager;
    @Inject
    ChatPresenter<IChatView> presenter;
    String message_id = "", user_to = "", str_date = "";
    int msgPosition=0;
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this));
            presenter.onAttach(this);
        }
        hideKeyboard();
        intializer();
    }//end onCreate


    private void intializer() {
        messageDetailList = new ArrayList<>();
        chatMessages = new ArrayList<>();
        toolbar_title.setText(R.string.message_detail);
        toolbar_firstImage.setImageDrawable(getResources().getDrawable(R.drawable.backwhiteicon));

        if (getIntent().getStringExtra("message_id") != null)
            message_id = getIntent().getStringExtra("message_id");
        if (getIntent().getStringExtra("message_position") != null)
          //  msgPosition = Integer.parseInt(getIntent().getStringExtra("message_position"));
        if (getIntent().getStringExtra("user_to") != null)
            user_to = getIntent().getStringExtra("user_to");

        chatAdapter = new ChatAdapter(messageDetailList);
        chatLayoutManager = new LinearLayoutManager(this);
        messageRecyclerView.setLayoutManager(chatLayoutManager);
        messageRecyclerView.setAdapter(chatAdapter);
        if (sessionManager.checkConnectionActivity())
            presenter.getMessageDetails(message_id);
    }//end initializer

    @OnClick({R.id.toolbr_firstImage, R.id.sendMessage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbr_firstImage:
                hideKeyboard();
                finish();
                break;
            case R.id.sendMessage:
                if (Validate_Structure.validateChatScreen(editTextMessage) &&
                        sessionManager.checkConnectionActivity()) {
                    hideKeyboard();
                    presenter.sendReplyMethod(message_id, user_to, editTextMessage.getText().toString());
                }
                break;
        }//end switch
    }//end onClick

    @Override
    public void onBackPressed() {  finish(); }

    @Override
    protected void setUp() { }

    @Override
    public void updateStatusChangeResponse() {
        if (sessionManager.checkConnectionActivity())
            presenter.getMessageDetails(message_id);
    }

    @Override
    public void updateDetailsResponse(ArrayList<MessageDetailsModel.DataBean> list) {
        if (list.size() > 0) {
            for (int i = 0; i < list.get(msgPosition).getReply().size(); i++) {
                str_date = list.get(msgPosition).getReply().get(i).getCreated_at();
                try {
                    date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss",
                            Locale.getDefault()).parse(str_date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                list.get(msgPosition).getReply().get(i).setCreated_at(getTimeAgo(date.getTime()));
            }
            subject.setText(list.get(msgPosition).getSubject());
            if (list.get(msgPosition).getRead_status().equals("0") &&
                    sessionManager.checkConnectionActivity())
                presenter.chaneMessageReadStatus(message_id);
            messageDetailList.clear();
            messageDetailList.addAll(list.get(msgPosition).getReply());
            chatAdapter.notifyDataSetChanged();
            messageRecyclerView.smoothScrollToPosition(chatAdapter.getItemCount());
        }
    }

    public  String getTimeAgo(long time) {
        final int SECOND_MILLIS = 1000;
        final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
        final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
        final int DAY_MILLIS = 24 * HOUR_MILLIS;
        if (time < 1000000000000L) {
            time *= 1000;
        }
        long now = System.currentTimeMillis();
        if (time > now || time <= 0) {
            return getString(R.string.time_just_now);
        }
        final long diff = now - time;
        if (diff < MINUTE_MILLIS) {
            return getString(R.string.time_just_now);
        } else if (diff < 2 * MINUTE_MILLIS) {
            return getString(R.string.time_minute_ago);
        } else if (diff < 50 * MINUTE_MILLIS) {
            return diff / MINUTE_MILLIS + " "+getString(R.string.time_min_ago);
        } else if (diff < 90 * MINUTE_MILLIS) {
            return getString(R.string.time_an_hr_ago);
        } else if (diff < 24 * HOUR_MILLIS) {
            return diff / HOUR_MILLIS + " "+getString(R.string.time_hr_ago);
        } else if (diff < 48 * HOUR_MILLIS) {
            return getString(R.string.time_yesterday);
        } else {
            return diff / DAY_MILLIS +" "+getString(R.string.time_day_ago);}
    }

    @Override
    public void updateReplyResponse(String msg) {
        editTextMessage.setText("");
        Toasty.success(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        messageDetailList.clear();
        chatLayoutManager.setStackFromEnd(true);
        chatLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        chatLayoutManager.setSmoothScrollbarEnabled(true);
        if (sessionManager.checkConnectionActivity())
            presenter.getMessageDetails(message_id);
    }//end updateReplyResponse

}//end main class
