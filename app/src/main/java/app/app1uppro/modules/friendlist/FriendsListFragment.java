package app.app1uppro.modules.friendlist;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import javax.inject.Inject;

import app.app1uppro.R;
import app.app1uppro.apibase.modelclass.FriendsListModel;
import app.app1uppro.baseui.BaseFragment;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.GlobalVariable;
import app.app1uppro.modules.friendprofile.FriendProfileStatus;
import app.app1uppro.modules.sendmessage.SendMessage;
import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class FriendsListFragment extends BaseFragment implements
        IFriendsListView, RadioGroup.OnCheckedChangeListener,
        GetFriendReqPosition, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    FriendsListPresenter<IFriendsListView> presenter;
    @Inject
    DataManager sessionManager;
    @BindView(R.id.friendslisting)
    RecyclerView friendslisting;
    LinearLayoutManager linearLayoutManager;
    FriendsListAdapter friendsListAdapter;
    @BindView(R.id.no_dataLayout)
    ImageView noDataLayout;
    ArrayList<FriendsListModel.DataBean> friendArrayList;
    @BindView(R.id.radiogroupid)
    RadioGroup radiogroup;
    @BindView(R.id.friendsbtnid)
    RadioButton friendradiobtn;
    @BindView(R.id.friendreqbtnid)
    RadioButton friendreqradiobtn;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    String tabValue;
    int pos = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend, container, false);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this, view));
        presenter.onAttach(this);
        initialize();
        return view;
    }//end onCreateView

    private void initialize() {
        friendArrayList = new ArrayList<>();
        hideKeyboard();
        tabValue = "1";
        friendradiobtn.setChecked(true);
        swipeRefreshLayout.setOnRefreshListener(this);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        friendslisting.setLayoutManager(linearLayoutManager);
        friendsListAdapter = new FriendsListAdapter(friendArrayList, this);
        friendslisting.setAdapter(friendsListAdapter);
        radiogroup.setOnCheckedChangeListener(this);
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
    public void FriendListResponse(ArrayList<FriendsListModel.DataBean> dataBeans, String type) {
        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
        if (dataBeans.size() == 0) {
            noDataLayout.setVisibility(View.VISIBLE);
            friendslisting.setVisibility(View.GONE);
            friendsListAdapter.notifyDataSetChanged();
        } else {
            friendArrayList.clear();
            friendArrayList.addAll(dataBeans);
            friendslisting.setVisibility(View.VISIBLE);
            noDataLayout.setVisibility(View.GONE);
            friendsListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void noSuccessMsg(String msg) {
        swipeRefreshLayout.setRefreshing(false);
        noDataLayout.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void fndReqResponse(String msg) {
        Toasty.success(getActivity(), msg, Toast.LENGTH_SHORT).show();
        if (sessionManager.checkConnectionActivity()) {
            if (tabValue.equals("1")) {
                friendradiobtn.setChecked(true);
                presenter.friendsListMethod(tabValue);
            } else {
                friendreqradiobtn.setChecked(true);
                presenter.friendsRequestListMethod(tabValue);
            }
        }
        if (pos > 0)
            friendslisting.scrollToPosition(pos);
        else friendslisting.scrollToPosition(0);

    }

    @Override
    public void onResume() {
        super.onResume();
        hideKeyboard();
        if (sessionManager.checkConnectionActivity()) {
            if (tabValue.equals("1")) {
                friendradiobtn.setChecked(true);
                presenter.friendsListMethod(tabValue);
            } else {
                friendreqradiobtn.setChecked(true);
                presenter.friendsRequestListMethod(tabValue);
            }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton rb = group.findViewById(checkedId);
        if (rb != null) {
            switch (rb.getId()) {
                case R.id.friendsbtnid:
                    friendradiobtn.setTextColor(getResources().getColor(R.color.textview_white_color));
                    friendreqradiobtn.setTextColor(getResources().getColor(R.color.toolbar_color));
                    tabValue = "1";
                    friendsMethod(tabValue);
                    break;
                case R.id.friendreqbtnid:
                    friendradiobtn.setTextColor(getResources().getColor(R.color.toolbar_color));
                    friendreqradiobtn.setTextColor(getResources().getColor(R.color.textview_white_color));
                    tabValue = "0";
                    friendsRequestMethod(tabValue);
                    break;
            }
        }
    }

    private void friendsMethod(String tabValue) {
        if (sessionManager.checkConnectionActivity())
            presenter.friendsListMethod(tabValue);
    }
    private void friendsRequestMethod(String tabValue) {
        if (sessionManager.checkConnectionActivity())
            presenter.friendsRequestListMethod(tabValue);
    }

    @Override
    public void getFriendReqAccept(int position) {
        pos = position;
        confirmAlertBox("Accept Request", "Are you sure you want to Accept this Friend Request?", "1");
    }

    @Override
    public void getFriendReqReject(int position) {
        pos = position;
        confirmAlertBox("Wuss Out", "Are you sure you want to Wuss Out this Friend Request?", "2");
    }

    @Override
    public void friendProfileDetail(int position) {
        Intent profileIntent=new Intent(getActivity(), FriendProfileStatus.class);
        profileIntent.putExtra("profile_id",friendArrayList.get(position).getUser_id());
        startActivity(profileIntent);
    }

    @Override
    public void sendMessage(int position) {
        Intent msgIntent=new Intent(getActivity(), SendMessage.class);
        msgIntent.putExtra("user_to_id",friendArrayList.get(position).getUser_id());
        msgIntent.putExtra("user_to_name",friendArrayList.get(position).getName());
        startActivity(msgIntent);
    }

    void confirmAlertBox(String title, String msg, String type) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(msg)
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (sessionManager.checkConnectionActivity()) {
                            presenter.fndReqAcceptReject(friendArrayList.get(pos).getId(), type);
                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setTitle(title)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        if (sessionManager.checkConnectionActivity()) {
            if (sessionManager.checkConnectionActivity()) {
                if (tabValue.equals("1")) {
                    friendradiobtn.setChecked(true);
                    presenter.friendsListMethod(tabValue);
                } else {
                    friendreqradiobtn.setChecked(true);
                    presenter.friendsRequestListMethod(tabValue);
                }
            }
        } else {
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}//end main class
