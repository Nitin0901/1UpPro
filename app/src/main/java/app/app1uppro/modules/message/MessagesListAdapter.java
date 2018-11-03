package app.app1uppro.modules.message;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import app.app1uppro.R;
import app.app1uppro.apibase.modelclass.FriendsListModel;
import app.app1uppro.apibase.modelclass.MessageListModel;
import app.app1uppro.modules.friendlist.GetFriendReqPosition;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MessagesListAdapter extends RecyclerView.Adapter<MessagesListAdapter.CallOutsViewholder> {

    ArrayList<MessageListModel.DataBean> messageArrayList;
    GetMessageListPosition getMessageListPosition;
    private Context context;

    MessagesListAdapter(ArrayList<MessageListModel.DataBean> messageArrayList,
                        GetMessageListPosition getMessageListPosition) {
        this.messageArrayList = messageArrayList;
        this.getMessageListPosition = getMessageListPosition;
    }

    @Override
    public CallOutsViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_list_card, parent, false);
        context = parent.getContext();
        return new CallOutsViewholder(v);
    }

    @Override
    public void onBindViewHolder(CallOutsViewholder holder, int position) {
        if (messageArrayList.get(holder.getAdapterPosition()).getSender_profile_image().isEmpty())
            Glide.with(context).load(R.drawable.user_dummy).into(holder.circleImageView);
        else
            Glide.with(context).load(messageArrayList.get(holder.getAdapterPosition()).getSender_profile_image()).into(holder.circleImageView);

        holder.userName.setText(messageArrayList.get(position).getSender_name());
        if (messageArrayList.get(holder.getAdapterPosition()).getRead_status().equals("0"))
            holder.unreadid.setText(R.string.unread_msg);
        else holder.unreadid.setVisibility(View.GONE);
        if (!messageArrayList.get(holder.getAdapterPosition()).getCreated_at().isEmpty())
            holder.messageDate.setText(messageArrayList.get(holder.getAdapterPosition()).getCreated_at());

        holder.messagetxt.setText(messageArrayList.get(holder.getAdapterPosition()).getSubject());

    }//end bindview holder

    @Override
    public int getItemCount() {
        return messageArrayList.size();
    }

    public class CallOutsViewholder extends RecyclerView.ViewHolder {
        @BindView(R.id.userImage)
        CircleImageView circleImageView;
        @BindView(R.id.userName)
        TextView userName;
        @BindView(R.id.messagetxt)
        TextView messagetxt;
        @BindView(R.id.messageDate)
        TextView messageDate;
        @BindView(R.id.unreadid)
        TextView unreadid;
        @BindView(R.id.messageLayout)
        LinearLayout messageLayout;

        public CallOutsViewholder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }//end constructor

        @OnClick(R.id.messageLayout)
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.messageLayout:
                    getMessageListPosition.getPositonFromList(getAdapterPosition());
                    break;
            }//end switch
        }//end onClick
    }//end viewHolder

}//end main class
