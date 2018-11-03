package app.app1uppro.modules.callouts;

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
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CallOutsAdapter extends RecyclerView.Adapter<CallOutsAdapter.CallOutsViewholder> {

    ArrayList<CallOutsModel.DataBean> callOutsList;
    private GetCallOutsPosition getCallOutsPosition;
    CallOutsAdapter(ArrayList<CallOutsModel.DataBean> callOutsList,
                    GetCallOutsPosition getCallOutsPosition) {
        this.callOutsList = callOutsList;
        this.getCallOutsPosition = getCallOutsPosition;
    }

    @Override
    public CallOutsViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.call_outs_list, parent, false);
        return new CallOutsViewholder(v);
    }

    @Override
    public void onBindViewHolder(CallOutsViewholder holder, int position) {

        holder.calloutfrom.setText(String.format("Callout from %s", callOutsList.get(holder.getAdapterPosition()).getCreatorusername()));
        holder.rules.setText(String.format("Rules: %s", callOutsList.get(holder.getAdapterPosition()).getRules()));
        holder.videoorientation.setText(String.format("Video Orientation: %s", callOutsList.get(holder.getAdapterPosition()).getOrientation()));
        holder.challengelength.setText(String.format("Challenge length: %s days", callOutsList.get(holder.getAdapterPosition()).getChallengeLength()));
        holder.videoview.clearThumbnail();
        holder.videoview.loadThumbnail(String.format("https://www.youtube.com/watch?v=%s",
                callOutsList.get(holder.getAdapterPosition()).getVideoEmbedID()));
    }//end bindview holder

    @Override
    public int getItemCount() {
        return callOutsList.size();
    }

    public class CallOutsViewholder extends RecyclerView.ViewHolder {
        @BindView(R.id.calloutfrom)
        TextView calloutfrom;
        @BindView(R.id.rules)
        TextView rules;
        @BindView(R.id.videoorientation)
        TextView videoorientation;
        @BindView(R.id.challengelength)
        TextView challengelength;
        @BindView(R.id.acceptchallengebtn)
        Button acceptchallengebtn;
        @BindView(R.id.rejectchallengebtn)
        Button rejectchallengebtn;
        @BindView(R.id.videoview)
        ThumbnailView videoview;
//        @BindView(R.id.yticon)
//        ImageView yticon;

        public CallOutsViewholder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }//end constructor

        @OnClick({R.id.acceptchallengebtn, R.id.rejectchallengebtn,R.id.videoview})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.acceptchallengebtn:
                    getCallOutsPosition.getPosition(getAdapterPosition(),"1");
                    break;
                case R.id.rejectchallengebtn:
                    getCallOutsPosition.getPosition(getAdapterPosition(),"0");
                    break;
                case R.id.videoview:
                    getCallOutsPosition.getVideoStart(getAdapterPosition());
                    break;
            }
        }
    }

}
