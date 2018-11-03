package app.app1uppro.modules.following;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import app.app1uppro.R;
import app.app1uppro.apibase.modelclass.FollowedCategoryModel;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FollowingAdapter extends RecyclerView.Adapter<FollowingAdapter.CallOutsViewholder> {

    ArrayList<FollowedCategoryModel.DataBean> followedList;
    private FollowedPosition getfollowedPosition;

    FollowingAdapter(ArrayList<FollowedCategoryModel.DataBean> followedList,
                     FollowedPosition getfollowedPosition) {
        this.followedList = followedList;
        this.getfollowedPosition = getfollowedPosition;
    }

    @Override
    public CallOutsViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.followedcategorylist,
                parent, false);
        return new CallOutsViewholder(v);
    }

    @Override
    public void onBindViewHolder(CallOutsViewholder holder, int position) {
        holder.followedcategory.setText(followedList.get(holder.getAdapterPosition()).getVideoCatName());
    }//end bindview holder

    @Override
    public int getItemCount() {
        return followedList.size();
    }

    public class CallOutsViewholder extends RecyclerView.ViewHolder {

        @BindView(R.id.followedcategory)
        TextView followedcategory;
        @BindView(R.id.unfollowtext)
        TextView unfollowtext;

        public CallOutsViewholder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }//end constructor

        @OnClick(R.id.unfollowtext)
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.unfollowtext:
                    getfollowedPosition.getPosition(getAdapterPosition());
                    break;
            }
        }
    }

}
