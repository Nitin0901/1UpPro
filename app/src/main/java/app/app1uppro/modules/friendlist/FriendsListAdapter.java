package app.app1uppro.modules.friendlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.codewaves.youtubethumbnailview.ThumbnailView;

import java.util.ArrayList;

import app.app1uppro.R;
import app.app1uppro.apibase.modelclass.CallOutsModel;
import app.app1uppro.apibase.modelclass.FriendsListModel;
import app.app1uppro.modules.callouts.GetCallOutsPosition;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FriendsListAdapter extends RecyclerView.Adapter<FriendsListAdapter.CallOutsViewholder> {

    ArrayList<FriendsListModel.DataBean> friendArrayList;
    GetFriendReqPosition getFriendReqPosition;

    FriendsListAdapter(ArrayList<FriendsListModel.DataBean> friendArrayList,
                       GetFriendReqPosition getFriendReqPosition) {
        this.friendArrayList = friendArrayList;
        this.getFriendReqPosition=getFriendReqPosition;
    }

    @Override
    public CallOutsViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.friends_list_card, parent, false);
        return new CallOutsViewholder(v);
    }

    @Override
    public void onBindViewHolder(CallOutsViewholder holder, int position) {
            holder.name.setText(String.format("Name: %s", friendArrayList.get(holder.getAdapterPosition()).getName()));
            holder.username.setText(String.format("Username: %s", friendArrayList.get(holder.getAdapterPosition()).getUsername()));
            if(friendArrayList.get(holder.getAdapterPosition()).getStatus().equals("0")){
                holder.acceptreqbtn.setVisibility(View.VISIBLE);
                holder.rejectreqbtn.setVisibility(View.VISIBLE);
                holder.viewprofilebtn.setVisibility(View.GONE);
                holder.sendmsgbtn.setVisibility(View.GONE);
            }else {
                holder.acceptreqbtn.setVisibility(View.GONE);
                holder.rejectreqbtn.setVisibility(View.GONE);
                holder.viewprofilebtn.setVisibility(View.VISIBLE);
                holder.sendmsgbtn.setVisibility(View.VISIBLE);
            }
    }//end bindview holder

    @Override
    public int getItemCount() {
        return friendArrayList.size();
    }

    public class CallOutsViewholder extends RecyclerView.ViewHolder {
        @BindView(R.id.nameid)
        TextView name;
        @BindView(R.id.usernameid)
        TextView username;
        @BindView(R.id.acceptreqbtn)
        Button acceptreqbtn;
        @BindView(R.id.rejectreqbtn)
        Button rejectreqbtn;
        @BindView(R.id.viewprofilebtn)
        Button viewprofilebtn;
        @BindView(R.id.sendmsgbtn)
        Button sendmsgbtn;

        public CallOutsViewholder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }//end constructor

        @OnClick({R.id.acceptreqbtn, R.id.rejectreqbtn,R.id.viewprofilebtn,R.id.sendmsgbtn})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.acceptreqbtn:
                    getFriendReqPosition.getFriendReqAccept(getAdapterPosition());
                    break;
                case R.id.rejectreqbtn:
                    getFriendReqPosition.getFriendReqReject(getAdapterPosition());
                    break;
                case R.id.viewprofilebtn:
                    getFriendReqPosition.friendProfileDetail(getAdapterPosition());
                    break;
                case R.id.sendmsgbtn:
                    getFriendReqPosition.sendMessage(getAdapterPosition());
                    break;
            }
        }
    }

}//end main class
