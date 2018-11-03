package app.app1uppro.modules.homefragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.Group;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.codewaves.youtubethumbnailview.ThumbnailView;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;

import app.app1uppro.R;
import app.app1uppro.apibase.modelclass.ChallengeListModel;
import app.app1uppro.common.GlobalVariable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BottomHomeAdapter extends RecyclerView.Adapter<BottomHomeAdapter.ListViewholder> {

    ArrayList<ChallengeListModel.DataBean.ChallengeDataBean> challengeArrayList;
    private GetVideoPosition getVideoPosition;

    public BottomHomeAdapter(ArrayList<ChallengeListModel.DataBean.ChallengeDataBean>
                                     challengeArrayList, GetVideoPosition getVideoPosition) {
        this.challengeArrayList = challengeArrayList;
        this.getVideoPosition = getVideoPosition;
    }

    @Override
    public ListViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_list_cardxml, parent, false);
        return new ListViewholder(v);
    }

    @Override
    public void onBindViewHolder(ListViewholder holder, int position) {
        if (challengeArrayList.get(holder.getAdapterPosition()).getChallengerID().equals("0")) {
            holder.categorytext.setText(String.format("Category: %s", challengeArrayList.get(holder.getAdapterPosition()).getVideoCategories().get(0).getVideoCatName()));
            holder.orientationtext.setText(String.format("Orientation: %s", challengeArrayList.get(holder.getAdapterPosition()).getOrientation()));
            holder.rulestext.setText(String.format("Rules: %s", challengeArrayList.get(holder.getAdapterPosition()).getRules()));
            holder.datetext.setText(String.format("%s to %s", challengeArrayList.get(holder.getAdapterPosition()).getChallengeStartDate(),
                    challengeArrayList.get(holder.getAdapterPosition()).getChallengeEndDate()));
            holder.leftname.setText(challengeArrayList.get(holder.getAdapterPosition()).getCreatorusername());
            holder.leftvideoview.clearThumbnail();
            holder.leftvideoview.loadThumbnail(String.format("https://www.youtube.com/watch?v=%s",
                    challengeArrayList.get(holder.getAdapterPosition()).getVideoEmbedID()));
            holder.openview.setVisibility(View.VISIBLE);
            holder.challengeview.setVisibility(View.GONE);
            holder.clickaccepttext.setVisibility(View.VISIBLE);
            holder.acceptshowgroup.setVisibility(View.GONE);
        }//end if
        else {
            holder.challengeview.setVisibility(View.VISIBLE);
            holder.openview.setVisibility(View.GONE);
            holder.clickaccepttext.setVisibility(View.GONE);
            holder.acceptshowgroup.setVisibility(View.VISIBLE);
            holder.categorytext.setText(String.format("Category: %s", challengeArrayList.get(holder.getAdapterPosition()).getVideoCategories().get(0).getVideoCatName()));
            holder.orientationtext.setText(String.format("Orientation: %s", challengeArrayList.get(holder.getAdapterPosition()).getOrientation()));
            holder.rulestext.setText(String.format("Rules: %s", challengeArrayList.get(holder.getAdapterPosition()).getRules()));
            holder.datetext.setText(String.format("%s to %s", challengeArrayList.get(holder.getAdapterPosition()).getChallengeStartDate(),
                    challengeArrayList.get(holder.getAdapterPosition()).getChallengeEndDate()));
            holder.leftname.setText(challengeArrayList.get(holder.getAdapterPosition()).getCreatorusername());
            holder.leftscore.setText(challengeArrayList.get(holder.getAdapterPosition()).getCreatorvideoScore());
            holder.leftvideoview.clearThumbnail();
            holder.leftvideoview.loadThumbnail(String.format("https://www.youtube.com/watch?v=%s",
                    challengeArrayList.get(holder.getAdapterPosition()).getCreatorvideoEmbedID()));
           holder.rightvideoview.clearThumbnail();
            holder.rightvideoview.loadThumbnail(String.format("https://www.youtube.com/watch?v=%s",
                    challengeArrayList.get(holder.getAdapterPosition()).getVideoEmbedID()));
            holder.rightname.setText(challengeArrayList.get(holder.getAdapterPosition()).getChallengerusername());
            holder.rightscore.setText(challengeArrayList.get(holder.getAdapterPosition()).getVideoScore());
        }//end if
    }//end bindview holder

    @Override
    public int getItemCount() {
        return challengeArrayList.size();
    }

    public class ListViewholder extends RecyclerView.ViewHolder {
        @BindView(R.id.categorytextid)
        TextView categorytext;
        @BindView(R.id.orientationtextid)
        TextView orientationtext;
        @BindView(R.id.rulestextid)
        TextView rulestext;
        @BindView(R.id.datetextid)
        TextView datetext;
        @BindView(R.id.leftvideoview)
        ThumbnailView leftvideoview;
        @BindView(R.id.clickaccepttextid)
        TextView clickaccepttext;
        @BindView(R.id.rightvideoview)
        ThumbnailView rightvideoview;
        @BindView(R.id.leftupicon)
        ImageView leftupicon;
        @BindView(R.id.rightupicon)
        ImageView rightupicon;
        @BindView(R.id.leftscoreid)
        TextView leftscore;
        @BindView(R.id.rightscoreid)
        TextView rightscore;
        @BindView(R.id.leftname)
        TextView leftname;
        @BindView(R.id.rightname)
        TextView rightname;
        @BindView(R.id.acceptshowgroup)
        Group acceptshowgroup;
        @BindView(R.id.openview)
        View openview;
        @BindView(R.id.challengeview)
        View challengeview;
       // @BindView(R.id.leftyticon)
      //  ImageView leftyticon;
     //   @BindView(R.id.rightyticon)
      //  ImageView rightyticon;
        @BindView(R.id.textView20)
        TextView voteleft;
        @BindView(R.id.textView21)
        TextView voteright;

        @SuppressLint("SetJavaScriptEnabled")
        public ListViewholder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }//end constructor

        @OnClick({R.id.leftupicon, R.id.rightupicon, R.id.clickaccepttextid,
                R.id.leftvideoview,R.id.rightvideoview,R.id.textView20,R.id.textView21})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.leftupicon:
                    getVideoPosition.getLeftVideoPosition(getAdapterPosition());
                    break;
                case R.id.rightupicon:
                    getVideoPosition.getRightVideoPosition(getAdapterPosition());
                    break;
                case R.id.clickaccepttextid:
                    getVideoPosition.getAcceptPosition(getAdapterPosition());
                    break;
                case R.id.leftvideoview:
                    getVideoPosition.getLeftVideoStart(getAdapterPosition());
                    break;
                case R.id.rightvideoview:
                    getVideoPosition.getRightVideoStart(getAdapterPosition());
                    break;
                case R.id.textView20:
                    getVideoPosition.getLeftVideoPosition(getAdapterPosition());
                    break;
                case R.id.textView21:
                    getVideoPosition.getRightVideoPosition(getAdapterPosition());
                    break;
            }
        }

    }

}
