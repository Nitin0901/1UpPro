package app.app1uppro.modules.chatscreen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import app.app1uppro.R;
import app.app1uppro.apibase.modelclass.FriendsListModel;
import app.app1uppro.apibase.modelclass.MessageDetailsModel;
import app.app1uppro.modules.friendlist.GetFriendReqPosition;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.CallOutsViewholder> {

    ArrayList<MessageDetailsModel.DataBean.ReplyBean> messageList;
    private Context context;

    ChatAdapter(ArrayList<MessageDetailsModel.DataBean.ReplyBean> messageList) {
        this.messageList = messageList;
    }

    @Override
    public CallOutsViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_detail_card, parent, false);
        context = parent.getContext();
        return new CallOutsViewholder(v);
    }

    @Override
    public void onBindViewHolder(CallOutsViewholder holder, int position) {
            if (messageList.get(holder.getAdapterPosition()).getSender_profile_image().isEmpty())
                Glide.with(context).load(R.drawable.user_dummy).into(holder.userImage);
            else
                Glide.with(context).load(messageList.get(holder.getAdapterPosition()).getSender_profile_image()).into(holder.userImage);

            holder.userName.setText(messageList.get(holder.getAdapterPosition()).getSender_name());
            if (!messageList.get(holder.getAdapterPosition()).getCreated_at().isEmpty())
                holder.messageDate.setText(messageList.get(holder.getAdapterPosition()).getCreated_at());
            holder.messagetxt.setText(messageList.get(holder.getAdapterPosition()).getMessage_reply());

    }//end bindview holder

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class CallOutsViewholder extends RecyclerView.ViewHolder {
        @BindView(R.id.userImage)
        CircleImageView userImage;
        @BindView(R.id.userName)
        TextView userName;
        @BindView(R.id.messagetxt)
        TextView messagetxt;
        @BindView(R.id.messageDate)
        TextView messageDate;

        public CallOutsViewholder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }//end constructor
    }//end viewholder class
}//end main class
