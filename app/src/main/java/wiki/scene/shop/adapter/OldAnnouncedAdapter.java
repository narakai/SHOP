package wiki.scene.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wiki.scene.shop.R;

/**
 * Case By:往期揭晓
 * package:wiki.scene.shop.adapter
 * Author：scene on 2017/7/5 12:58
 */

public class OldAnnouncedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<String> list;

    public OldAnnouncedAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OldAnnouncedViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_old_announced_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        OldAnnouncedViewHolder viewHolder = (OldAnnouncedViewHolder) holder;
        viewHolder.winnerName.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    static class OldAnnouncedViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.times)
        TextView times;
        @BindView(R.id.announced_time)
        TextView announcedTime;
        @BindView(R.id.user_avater)
        ImageView userAvater;
        @BindView(R.id.image_level)
        TextView imageLevel;
        @BindView(R.id.winner_name)
        TextView winnerName;
        @BindView(R.id.join_times)
        TextView joinTimes;
        @BindView(R.id.luck_code)
        TextView luckCode;

        OldAnnouncedViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
