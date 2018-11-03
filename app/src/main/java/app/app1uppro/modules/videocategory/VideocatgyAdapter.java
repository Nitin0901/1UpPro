package app.app1uppro.modules.videocategory;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import app.app1uppro.R;
import app.app1uppro.apibase.modelclass.VideoCategoryModel;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideocatgyAdapter extends RecyclerView.Adapter<VideocatgyAdapter.ItemViewHolder> {

    GetVideocategoryPos getVideocategoryPos;
    ArrayList<VideoCategoryModel.DataBean> categorydataList;

    public VideocatgyAdapter(GetVideocategoryPos getVideocategoryPos, ArrayList<VideoCategoryModel.DataBean> categorydataList) {
        this.getVideocategoryPos = getVideocategoryPos;
        this.categorydataList = categorydataList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.videocatgrycard, parent, false);
        return new ItemViewHolder(v);
    }//end onCreateViewHolder

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.nametext.setText(categorydataList.get(position).getVideoCatName());
        if (categorydataList.get(position).getCatChild() > 0) {
            holder.nametext.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.rightarrow,0);
        }
    }//end onBindViewHolder

    @Override
    public int getItemCount() {
        return categorydataList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.nametext)
        TextView nametext;

        ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick({R.id.nametext})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.nametext:
                    getVideocategoryPos.getCategoryPosition(getAdapterPosition());
                    break;
            }
        }
    }

}
