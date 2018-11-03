package app.app1uppro.modules.pastvideos;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.Group;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.codewaves.youtubethumbnailview.ThumbnailView;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;

import app.app1uppro.R;
import app.app1uppro.apibase.modelclass.PastVideosModel;
import app.app1uppro.common.GlobalVariable;
import app.app1uppro.modules.homefragment.GetVideoPosition;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PastVideoAdapter extends RecyclerView.Adapter<PastVideoAdapter.ListViewholder> {

    private ArrayList<PastVideosModel.DataBean> pastVideosList;
    private GetPastVideoPosition getVideoPosition;

    PastVideoAdapter(ArrayList<PastVideosModel.DataBean> pastVideosList,
                     GetPastVideoPosition getVideoPosition) {
        this.pastVideosList = pastVideosList;
        this.getVideoPosition = getVideoPosition;
    }

    @Override
    public ListViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pastvideolist_card, parent, false);
        return new ListViewholder(v);
    }

    @Override
    public void onBindViewHolder(ListViewholder holder, int position) {
        holder.videocount.setText(String.valueOf(holder.getAdapterPosition()+1));
        holder.overallscore.setText(pastVideosList.get(holder.getAdapterPosition()).getVideoScore());
        holder.videoview.clearThumbnail();
        holder.videoview.loadThumbnail(String.format("https://www.youtube.com/watch?v=%s",
                pastVideosList.get(holder.getAdapterPosition()).getVideoEmbedID()));
    }//end bindview holder

    @Override
    public int getItemCount() {
        return pastVideosList.size();
    }

    public class ListViewholder extends RecyclerView.ViewHolder {

        @BindView(R.id.videoview)
        ThumbnailView videoview;
        @BindView(R.id.videocount)
        TextView videocount;
        @BindView(R.id.yticon)
        ImageView yticon;
        @BindView(R.id.overallscore)
        TextView overallscore;
        @BindView(R.id.leftvoteicon)
        TextView leftvoteicon;
        @BindView(R.id.rightvoteicon)
        TextView rightvoteicon;

        public ListViewholder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }//end constructor

        @OnClick({R.id.leftvoteicon, R.id.rightvoteicon,R.id.videoview})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.leftvoteicon:
                    getVideoPosition.getLeftVideoPosition(getAdapterPosition());
                    break;
                case R.id.rightvoteicon:
                    getVideoPosition.getRightVideoPosition(getAdapterPosition());
                    break;
                case R.id.videoview:
                    getVideoPosition.getVideoStart(getAdapterPosition());
                    break;
            }
        }
    }
}
