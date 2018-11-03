package app.app1uppro.modules.message;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import app.app1uppro.R;
import app.app1uppro.apibase.modelclass.FriendsListModel;
import app.app1uppro.apibase.modelclass.MessageListModel;
import app.app1uppro.baseui.BaseFragment;
import app.app1uppro.common.DataManager;
import app.app1uppro.modules.chatscreen.ChatActivity;
import app.app1uppro.modules.friendlist.FriendsListAdapter;
import app.app1uppro.modules.friendlist.FriendsListPresenter;
import app.app1uppro.modules.friendlist.IFriendsListView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageFragment extends BaseFragment implements IMessagesListView,
        SwipeRefreshLayout.OnRefreshListener,GetMessageListPosition {

    @Inject
    MessagesListPresenter<IMessagesListView> presenter;
    @Inject
    DataManager sessionManager;

    @BindView(R.id.messageRecylerview)
    RecyclerView messageRecylerview;
    @BindView(R.id.no_dataLayout)
    ImageView noDataLayout;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    LinearLayoutManager linearLayoutManager;
    MessagesListAdapter messagesListAdapter;
    ArrayList<MessageListModel.DataBean> messageArrayList;
    String str_date="";
    int position=0;
    Date date;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this, view));
        presenter.onAttach(this);
        initialize();
        return view;
    }//end onCreateView

    private void initialize() {
        messageArrayList = new ArrayList<>();
        hideKeyboard();
        swipeRefreshLayout.setOnRefreshListener(this);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        messageRecylerview.setLayoutManager(linearLayoutManager);
        messagesListAdapter = new MessagesListAdapter(messageArrayList, this);
        messageRecylerview.setAdapter(messagesListAdapter);
        if (sessionManager.checkConnectionActivity())
            presenter.messageListMethod();
    }//end initialize


    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }

    @Override
    protected void setUp(View view) {

    }

    @Override
    public void messageListResponse(ArrayList<MessageListModel.DataBean> dataBeans) {
        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
        if (dataBeans.size() == 0) {
            noDataLayout.setVisibility(View.VISIBLE);
            messageRecylerview.setVisibility(View.GONE);
            messagesListAdapter.notifyDataSetChanged();
        } else {
            messageArrayList.clear();
            for (int i = 0; i < dataBeans.size(); i++) {
                str_date=dataBeans.get(i).getCreated_at();
                try {
                    date=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss",
                            Locale.getDefault()).parse(str_date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                dataBeans.get(i).setCreated_at(getTimeAgo(date.getTime()));
            }
            messageArrayList.addAll(dataBeans);
            messageRecylerview.setVisibility(View.VISIBLE);
            noDataLayout.setVisibility(View.GONE);
            messagesListAdapter.notifyDataSetChanged();
            messageRecylerview.scrollToPosition(position);

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
    public void noSuccessMsg(String msg) {
        swipeRefreshLayout.setRefreshing(false);
        noDataLayout.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();
        hideKeyboard();
        if (sessionManager.checkConnectionActivity())
            presenter.messageListMethod();
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        if (sessionManager.checkConnectionActivity())
            presenter.messageListMethod();
        else
            swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void getPositonFromList(int position) {
        this.position=position;
        Intent chat_intent=new Intent(getActivity(), ChatActivity.class);
        chat_intent.putExtra("message_id",messageArrayList.get(position).getId());
        chat_intent.putExtra("user_to",messageArrayList.get(position).getUser_from());
        chat_intent.putExtra("message_position",String.valueOf(position));
        startActivity(chat_intent);
    }
}//end main class
